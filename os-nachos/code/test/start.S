/* Start.s 
 *	Assembly language assist for user programs running on top of Nachos.
 *
 *	Since we don't want to pull in the entire C library, we define
 *	what we need for a user program here, namely Start and the system
 *	calls.
 */

#include "syscall.h"

        .text   
        .align  2

/* -------------------------------------------------------------
 * __start
 *	Initialize running a C program, by calling "main". 
 *
 * 	NOTE: This has to be first, so that it gets loaded at location 0.
 *	The Nachos kernel always starts a program by jumping to location 0.
 * -------------------------------------------------------------
 */

	.globl __start
	.ent	__start
__start:
	jal	main
	move	$4,$0		
	jal	Exit	 /* if we return from main, exit(0) */
	.end __start

/* -------------------------------------------------------------
 * System call stubs:
 *	Assembly language assist to make system calls to the Nachos kernel.
 *	There is one stub per system call, that places the code for the
 *	system call into register r2, and leaves the arguments to the
 *	system call alone (in other words, arg1 is in r4, arg2 is 
 *	in r5, arg3 is in r6, arg4 is in r7)
 *
 * 	The return value is in r2. This follows the standard C calling
 * 	convention on the MIPS.
 * -------------------------------------------------------------
 */

	.globl Halt
	.ent	Halt
Halt:
	addiu $2,$0,SC_Halt
	syscall
	j	$31
	.end Halt

	.globl Exit
	.ent	Exit
Exit:
	addiu $2,$0,SC_Exit
	syscall
	j	$31
	.end Exit

	.globl Exec
	.ent	Exec
Exec:
	addiu $2,$0,SC_Exec
	syscall
	j	$31
	.end Exec

	.globl Join
	.ent	Join
Join:
	addiu $2,$0,SC_Join
	syscall
	j	$31
	.end Join

	.globl Create
	.ent	Create
Create:
	addiu $2,$0,SC_Create
	syscall
	j	$31

	.end Create

	.globl Open
	.ent	Open
Open:
	addiu $2,$0,SC_Open
	syscall
	j	$31
	.end Open

	.globl Read
	.ent	Read
Read:
	addiu $2,$0,SC_Read
	syscall
	j	$31
	.end Read

	.globl Write
	.ent	Write
Write:
	addiu $2,$0,SC_Write
	syscall
	j	$31
	.end Write

	.globl Close
	.ent	Close
Close:
	addiu $2,$0,SC_Close
	syscall
	j	$31
	.end Close

	.globl Fork
	.ent	Fork
Fork:
	addiu $2,$0,SC_Fork
	syscall
	j	$31
	.end Fork

	.globl Yield
	.ent	Yield
Yield:
	addiu $2,$0,SC_Yield
	syscall
	j	$31
	.end Yield

	.globl PutChar
	.ent	PutChar
#ifdef CHANGED
PutChar:
	addiu $2,$0,SC_PutChar // pass the parameter SC_PutChar to reg2
	syscall // call the system
	j	$31 // jump to the return adress
	.end PutChar

	.globl PutString
	.ent	PutString
PutString:
	addiu $2,$0,SC_PutString // pass the parameter SC_PutString to reg2
	syscall // call the system
	j	$31 // jump to the return adress
	.end PutString

        .globl GetChar
	.ent	GetChar

GetChar:
	addiu $2,$0,SC_GetChar // pass the parameter SC_GetChar to reg2
	syscall // call the system
	j	$31 // jump to the return adress
	.end GetChar

        .globl GetString
	.ent	GetString

GetString:
	addiu $2,$0,SC_GetString // pass the parameter SC_GetChar to reg2
	syscall // call the system
	j	$31 // jump to the return adress
	.end GetString

        .globl PutInt
	.ent	PutInt

PutInt:
	addiu $2,$0,SC_PutInt // pass the parameter SC_PutInt to reg2
	syscall // call the system
	j	$31 // jump to the return adress
	.end PutInt

        .globl GetInt
	.ent	GetInt

GetInt:
	addiu $2,$0,SC_GetInt // pass the parameter SC_GetInt to reg2
	syscall // call the system
	j	$31 // jump to the return adress
	.end GetInt

        .globl UserThreadCreate
        .ent	UserThreadCreate

UserThreadCreate:
        addiu $2,$0,SC_UserThreadCreate // pass the parameter SC_UserThreadCreate to reg2
        addiu $6,$0,UserThreadExit
	syscall // call the system
	j	$31 // jump to the return adress
	.end UserThreadCreate

        .globl UserThreadJoin
	.ent	UserThreadJoin

UserThreadJoin:
	addiu $2,$0,SC_UserThreadJoin // pass the parameter SC_UserThreadJoin to reg2
	syscall // call the system
	j	$31 // jump to the return adress
	.end UserThreadJoin

        .globl UserThreadExit
	.ent	UserThreadExit

UserThreadExit:
	addiu $2,$0,SC_UserThreadExit // pass the parameter SC_UserThreadExit to reg2
	syscall // call the system
	j	$31 // jump to the return adress
	.end UserThreadExit

       .globl ForkExec
	.ent	ForkExec

ForkExec:
	addiu $2,$0,SC_ForkExec // pass the parameter SC_ForkExec to reg2
	syscall // call the system
	j	$31 // jump to the return adress
	.end ForkExec

#endif
/* dummy function to keep gcc happy */
        .globl  __main
        .ent    __main
__main:
        j       $31
        .end    __main

