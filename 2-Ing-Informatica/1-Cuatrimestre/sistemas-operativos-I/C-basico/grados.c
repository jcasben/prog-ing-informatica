#include <stdio.h>

int main() {
    float fahr = 0;
    printf(
        "*** Conversión de grados Fahrenheit a grados centígrados ***" 
        "\nIntroduce grados Fahrenheit: "
    );
    scanf("%f", &fahr);
    printf("%.1f grados Fahrenheit son %.1f grados centígrados\n", fahr, (5.0/9.0) * (fahr - 32.0));
    
    return 0;
}