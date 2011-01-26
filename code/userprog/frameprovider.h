
#ifndef FRAMEPROVIDER_H
#define	FRAMEPROVIDER_H

#include "bitmap.h"
#include <map>

class Semaphore;
class FrameProvider {
public:
    FrameProvider(int numPages);
    ~FrameProvider();
    int GetEmptyframe(int pid);
    void ReleaseFrame(int pid);
    int NumAvailFrame();
    int generateAddrID();
    void IncrementNumProcesses();
    void DecrementNumProcesses();
    Semaphore *numProcessesSem;
    int getNumProcesses();
    void TryHalt();
    void DisableHalt();
    void EnableHalt();
private:
    BitMap *frameBitMap;
    std::multimap<int, int> addrMap; // keep track for resources
    int numPages; // keep track of num of availble pages
    int numProcesses; // keep track of how many processes run on the kernel
    Semaphore *frameDoor; // Frame provider is exclusively used
    Semaphore *haltSem; // to let the init main shut down.
    int lastID; // last id generated
};

#endif	/* FRAMEPROVIDER_H */

