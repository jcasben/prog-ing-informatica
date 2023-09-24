#include <stdio.h>

int main() {
    int n = 0;
    int i = 1;
    int res = 0;
    printf(
        "*** Cálculo de 1+2+3+...+N ***"
        "\nIntroduce un número N: "
    );
    scanf("%d", &n);

    while (i <= n) {
        res += i;
        i++;
    }
    
    printf("1+2+...+%d = %d\n", n, res);

    return 0;
}