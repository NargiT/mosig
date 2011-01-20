#ifdef CHANGED

#include "../threads/copyright.h"
#include "../threads/system.h"
#include "userthread.h"

static void StartUserThread(int functionAndArg) {
    int addrOfFunction = ((serial_Funct_p) functionAndArg)->addrFunc;
    int arg = ((serial_Funct_p) functionAndArg)->arg;
    int addrExitThread = ((serial_Funct_p) functionAndArg)->addrExitThread;
    // use current thread to intialize the right adresse space
    currentThread->space->InitUserRegisters(addrOfFunction, arg,addrExitThread);
    machine->Run();


}

int do_createUserThread(int f, int arg, int addrExitThread) {
    
    Thread *newThread = new Thread("newThread");
    serial_Funct_p structFunction = new serialFunct_s;
    structFunction->addrFunc = f;
    structFunction->arg = arg;
    structFunction->addrExitThread = addrExitThread;       
    int id = -1;

    currentThread->space->manageThreadsSem->P();
    // find and mark the free bit otherwise return -1
    if ((id = currentThread->space->manageThreads->Find()) == -1) {
        DEBUG('a', "do_createUserThread cannot create an additional thread.\n");
        printf("cannot create a thread.");
        currentThread->space->manageThreadsSem->V();
        return -1;
    }
    newThread->Fork(StartUserThread, (int) structFunction);
    newThread->space->IncrementNbThread();
    if (newThread->space->NbRunningThreads() == 1)
        newThread->space->exitSem->P();
    newThread->space->manageThreadsSem->V();

    newThread->setID(id);
    return id;
}

void do_UserThreadExit() {
    int id = currentThread->getID();
    // lock manageThreads to clear the current thread from the bitmap
    currentThread->space->manageThreadsSem->P();
    currentThread->space->manageThreads->Clear(id);
    currentThread->space->DecrementNbThread();
    // release the lock if the last threads is main
    if (currentThread->space->MainRemains())
       currentThread->space->exitSem->V();
    currentThread->space->manageThreadsSem->V();
    
    currentThread->Finish();
}


#endif
