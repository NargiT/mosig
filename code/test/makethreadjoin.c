#include "syscall.h"

void First(int id) {
    PutString("Fuck");PutInt(id);PutString("\n");
}

void Second(int id) {
    PutString("Fuck 2");PutInt(id);PutChar('h');
    UserThreadJoin(id);
}

int main() {
    int one, two;
    one = UserThreadCreate(First, 0);
    two = UserThreadCreate(Second, one);
    UserThreadJoin(one);
    
    // Wierd things happended
    //PutInt(one);
    //PutInt(two);
}