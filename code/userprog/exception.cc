// exception.cc 
//      Entry point into the Nachos kernel from user programs.
//      There are two kinds of things that can cause control to
//      transfer back to here from user code:
//
//      syscall -- The user code explicitly requests to call a procedure
//      in the Nachos kernel.  Right now, the only function we support is
//      "Halt".
//
//      exceptions -- The user code does something that the CPU can't handle.
//      For instance, accessing memory that doesn't exist, arithmetic errors,
//      etc.  
//
//      Interrupts (which can also cause control to transfer from user
//      code into the Nachos kernel) are handled elsewhere.
//
// For now, this only handles the Halt() system call.
// Everything else core dumps.
//
// Copyright (c) 1992-1993 The Regents of the University of California.
// All rights reserved.  See copyright.h for copyright notice and limitation 
// of liability and disclaimer of warranty provisions.

#include "../threads/copyright.h"
#include "../threads/system.h"
#include "syscall.h"
#include "userthread.h"
#include <string>

//----------------------------------------------------------------------
// UpdatePC : Increments the Program Counter register in order to resume
// the user program immediately after the "syscall" instruction.
//----------------------------------------------------------------------

static void
UpdatePC() {
    int pc = machine->ReadRegister(PCReg);
    machine->WriteRegister(PrevPCReg, pc);
    pc = machine->ReadRegister(NextPCReg);
    machine->WriteRegister(PCReg, pc);
    pc += 4;
    machine->WriteRegister(NextPCReg, pc);
}


//----------------------------------------------------------------------
// ExceptionHandler
//      Entry point into the Nachos kernel.  Called when a user program
//      is executing, and either does a syscall, or generates an addressing
//      or arithmetic exception.
//
//      For system calls, the following is the calling convention:
//
//      system call code -- r2
//              arg1 -- r4
//              arg2 -- r5
//              arg3 -- r6
//              arg4 -- r7
//
//      The result of the system call, if any, must be put back into r2. 
//
// And don't forget to increment the pc before returning. (Or else you'll
// loop making the same system call forever!
//
//      "which" is the kind of exception.  The list of possible exceptions 
//      are in machine.h.
//----------------------------------------------------------------------

void
ExceptionHandler(ExceptionType which) {
    int type = machine->ReadRegister(2);
#ifdef CHANGED
    if (which == SyscallException) {
        switch (type) {
            case SC_Halt:
            {
                DEBUG('a', "Shutdown, initiated by user program.\n");
                interrupt->Halt();
                break;
            }
            case SC_Exit:
            {
                DEBUG('a', "SC_Exit - No killing just the the last one will leave");
                currentThread->space->exitSem->P();
                if (false) {
                    NULL; // will later release the ressources (kill)
                } else
                    interrupt->Halt(); //the thread exit
                break;
            }
            case SC_Exec:
            {
                printf("SC_EXEC");
                break;
            }
            case SC_Join:
            {

            }
            case SC_Create:
            {

            }
            case SC_Open:
            {

            }
            case SC_Read:
            {

            }
            case SC_Write:
            {

            }
            case SC_Close:
            {

            }
            case SC_Fork:
            {

            }
            case SC_Yield:
            {

            }
            case SC_PutChar:
            {
                DEBUG('a', "PutChar, initiated by user.\n");
                char c = (char) machine->ReadRegister(4);
                synchconsole->SynchPutChar(c);
                break;
            }
            case SC_PutString:
            {
                DEBUG('a', "PutString, initiated by user.\n");
                char to[MAX_STRING_SIZE];
                synchconsole->CopyStringFromMachine(machine->ReadRegister(4), to, (unsigned int) MAX_STRING_SIZE);
                synchconsole->SynchPutString(to);
                break;
            }
            case SC_GetChar:
            {
                DEBUG('a', "GetChar, initiated by user.\n");
                char ch = synchconsole->SynchGetChar();
                machine->WriteRegister(2, int(ch));
                break;
            }
            case SC_GetString:
            {
                DEBUG('a', "GetString, initiated by user.\n");
                int addr, size;
                char *buffer = new char[MAX_STRING_SIZE];
                addr = machine->ReadRegister(4);
                size = machine->ReadRegister(5);
                buffer = &(machine->mainMemory[addr]);
                synchconsole->SynchGetString(buffer, size);
                buffer = NULL;
                break;
            }
            case SC_PutInt:
            {
                DEBUG('a', "PutInt, initiated by user.\n");
                int value = machine->ReadRegister(4);
                synchconsole->SynchPutInt(value);
                break;
            }
            case SC_GetInt:
            {
                DEBUG('a', "GetInt, initiated by user.\n");
                int val = synchconsole->SynchGetInt();
                machine->WriteMem(machine->ReadRegister(4), 4, val);
                // transfert it to the function
                break;
            }
            case SC_UserThreadCreate:
            {
                DEBUG('a', "UserThreadCreate, initialted by main");
                int addrFunctionToCall = machine->ReadRegister(4);
                int arg = machine->ReadRegister(5);
                int addrExitThread = machine->ReadRegister(6);
                // save the id of the new thread
                int tid = do_createUserThread(addrFunctionToCall, arg, addrExitThread);
                machine->WriteRegister(2, tid);
                // return the id of the thread
                break;
            }
            case SC_UserThreadExit:
            {
                DEBUG('a', "UserThreadExit, initiated by thread `%s` id = %d ", currentThread->getName(), currentThread->getID());
                do_UserThreadExit();
                break;
            }
            case SC_UserThreadJoin:
            {
                DEBUG('a', "UserThreadJoin, initialted by thread `%s` id = %d ", currentThread->getName(), currentThread->getID());
                unsigned int toJoin = machine->ReadRegister(4);
                do_UserThreadJoin(toJoin);
                break;
            }
            case SC_ForkExec:
            {  
                char *buffer = new char[MAX_STRING_SIZE];
                synchconsole->CopyStringFromMachine(machine->ReadRegister(4),buffer,MAX_STRING_SIZE);
                break;
            }
            default:
            {
                printf("Unexpected user mode exception %d %d\n", which, type);
                ASSERT(FALSE);
            }
        }
    }
#else // CHANGED
    if ((which == SyscallException) && (type == SC_Halt)) {
        DEBUG('a', "Shutdown, initiated by user program.\n");
        interrupt->Halt();
    } else {
        printf("Unexpected user mode exception %d %d\n", which, type);
        ASSERT(FALSE);
    }
#endif
    // LB: Do not forget to increment the pc before returning!
    UpdatePC();
    // End of addition
}
