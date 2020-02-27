package com.thoughtworks.io;

public enum Commands {
    WinModern("cmd /c robocopy {0} {1} /MIR"),
    WinClean("cmd /c rmdir /S /Q {1}"),
    WinCopy("cmd /c xcopy {0} {1} /E /C /Y"),
    PosixClean("rm -rf {1}"),
    PosixCopy("cp -r {0} {1}");
    public final String command;

    Commands(String commandString) {
        command = commandString;
    }
}
