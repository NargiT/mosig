#include "syscall.h"

int main() {
    int i;
    PutString("start program 2");
    PutString("\n");    
    for (i=10;i<20;i++){
        PutInt(i);
    }
    PutString("\n");
    PutString("end program 2");
}


