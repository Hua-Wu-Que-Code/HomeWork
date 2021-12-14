#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>

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

int main()
{
    int i;
    pid_t pid;
    char ch;

    /* 创建信号量集*/
    sem_id = semget(IPC_PRIVATE, 1, 0666 | IPC_CREAT);	
    if(sem_id == -1) {
        fprintf(stderr, "Failed to create semaphore set. \n");
        exit(EXIT_FAILURE);
    }
    if(!set_semvalue()) {	        /* 设置信号量的值*/
        fprintf(stderr, "Failed to initialize semaphore\n");
        exit(EXIT_FAILURE);
    }

    pid = fork();				    /* 创建子进程*/
    switch(pid)
    {
    case -1:
        perror("fork failed");
        exit(EXIT_FAILURE);
    case 0:						    /* 子进程*/
        ch = 'O';
        break;
    default:						/* 父进程*/
        ch = 'X';
        break;
    }

    srand((unsigned int)getpid());  /* 为随机数播种*/

    for(i=0; i < 10; i++){		    /* 使用信号量控制*/
        semaphore_p();			    /* P操作，获取信号量*/
        printf("%c", ch);		    /* 在父进程中输出X，在子进程中输出O */
        fflush(stdout);
        sleep(rand() % 4);

        printf("%c", ch);
        fflush(stdout);
        sleep(1);
        semaphore_v();			    /* V操作，释放信号量*/
    }

    if(pid > 0) {				    /* 父进程*/
        wait(NULL);				    /* 等待子进程退出*/
        del_sem_set();			    /* 删除信号量集*/
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
