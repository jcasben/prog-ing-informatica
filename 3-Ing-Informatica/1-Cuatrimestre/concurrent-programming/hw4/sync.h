/****************************************************************************
* sync.h
*
* (c) Tomas Plachetka
*****************************************************************************/
#ifndef SYNC_H
#define SYNC_H

#ifdef SYNC_SEM
#include <semaphore.h>
#elif SYNC_MUTEX
#include <pthread.h>
#elif SYNC_SYSV
#include <sys/sem.h>
#include <sys/ipc.h>
#include <sys/types.h>
#endif

/*****************************************************************************
* Global preprocessor defines
******************************************************************************/

#ifdef SYNC_SEM
/* Use semaphore synchronisation */
#define MUTEX_T sem_t
#define COND_T sem_t

#define MUTEX_INIT(m) sem_init(m, 0, 1)
#define MUTEX_DESTROY(m) sem_destroy(m)
#define LOCK(m) sem_wait(m)
#define UNLOCK(m) sem_post(m)

#define COND_INIT(c) sem_init(c, 0, 0)
#define COND_DESTROY(c) sem_destroy(c)
#define WAIT(c, m) do { UNLOCK(m); sem_wait(c); LOCK(m); } while (0)
#define SIGNAL(c) sem_post(c)

#elif SYNC_SYSV
/* System V Semaphore synchronisation defines */
#define MUTEX_T int
#define COND_T int

#define MUTEX_INIT(m) (*(m) = semget(IPC_PRIVATE, 1, IPC_CREAT | 0666), semctl(*(m), 0, SETVAL, 1))
#define MUTEX_DESTROY(m) semctl(*(m), 0, IPC_RMID)
#define LOCK(m) do { struct sembuf sb = { 0, -1, 0 }; semop(*(m), &sb, 1); __sync_synchronize(); } while (0)
#define UNLOCK(m) do { __sync_synchronize(); struct sembuf sb = { 0, 1, 0 }; semop(*(m), &sb, 1); } while (0)

#define COND_INIT(c) (*(c) = semget(IPC_PRIVATE, 1, IPC_CREAT | 0666), semctl(*(c), 0, SETVAL, 0))
#define COND_DESTROY(c) semctl((*c), 0, IPC_RMID)
#define WAIT(c, m) do { UNLOCK(m); struct sembuf sb = { 0, -1, 0 }; semop(*(c), &sb, 1); LOCK(m); } while (0)
#define SIGNAL(c) do { struct sembuf sb = { 0, 1, 0 }; semop(*(c), &sb, 1); } while (0)

#elif SYNC_MUTEX
/* Use pthread synchronisation */
#define MUTEX_INIT(m) pthread_mutex_init(m, NULL)
#define MUTEX_DESTROY(m) pthread_mutex_destroy(m)
#define LOCK(m) pthread_mutex_lock(m)
#define UNLOCK(m) pthread_mutex_unlock(m)
#define COND_INIT(c) pthread_cond_init(c, NULL)
#define COND_DESTROY(c) pthread_cond_destroy(c)
#define WAIT(c, m) pthread_cond_wait(c, m)
#define SIGNAL(c) pthread_cond_signal(c)
#endif

/*****************************************************************************
* Global typedefs
******************************************************************************/
#ifdef SYNC_SEM
/* Use semaphore synchronisation */
/* Write your code here (and to sync.c if needed) */
#elif SYNC_MUTEX
/* Use mutex synchronisation */
typedef pthread_mutex_t MUTEX_T;
typedef pthread_cond_t COND_T;

#elif SYNC_SYSV

#endif

/*****************************************************************************
* Global variables
******************************************************************************/

/*****************************************************************************
* Global functions
******************************************************************************/

#endif
