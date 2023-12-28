/**
 * @authors Jesús Castillo Benito, Carlos Galvez Mena, Marc Link Cladera.
*/

#include <stdio.h>
#include <fcntl.h>
#include <pthread.h>
#include <semaphore.h>
#include "my_lib.h"

// Número de threads que se van a crear.
#define NUM_THREADS 10
// Número de iteraciones que va a realizar cada thread.
#define N 1000000

// Semáforo que controlará que no modifiquen mas de diez threads la pila a la vez.
sem_t sem;
// Mutex para asegurar la exclusión mutua.
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
// Pila que se va a utilizar.
struct my_stack *pila;

/**
 * Función que se ejecuta en cada thread para sumar 1 al elemento situado más arriba de la pila en ese momento.
 * @param ptr Puntero a void que no utilizamos.
 * @return NULL
 */
void *worker(void *ptr)
{
    for (size_t i = 0; i < N; i++)
    {
        sem_wait(&sem);
        pthread_mutex_lock(&mutex);
        int *data = (int *)my_stack_pop(pila);

        pthread_mutex_unlock(&mutex);

        if (data != NULL)
        {
            int *valor_incrementado = malloc(sizeof(int));
            *valor_incrementado = *data + 1;

            pthread_mutex_lock(&mutex);
            my_stack_push(pila, valor_incrementado);
            pthread_mutex_unlock(&mutex);

            sem_post(&sem);
        }
        else
        {
            pthread_exit(NULL);
        }
    }
    pthread_exit(NULL);
}

/**
 * Función principal del programa.
 * @param argc Número de argumentos pasados por línea de comandos.
 * @param argv Argumentos pasados por línea de comandos, en argv[1] deberá estar el nombre de la pila.
 * @return 0 si todo ha ido bien, -1 si ha habido algún error.
 */
int main(int argc, char *argv[])
{
    printf("Threads: %d, Iterations: %d\n", NUM_THREADS, N);

    // Nos aseguramos que haya exactamente dos paramtros: 1) Nombre del programa, 2) Nombre de la pila.
    if (argc != 2)
    {
        printf("USAGE: ./stack_counters <stack_file>\n");
        return -1;
    }
    // Intentamos abrir el fichero que contiene la pila.
    int fd = open(argv[1], O_RDWR);
    // Reservamos memoria para un entero.
    int *data = malloc(sizeof(int));

    if (data == NULL)
    {
        printf("Error: No se pudo asignar memoria para el elemento %d.\n", 0);
        return -1;
    }
    *data = 0;

    // Paso 1
    // Si el fichero no existe (fd sera menor estricto que 0), entonces creamos una pile de tamaño 10 inicializada a 0.
    if (fd < 0)
    {
        printf("Initial syack length: 0\n");
        printf("Initial stack content:\n");
        // Creamos la pila.
        pila = my_stack_init(sizeof(int));
        // Inizializa la pila a 0.
        for (int i = 0; i < 10; i++)
        {
            if (my_stack_push(pila, data) < 0)
            {
                // En caso de que no se haya podido hacer el push, liberamos la memoria reservada para el entero y la pila.
                printf("Error: No se ha podido introducir el elemento %d en la pila.\n", *data);
                my_stack_purge(pila);
                free(data);
                return -1;
            }
        }
    }
    // Si el fichero existe, entonces leemos la pila del fichero.
    else
    {

        pila = my_stack_read(argv[1]);
        int size = my_stack_len(pila);

        printf("Initial stack length: %d\n", size);
        printf("Initial stack content: \n");
        // Leemos el contenido inicial de la pila para imprimirlo por pantalla.
        if (my_stack_write(pila, "aux") < 0)
        {
            printf("Error: No se ha podido escribir la pila en el fichero %s.\n", argv[1]);
            my_stack_purge(pila);
            free(data);
            return -1;
        }
        struct my_stack *pilaaux = my_stack_read("aux");
        int *dato;
        for (size_t i = 0; i < size; i++)
        {
            dato = my_stack_pop(pilaaux);
            printf("%d\n", *dato);
        }

        // Si la pila tiene menos de 10 elementos, añadimos 0 hasta que tenga 10 elementos.
        if (size < 10)
        {
            // Añadimos 0 a la pila para llegar a tener una pila de tamaño 10.
            for (size_t i = size; i < 10; i++)
            {
                if (my_stack_push(pila, data) < 0)
                {
                    // En caso de que no se haya podido hacer el push, liberamos la memoria reservada para el entero y la pila.
                    printf("Error: No se ha podido introducir el elemento %d en la pila.\n", *data);
                    my_stack_purge(pila);
                    free(data);
                    return -1;
                }
            }
        }
    }

    printf("\nstack->size: %d\n", pila->size);
    printf("Stack content for traetment: \n");
    // Enseñamos por pantalla cual es el contenido de la pila antes de que los threads empiecen a modificarla.
    if (my_stack_write(pila, "aux") < 0)
    {
        printf("Error: No se ha podido escribir la pila en el fichero %s.\n", argv[1]);
        my_stack_purge(pila);
        free(data);
        return -1;
    }
    struct my_stack *pilaaux = my_stack_read("aux");
    int *dato;
    for (size_t i = 0; i < my_stack_len(pila); i++)
    {
        dato = my_stack_pop(pilaaux);
        printf("%d\n", *dato);
    }

    printf("\nStack len: %d\n\n", my_stack_len(pila));
    // Paso 2
    // Guardamos en un array de threads los threads que vamos a crear.
    pthread_t threads[NUM_THREADS];
    // Este int lo utilizamos para comprobar que no haya habido ningún error al crear los threads.
    int value;
    // Inicializamos el semáforo a NUM_THREADS para que no haya más NUM_THREADS threads modificando la pila a la vez.
    sem_init(&sem, 0, NUM_THREADS);
    // Creamos los threads.
    for (size_t i = 0; i < NUM_THREADS; i++)
    {
        if ((value = pthread_create(&threads[i], NULL, worker, NULL)))
        {
            // En caso de que no se haya podido crear el thread, liberamos la memoria reservada para el entero y la pila.
            my_stack_purge(pila);
            free(data);
            exit(value);
        }
        printf("%ld) thread %ld created\n", i, threads[i]);
    }

    // Esperamos a que todos los threads terminen.
    for (size_t i = 0; i < NUM_THREADS; i++)
    {
        pthread_join(threads[i], NULL);
    }
    // Escibimos la pila en el fichero con el nombre que nos han pasado por línea de comandos(argv[1]) y guardamos el número de elementos escritos.
    int elementos_escritos = my_stack_write(pila, argv[1]);
    if (elementos_escritos < 0)
    {
        // En caso de que no se haya podido escribir la pila en el fichero, liberamos la memoria reservada para el entero y la pila.
        printf("Error: No se ha podido escribir la pila en el fichero %s.\n", argv[1]);
        my_stack_purge(pila);
        free(data);
        return -1;
    }
    printf("\nStack content after thread iterations: \n");
    // Enseñamos por pantalla cual es el contenido de la pila después de que los threads hayan terminado de modificarla.
    struct my_stack *pilaaux1 = my_stack_read(argv[1]);
    int *datoaux;
    for (size_t i = 0; i < my_stack_len(pila); i++)
    {
        datoaux = my_stack_pop(pilaaux1);
        printf("%d\n", *datoaux);
    }
    // my_stack_len(pila) == my_stack_len(pilaaux1)
    printf("Stack len: %d\n\n", my_stack_len(pila));
    printf("Written elements from stack to file: %d\n", elementos_escritos);

    // Libreamos la memoria reservada para el entero, la pila y el semáforo.
    sem_destroy(&sem);
    pthread_mutex_destroy(&mutex);
    free(data);
    int released_bytes = my_stack_purge(pila);
    printf("Released bytes: %d\n", released_bytes);
    printf("Bye from main\n");
    pthread_exit(NULL);
}