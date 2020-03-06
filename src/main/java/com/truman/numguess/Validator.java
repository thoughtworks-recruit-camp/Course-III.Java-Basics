package com.thoughtworks.numguess;

import com.thoughtworks.numguess.exceptions.InvalidFormat;
import com.thoughtworks.numguess.exceptions.InvalidFormatDigit;
import com.thoughtworks.numguess.exceptions.InvalidFormatChar;
import com.thoughtworks.numguess.exceptions.InvalidFormatLength;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class Validator {
    static List<Integer> toDigits(String input, int len) throws InvalidFormat {
        if (input.length() != len) {  // Validate Length
            throw new InvalidFormatLength(input.length());
        }
        char[] inputChars = input.toCharArray();
        Set<Integer> uniqueDigits = new LinkedHashSet<>();
        for (char inputChar : inputChars) {
            int digit = Character.getNumericValue(inputChar);
            if (digit < 0 || digit > 9) {  // Validate Digit
                throw new InvalidFormatChar(inputChar);
            }
            if (uniqueDigits.contains(digit)) {  // Validate Duplicates
                throw new InvalidFormatDigit(digit);
            }
            uniqueDigits.add(digit);
        }
        return new LinkedList<>(uniqueDigits);
    }
}
