package com.thoughtworks.io;

public enum CopyCommands {
    WinModern("cmd /c robocopy {0} {1} /E"),
    WinLegacy("cmd /c xcopy {0} {1} /E"),
    Posix("cp -r {0}/. {1}");
    public final String command;

    CopyCommands(String commandString) {
        command = commandString;
    }
}
