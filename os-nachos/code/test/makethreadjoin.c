#include "syscall.h"

void First(int id) {
    PutInt(id);PutString("\n");
}

void Second(int id) {
    PutInt(id);PutChar('\n');
    UserThreadJoin(id);
}

int main() {
    int one, two;
    one = UserThreadCreate(First, 0);
    two = UserThreadCreate(Second, one);
    UserThreadJoin(one);
}