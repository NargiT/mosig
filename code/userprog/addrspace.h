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

    typedef struct id_structure {
        int threadID[MAX_NUMBER_THREADS + 1]; // user thread ids
        BitMap *stackID; // manage the stack of multiple ids
    } id_structure_s, *id_structure_p;

public:
    AddrSpace(OpenFile * executable); // Create an address space,
    // initializing it with the program
    // stored in the file "executable"
    ~AddrSpace(); // De-allocate an address space

    void InitRegisters(); // Initialize user-level CPU registers,
    // before jumping to user code
#ifdef CHANGED
    void InitUserRegisters(int addFunc, int arg, int addrExitThread); // Initialize user-level thread CPU registers
    id_structure_p thread_management; // Thread managemend (stack + ids)
    //BitMap *manageThreads; // Managed multiple threading
    Semaphore *manageThreadsSem; // Semaphore to protect the id_strcut_p and nbCurThread
    Semaphore *exitSem; // To let the main function exit the program
#endif // CHANGED
    void SaveState(); // Save/restore address space-specific
    void RestoreState(); // info on a context switch
#ifdef CHANGED
    void IncrementNbThread();
    void DecrementNbThread();

    /*
     * Check if the last remaining threads is main.
     * If so returns ture otherwise false
     */
    bool MainRemains();

    /*
     * returns the number of threads created by the main and
     * currently running
     */
    int NbRunningThreads();

    /*
     * increment the id thread and returns a new (unique) tread id
     */
    int newID();
#endif
private:
    TranslationEntry * pageTable; // Assume linear page table translation
    // for now!
    unsigned int numPages; // Number of pages in the virtual
    // address space
#ifdef CHANGED
    int StartStack;
    int nbCurThread; // Number of threads running
    int lastThreadID; // The last thread id given
#endif
};

#endif // ADDRSPACE_H
