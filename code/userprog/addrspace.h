// addrspace.h 
//      Data structures to keep track of executing user programs 
//      (address spaces).
//
//      For now, we don't keep any information about address spaces.
//      The user level CPU state is saved and restored in the thread
//      executing the user program (see thread.h).
//
// Copyright (c) 1992-1993 The Regents of the University of California.
// All rights reserved.  See copyright.h for copyright notice and limitation 
// of liability and disclaimer of warranty provisions.
#ifndef ADDRSPACE_H
#define ADDRSPACE_H

#include "../threads/copyright.h"
#include "../filesys/filesys.h"

#include "bitmap.h"
#include "../threads/synch.h"


#define UserStackSize		1024	// increase this as necessary!
#ifdef CHANGED
#define MAX_NUMBER_THREADS 3 // Maximum number of threads
// at the same time (+main)
class Semaphore;
#endif


class AddrSpace {
public:
    AddrSpace(OpenFile * executable); // Create an address space,
    // initializing it with the program
    // stored in the file "executable"
    ~AddrSpace(); // De-allocate an address space

    void InitRegisters(); // Initialize user-level CPU registers,
    // before jumping to user code
#ifdef CHANGED
    int InitUserRegisters(int addFunc, int arg, int addrExitThread); // Initialize user-level thread CPU registers
    BitMap *manageThreads; // Managed multiple threading
    Semaphore *manageThreadsSem;
#endif // CHANGED
    void SaveState(); // Save/restore address space-specific
    void RestoreState(); // info on a context switch
    void IncrementNbThread();
    void DecrementNbThread();
private:
    TranslationEntry * pageTable; // Assume linear page table translation
    // for now!
    unsigned int numPages; // Number of pages in the virtual
    // address space
#ifdef CHANGED
    int StartStack;
    int nbCurThread;
#endif
};

#endif // ADDRSPACE_H
