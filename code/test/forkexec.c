/* 
 * File:   forkexec.c
 * Author: nargit
 *
 * Created on 25 janvier 2011, 15:20
 */
#include "syscall.h"

/*
 * 
 */
int main() {
    PutString("Lunch putstring\n");
    ForkExec("test/putstring");
    PutString("End\n");
    return 0;
}

