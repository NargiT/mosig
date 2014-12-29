/* 
 * File:   getInt.c
 * Author: Group 2
 *
 * Created on 20 janvier 2011, 10:27
 */

#include "syscall.h"
/*
 * 
 */
int main() {

    char number;
    GetInt(&number);
    PutInt(number);
    return 0;
}

