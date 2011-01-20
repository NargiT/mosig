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
    console = new Console(readFile, writeFile, ReadAvail, WriteDone, 0);

}

SynchConsole::~SynchConsole() {
    delete console;
    delete writeDone;
    delete readAvail;

}

void SynchConsole::SynchPutChar(const char ch) {
    console->PutChar(ch);
    writeDone->P();
}

char SynchConsole::SynchGetChar() {
    readAvail->P();
    return console->GetChar();
}

void SynchConsole::SynchPutString(const char *s) {

    for (int i = 0; i < MAX_STRING_SIZE; i++) {
        if (*(s + i) == '\0') break;
        console->PutChar(*(s + i));
        console->WriteDone();
    }
    writeDone->P();
}

void SynchConsole::SynchGetString(char *s, int n) {
    readAvail->P();
    int i;
    for (i = 0; i < n; i++) {
        s[i] = console->GetChar();
        console->CheckCharAvail();
    }
}

void SynchConsole::SynchPutInt(int n) {
    char buffer[MAX_INT_SIZE + 2] = {'0'};
    snprintf(buffer, MAX_INT_SIZE + 2, "%d", n);
    this->SynchPutString(buffer);
    //delete[] buffer;
}//

void SynchConsole::SynchGetInt(int *n) {
    char buffer[MAX_INT_SIZE + 2];
    // use the getString to read from the console the needed value
    this->SynchGetString(buffer, MAX_INT_SIZE + 2);
    // store the value in to the memory
    sscanf(buffer, "%d", &n);
}//

void SynchConsole::CopyStringFromMachine(int from, char *to, unsigned int size) {

    unsigned int i;
    for (i = 0; i < size; i++) {
        to[i] = machine->mainMemory[from + i];
        if (to[i] == '\0') break;
    }
    to[i] = '\0';
}

#endif
