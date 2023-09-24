#include <stdio.h>

int main() {
    char c = ' ';
    printf(
        "*** Determinar si continuar o no ***"
        "\n¿Deseas continuar (S) o no (N)? "
    );
    scanf("%c", &c);
    switch (c) {
    case 'S':
    case 's':
        printf("Ahora continuamos.\n");
        break;
    case 'N':
    case 'n':
        printf("Nos vemos en otra ocasión.\n");
        break;
    default:
        printf("Opción incorrecta.\n");
        break;
    }

    return 0;
}