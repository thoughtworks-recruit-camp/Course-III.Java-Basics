package com.thoughtworks.io;

public enum CopyCommands {
    WinModern("cmd /c robocopy {0} {1} /E"),
    WinLegacy("cmd /c xcopy {0} {1} /E"),
    Posix("mkdir -p {1} && cp -rdp {0} {1}");
    public final String command;

    CopyCommands(String commandString) {
        command = commandString;
    }
}
