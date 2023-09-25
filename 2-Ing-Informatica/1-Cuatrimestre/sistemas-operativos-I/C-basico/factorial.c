#include <stdio.h>

int main() {
    unsigned long long int factorial(int n);
    int n;
    printf(
        "*** Cálculo del factorial de un número ***\n"
        "Introduce un número: "
    );
    scanf("%d", &n);
    printf("%d! = %lld\n", n,factorial(n));

    return 0;
}

unsigned long long int factorial(int n) {
    if (n <= 1) {
        return 1;
    }

    return n * factorial(n - 1);
}