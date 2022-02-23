#include <stdio.h>
#include <unistd.h>
#include <pthread.h>
#include <stdlib.h>
pthread_mutex_t  mutex = PTHREAD_MUTEX_INITIALIZER;
int value = 1;
void* fun(void* arg) {
    //pthread_mutex_lock(&mutex);
    int temp = value;
    temp++;
    value = temp;
    printf("value is %d\n", value);
    //pthread_mutex_unlock(&mutex);
    return (void*)0;
}

int main(int argc, char const* atgv[]) {
    pthread_t thread1, thread2;
    int flag1, flag2;
    pthread_mutex_init(&mutex, NULL);
    flag1 = pthread_create(&thread1, NULL, fun, NULL);
    if (flag1) {
        printf("can't create thread1\n");
    }
    flag2 = pthread_create(&thread2, NULL, fun, NULL);
    if (flag2) {
        printf("can't create thread2\n");
    }
    sleep(2);
    pthread_mutex_destroy(&mutex);
    return 0;
}
