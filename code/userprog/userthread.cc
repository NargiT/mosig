#ifdef CHANGED

#include "../threads/copyright.h"
#include "../threads/system.h"
#include "userthread.h"

static void StartUserThread(int functionAndArg) {
    int addrOfFunction = ((serial_Funct_p) functionAndArg)->addrFunc;
    int arg = ((serial_Funct_p) functionAndArg)->arg;
    int addrExitThread = ((serial_Funct_p) functionAndArg)->addrExitThread;
    // use current thread to intialize the right adresse space
    currentThread->space->InitUserRegisters(addrOfFunction, arg, addrExitThread);
    machine->Run();
}

int do_createUserThread(int f, int arg, int addrExitThread) {
    Thread *newThread = new Thread("newThread");
    // Serialize
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

    // Generate a new thread id for the user 
    // Set the new ID of the Thread from the bitmap
    newThread->setID(bitmap_id);

    // critical section ends
    newThread->space->manageThreadsSem->V();

    newThread->space->manageJoinSem->P();
    // Do bunch of stuff to keep the structure synchronized
    newThread->space->AddJoin(bitmap_id, newThread->CreateSemaphore());
    newThread->space->manageJoinSem->V();

    return newThread->getID();
}

void do_UserThreadExit() {
    // delete the join
    //currentThread->space->manageJoinSem->P();
    // the current Thread signal the Threads which want to join
    currentThread->ServeAndRemove();
    //currentThread->space->manageJoinSem->V();

    // delete the thread it self
    // lock manageThreads to clear the current thread from the bitmap
    currentThread->space->manageThreadsSem->P();
    currentThread->DestroyAndRemove();
    currentThread->space->manageThreadsSem->V();

    // release the main if needed
    // release the lock if the last threads is main
    if (currentThread->space->MainRemains())
        currentThread->space->exitSem->V();

    // terminate
    currentThread->Finish();
}

void do_UserThreadJoin(int id_to_join) {
    // find the id_to_join and save it's bitmap ID
    int bitmap_id = currentThread->space->FindBitMapIndex(id_to_join);
    // if already terminated then leave
    if (bitmap_id != -1) {
        currentThread->space->manageJoinSem->P();
        currentThread->space->thread_join->nbWaiter[bitmap_id - 1]++;
        currentThread->space->manageJoinSem->V();
        //currentThread->space->thread_join->join[bitmap_id - 1]->V();

    }
}
#endif
