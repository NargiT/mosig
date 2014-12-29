//#ifdef CHANGED

#include "../threads/copyright.h"
#include "../threads/system.h"
#include "userprocess.h"
#include "addrspace.h"

static void StartUserProcess() {
    currentThread->space->InitRegisters(); // set the initial register values
    machine->Run();
}

extern void do_ForkExec(char* filename) {
    
    OpenFile *executable = fileSystem->Open(filename);
    AddrSpace *space;

    if (executable == NULL) {
        printf("Unable to open file %s\n", filename);
        return;
    }
    space = new AddrSpace(executable);
    delete executable;
    Thread *new_process = new Thread("new process");
    new_process->space = space;

    // to avoid halting when other are still executed
    machine->frameProvider->numProcessesSem->P();   
    if (machine->frameProvider->getNumProcesses() == 2)
        machine->frameProvider->DisableHalt();
    machine->frameProvider->numProcessesSem->V();

    currentThread->SaveUserState();
    currentThread->space->RestoreState();
    new_process->ForkProcess(StartUserProcess);
    currentThread->RestoreUserState();

}

//#endif


