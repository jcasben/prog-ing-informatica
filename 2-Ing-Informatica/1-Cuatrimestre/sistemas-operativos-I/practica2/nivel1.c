#define _POSIX_C_SOURCE 200112L
#define COMMAND_LINE_SIZE 1024
#define ARGS_SIZE 64
#define RESET "\033[0m"
#define NEGRO_T "\x1b[30m"
#define NEGRO_F "\x1b[40m"
#define GRIS_T "\x1b[94m"
#define ROJO_T "\x1b[31m"
#define VERDE_T "\x1b[32m"
#define AMARILLO_T "\x1b[33m"
#define AZUL_T "\x1b[34m"
#define MAGENTA_T "\x1b[35m"
#define CYAN_T "\x1b[36m"
#define BLANCO_T "\x1b[97m"
#define NEGRITA "\x1b[1m"
#define DEBUGN1 1

#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>

/* Function prototypes */
char *read_line(char *line);
int execute_line(char *line);
int parse_args(char **args, char *line);
int check_internal(char **args);
int internal_cd(char **args);
int internal_export(char **args);
int internal_source(char **args);
int internal_jobs();
int internal_fg(char **args);
int internal_bg(char **args);
void print_prompt();

/// @brief The main function that starts an infinite loop to read and execute commands.
int main()
{
    char line[COMMAND_LINE_SIZE];
    while(1)
    {
        if(read_line(line))
        {
            execute_line(line);
        }
    }
}

/// @brief reads a line of user input and trims it.
/// @param line where the input will be saved.
/// @return the read line; NULL if too short.
char *read_line(char *line)
{
    print_prompt();
    fgets(line, COMMAND_LINE_SIZE, stdin);
    if(strlen(line) < 2) return NULL;
    line[strlen(line) - 1] = '\0';
    
    return line;
}

/// @brief parses the line into tokens and checks if the first token refers to an internal command.
/// @param line the input line.
/// @return 0 if successful.
int execute_line(char *line)
{
    char *args[ARGS_SIZE];
    if (parse_args(args, line) > 0)
    {
        if (check_internal(args) == 0) 
            printf(ROJO_T "%s: command not found\n" RESET, args[0]);
    }
    return 0;
}

/// @brief parses the line into tokens, dividing the input by spaces. 
/// Deletes the comments (tokens starting with '#').
/// @param args the array where the tokens will be saved.
/// @param line the input line.
/// @return the number of tokens found.
int parse_args(char **args, char *line)
{
    //The input will be separated on this chars.
    char *delim = " ";
    int i = 0;
    args[i] = strtok(line, delim);

    while (args[i] != NULL)
    {
        if(args[i][0] == '#')
        {
           break;
        }
        i++;
        args[i] = strtok(NULL, delim);
    }
    args[i] = NULL;

    #if DEBUGN1
        printf(BLANCO_T "Número de tokens: %d\n" RESET, i);
        int j = 0;
        while (args[j] != NULL)
        {
            printf(BLANCO_T "[Token %d -> %s]\n" RESET, j, args[j]);
            j++;
        }
        printf(BLANCO_T "[Token %d -> (null)]\n" RESET, j);
    #endif

    return i;
}

/// @brief Checks if the first argument refers to an internal command 
/// and calls the corresponding function.
/// @param args The array of arguments.
/// @return 1 if successful; 0 if not successful.
int check_internal(char **args)
{
    if (strcmp(args[0], "exit") == 0) 
    {
        printf(NEGRITA MAGENTA_T "See you soon! :D\n");
        exit(0);
    }
    else if (strcmp(args[0], "cd") == 0) internal_cd(args);
    else if (strcmp(args[0], "export") == 0) internal_export(args);
    else if (strcmp(args[0], "source") == 0) internal_source(args);
    else if (strcmp(args[0], "jobs") == 0) internal_jobs(args);
    else if (strcmp(args[0], "fg") == 0) internal_fg(args);
    else if (strcmp(args[0], "bg") == 0) internal_bg(args);
    else return 0;

    return 1;
}

/// @brief changes the current directory.
/// @param args the array of arguments.
/// @return 0 if successful.
int internal_cd(char **args)
{
    #if DEBUGN1
        printf(BLANCO_T "[internal_cd() -> Esta función cambia de directorio]\n" RESET);
    #endif

    return 0;
}

/// @brief Assigns values to enviroment variables.
/// @param args the array of arguments.
/// @return 0 if successful.
int internal_export(char **args)
{
    #if DEBUGN1
        printf(BLANCO_T "[internal_export() -> Esta función asigna valores a variables de entorno]\n" RESET);
    #endif

    return 0;
}

/// @brief Executes a command file.
/// @param args the array of arguments.
/// @return 0 if successful.
int internal_source(char **args)
{
    #if DEBUGN1
        printf(BLANCO_T "[internal_source() -> Esta función ejecutará un fichero de linea de comandos]\n" RESET);
    #endif

    return 0;
}

/// @brief Shows the PIDs of processes not in the foreground.
/// @return 0 if successful.
int internal_jobs()
{
    #if DEBUGN1
        printf(BLANCO_T "[internal_jobs() -> Esta función mostrará el PID de los procesos que no estén en fg]\n" RESET);
    #endif

    return 0;
}

/// @brief Brings a process to the foreground.
/// @param args the array of arguments.
/// @return 0 if successful.
int internal_fg(char **args)
{
    #if DEBUGN1
        printf(BLANCO_T "[internal_fg() -> Esta función envía un proceso al fg]\n" RESET);
    #endif

    return 0;
}

/// @brief Sends a process to the background.
/// @param args the array of arguments.
/// @return 0 if successful.
int internal_bg(char **args)
{
    #if DEBUGN1
        printf(BLANCO_T "[internal_bg() -> Esta función envía un proceso al bg]\n" RESET);
    #endif

    return 0;
}

/// @brief Prints the prompt of the mini shell.
void print_prompt()
{
    fflush(stdout);
    sleep(0.5);
    char *prompt = "$ ";
    char cwd[COMMAND_LINE_SIZE];
    getcwd(cwd, COMMAND_LINE_SIZE);
    printf(NEGRITA AMARILLO_T "%s" RESET ":" CYAN_T "%s" RESET "%s" RESET,getenv("USER"), cwd, prompt);
}