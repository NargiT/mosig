#include "syscall.h"

int main() {
    PutString("start main\n");
    ForkExec("../test/simpleprogram1");
    PutString("exit main\n");
  
}

