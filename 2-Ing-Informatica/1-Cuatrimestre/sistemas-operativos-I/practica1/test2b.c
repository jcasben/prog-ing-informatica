/*
 * This code is a test of the functions implemented in my_lib.c
 * It's required to declare the functions and types in my_lib.h
 */

#include <stdio.h>
#include "my_lib.h"

//#define NODES 100
#define NODES 10

#define ROJO "\x1b[31m"
#define VERDE "\x1b[32m"
#define NEGRITA "\x1b[1m"
#define RESET "\033[0m"

struct my_data {
    int val;
    char name[60];
};

int main() {

    struct my_stack *s1, *s2, *fs1;
    struct my_data *data, *data1, *data2;
    int len1, len2;
    int size1, size2;
    int occupied_bytes=0;

    s1 = my_stack_init(sizeof(struct my_data));
    if (!s1) {
        fprintf(stderr, ROJO "Error in my_stack_init()\n" RESET);
        exit(EXIT_FAILURE);
    }
    printf("\ns1 initialized, size of data: %lu\n", sizeof(struct my_data));

    for (int i = 0; i < NODES; i++) {
        data = malloc(sizeof(struct my_data)); 
        if (!data) {
            fprintf(stderr, ROJO "Out of memory\n" RESET);
            exit(EXIT_FAILURE);
        }
        data->val = i;
        sprintf(data->name, "Value %d", i);
        if (my_stack_push(s1, data)<0) {
            fprintf(stderr, ROJO "Error in my_stack_push()\n" RESET);
            exit(EXIT_FAILURE);
        }
        printf("New node in s1: (%d, %s)\n", data->val, data->name);
    }

    len1 = my_stack_len(s1);
    if (NODES != len1) {
        fprintf(stderr, ROJO "Error in my_stack_len()\n" RESET);
        exit(EXIT_FAILURE);
    }
    printf("s1 length: %d\n", len1);

    // Create a second larger stack
    s2 = my_stack_init(sizeof(struct my_data));
    if (!s2) {
        fprintf(stderr, ROJO "Error in my_stack_init()\n" RESET);
        exit(EXIT_FAILURE);
    }
    printf("\ns2 initialized, size of data: %lu\n", sizeof(struct my_data));

    for (int i = 0; i < NODES * 3; i++) {
        data = malloc(sizeof(struct my_data)); 
        if (!data) {
            fprintf(stderr, ROJO "Out of memory\n" RESET);
            exit(EXIT_FAILURE);
        }
        data->val = i;
        sprintf(data->name, "Value %d", i);
        if (my_stack_push(s2, data)<0) {
            fprintf(stderr, ROJO "Error in my_stack_push (%d)\n" RESET, i);
            exit(EXIT_FAILURE);
        }
        printf("New node in s2: (%d, %s)\n", data->val, data->name);
    }
    len2 = my_stack_len(s2);
    printf("s2 length: %d\n\n", len2);


    // Write the larger stack top
    puts("Writting the larger stack top (s2) in the file \"/tmp/my_stack.data\".");
    if (my_stack_write(s2, "/tmp/my_stack.data") <= 0) {
        fprintf(stderr, ROJO "Error in my_stack_write (s2)\n" RESET);
        exit(EXIT_FAILURE);
    }

    puts("Writting the smaller stack (s1), it must truncate the file.");
    // Now the smaller stack, it must truncate the file
    if (my_stack_write(s1, "/tmp/my_stack.data") != len1) {
        fprintf(stderr, ROJO "Error in my_stack_write (s1)\n" RESET);
        exit(EXIT_FAILURE);
    }
    puts("\nReading the file...");
    fs1 = my_stack_read("/tmp/my_stack.data");
    if (!fs1) {
        fprintf(stderr, ROJO "Error in my_stack_read (fs1)\n" RESET);
        exit(EXIT_FAILURE);
    }

    if (my_stack_len(s1) != my_stack_len(fs1)) {
        puts("Stacks s1 (initial stack 1) and fs1 (retrieved from file) don't have the same length");
        exit(EXIT_FAILURE);
    }
    puts("s1 and the one retrieved from file (fs1) have the same length.");
    puts("Comparing the data with pop...");

    // Test we can free the data and compare stacks s1 and fs1
    while ((data1 = my_stack_pop(s1))) {
        data2 = my_stack_pop(fs1);
        printf("Node of s1: (%d, %s)\t", data1->val, data1->name);
        printf("Node of fs1: (%d, %s)\n", data2->val, data2->name);
        if (!data2 || data1->val != data2->val || my_strcmp(data1->name, data2->name)) {
            fprintf(stderr, ROJO "Data in s1 and fs1 are not the same.\n (data1->val: %d <> data2->val: %d) o (data1->name: %s <> data2->name: "
                   "%s)\n" RESET,
                   data1->val, data2->val, data1->name, data2->name);
            exit(EXIT_FAILURE);
        }
        size1 = sizeof(*data1);
        size2 = sizeof(*data2);
        free(data1);
        free(data2);
    }

    printf("size of data from s1: %d\t", size1);
    printf("size of data from fs1: %d\n", size2);
    len1 = my_stack_len(s1);
    occupied_bytes = len1*(sizeof(struct my_stack_node)+size1) + sizeof (struct my_stack);
    printf ("\nWhole stack space s1 (after pops): %d bytes\n", occupied_bytes);
    if (occupied_bytes != my_stack_purge(s1)) {
        fprintf(stderr, ROJO "Error purging s1\n" RESET);
        exit(EXIT_FAILURE);
    }  
    printf("Purging s1: %d bytes released\n", occupied_bytes);

    len1 = my_stack_len(fs1);
    occupied_bytes = len1*(sizeof(struct my_stack_node)+size2) + sizeof (struct my_stack);
    printf ("\nWhole stack space fs1 (after pops): %d bytes\n", occupied_bytes);
    if (occupied_bytes != my_stack_purge(fs1)) {
        fprintf(stderr, ROJO "Error purging fs1\n" RESET);
        exit(EXIT_FAILURE);
    }
    printf("Purging fs1: %d bytes released\n", occupied_bytes);

    len2 = my_stack_len(s2);
    occupied_bytes = len2*(sizeof(struct my_stack_node)+size1) + sizeof (struct my_stack);
    printf ("\nWhole stack space s2: %d bytes\n", occupied_bytes);
    if (occupied_bytes != my_stack_purge(s2)) {
        fprintf(stderr, ROJO "Error purging s2\n" RESET);
        exit(EXIT_FAILURE);
    }
    printf("Purging s2: %d bytes released\n", occupied_bytes);

    printf(VERDE "\nAll tests passed :-)\n");
    return EXIT_SUCCESS; // To avoid warning in -Wall
}

/// @brief Calculates the length of a string.
/// @param str pointer to the string.
/// @return the length of the string.
size_t my_strlen(const char *str)
{
    size_t len;
    for (len = 0; str[len]; len++);

    return len;
}

/// @brief Compares 2 strings and determines which is larger.
/// @param str1 pointer to the first string element.
/// @param str2 pointer to the second string element.
/// @return 0 if they are equals; 1 if the second string is larger; 
/// -1 if the first string is larger.
int my_strcmp(const char *str1, const char *str2)
{
    int i;
    for (i = 0; i < (str1[i] | str2[i]); i++)
    {
        // Si es igual vamos a la siguiente iteración
        if (str1[i] == str2[i])
        {
            continue;
        }
        else
        {
            return str1[i] - str2[i];
        }
    }

    // Si llegamos aquí es porqué en la iteración hemos llegado
    // al final de alguna de las cadenas o al final de las dos

    // Miramos si las dos han llegado al final, si es así las dos
    // cadenas son iguales
    if ((str1[i] == '\0') && (str2[i] == '\0'))
    {
        return 0;
    }
    // Miramos si hemos llegado solo al final de stri1, si es
    // así significa que str2 es mayor
    else if (str1[i] == '\0')
    {
        return -1;
    }
    // En este punto ya sabremos que hemos llegado al final de
    // str2, por este motivo str1 será mayor
    else
    {
        return 1;
    }
}

/// @brief Copies the content of source into destination, overwritting it.
/// @param dest pointer to the destination string.
/// @param src pointer to the source string.
/// @return the pointer to the resulting string.
char *my_strcpy(char *dest, const char *src)
{
    size_t i = 0;
    // Iteramos hasta que se encuentra el \0 en src
    while (src[i])
    {
        dest[i] = src[i];
        i++;
    }
    // Una vez hemos acabado de recorrer src, añadimos
    // un \0 detrás del último valor
    dest[strlen(src)] = '\0';

    return dest;
}

/// @brief Copies n positions of the content of source into destination,
/// overwritting it.
/// @param dest pointer to the destination string.
/// @param src pointer to the source string.
/// @param n number of elements to copy.
/// @return the pointer to the resulting string.
char* my_strncpy(char* dest, const char* src, size_t n)
{
    // Copiamos todos los carácteres de src en dest
    for (size_t i = 0; i < n || !src[i]; i++)
    {
        dest[i] = src[i];
    }

    // Si la longitud de str es menor que n,
    // rellenamos el resto de dest con \0's
    if(strlen(src) < n)
    {
        for (size_t i = n; i < strlen(dest); i++)
        {
            dest[i] = '\0';
        }
    }
    
    return dest;
}

/// @brief Concatenates the content of source into destination.
/// @param dest pointer to the destination string.
/// @param src pointer to the destination string. 
/// @return 
char *my_strcat(char *dest, const char *src)
{
    //Guardamos la longitud de dest
    size_t ind_dest = strlen(dest);
    //Iteramos la longitud de src y pasamos cada uno de sus caracteres a la siguiente posicion de dest
    //empezando desde la longitud de dest
    for (size_t i = 0; i < strlen(src); i++)
    {
        dest[ind_dest++] = src[i];
    }
    //Colocamos un \0 en la posicion de despues de la longitud de dest + longitud de src
    dest[ind_dest + strlen(src) + 1] = '\0';
    return dest;
}

char* my_strchr(const char* str, int c)
{
    // Recorremos la cadena hasta lleagr al final
    for (size_t i = 0; i != NULL; i++)
    {
        if(str[i] == c)
        {
            // Si encontramos el carácter,
            // devolvemos su dirección
            return &str[i];
        }
    }
    // Si no lo encontramos, devolvemos NULL
    return NULL;
}

struct my_stack *my_stack_init(int size)
{
    struct my_stack *stack = malloc(sizeof(struct my_stack));
    stack->size = size;
    stack->top = NULL;

    return stack;
}

int my_stack_push(struct my_stack *stack, void *data)
{
    if(!stack || stack->size <=0) return -1;

    struct my_stack_node *new_node = malloc(sizeof(struct my_stack_node));
    new_node->data = data;
    new_node->next = stack->top;
    
    stack->top = new_node;

    return 0;
}

void *my_stack_pop(struct my_stack *stack)
{
    struct my_stack_node *top1 = stack->top;
    if (top1 == NULL)
    {
        return NULL;
    }

    stack->top = top1->next;
    void *data = top1->data;
    free(top1);
    
    return data;
}

int my_stack_len(struct my_stack *stack)
{
    int contador = 0;
    struct my_stack_node *tmp = stack->top;
    while (tmp)
    {
        contador++;
        tmp = tmp->next;
    }
    free(tmp);
    
    return contador;
}

int my_stack_purge(struct my_stack *stack)
{
    int released_memory = 0;
    
    while(stack->top)
    {
        struct my_stack_node *node = stack->top;
        stack->top = node->next;
        released_memory += sizeof(*node->data);  // Libera primero los datos del nodo.
        free(node->data);
        released_memory += sizeof(node);
        free(node);  // Luego libera el nodo.
    }
    
    free(stack);  // Finalmente, libera la estructura de la pila.
    released_memory += sizeof(*stack);

    return released_memory;
}

/// @brief 
/// @param filename
/// @return
struct my_stack *my_stack_read(char *filename)
{
    int fd = open(filename, O_RDONLY);
    if (fd == -1) return NULL;
    
    // Get the size of the data that is stored in the file.
    int size = 0;
    read(fd, &size, sizeof(int));
    // Initialize a new stack with the obtained size.
    struct my_stack *stack = my_stack_init(size);
    void *data = malloc(size);
    while (read(fd, data, size))
    {
        my_stack_push(stack, data);
    }
    close(fd);

    return stack;
}

/// @brief Writes the size of the data that the stack contains and
/// the content of the stack.
/// @param stack pointer to a stack.
/// @param filename route of the file where the info will be saved.
/// @return the quantity of elements that were saved into the file.
int my_stack_write(struct my_stack *stack, char *filename)
{
    int num_elements = 0;
    struct my_stack *aux_stack = my_stack_init(stack->size);
    
    struct my_stack_node* tmp_next = stack->top;
    while (tmp_next)
    {
        my_stack_push(aux_stack, tmp_next->data);
        tmp_next = tmp_next->next;
    }

    printf("fd\n\n");

    int fd = open(filename, O_WRONLY | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR);
    if (fd == -1)
    {
        perror("ERROR: couldn't open the file.");
        return -1;
    }

    tmp_next = aux_stack->top;
    int stack_data_size = stack->size;
    printf("%d\n", stack_data_size);
    if(write(fd, &stack_data_size, sizeof(int)) == -1)
    {
        perror("ERROR: couldn't write the stack's data size.");
        close(fd);
        return -1;
    }
    
    while (aux_stack->top != NULL)
    {
        void *data = my_stack_pop(aux_stack);
        printf("%p", data);
        int data_size = stack->size;
        if (write(fd, data, data_size))
        {
            perror("ERROR: couldn't write the element in the file.");
            printf("%d", errno);
            close(fd);
            return -1;
        }

        num_elements++;
    }
    
    close(fd);
    
    return num_elements;
}
