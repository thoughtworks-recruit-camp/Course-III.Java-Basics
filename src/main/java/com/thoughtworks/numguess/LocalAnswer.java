package com.thoughtworks.numguess;

import com.thoughtworks.numguess.exceptions.InvalidFormat;
import com.thoughtworks.numguess.exceptions.InvalidLocalAnswer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

final class LocalAnswer extends Answer {

    LocalAnswer(File answerFile, int answerLen) throws InvalidLocalAnswer {
        try (BufferedReader reader = new BufferedReader(new FileReader(answerFile))) {
            literal = reader.readLine();
            digits = Validator.toDigits(literal, answerLen);
        } catch (InvalidFormat e) {
            throw new InvalidLocalAnswer(e);
        } catch (FileNotFoundException e) {
            throw new InvalidLocalAnswer(new FileNotFoundException(String.format("Answer file not found: %s", answerFile)));
        } catch (IOException e) {
            throw new InvalidLocalAnswer(new IOException(String.format("Failed loading answer file: %s", answerFile)));
        }
    }
}
