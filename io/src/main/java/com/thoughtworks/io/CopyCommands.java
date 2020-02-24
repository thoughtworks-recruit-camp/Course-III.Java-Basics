package com.thoughtworks.io;

public enum CopyCommands {
    WinModern("cmd /c robocopy %s %s /E"),
    WinLegacy("cmd /c xcopy %s %s /E"),
    Posix("/bin/bash -c cp -r %s %s");
    public final String command;

    CopyCommands(String commandString) {
        command = commandString;
    }
}
