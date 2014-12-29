/* 
 * File:   getstring.c
 * Author: nargit
 *
 * Created on 20 janvier 2011, 10:27
 */

#include "syscall.h"
/*
 * 
 */
int main(int argc, char** argv) {

    char s[10];
    GetString(s, 10);
    PutString(s);
    Halt();
    return 0;
}

