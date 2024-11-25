CC=	gcc
#For debugging, use -O0 and -g in CFLAGS
#Mutex synchronisation
#CFLAGS=	-c -ansi -Wall -O3 -DSYNC_MUTEX $(INCL)
#Semaphore synchronisation
#CFLAGS=	-c -ansi -Wall -O3 -DSYNC_SEM $(INCL)
#System V Semaphore synchronisation
CFLAGS=	-c -ansi -Wall -O3 -DSYNC_SYSV $(INCL)

LFLAGS= -g -lpthread
INCL=	-I. -I$(HOME)/include
RM=	"rm" -f
LIBS=	-lpthread

SOURCES= prodcon.c sync.c
INCLUDES= prodcon.h sync.h
PROGRAMS= prodcon
OBJS =\
	prodcon.o \
	sync.o

all: $(OBJS) $(PROGRAMS)

default:	prodcon

prodcon:       $(OBJS)
	$(CC) $(LFLAGS) -o $@ $(OBJS) $(LIBS)

prodcon.o:     prodcon.c prodcon.h sync.h
	$(CC) $(CFLAGS) -o $@ prodcon.c

sync.o:	sync.c sync.h
	$(CC) $(CFLAGS) -o $@ sync.c

clean:
	$(RM) prodcon $(OBJS)

zip:
	zip -9 prodcon.zip *.c *.h Makefile
