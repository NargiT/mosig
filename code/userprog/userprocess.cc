//#ifdef CHANGED

#include "../threads/copyright.h"
#include "../threads/system.h"
#include "userprocess.h"
#include "addrspace.h"

static void StartUserProcess() {
    currentThread->space->InitRegisters(); // set the initial register values
    //currentThread->space->RestoreState(); // load page table register
    //currentThread->RestoreUserState()
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
    new_process->SaveUserState();
    new_process->space->SaveState();

    new_process->ForkProcess(StartUserProcess);
}

//#endif


