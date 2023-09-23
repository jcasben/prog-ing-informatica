#include <stdio.h>

int main() {
    int num = 0;
    printf(
        "*** Determinar si un número es par o impar ***"
        "\nIntroduce un número: "
    );
    scanf("%d", &num);

    if (num % 2 == 0) {
        printf("El número %d es par\n", num);
    } else {
        printf("El número %d es impar\n", num);
    }

    return 0;
}