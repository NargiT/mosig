#ifdef CHANGED

#ifndef SYNCHCONSOLE_H
#define SYNCHCONSOLE_H

#include "../threads/copyright.h"
#include "../threads/utility.h"
#include "../machine/console.h"

class SynchConsole {
public:
    SynchConsole(char *readFile, char *writeFile); // initialize the harware console device

    ~SynchConsole(); // clean up the console emulation

    void SynchPutChar(const char ch); // Unix putchar(3S)
    char SynchGetChar(); // Unix getchar(3S)

    void SynchPutString(const char *); // Unix puts(3S)
    void SynchGetString(char *s, int n); // Unix fgets(3S)

    void SynchPutInt(int n); //
    void SynchGetInt(int *n); //    
    void CopyStringFromMachine(int from, char *to, unsigned size);



private:
    Console *console;
    
};

#endif // SYNCHCONSOLE_H

#endif // CHANGED
