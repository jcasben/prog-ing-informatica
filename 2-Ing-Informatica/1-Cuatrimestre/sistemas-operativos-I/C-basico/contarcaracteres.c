#include <stdio.h>

int main() {
    int contar_caracteres(void);
    printf(
        "*** Función contar carácteres de stdin hasta EOF ***\n"
    );
    int caracts = contar_caracteres();
    printf("\nHas introducido %d carcateres.\n", caracts);

    return 0;
}

int contar_caracteres(void) {
    int count = 0;
    char c = getchar();
    while (c != EOF) {
        count++;
        c = getchar();
    }
    return count;
}