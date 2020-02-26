package com.thoughtworks.io;

public enum CopyCommands {
    WinModern("cmd /c robocopy {0} {1} /MIR"),
    WinLegacy("cmd /c rmdir /S /Q {1} & xcopy {0} {1} /E"),
    Posix("rm -rf {1} && cp -r {0}/. {1}");
    public final String command;

    CopyCommands(String commandString) {
        command = commandString;
    }
}
