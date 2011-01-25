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

    typedef struct idArray {
        int threadID[MAX_NUMBER_THREADS + 1]; // user thread ids (main included)
        BitMap *stackID; // manage the stack of multiple ids (main included)
    } idArray_s, *idArray_p;

    typedef struct idJoin {
        // join[] and nbWaiter[] exclude main function
        Semaphore* join[MAX_NUMBER_THREADS]; // array of semaphore to permit the USER THREAD JOIN
        // each semaphore has a number of threads waiting, a user thread cannot
        // wait for main (main excluded)
        int nbWaiter[MAX_NUMBER_THREADS];
    } idJoin_s, *idJoin_p;

public:
    AddrSpace(OpenFile * executable); // Create an address space,
    // initializing it with the program
    // stored in the file "executable"
    ~AddrSpace(); // De-allocate an address space

    void InitRegisters(); // Initialize user-level CPU registers,
    // before jumping to user code
#ifdef CHANGED
    void InitUserRegisters(int addFunc, int arg, int addrExitThread); // Initialize user-level thread CPU registers

    idArray_p thread_management; // Keep track of stack and ids of all threads (main included)
    Semaphore *manageThreadsSem; // Semaphore to protect thread_management

    idJoin_p thread_join; // Keep track of all the USER THREAD JOIN (main excluded)
    Semaphore *manageJoinSem; // Semaphore to protect thread_join

    Semaphore *exitSem; // To let the main function exit the program
#endif // CHANGED
    void SaveState(); // Save/restore address space-specific
    void RestoreState(); // info on a context switch
#ifdef CHANGED
    // used by idArray
    void IncrementNbThread();
    void DecrementNbThread();

    /*
     * Check if the last remaining threads is main.
     * If so returns ture otherwise false
     * used by USER THREAD EXIT
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
    int generatePrivateID();
    
    /*
     * Set up a new join at index index with the semaphore lock
     */
    void AddJoin(int index, Semaphore *lock);

    /*
     * Remove the join at index index
     */
    void RemoveJoin(int index);

    /*
     * Return the index in the set from the private id
     * -1 if not present
     */
    int FindBitMapIndex(int id);
#endif // CHANGED

#ifdef CHANGED
protected:
    void CreateIdArray();
    void CreateIdJoin();
#endif // CHANGED
private:
    TranslationEntry * pageTable; // Assume linear page table translation
    // for now!
    unsigned int numPages; // Number of pages in the virtual
    // address space
#ifdef CHANGED
    static void ReadAtVirtual(OpenFile *executable, int virtualaddr, int numBytes, int position, TranslationEntry *pageTable, unsigned numPages);
    int StartStack;
    int nbCurThread; // Number of threads running
    int lastThreadID; // The last thread id given
#endif
};

#endif // ADDRSPACE_H
