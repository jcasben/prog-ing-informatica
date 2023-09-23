#include <stdio.h>

int main() {
    int number = 0;
    printf("*** Cálculo del cuadrado de un número ***\nIntroduce un número: ");
    scanf("%d", &number);
    printf("El cuadrado de %d es %d\n", number, number * number);
    
    return 0;
}