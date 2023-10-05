#include <stdio.h>

void funcionValor(int a, int b);
void funcionReferencia(int *a, int *b);
int main() {
    printf("*** Paso de parámetros por referencia ***\n");
    int x = 2;
    int y = 5;
    printf("antes: x = %d, y = %d\n", x, y);
    funcionValor(x,y);
    printf("despues: x = %d, y = %d\n", x, y);

    printf("*** Paso de parámetros por referencia ***\n");
    x = 2;
    y = 5;
    printf("antes: x = %d, y = %d\n", x, y);
    funcionReferencia(&x,&y);
    printf("despues: x = %d, y = %d\n", x, y);

    return 0;
}

void funcionValor(int a, int b) {
    a = 0;
    b = 0;
    printf("Dentro de funcionValor(): *a = %d, *b = %d\n", a, b);
    return;
}

void funcionReferencia(int *a, int *b) {
    *a = 0;
    *b = 0;
    printf("Dentro de funcionReferencia(): a = %d, b = %d\n", *a, *b);
    return;
}