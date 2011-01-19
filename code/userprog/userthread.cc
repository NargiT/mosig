#ifdef CHANGED

#include "../threads/copyright.h"
#include "../threads/system.h"
#include "userthread.h"

static void StartUserThread(int functionAndArg) {
    int AddrOfFunction = ((serial_Funct_p) functionAndArg)->addrFunc;
    int arg = ((serial_Funct_p) functionAndArg)->arg;

    // use current thread to intialize the right adresse space
    currentThread->space->InitUserRegisters(AddrOfFunction, arg);
    machine->Run();


}

int do_createUserThread(int f, int arg) {
    Thread *newThread = new Thread("newThread");
    serial_Funct_p structFunction = new serialFunct_s;
    structFunction->addrFunc = f;
    structFunction->arg = arg;
    newThread->Fork(StartUserThread, (int) structFunction);

    int id = -1;

    newThread->space->manageThreadsSem->P();
    // find and mark the free bit otherwise return -1
    if ((id = newThread->space->manageThreads->Find()) == -1) {
        DEBUG('a', "do_createUserThread cannot create an additional thread.\n");
        return -1;
    }
    newThread->space->manageThreadsSem->V();

    newThread->setID(id);
    return id;
}

void do_UserThreadExit() {
    int id = currentThread->getID();
    // lock manageThreads to clear the current thread from the bitmap
    currentThread->space->manageThreadsSem->P();
    currentThread->space->manageThreads->Clear(id);
    // release the lock
    currentThread->space->manageThreadsSem->V();

    currentThread->Finish();
}


#endif
