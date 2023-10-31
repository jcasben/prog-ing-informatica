#include <stdlib.h>
#include "my_lib.h"

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
            //perror("ERROR: couldn't write the element in the file.");
            printf("%d", errno);
            close(fd);
            return -1;
        }

        num_elements++;
    }
    
    close(fd);
    
    return num_elements;
}