CC=	gcc
#For debugging, use -O0 and -g in CFLAGS
CFLAGS=	-c -ansi -Wall -O3 $(INCL)
LFLAGS=
INCL=	-I. -I$(HOME)/include
RM=	"rm" -f
LIBS=	-lpthread -lrt

OBJS =\
	nomutex.o \
	dekker.o \
	peterson.o

default:	nomutex dekker peterson

nomutex:       $(OBJS)
	$(CC) $(LFLAGS) -o $@ nomutex.o $(LIBS)

nomutex.o:     concurrency.c
	$(CC) $(CFLAGS) -DMUTEX_NONE -o $@ concurrency.c

dekker:       $(OBJS)
	$(CC) $(LFLAGS) -o $@ dekker.o $(LIBS)

dekker.o:     concurrency.c
	$(CC) $(CFLAGS) -DMUTEX_DEKKER -o $@ concurrency.c

peterson:       $(OBJS)
	$(CC) $(LFLAGS) -o $@ peterson.o $(LIBS)

peterson.o:     concurrency.c
	$(CC) $(CFLAGS) -DMUTEX_PETERSON -o $@ concurrency.c

clean:
	$(RM) nomutex dekker peterson $(OBJS)

zip:
	zip -9 concurrency.zip *.c *.h Makefile
