/* 
 * File:   getchar.c
 * Author: Group 2
 *
 * Created on 20 janvier 2011, 10:27
 */

#include "syscall.h"
/*
 * 
 */
int main(int argc, char** argv) {

    char c;
    c = GetChar();
    PutChar(c);
    return 0;
}

