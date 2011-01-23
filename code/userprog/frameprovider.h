
#ifndef FRAMEPROVIDER_H
#define	FRAMEPROVIDER_H

#include "bitmap.h"

class FrameProvider {
public:
    FrameProvider(int numPages);
    ~FrameProvider();
    int GetEmptyframe();
    void ReleaseFrame(int which);
    int NumAvailFrame();
private:
    BitMap *frameBitMap;
    int numPages;
};

#endif	/* FRAMEPROVIDER_H */

