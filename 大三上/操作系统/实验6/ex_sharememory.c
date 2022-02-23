#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/ipc.h>
#include <sys/shm.h>

static int set_semvalue(void);
static int semaphore_p(void);
static int semaphore_v(void);
static void del_sem_set(void);

/* 定义自己的semun联合体*/
union semun {
    int                 val;
    struct semid_ds     *buf;
    unsigned short      *array;
    struct seminfo      *__buf;
};

/* 定义全局变量sem_id保存信号量集的标识符*/
static int sem_id;
/* 定义全局变量shm_id保存共享内存的标识符*/
static int shm_id;

int main()
{
    int i;
    pid_t pid;
    char ch1, ch2;
    char* pData = NULL;

    /* 创建信号量集*/
    sem_id = semget(IPC_PRIVATE, 1, 0666 | IPC_CREAT);	
    if(sem_id == -1) {
        fprintf(stderr, "Failed to create semaphore set. \n");
        exit(EXIT_FAILURE);
    }
    if(!set_semvalue()) {	                /* 设置信号量的值*/
        fprintf(stderr, "Failed to initialize semaphore\n");
        exit(EXIT_FAILURE);
    }
    shm_id = shmget(IPC_PRIVATE, 4096, 0666 | IPC_CREAT);	
    if(shm_id == -1) {
        fprintf(stderr, "Failed to create sharememory. \n");
        del_sem_set();
        exit(EXIT_FAILURE);
    }

    pid = fork();				            /* 创建子进程*/
    if(pid == -1) {
        perror("fork failed");
        shmctl(shm_id, IPC_RMID, 0);        /* 删除共享内存*/
        del_sem_set();
        exit(EXIT_FAILURE);
    }
    else {
        srand((unsigned int)getpid());      /* 为随机数播种*/
        pData = (char*)shmat(shm_id, 0, 0); /* 绑定*/
        if (pid == 0) {                     /* 子进程*/
            do {
                semaphore_p();
                ch1 = *pData;               /* 读*/
                ch2 = *(pData + 1);
                if(ch2 == '@') {
                    *pData = tolower(ch1);  /* 写*/
                    *(pData + 1) = '#';
                }
                if(ch1 == 'Z') break;
                semaphore_v();
               
                sleep(1);
            }while(1);
        }
        else {                              /* 父进程*/
            for(i=0; i < 26; i++){
                semaphore_p();
                *pData = 'A' + rand() % 26; /* 写*/
                if(i == 25) *pData = 'Z';
                printf("%c", *pData);
                *(pData + 1) = '@';
                semaphore_v();
                sleep(1);

                do {
                    semaphore_p();
                    ch1 = *pData;           /* 读*/
                    ch2 = *(pData + 1);
                    if(ch2 == '#') {
                        printf("%c", ch1);
                        fflush(stdout);
                        semaphore_v();
                        break;
                    }
                    semaphore_v();
                }while(1);
            }
        }

        shmdt(pData);                       /* 分离*/
    }

    if(pid > 0) {				            /* 父进程*/
        wait(NULL);				            /* 等待子进程退出*/
        shmctl(shm_id, IPC_RMID, 0);        /* 删除共享内存*/
        del_sem_set();			            /* 删除信号量集*/
    }

    printf("\n%d - finished\n", getpid());
    exit(EXIT_SUCCESS);
}

/* 设置信号量的值*/
static int set_semvalue(void)
{
    union semun sem_union;

    sem_union.val = 1;
    if(semctl(sem_id, 0, SETVAL, sem_union) == -1)
        return 0;

    return 1;
}

/* P操作，获取信号量*/
static int semaphore_p(void)
{
    struct sembuf sem_b;

    sem_b.sem_num  = 0;
    sem_b.sem_op   = -1;
    sem_b.sem_flg  = SEM_UNDO;
    if(semop(sem_id, &sem_b, 1) == -1) {
        fprintf(stderr, "semaphore_p failed/n");
        return 0;
    }

    return 1;
}

/* V操作，释放信号量*/
static int semaphore_v(void)
{
    struct sembuf sem_b;

    sem_b.sem_num  = 0;
    sem_b.sem_op   = 1;
    sem_b.sem_flg  = SEM_UNDO;
    if(semop(sem_id, &sem_b, 1) == -1) {
        fprintf(stderr, "semaphore_v failed/n");
        return 0;
    }

    return 1;
}

/* 删除信号量集*/
static void del_sem_set(void)
{
    union semun sem_union;

    if(semctl(sem_id, 0, IPC_RMID, sem_union) == -1)
        fprintf(stderr, "Failed to delete semaphore/n");
}
