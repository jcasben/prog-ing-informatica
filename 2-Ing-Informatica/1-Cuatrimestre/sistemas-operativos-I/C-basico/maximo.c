#include <stdio.h>

int main() {
    int a = 0;
    int b = 0;
    printf(
        "*** Función máximo de dos números enteros ***\n"
        "Introduce dos números: \n"
    );
    scanf("%d%d", &a, &b);
    if (a < b) {
        printf("max(%d,%d) = %d\n", a, b, b);
    } else {
        printf("max(%d,%d) = %d\n", a, b, a);
    }
    
    return 0;
}