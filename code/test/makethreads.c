/* 
 * File:   makethreads.c
 * Author: Group 2
 *
 * Created on 20 janvier 2011, 00:17
 */

#include "syscall.h"

void startCounting(int i) {
    int j;
    
    PutString("Process "); PutInt(i); PutString(" starts the counting");
    for (j = 0; j < 10; j++)
        PutInt(j);
    return;
}

/*
 * 
 */
int main(int argc, char** argv) {

    int nb_threads, i;
    short int done = 0;

    PutString("Start main\n");
    PutString("Choose a number between 1..3:\n");
    while (!done) {
        GetInt(&nb_threads);
        if (!(nb_threads < 1 || nb_threads > 3))
            done = 1;
        else {
            PutString("...Man I need 1, 2 or 3 not "); PutInt(nb_threads); PutChar('\n');
        }
    }

    PutString("Creation of threads...\n");

    for (i = 0; i < nb_threads; i++) {
        UserThreadCreate(startCounting, i);
    }

    PutString("The main is done.\n");
    return 0;
}
