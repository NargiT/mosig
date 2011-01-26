#include "syscall.h"

int main() {
    int i;
    PutString("start program 1\n");
    for (i = 0; i < 10; i++) {
        PutInt(i);
    }
    PutString("\n");
    PutString("end program 1\n");
}


