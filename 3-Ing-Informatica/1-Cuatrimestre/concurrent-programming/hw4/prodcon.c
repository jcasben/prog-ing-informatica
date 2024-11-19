/****************************************************************************
* prodcon.c
*
* (c) Tomas Plachetka
*****************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#include "prodcon.h"

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
static MUTEX_T mutex_nr_it; /* For passing initial arguments to threads. */

static MUTEX_T mutex;

static COND_T empty_cond; /* All producers sleep here. Oh well... */
static int waiting_empty = 0;
static COND_T full_cond; /* All producers sleep here. Oh well... */
static int waiting_full = 0;

/* concurrently accessed variables */
static int nr_iterations;
static int queue_size;
static int *queue;
static int in = 0;
static int out = 0;

/*****************************************************************************
* Static functions
******************************************************************************/
static void *producer(void *arg)
{
  int nr_it;
  int i; 
  
  LOCK(&mutex_nr_it);
  nr_it = nr_iterations;
  UNLOCK(&mutex_nr_it);

  for (i = 0; i < nr_it; i++) 
  {
    LOCK(&mutex);	/* Begin critical section */
    if (((out + 1) % (queue_size == 1 ? 2 : queue_size)) == in)
    { /* The queue is full */
      waiting_full++;
      do
        WAIT(&full_cond, &mutex);
      while (((out + 1) % (queue_size == 1 ? 2 : queue_size)) == in); /* Why this loop?) */
      waiting_full--;
    }
    /* When we get here, the queue is not full. Write to queue[out] (omitted here), increase out */
    out = (out + 1) % (queue_size == 1 ? 2 : queue_size);
    if (waiting_empty > 0) /* Why not just cond_signal? */
    {
      SIGNAL(&empty_cond); 
    }
    UNLOCK(&mutex); 	/* End critical section */
  }

  return NULL;
}

static void *consumer(void *arg)
{
  int nr_it;
  int i; 

  LOCK(&mutex_nr_it);
  nr_it = nr_iterations;
  UNLOCK(&mutex_nr_it);

  for (i = 0; i < nr_it; i++)
  {
    LOCK(&mutex); /* Begin critical section */
    if (in == out)
    { /* The queue is empty */
      waiting_empty++;
      do
        WAIT(&empty_cond, &mutex);
      while (in == out); /* Why this loop? */
      waiting_empty--;
    }
    /* When we get here, the queue is not empty. Read from queue[in] (omitted here), increase in */
    in = (in + 1) % (queue_size == 1 ? 2 : queue_size);
    if (waiting_full > 0) /* Why not just cond_signal? */
    {
      SIGNAL(&full_cond);
    }
    UNLOCK(&mutex); /* End critical section */
  }

  return NULL;
}

/*****************************************************************************
* Global functions
******************************************************************************/
int main(int argc, char *argv[])
{
  int nr_producers;
  pthread_t *thread_ids;
  void *thread_result;
  int retval;
  int i;
    
  if (argc != 4)
  {
    printf("Usage: %s <nr_producers> <queue_size> <nr_iterations>\n(Note: <nr_consumers> is automatically set to <nr_producers>)\n", argv[0]);
    exit(1);
  }
  
  nr_producers = atoi(argv[1]);
  queue_size = atoi(argv[2]);  
  nr_iterations = atoi(argv[3]);
  
  queue = malloc(sizeof(int) * queue_size);
  thread_ids = malloc(sizeof(pthread_t) * 2 * nr_producers);
  if ((queue == NULL) || (thread_ids == NULL))
  {
    printf("Error malloc\n");
    exit(1);
  }

  MUTEX_INIT(&mutex_nr_it);
  MUTEX_INIT(&mutex);
  COND_INIT(&empty_cond);  
  COND_INIT(&full_cond);

  for (i = 0; i < nr_producers; i++)
  {
    retval = pthread_create(&thread_ids[i], NULL, producer, NULL);
    if (retval != 0)
    {
      printf("Error pthread_create\n");
      exit(1);
    }
  }

  for (i = 0; i < nr_producers; i++)
  {
    retval = pthread_create(&thread_ids[nr_producers + i], NULL, consumer, NULL);
    if (retval != 0)
    {
      printf("Error pthread_create\n");
      exit(1);
    }

  }

  for (i = 0; i < 2 * nr_producers; i++)
  {
    pthread_join(thread_ids[i], &thread_result);
  }

  MUTEX_DESTROY(&mutex_nr_it);
  MUTEX_DESTROY(&mutex);
  COND_DESTROY(&empty_cond);
  COND_DESTROY(&full_cond);
  free(thread_ids);
  free(queue);
  return 0; /* required */
}
