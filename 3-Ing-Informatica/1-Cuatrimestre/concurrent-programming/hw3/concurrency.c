/****************************************************************************
* concurrency.c
*
* (c) Tomas Plachetka
*****************************************************************************/
#define _POSIX_C_SOURCE 199309L /* clock_gettime */

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h> /* clock_gettime */

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
static int dummy1 = 0;
static int dummy2 = 0;
static long int c1 = 0;
static long int c2 = 0;

/* concurrently accessed variables */
static int n;
static int s = 0;

#ifdef MUTEX_DEKKER
static int in1 = 0;
static int in2 = 0;
static int turn = 1;
#endif

#ifdef MUTEX_PETERSON
static int in1 = 0;
static int in2 = 0;
static int last = 1;
#endif

/*****************************************************************************
* Static functions
******************************************************************************/
static void inc_dummy(int c, int *d)
{
  *d += c;
}

static void *add(void *arg)
{
  int i;
  int local_n;
  
  local_n = n;

  for (i = 0; i < local_n; i++) 
  {
#ifdef MUTEX_DEKKER
  in1 = 1;
  while (in2)
  {
    if (turn == 2)
    {
      in1 = 0;
      while (turn == 2) c1++;
      in1 = 1;
    }
  }
  
#elif MUTEX_PETERSON
  in1 = 1;
  last = 1;
  while (in2 && last == 1) c1++;
#endif
    s++;
    inc_dummy(s, &dummy2); /* This prevents optimisation of the loop. */
#ifdef MUTEX_DEKKER
  turn = 2;
  in1 = 0;
#elif MUTEX_PETERSON
  in1 = 0;
#endif
  }

  return NULL;
}


/*****************************************************************************
* Global functions
******************************************************************************/
int main(int argc, char *argv[])
{
  pthread_t tid;
  int retval;
  
  struct timespec tstart, tend;

  int i;
  int local_n;

  if (argc != 2)
  {
    printf("Usage: %s <nr_iterations>\n", argv[0]);
    exit(1);
  }
  
  n = atoi(argv[1]);
  local_n = n;
  
  clock_gettime(CLOCK_REALTIME, &tstart);

  retval = pthread_create(&tid, NULL, add, NULL);
  if (retval != 0)
  {
    printf("Error pthread_create\n");
    exit(1);
  }

  for (i = 0; i < local_n; i++)
  {
#ifdef MUTEX_DEKKER
  in2 = 1;
  while (in1)
  {
    if (turn == 1)
    {
      in2 = 0;
      while (turn == 1) c2++;
      in2 = 1;
    }
  }
#elif MUTEX_PETERSON
  in2 = 1;
  last = 2;
  while (in1 && last == 2) c2++;
#endif
    s--;
    inc_dummy(s, &dummy1); /* This prevents optimisation of the loop. */
#ifdef MUTEX_DEKKER
  turn = 1;
  in2 = 0;
#elif MUTEX_PETERSON
  in2 = 0;
#endif
  }

  pthread_join(tid, NULL);
  clock_gettime(CLOCK_REALTIME, &tend);
  printf("n=%d, s=%d, c1=%ld, c2=%ld, c1+c2=%ld, time %lf\n", n, s, c1, c2, c1 + c2, ((double) tend.tv_sec + 1.0e-9 * tend.tv_nsec) - ((double) tstart.tv_sec + 1.0e-9 * tstart.tv_nsec));
  return 0;
}
