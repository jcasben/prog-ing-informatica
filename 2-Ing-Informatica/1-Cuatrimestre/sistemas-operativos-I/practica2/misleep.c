#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <time.h>
#include <errno.h>

void main(int argc, char **argv) {
    if (!argv[1]) {
        fprintf(stderr, "Error de sintaxis. misleep <cantidad_segundos>\n");
        exit(-1);
    }
    int sec = atoi(argv[1]);
    for (int i=0; i<sec; i++) {
        //fprintf(stderr, "%d\n",i);
        sleep(1);
    }
    exit(0);
}