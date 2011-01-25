#include "syscall.h"

void test(char *s) {
    PutString(s);
    PutInt(14);
    PutChar('H');
}

int main() {
    test("hello");
    PutString("hello world !");
    PutInt(3);
    PutChar('A');
    PutInt(2);
    test("world");
    PutString("youpla boom");
    Halt();
}

