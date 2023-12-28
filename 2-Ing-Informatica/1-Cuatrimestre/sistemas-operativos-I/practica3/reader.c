/**
 * @authors Jesús Castillo Benito, Carlos Galvez Mena, Marc Link Cladera.
*/

#include <stdio.h>
#include <limits.h>
#include "my_lib.h"
#define NUM_THREADS 10

int main(int argc, char *argv[])
{

    if (argc != 2)
    {
        printf("USAGE: ./reader <stack_file>\n");
        return -1;
    }
    if (open(argv[1], O_RDWR) < 0)
    {
        printf("Couldn't open stack file %s\n", argv[1]);
        return -1;
    }

    struct my_stack *pila = my_stack_read(argv[1]);
    int suma = 0;
    int min = INT_MAX;
    int max = 0;
    // int *dato = my_stack_pop(pila);
    int *dato;

    printf("Stack length: %d\n", my_stack_len(pila));
    for (size_t i = 0; i < NUM_THREADS; i++)
    {
        dato = my_stack_pop(pila);
        printf("%d\n", *dato);
        int num = *dato;
        suma += num;
        if (num > max)
        {
            max = num;
        }
        if (num < min)
        {
            min = num;
        }
        free(dato);
    }

    printf("\nItems: %d, Sum: %d, Min: %d, Max: %d, Average: %d\n",
           NUM_THREADS, suma, min, max, (suma / NUM_THREADS));

    return 0;
}
