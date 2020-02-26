package com.thoughtworks.io;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.thoughtworks.io.util.TestUtil.*;

class FileUtilTestUniversal {

    @Test
    void should_copy_correctly_given_from_and_to_both_empty() throws IOException {
        File from = FROM_PATH.toFile();
        File to = TO_PATH.toFile();
        FileUtil.copyDirectoryUniversal(from, to);

        assertDirsEqual(from, to);
    }

    @Test
    void should_copy_correctly_given_from_includes_file() throws IOException {
        File from = FROM_PATH.toFile();
        File to = TO_PATH.toFile();
        createFile(FROM_PATH, "1.txt", "123");

        FileUtil.copyDirectoryUniversal(from, to);

        assertDirsEqual(from, to);
    }

    @Test
    void should_copy_correctly_given_from_includes_file_and_empty_folder() throws IOException {
        File from = FROM_PATH.toFile();
        File to = TO_PATH.toFile();
        createFile(FROM_PATH, "1.txt", "123");
        createFolder(Paths.get(FROM_PATH.toString(), "empty"));

        FileUtil.copyDirectoryUniversal(from, to);

        assertDirsEqual(from, to);
    }

    @Test
    void should_copy_correctly_given_from_includes_file_and_empty_folder_which_includes_file() throws IOException {
        File from = FROM_PATH.toFile();
        File to = TO_PATH.toFile();
        commonCreate();

        FileUtil.copyDirectoryUniversal(from, to);

        assertDirsEqual(from, to);
    }

    @Test
    void should_copy_correctly_given_from_includes_multiple_file() throws IOException {
        File from = FROM_PATH.toFile();
        File to = TO_PATH.toFile();
        commonCreate();
        createFolder(Paths.get(FROM_PATH.toString(), "empty"));
        createFile(FROM_PATH, "3.txt", "000");
        Path musicPath = Paths.get(FROM_PATH.toString(), "music");
        createFile(musicPath, "5.txt", "888");
        createFile(musicPath, "6.txt", "999");


        FileUtil.copyDirectoryUniversal(from, to);

        assertDirsEqual(from, to);
    }

    @Test
    void should_copy_correctly_given_from_and_to_both_includes_file() throws IOException {
        File from = FROM_PATH.toFile();
        File to = TO_PATH.toFile();
        commonCreate();
        createFile(TO_PATH, "8.txt", "888");

        FileUtil.copyDirectoryUniversal(from, to);

        assertDirsEqual(from, to);
    }

    private void commonCreate() throws IOException {
        createFile(FROM_PATH, "1.txt", "123");
        Path workPath = Paths.get(FROM_PATH.toString(), "work");
        createFile(workPath, "2.txt", "456");
    }

    @BeforeEach
    void setUp() {
        createFolder(FROM_PATH);
        createFolder(TO_PATH);
    }

    @AfterEach
    void tearDown() {
        cleanDirectory(new File(TEST_RESOURCE_PATH));
    }

    @AfterAll
    static void afterAll() {
        cleanDirectory(new File(TEST_RESOURCE_PATH));
    }
}