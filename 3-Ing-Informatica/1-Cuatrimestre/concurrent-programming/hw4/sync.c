/****************************************************************************
* sync.c
*
* (c) xxx
*****************************************************************************/

#ifdef SYNC_SEM
#include <semaphore.h>
#elif SYNC_SYSV
#include <sys/sem.h>
#include <sys/ipc.h>
#include <sys/types.h>
#elif SYNC_MUTEX
#include <pthread.h>
#endif

#include "sync.h"

/*****************************************************************************
* Local preprocessor defines
******************************************************************************/

#define DEBUG(x) x

/*****************************************************************************
* Local typedefs
******************************************************************************/

/*****************************************************************************
* Local variables
******************************************************************************/

/*****************************************************************************
* Static functions
******************************************************************************/