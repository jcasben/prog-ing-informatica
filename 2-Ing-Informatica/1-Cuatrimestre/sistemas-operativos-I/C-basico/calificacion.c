#include <stdio.h>

int main() {
    float mark = 0;
    printf(
        "*** Determinar la calificación cualitativa ***"
        "\nIntroduce una nota: "
    );
    scanf("%f", &mark);

    if (mark >= 0 && mark < 5) {
        printf("Suspenso\n");
    } else if (mark >= 5 && mark < 7) {
        printf("Aprobado\n");
    } else if (mark >= 7 && mark <= 9) {
        printf("Notable\n");
    } else if (mark >= 9 && mark <= 10) {
        printf("Sobresaliente\n");
    } else {
        printf("Introduce un número entre 0 y 10\n");
    }

    return 0;
}