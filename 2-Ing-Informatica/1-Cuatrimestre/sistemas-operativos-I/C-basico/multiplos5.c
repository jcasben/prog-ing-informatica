#include <stdio.h>

int main() {
    int count = 0;
    for (int i = 5; i <= 100; i += 5) {
        printf("%d\t", i);
        count++;
        if (count == 5) {
            printf("\n");
            count = 0;
        }
    }

    return 0;
}