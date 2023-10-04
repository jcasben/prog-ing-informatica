#include <stdio.h>

int main() {
    int x,y;
    int *px;

    x = 5;
    px = &x;
    y = *px;

    printf("&x = %p\n", &x);
    printf("x = %d\n", x);
    printf("&px = %p\n", &px);
    printf("px = %p\n", px);
    printf("&y = %p\n", &y);
    printf("*px = %d\n", *px);
    printf("y = %d\n", y);
    
    return 0;
}