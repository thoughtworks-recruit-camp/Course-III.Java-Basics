package com.thoughtworks.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;

public class FileUtil {
    public static void copyDirectory(File from, File to) {
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        if (osName.contains("Windows") && Double.parseDouble(osVersion) >= 5.2) {  // Windows 2003 & above OSs
            runCopyCommand(CopyCommands.WinModern, from, to);
        } else if (osName.contains("Windows")) {  // Legacy Windows OSs
            runCopyCommand(CopyCommands.WinLegacy, from, to);
        } else if (osName.contains("Linux") || osName.contains("Mac")) {  // POSIX OSs
            runCopyCommand(CopyCommands.Posix, from, to);
        } else {  // Java Implementation
            copyDirectoryUniversal(from, to);
        }
    }

    private static void runCopyCommand(CopyCommands copyCommand, File from, File to) {
        try {
            Runtime.getRuntime().exec(MessageFormat.format(copyCommand.command, from, to))
                    .waitFor();  //for tests
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyDirectoryUniversal(File from, File to) {
        try {
            Files.walkFileTree(from.toPath(), new TreeCopyVisitor(from.toPath(), to.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
