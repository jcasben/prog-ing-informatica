#include <stdio.h>

int main() {
    int x, *px;
    printf("---- a)x = 12;px = &x; ----\n");
    x = 12;
    px = &x;
    printf("x = %d\n", x);
    printf("&x = %p\n", &x);
    printf("*px = %d\n", *px);
    printf("px = %p\n", px);

    printf("---- b)*px = *px + 10; ----\n");
    *px = *px + 10;
    printf("x = %d\n", x);
    printf("&x = %p\n", &x);
    printf("*px = %d\n", *px);
    printf("px = %p\n", px);

    printf("---- c)x = *px + 10; ----\n");
    x = *px + 10;
    printf("x = %d\n", x);
    printf("&x = %p\n", &x);
    printf("*px = %d\n", *px);
    printf("px = %p\n", px);

    printf("---- d)*px += 1; ----\n");
    *px += 1;
    printf("x = %d\n", x);
    printf("&x = %p\n", &x);
    printf("*px = %d\n", *px);
    printf("px = %p\n", px);

    return 0;
}