package com.thoughtworks.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class TreeCopyVisitor extends SimpleFileVisitor<Path> {
    private final Path fromPath;
    private final Path toPath;

    public TreeCopyVisitor(Path fromPath, Path toPath) {
        this.fromPath = fromPath;
        this.toPath = toPath;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path targetDirPath = toPath.resolve(fromPath.relativize(dir));
        if (!Files.exists(targetDirPath)) {
            Files.createDirectory(targetDirPath);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Files.copy(file, toPath.resolve(fromPath.relativize(file)));
        return FileVisitResult.CONTINUE;
    }
}
