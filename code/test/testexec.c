#include "syscall.h"

int main() {
    PutString("start main");
    ForkExec("../test/simpleprogram1");
    PutString("exit main");
  
}

