/***************************************************************************
 * main.c
 ****************************************************************************/

/****************************************************************************
 * Includes
 ****************************************************************************/
#include "main.h"
/****************************************************************************
 * Local preprocessor defines
 ****************************************************************************/

/****************************************************************************
 * Local typedefs
 ****************************************************************************/

/****************************************************************************
 * Local variables
 ****************************************************************************/

/****************************************************************************
 * Local functions
 ****************************************************************************/
void clear_line() {
    printf("\33[2K\r");
}

void move_cursor_up(int lines) {
    printf("\033[%dA", lines);
}

void move_cursor_down(int lines) {
    printf("\033[%dB", lines);
}
/****************************************************************************
 * Implementation of functions
 ****************************************************************************/
void check_alarms(float alarms[], int num_alarms, int current_time, int *terminate)
{
    size_t i;
    for (i = 0; i < num_alarms; i++)
    {
        if (alarms[i] == 0) *terminate = 0;
        else if (alarms[i] <= current_time)
        {
            printf(
                "\n\nAn alarm has been triggered. Expected time: %.2f | Actual time: %d | Diff: %.2f\n\n", 
                alarms[i], current_time, current_time - alarms[i]
            );
            alarms[i] = 1e9;
        }
    }
    
}

int add_alarm(float alarms[], int *num_alarms, float new_alarm, char *input)
{
    char *token = strtok(input, " ");
    while (token != NULL) {
        float new_alarm = atof(token);
        if (*num_alarms >= MAX_ALARMS)
        {
            printf("\n\nMAX ALARMS REACHED!\n\n");
            return 0;
        }

        if (new_alarm == 0) return -1;

        alarms[(*num_alarms)++] = new_alarm;
        move_cursor_down(1);
        printf("\n\nAdded new alarm: %.2f\n\n", new_alarm);
        token = strtok(NULL, " ");
    }

    return 1;
}


int main(int argc, char *argv[])
{
    int current_time = 0;
    float alarms[MAX_ALARMS];
    int num_alarms = 0;
    int terminate = 0;

    while (1)
    {
        struct timeval tv;
        tv.tv_sec = 1;
        tv.tv_usec = 0;

        fd_set read_fs;
        FD_ZERO(&read_fs);
        FD_SET(STDIN_FILENO, &read_fs);

        int retval = select(STDIN_FILENO + 1, &read_fs, NULL, NULL, &tv);

        if (retval == -1)
        {
            perror("select()");
            exit(EXIT_FAILURE);
        }
        else if (retval == 0)
        {
            current_time++;
            clear_line();
            printf("Current time: %d\n\n", current_time);
            fflush(stdout);
            check_alarms(alarms, num_alarms, current_time, &terminate);

        }
        else if (FD_ISSET(STDIN_FILENO, &read_fs))
        {
            char input[8196];
            if (fgets(input, sizeof(input), stdin) != NULL)
            {
                float new_alarm = atof(input);
                if (!add_alarm(alarms, &num_alarms, new_alarm, input))
                {
                    printf("Couldn't add a new alarm\n");
                }
                if (new_alarm == 0)
                {
                    terminate = 1;
                    break;
                }
            }
        }
    }

    return 0;
}
