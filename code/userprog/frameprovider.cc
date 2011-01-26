#include <stdlib.h>

#include "frameprovider.h"

FrameProvider::FrameProvider(int numPages) {
    this->numPages = numPages;
    frameBitMap = new BitMap(this->numPages);
}

int FrameProvider::GetEmptyframe() {
    int randomFrame;
    if (this->NumAvailFrame() == 0) return -1;
    do {
        randomFrame = rand() % this->numPages;
    } while (frameBitMap->Test(randomFrame));
    frameBitMap->Mark(randomFrame);
    return randomFrame;
}

void FrameProvider::ReleaseFrame(int which) {
    frameBitMap->Clear(which);
}

int FrameProvider::NumAvailFrame() {
    return frameBitMap->NumClear();
}


