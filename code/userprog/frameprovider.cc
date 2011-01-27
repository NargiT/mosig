#include <stdlib.h>

#include "frameprovider.h"
#include "synch.h"
#include "system.h"
#include <utility>
#include <time.h>

using namespace std;

FrameProvider::FrameProvider(int numPages) {
    srand(time(NULL)); // initialize the random algorithm
    numProcesses = 0;
    numProcessesSem = new Semaphore("Num Processes", 1);
    lastID = 0;
    this->numPages = numPages;
    frameBitMap = new BitMap(this->numPages);
    //addrMap.multimap();
    frameDoor = new Semaphore("Frame door", 1);
    haltSem = new Semaphore("Halt Nachos", 1);
}

FrameProvider::~FrameProvider() {
    delete frameBitMap;
    delete numProcessesSem;
    delete haltSem;
    delete frameDoor;
}

int FrameProvider::GetEmptyframe(int aid) {
    int randomFrame;
    // close the door
    frameDoor->P();
    // Check if there are frames available
    if (this->NumAvailFrame() == 0) {
        frameDoor->V();
        return -1;
    }
    // look until you find one free
    do {
        randomFrame = rand() % this->numPages;
    } while (frameBitMap->Test(randomFrame));
    // mark it as busy
    frameBitMap->Mark(randomFrame);
    // create a new pair
    pair<int, int> aProcess(aid, randomFrame);
    printf("%d __ %d\n", aid, randomFrame);
    // insert the pair in to the multimapping
    addrMap.insert(aProcess);
    bzero( &( machine->mainMemory[ PageSize * randomFrame ] ), PageSize );
    // open the door
    frameDoor->V();
    // return the adress
    return randomFrame;
}

void FrameProvider::ReleaseFrame(int aid) {
    multimap<int, int>::iterator it;
    //close the door
    frameDoor->P();
    /*
     * if empty == true then exit without checking the second member
     * otherwise check the second member
     */
    if (addrMap.empty())
        return;
    if ((it = addrMap.find(aid)) == addrMap.end())
        return; // nothing to release

    // decrement the number of processes
    DecrementNumProcesses();
    while ((it = addrMap.find(aid)) != addrMap.end()) {
        int whichBitMap = (*it).second;
        printf("%d - %d\n",aid, whichBitMap);
        frameBitMap->Clear(whichBitMap);
        addrMap.erase(it);
    }
    // to erase all of them
    //processesMap.erase(pid);
    // open the door
    frameDoor->V();
}

int FrameProvider::NumAvailFrame() {
    return frameBitMap->NumClear();
}

void FrameProvider::IncrementNumProcesses() {
    numProcessesSem->P();
    this->numProcesses++;
    numProcessesSem->V();
}

void FrameProvider::DecrementNumProcesses() {
    numProcessesSem->P();
    this->numProcesses--;
    numProcessesSem->V();
}

int FrameProvider::generateAddrID() {
    // a dum id generation
    frameDoor->P();
    int newID = lastID++;
    frameDoor->V();
    return newID;
}

int FrameProvider::getNumProcesses() {
    return numProcesses;
}

void FrameProvider::TryHalt() {
    if (currentThread->space->getID() == 0) {
        haltSem->P();
        printf("current thread is able to halt --> %d\n", currentThread->space->getID());
        interrupt->Halt();
    } else {
        numProcessesSem->P();
        if (numProcesses == 0)
            EnableHalt();
        numProcessesSem->V();
        currentThread->Finish();
    }
}

void FrameProvider::DisableHalt() {
    haltSem->P();
}

void FrameProvider::EnableHalt() {
    haltSem->V();
}
