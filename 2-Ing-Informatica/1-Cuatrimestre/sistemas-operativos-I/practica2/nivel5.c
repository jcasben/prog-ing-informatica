#define _POSIX_C_SOURCE 200112L

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

#define COMMAND_LINE_SIZE 1024
#define ARGS_SIZE 64
#define N_JOBS 64
#define DEBUGN1 0
#define DEBUGN2 0
#define DEBUGN3 1
#define DEBUGN4 1
#define DEBUGN5 1

#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <sys/wait.h>
#include <signal.h>

struct info_job
{
    pid_t pid;
    char estado;
    char cmd[COMMAND_LINE_SIZE];
};

static char mi_shell[COMMAND_LINE_SIZE];
static struct info_job jobs_list[N_JOBS];
int n_job = 0;

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
void reaper(int signum);
void ctrlc(int signum);
void ctrlz(int signum);
int is_background(char **args);
int jobs_list_add(pid_t pid, char estado, char *cmd);
int jobs_list_find(pid_t pid);
int jobs_list_remove(int pos);


/// @brief The main function that starts an infinite loop to read and execute commands.
int main(int argc, char *argv[])
{
    signal(SIGCHLD, reaper);
    signal(SIGINT, ctrlc);
    signal(SIGTSTP, ctrlz);

    strcpy(mi_shell, argv[0]);
    struct info_job *job0 = malloc(sizeof(struct info_job));
    if (job0 == NULL)
    {
        perror("ERROR");
        return -1;
    }
    job0->pid = 0;
    job0->estado = 'N';
    jobs_list[0] = *job0;
    
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
/// @return the read line.
char *read_line(char *line)
{
    print_prompt();
    char *result_fgets = fgets(line, COMMAND_LINE_SIZE, stdin);
    
    if(result_fgets) line[strlen(line) - 1] = '\0';
    else
    {
        printf("\r");
        if (feof(stdin))
        {
            printf(NEGRITA MAGENTA_T "\nSee you soon! :D\n" RESET);
            exit(0);
        }
    }
    
    return result_fgets;
}

/// @brief parses the line into tokens and checks if the first token refers to an internal command.
/// @param line the input line.
/// @return 0 if successful.
int execute_line(char *line)
{
    char full_line[ARGS_SIZE] = "";
    char *args[ARGS_SIZE];
    pid_t pid;
    if (parse_args(args, line) > 0)
    {
        //Concatenate the tokens to get the full line.
        for (int i = 0; args[i] != NULL; i++)
        {
            strcat(full_line, args[i]);
            if (args[i + 1] != NULL)
            {
                strcat(full_line, " ");
            }
        }

        if (check_internal(args) == 0)
        {
            #if DEBUGN2
                printf(ROJO_T "%s: command not found\n" RESET, args[0]);
            #endif

            int background = is_background(args);
            pid = fork();
            //Child process
            if (pid == 0)
            {   
                signal(SIGCHLD, SIG_DFL);
                signal(SIGINT, SIG_IGN);
                signal(SIGTSTP, SIG_IGN);

                execvp(args[0], args);
                
                perror(ROJO_T "ERROR");
                printf(RESET);
                exit(-1);
            }
            // Parent process
            else if (pid > 0)
            {
                #if DEBUGN3
                    printf(BLANCO_T "[execute_line() -> PID padre: %d (%s)]\n" RESET, getpid(), mi_shell);
                    printf(BLANCO_T "[execute_line() -> PID hijo: %d (%s)]\n" RESET, pid, full_line);
                #endif

                if (background == 0)
                {
                    strcpy(jobs_list[0].cmd, full_line);
                    jobs_list[0].estado = 'E';
                    jobs_list[0].pid = pid;

                    while (jobs_list[0].pid > 0) pause();
                }
                else
                {
                    jobs_list_add(pid, 'E', full_line);
                    printf("[%d] %d\t%c\t%s\n", n_job, jobs_list[n_job].pid, jobs_list[n_job].estado, jobs_list[n_job].cmd);
                }
            }
            // pid < 0 --> error
            else
            {
                perror("fork");
                exit(-1);
            }
        }
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
        printf(NEGRITA MAGENTA_T "See you soon! :D\n" RESET);
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

    if (args[1] == NULL) chdir(getenv("HOME"));
    else if (args[2])
    {
        int i = 1;
        char *advanced_cd = malloc(COMMAND_LINE_SIZE);
        strcpy(advanced_cd, args[1]);

        while (args[i + 1])
        {
            if (strchr(advanced_cd,'\\'))
            {
                advanced_cd[strlen(advanced_cd) - 1] = '\0';
            }
            strcat(advanced_cd, " ");
            strcat(advanced_cd, args[++i]);
        }

        if (strchr(advanced_cd, '\'') || strchr(advanced_cd, '\"'))
        {
            advanced_cd = &advanced_cd[1];
            advanced_cd[strlen(advanced_cd) - 1] = '\0'; 
        }

        if (chdir(advanced_cd) == -1)
        {
            fprintf(stderr, ROJO_T "-mini_shell: cd: %s: %s\n" RESET, advanced_cd, strerror(errno));
        }
    }
    else
    {
        if (chdir(args[1]) == -1)
            fprintf(stderr, ROJO_T "-mini_shell: cd: %s: %s\n" RESET, args[1], strerror(errno));
    }

    #if DEBUGN2
        char cwd[COMMAND_LINE_SIZE];
        getcwd(cwd, COMMAND_LINE_SIZE);
        printf(BLANCO_T "[internal_cd() -> PWD: %s]\n" RESET, cwd);
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
    
    char *delim = "=";
    char *name = strtok(args[1], delim);
    char * value = strtok(NULL, " \n\t\r");
    if (name == NULL || value == NULL)
    {
        fprintf(stderr, ROJO_T "-mini_shell: export: invalid syntax. Expected NAME=value\n" RESET);
        return -1;
    }

    #if DEBUGN2
        printf(BLANCO_T "[internal_export() -> name: %s]\n" RESET, name);
        printf(BLANCO_T "[internal_export() -> value: %s]\n" RESET, value);
        printf(BLANCO_T "[internal_export() -> previous value of %s: %s]\n" RESET, name, getenv(name));
    #endif
    
    setenv(name, value, 1);
    
    #if DEBUGN2
        printf(BLANCO_T "[internal_export() -> new value of %s: %s]\n" RESET, name, getenv(name));
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

    if (args[1] == NULL)
    {
        fprintf(stderr, ROJO_T "-mini_shell: source: invalid syntax. Expected source \"filename\"\n" RESET);
        return -1;
    }

    FILE *fp;
    char line[COMMAND_LINE_SIZE];
    fp = fopen(args[1], "r");
    if (fp == NULL)
    {
        fprintf(stderr, ROJO_T "-mini_shell: source: file doesn't exist.\n" RESET);
        return -1;
    }

    while (fgets(line, COMMAND_LINE_SIZE, fp) != NULL)
    {
        fflush(fp);
        line[strlen(line) - 1] = '\0';

        #if DEBUGN3
            printf(BLANCO_T "\n[internal_source() -> LINE: %s]\n" RESET, line);
        #endif

        execute_line(line);
    }
    fclose(fp);

    return 0;
}

/// @brief Shows the PIDs of processes not in the foreground.
/// @return 0 if successful.
int internal_jobs()
{
    #if DEBUGN1
        printf(BLANCO_T "[internal_jobs() -> Esta función mostrará el PID de los procesos que no estén en fg]\n" RESET);
    #endif
    for (int i = 1; i <= n_job; i++)
    {
        if (jobs_list[i].pid > 0)
        {
            printf("[%d] %d\t%c\t%s\n", i, jobs_list[i].pid, jobs_list[i].estado, jobs_list[i].cmd);
        }
    }

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
    char cwd[COMMAND_LINE_SIZE];
    getcwd(cwd, COMMAND_LINE_SIZE);
    printf(NEGRITA AMARILLO_T "%s" RESET ":" CYAN_T "%s" RESET "$ " RESET,getenv("USER"), cwd);
    sleep(0.5);
    fflush(stdout);
}

/// @brief Handles the SIGCHLD signal.
/// @param signum signal number.
void reaper(int signum)
{
    signal(SIGCHLD, reaper);
    int status;
    int ended;

    while ((ended = waitpid(-1, &status, WNOHANG)) > 0)
    {
        if (ended == jobs_list[0].pid)
        {
            #if DEBUGN4
                char mensaje[1200];
                sprintf(mensaje, BLANCO_T "[reaper() -> Proceso hijo %d en foreground (%s) finalizado con exit code %d]\n" RESET, ended, jobs_list[0].cmd, status);
                write(2, mensaje, strlen(mensaje));
            #endif

            strcpy(jobs_list[0].cmd, "");
            jobs_list[0].estado = 'F';
            jobs_list[0].pid = 0;
        }
        else
        {
            int pos_ended = jobs_list_find(ended);
            #if DEBUGN5
                char mensaje[1200];
                sprintf(mensaje, BLANCO_T "[reaper() -> Proceso hijo %d en background (%s) finalizado con exit code %d]\n" RESET, ended, jobs_list[pos_ended].cmd, status);
                write(2, mensaje, strlen(mensaje));
            #endif

            printf("Terminado PID %d (%s) en jobs_list[%d] con status %d\n", ended, jobs_list[pos_ended].cmd, pos_ended, status);
            jobs_list_remove(pos_ended);
        }
    }
    
    fflush(stdout);
}

/// @brief Handles the SIGINT signal.
/// @param signum signal number.
void ctrlc(int signum)
{
    signal(SIGINT, ctrlc);
    #if DEBUGN4
        char mensaje[3000];
        sprintf(mensaje, BLANCO_T "\n[ctrlc() -> Soy el proceso con PID %d (%s), el proceso en el foreground es %d (%s)]\n" RESET, getpid(), mi_shell, jobs_list[0].pid, jobs_list[0].cmd);
        write(2, mensaje, strlen(mensaje));
    #endif

    if (jobs_list[0].pid > 0)
    {
        if (strcmp(mi_shell, jobs_list[0].cmd) != 0)
        {
            kill(jobs_list[0].pid, SIGTERM);
            
            #if DEBUGN4
                char mensaje[3000];
                sprintf(mensaje, BLANCO_T "[ctrlc() -> Señal %d enviada a %d (%s) por %d (%s)]\n" RESET, SIGTERM, jobs_list[0].pid, jobs_list[0].cmd, getpid(), mi_shell);
                write(2, mensaje, strlen(mensaje));
            #endif
        } 
        else
        {
            #if DEBUGN4
                char mensaje[2000];
                sprintf(mensaje, BLANCO_T "[ctrlc() -> Señal %d no enviada por %d (%s) debido a que su proceso en foreground es el shell]\n" RESET, SIGTERM, getpid(), mi_shell);
                write(2, mensaje, strlen(mensaje));
            #endif
        }
    } 
    else
    {
        #if DEBUGN4
            char mensaje[2000];
            sprintf(mensaje, BLANCO_T "[ctrlc() -> Señal %d no enviada por %d (%s) debido a que no hay procesos en el foreground]\n" RESET, SIGTERM, getpid(), mi_shell);
            write(2, mensaje, strlen(mensaje));
        #endif
    }

    fflush(stdout);
}

/// @brief Handles the SIGTSTP signal.
/// @param signum signal number.
void ctrlz(int signum)
{
    signal(SIGTSTP, ctrlz);
    #if DEBUGN5
        char mensaje[3000];
        sprintf(mensaje, BLANCO_T "\n[ctrlz() -> Soy el proceso con PID %d (%s), el proceso en el foreground es %d (%s)]\n" RESET, getpid(), mi_shell, jobs_list[0].pid, jobs_list[0].cmd);
        write(2, mensaje, strlen(mensaje));
    #endif

    if (jobs_list[0].pid > 0)
    {
        if (strcmp(mi_shell, jobs_list[0].cmd) != 0)
        {
            kill(jobs_list[0].pid, SIGSTOP);
            jobs_list[0].estado = 'D';
            jobs_list_add(jobs_list[0].pid, jobs_list[0].estado, jobs_list[0].cmd);
            printf("[%d] %d\t%c\t%s\n", n_job, jobs_list[0].pid, jobs_list[0].estado, jobs_list[0].cmd);
            
            #if DEBUGN5
                char mensaje[3000];
                sprintf(mensaje, BLANCO_T "\n[ctrlz() -> Señal %d enviada a %d (%s) por %d (%s)]\n" RESET, SIGSTOP, jobs_list[0].pid, jobs_list[0].cmd, getpid(), mi_shell);
                write(2, mensaje, strlen(mensaje));
            #endif
            strcpy(jobs_list[0].cmd, "");
            jobs_list[0].pid = 0;
        } 
        else
        {
            #if DEBUGN5
                char mensaje[2000];
                sprintf(mensaje, BLANCO_T "\n[ctrlz() -> Señal %d no enviada por %d (%s) debido a que su proceso en foreground es el shell]\n" RESET, SIGSTOP, getpid(), mi_shell);
                write(2, mensaje, strlen(mensaje));
            #endif
        }
    }
    else
    {
        #if DEBUGN5
            char mensaje[2000];
            sprintf(mensaje, BLANCO_T "\n[ctrlz() -> Señal %d no enviada por %d (%s) debido a que no hay procesos en el foreground]\n" RESET, SIGSTOP, getpid(), mi_shell);
            write(2, mensaje, strlen(mensaje));
        #endif
    }

    fflush(stdout);
}

/// @brief Checks if the command is to be executed in the background.
/// @param args tokenized command.
/// @return 1 if the command is to be executed in the background; 0 if not.
int is_background(char **args)
{
    int i = 0;
    while (args[i] != NULL)
    {
        i++;
    }
    if (strcmp(args[i - 1], "&") == 0)
    {
        args[i - 1] = NULL;
        return 1;
    }

    return 0;
}

/// @brief Adds a job to the jobs list and increments the number of jobs.
/// @param pid pid of the job.
/// @param estado state of the job.
/// @param cmd command of the job.
int jobs_list_add(pid_t pid, char estado, char *cmd)
{
    n_job++;
    if (n_job < N_JOBS) {
        jobs_list[n_job].pid = pid;
        jobs_list[n_job].estado = estado;
        strcpy(jobs_list[n_job].cmd, cmd);
    } else {
        fprintf(stderr, ROJO_T "-mini_shell: jobs_list_add: jobs_list is full.\n" RESET);
        return -1;
    }

    return 0;
}

/// @brief Finds a job in the jobs list by its pid.
/// @param pid pid of the job.
/// @return the position of the job in the jobs list.
int jobs_list_find(pid_t pid)
{
    for (int i = 0; i < N_JOBS; i++)
    {
        if (jobs_list[i].pid == pid) return i;
    }

    return 0;
}

/// @brief Removes a job from the jobs list.
/// @param pos position of the job in the jobs list.
/// @return 0 if successful.
int jobs_list_remove(int pos)
{
    if (pos < N_JOBS)
    {
        //Move the last job to the position of the deleted job.
        jobs_list[pos].pid = jobs_list[n_job].pid;
        jobs_list[pos].estado = jobs_list[n_job].estado;
        strcpy(jobs_list[pos].cmd, jobs_list[n_job].cmd);
    }
    else
    {
        fprintf(stderr, ROJO_T "-mini_shell: jobs_list_remove: possition is out of bounds.\n" RESET);
        return -1;
    }
    n_job--;

    return 0;
}