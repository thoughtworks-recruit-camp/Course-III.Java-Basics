package com.thoughtworks.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;

public class FileUtil {
    public static void copyDirectory(File from, File to) {
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        if (osName.contains("Windows") && Double.parseDouble(osVersion) >= 15.2) {  // Windows 2003 & above OSs
            runCommand(Commands.WinModern, from, to);
        } else if (osName.contains("Windows")) {  // Legacy Windows OSs
            runCommand(Commands.WinClean, from, to);
            runCommand(Commands.WinCopy, from, to);
        } else if (osName.contains("Linux") || osName.contains("Mac")) {  // POSIX OSs
            runCommand(Commands.PosixClean, from, to);
            runCommand(Commands.PosixCopy, from, to);
        } else {  // Java Implementation
            copyDirectoryUniversal(from, to);
        }
    }

    private static void runCommand(Commands copyCommand, File from, File to) {
        try {
            Runtime.getRuntime().exec(MessageFormat.format(copyCommand.command, from, to))
                    .waitFor();  //for tests
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyDirectoryUniversal(File from, File to) {
        if (to.exists())
            try {
                Files.walkFileTree(to.toPath(), new DirDeleteVisitor(to.toPath()));
                Files.walkFileTree(from.toPath(), new TreeCopyVisitor(from.toPath(), to.toPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
