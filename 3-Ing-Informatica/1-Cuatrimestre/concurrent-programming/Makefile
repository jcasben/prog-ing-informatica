CC=	gcc
#For debugging
CFLAGS=	-c -ansi -Wall -O0 -g $(INCL)
#For optimised code
#CFLAGS= -c -ansi -Wall -O3 $(INCL)
#For debugging
LFLAGS= -g
#LFLAGS=
INCL=	-I. -I$(HOME)/include
RM=	"rm" -f
LIBS=

OBJS =\
	main.o

default:	main

main:       $(OBJS)
	$(CC) $(LFLAGS) -o $@ $(OBJS) $(LIBS)

main.o:     main.c main.h
	$(CC) $(CFLAGS) -o $@ main.c

clean:
	$(RM) main $(OBJS)
