/* 
 * File:   userthread.h
 * Author: Group 2
 *
 * Created on 14 janvier 2011, 11:51
 */

#ifndef USERTHREAD_H
#define	USERTHREAD_H

typedef struct serialFunct {
    int addrFunc;
    int arg;
    int addrExitThread;
} serialFunct_s, *serial_Funct_p;

static void StartUserThread(int functionAndArg);

extern int do_createUserThread(int f, int arg, int addrExitThread);
extern void do_UserThreadExit();


#endif	/* USERTHREAD_H */

