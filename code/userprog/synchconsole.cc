#ifdef CHANGED

#include <stdlib.h>

#include "../threads/copyright.h"
#include "../threads/system.h"
#include "../threads/synch.h"
#include "synchconsole.h"

static Semaphore *readAvail;
static Semaphore *writeDone;

static void ReadAvail(int arg) {
    readAvail->V();
}

static void WriteDone(int arg) {
    writeDone->V();
}

SynchConsole::SynchConsole(char *readFile, char* writeFile) {

    readAvail = new Semaphore("read avail", 0);
    writeDone = new Semaphore("write done", 0);

    protectIO = new Semaphore("protect IO from Threads", 1);

    console = new Console(readFile, writeFile, ReadAvail, WriteDone, 0);

}

SynchConsole::~SynchConsole() {
    delete console;
    delete writeDone;
    delete readAvail;

}

void SynchConsole::SynchPutChar(const char ch) {
    protectIO->P();
    console->PutChar(ch);
    writeDone->P();
    protectIO->V();
}

char SynchConsole::SynchGetChar() {
    protectIO->P();
    readAvail->P();
    protectIO->V();
    return console->GetChar();

}

void SynchConsole::SynchPutString(const char *s) {

    protectIO->P();
    for (int i = 0; i < MAX_STRING_SIZE; i++) {
        if (*(s + i) == '\0') break;
        console->PutChar(*(s + i));
        writeDone->P();
    }
    protectIO->V();
}

void SynchConsole::SynchGetString(char *buffer, int n) {
    protectIO->P();
    int i = 0;
    // recover the string from the standard input
    readAvail->P();
    buffer[i] = console->GetChar();
    while(buffer[i] != '\n' && i < n - 1 && buffer[i] != EOF) {
        readAvail->P();
        buffer[++i] = console->GetChar();
    }
    buffer[i+1] = '\0';
    protectIO->V();
}

void SynchConsole::SynchPutInt(int n) {
    char buffer[MAX_INT_SIZE + 2] = {'0'};
    snprintf(buffer, MAX_INT_SIZE + 2, "%d", n);
    this->SynchPutString(buffer);
}//

int SynchConsole::SynchGetInt() {
    char buffer[MAX_INT_SIZE + 2];
    // use the getString to read from the console the needed value
    this->SynchGetString(buffer, MAX_INT_SIZE + 2);
    int toReturn;
    // store the value in to the memory
    sscanf(buffer, "%d", &toReturn);
    return toReturn;
}//

void SynchConsole::CopyStringFromMachine(int from, char *to, unsigned int size) {
    unsigned int i;
    int tmp;
    for (i = 0; i < size; i++) {
        machine->ReadMem(from+i,1,&tmp);
        to[i] = (char) tmp;
        if (to[i] == '\0') break;
    }
    to[i] = '\0';
}

#endif
