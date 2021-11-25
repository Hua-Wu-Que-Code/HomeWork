//
// Created by HuaWuQue on 2021/11/25.
//
#include<stdio.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<semaphore.h>
#include <pthread.h>
#include <unistd.h>

int total=10;//总量
int last=7;//剩余量
sem_t sem_p;
sem_t sem_c;
pthread_mutex_t  mutex=PTHREAD_MUTEX_INITIALIZER;
void *produce(void *arg)
{
    while (1) {
        sem_wait(&sem_p);
        pthread_mutex_lock(&mutex);
        last++;
        printf("produce last is %d\n",last);
        sleep(1);
    }

}
void *cost(void *arg)
{
    while (1) {
        sem_wait(&sem_c);
        pthread_mutex_lock(&mutex);
        last++;
        printf("const last is %d\n",last);
        pthread_mutex_unlock(&mutex);
        sem_post(&sem_p);
    }
}
int main(int argc,char const *atgv[])
{
    pthread_t pth_p,pth_c;
    pthread_mutex_init(&mutex,NULL);
    sem_init(&sem_p,0,total-last);
    sem_init(&sem_c,0,last);
    printf("init_last=%d\n",last);
    pthread_create(&pth_p,NULL,produce,NULL);
    pthread_create(&pth_c,NULL,cost,NULL);
    sem_destory(&sem_p);
    sem_destory(&sem_c);

    while(1);
    return 0;
}

