#include <stdio.h>

int main() {
    #define PI 3.141593 
    float radius = 0;
    printf("*** Cálculo del area de un círculo ***\nIntroduce el radio: ");
    scanf("%f", &radius);
    printf("El área del circulo de radio %.2f es: %.4f\n", radius, radius * radius * PI);
    
    return 0;
}