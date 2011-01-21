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
    // function call automatically at the end of the UserThread
    // UserThreadExit
    structFunction->addrExitThread = addrExitThread;       
    int bitmap_id = -1;

    // critical section start
    currentThread->space->manageThreadsSem->P();

    // find and mark the free bit otherwise return -1
//    if ((bitmap_id = currentThread->space->manageThreads->Find()) == -1) {
//        DEBUG('a', "do_createUserThread cannot create an additional thread.\n");
//        currentThread->space->manageThreadsSem->V();
//        return -1;
//    }
    if ((bitmap_id = currentThread->space->thread_management->stackID->Find()) == -1) {
        DEBUG('a', "do_createUserThread cannot create an additional thread.\n");
        currentThread->space->manageThreadsSem->V();
        return -1;
    }

    // Create squelleton of the Thread
    newThread->Fork(StartUserThread, (int) structFunction);
    // Increment number of threads create by the main
    newThread->space->IncrementNbThread();
    // The first created thread, avoid the main to exit without synchronization
    if (newThread->space->NbRunningThreads() == 1)
        newThread->space->exitSem->P();
    
    newThread->setBitMapID(bitmap_id);
    
    // Generate a new thread id for the user
    int id = currentThread->space->newID();
    // Set the new ID of the Thread
    newThread->setID(id);
    // store the new ID in the structure
    newThread->space->thread_management->threadID[bitmap_id] = id;
    
    // critical section ends
    newThread->space->manageThreadsSem->V(); 

    return id;
}

void do_UserThreadExit() {
    int id = currentThread->getBitMapID();
    // lock manageThreads to clear the current thread from the bitmap
    currentThread->space->manageThreadsSem->P();
    currentThread->space->thread_management->threadID[id] = NULL;
    currentThread->space->thread_management->stackID->Clear(id);
    //currentThread->space->manageThreads->Clear(id);
    currentThread->space->DecrementNbThread();
    // release the lock if the last threads is main
    if (currentThread->space->MainRemains())
       currentThread->space->exitSem->V();
    currentThread->space->manageThreadsSem->V();
    
    currentThread->Finish();
}

void do_UserThreadJoin(int id_to_join) {
    ;
}
#endif
