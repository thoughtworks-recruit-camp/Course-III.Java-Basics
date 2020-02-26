package com.thoughtworks.io.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestUtil {

    public static final String TEST_RESOURCE_PATH = "src/test/resources";
    public static final Path FROM_PATH = Paths.get(TEST_RESOURCE_PATH, "from");
    public static final Path TO_PATH = Paths.get(TEST_RESOURCE_PATH, "to");


    public static void assertDirsEqual(File from, File to) throws IOException {
        Files.walkFileTree(from.toPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                FileVisitResult result = super.preVisitDirectory(dir, attrs);

                Path relativize = from.toPath().relativize(dir);
                File otherDir = to.toPath().resolve(relativize).toFile();

                assertArrayEquals(dir.toFile().list(), otherDir.list(),
                        String.format("文件夹下结构不匹配:\n from: %s \n to: %s \n",
                                Arrays.toString(dir.toFile().list()),
                                Arrays.toString(otherDir.list())));
                return result;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                FileVisitResult result = super.visitFile(file, attrs);

                Path relativize = from.toPath().relativize(file);
                Path fileInOther = to.toPath().resolve(relativize);

                byte[] otherBytes = Files.readAllBytes(fileInOther);
                byte[] theseBytes = Files.readAllBytes(file);

                assertArrayEquals(theseBytes, otherBytes,
                        String.format("文件内容不匹配:\n from文件: %s \n to文件: %s \n",
                                file.toString(),
                                fileInOther.toString()));
                return result;
            }
        });
    }

    public static void createFile(Path path, String fileName, String content) throws IOException {
        File file = Paths.get(path.toString(), fileName).toFile();
        createFolder(path);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
        }
    }

    public static void createFolder(Path path) {
        File file = path.toFile();
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void cleanDirectory(File file) {
        if (file.exists()) {
            for (File f : file.listFiles()) {
                if (f.isDirectory()) {
                    cleanDirectory(f);
                } else {
                    f.delete();
                }
            }
        }
        file.delete();
    }


}
