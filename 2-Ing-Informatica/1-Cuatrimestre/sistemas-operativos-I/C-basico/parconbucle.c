#include <stdio.h>

int main() {
    int n = 0;
    printf("*** Determinar si un número es par o impar ***\n");
    do {
        printf("- Introduce un número: ");
        scanf("%d", &n);
        if (n % 2 == 0) {
            printf("El número %d es par\n", n);
        } else {
            printf("El número %d es impar\n", n);
        }
    } while (n != 0);

    return 0;
}