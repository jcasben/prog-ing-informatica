#include <stdlib.h>
#include "my_lib.h"

/*
    AUTORES:
     - Marc Link Cladera
     - Carlos Galvez Mena
     - Jesus Castillo Benito
*/

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
        // If equals, go to the next iteration.
        if (str1[i] == str2[i])
        {
            continue;
        }
        else
        {
            return str1[i] - str2[i];
        }
    }
    /*
      If we arrive here is because we arrived to the final of one or both
      strings.
    
      Check if both arrived to the end, if that's true, they are equals.
    */
    if ((str1[i] == '\0') && (str2[i] == '\0'))
    {
        return 0;
    }
    /*
      Check if we arrived only to the end of stri1. If that's true, 
      str2 is greater.
    */
    else if (str1[i] == '\0')
    {
        return -1;
    }
    /*
      At this point we know that we arrived to the end of str2,
      for that reason str1 is greater.
    */
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
    //Iterate until we find \0 in src.
    while (src[i])
    {
        dest[i] = src[i];
        i++;
    }
    /*
      When we finish iterating over src, we add \0 after
      the las value.
    */
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
    // Copy all the chars of src on dest.
    for (size_t i = 0; i < n || !src[i]; i++)
    {
        dest[i] = src[i];
    }

    //If the length of str is less than n, fill the rest with \0.
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
/// @return pointer to the resulting string.
char *my_strcat(char *dest, const char *src)
{
    // Save the length of dest.
    size_t ind_dest = strlen(dest);
    
    //Iterate over the length of src and put each of its chars into the final of dest.
    for (size_t i = 0; i < strlen(src); i++)
    {
        dest[ind_dest++] = src[i];
    }
    //Put \0 in the last position of the resulting string (dest + src).
    dest[ind_dest + strlen(src) + 1] = '\0';

    return dest;
}

/// @brief Returns the pointer to the searched char in a string.
/// @param str pointer to the string.
/// @param c chat to be searched.
/// @return pointer to the char.
char* my_strchr(const char* str, int c)
{
    // Iterate over the string until the final.
    for (size_t i = 0; i != NULL; i++)
    {
        if(str[i] == c)
        {
            // If we find the char, return its pointer.
            return &str[i];
        }
    }
    // If we don't find it, return NULL
    return NULL;
}

/// @brief Initializes a new stack.
/// @param size size in bytes of the data that will store the stack.
/// @return the pointer to the new stack.
struct my_stack *my_stack_init(int size)
{
    struct my_stack *stack = malloc(sizeof(struct my_stack));
    // Check if malloc gived us a space in memory.
    if (stack == NULL)
    {
        perror("ERROR: ");
        return NULL;
    }
    stack->size = size;
    stack->top = NULL;

    return stack;
}

/// @brief Adds a new element to the top of the stack.
/// @param stack pointer to the stack where we want to add the element.
/// @param data pointer to the data that we want to add.
int my_stack_push(struct my_stack *stack, void *data)
{
    if(!stack || stack->size <=0) return -1;

    struct my_stack_node *new_node = malloc(sizeof(struct my_stack_node));
    // Check if malloc gives a space in memory.
    if (new_node == NULL)
    {
        perror("ERROR: ");
        return NULL;
    }
    new_node->data = data;
    new_node->next = stack->top;
    
    stack->top = new_node;

    return 0;
}

/// @brief Removes the top element of the stack.
/// @param stack pointer to the stack.
/// @return pointer to the top element of the stack.
void *my_stack_pop(struct my_stack *stack)
{
    struct my_stack_node *top1 = stack->top;
    if (top1 == NULL)
    // Chech if malloc gived a position in memory.
    {
        return NULL;
    }

    stack->top = top1->next;
    void *data = top1->data;
    free(top1);
    
    return data;
}

/// @brief Calculates the length of a stack.
/// @param stack pointer to the stack.
/// @return length of the stack.
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

/// @brief Frees all the memory that the stack and its elements
/// are ocuping.
/// @param stack pointer to the stack that we want to purge.
/// @return the quantity of bytes that we have purged.
int my_stack_purge(struct my_stack *stack)
{
    int released_memory = 0;
    
    // Until the stack is empty.
    while(stack->top)
    {
        struct my_stack_node *node = stack->top;
        stack->top = node->next;
        released_memory += stack->size;  // First free the data of the node.
        free(node->data);
        released_memory += sizeof(*node);
        free(node);  // Free the node.
    }
    
    free(stack);  // Finalmente, libera la estructura de la pila.
    released_memory += sizeof(*stack);

    return released_memory;
}

/// @brief Creates a new stack reading the content
/// of a file that contains the data of a previous
/// stack.
/// @param filename path to the file.
/// @return a pointer to the new stack.
struct my_stack *my_stack_read(char *filename)
{
    int fd = open(filename, O_RDONLY);
    if (fd == -1)
    {
        perror("ERROR: ");
        return NULL;
    }
    
    // Get the size of the data that is stored in the file.
    int size = 0;
    read(fd, &size, sizeof(int));
    // Initialize a new stack with the obtained size.
    struct my_stack *stack = my_stack_init(size);
    void *data = malloc(size);
    if (data == NULL)
    {
        perror("ERROR: ");
        return NULL;
    }

    /*
      While the read operation is successfull, we continue
      reading and pushing elements into the new stack.
    */
    while (read(fd, data, size) > 0)
    {
        my_stack_push(stack, data);
        data = malloc(size);
        // Check if the malloc gived a space in memory
        if (data == NULL)
        {
            perror("ERROR: ");
            return NULL;
        }
    }
    //Free the last space reserved for data and close the file.
    free(data);
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
    /*
      Creation of the aux_stack. With this auxiliar stack, we can
      write the stack in the file in reversed order.
    */
    struct my_stack *aux_stack = my_stack_init(stack->size);
    struct my_stack_node* tmp_next = stack->top;
    while (tmp_next)
    {
        my_stack_push(aux_stack, tmp_next->data);
        tmp_next = tmp_next->next;
    }

    int fd = open(filename, O_WRONLY | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR);
    //Check if the file was opened successfully.
    if (fd == -1)
    {
        perror("ERROR: ");
        return -1;
    }

    tmp_next = aux_stack->top;
    int stack_data_size = stack->size;
    // Check if write operation is successfull.
    if(write(fd, &stack_data_size, sizeof(int)) == -1)
    {
        perror("ERROR: ");
        close(fd);
        return -1;
    }
    
    // Write until aux_stack is null.
    while (aux_stack->top != NULL)
    {
        void *data = my_stack_pop(aux_stack);
        // Check if write operations are successfull.
        if (write(fd, data, stack_data_size) < 0)
        {
            perror("ERROR: ");
            close(fd);
            return -1;
        }

        num_elements++;
    }
    
    close(fd);
    
    return num_elements;
}