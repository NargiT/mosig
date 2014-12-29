#include "syscall.h"

void test(char *s) {
    PutString(s);
}

int main() {
    PutString("Hello ");
    test("World!");
}

