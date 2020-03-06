package com.thoughtworks;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Pratice2 {
    private static final String[] PINYINS = "ling yi er san si wu liu qi ba jiu shi".split(" ");

    /**
     * 计算某个整数各位数之和,并用拼音输出
     * 例如 12345
     * 因为 1 + 2 + 3 + 4 + 5 = 15
     * 所以输出 shi wu
     * <p>
     * 汉语拼音:
     * 0~9：ling yi er san si wu liu qi ba jiu shi
     */
    public static String buildCountEachString(long digital) {
        char[] chars = String.valueOf(digital).toCharArray();
        int sum = 0;
        for (char c : chars) {
            sum += Character.getNumericValue(c);
        }

        char[] sumChars = String.valueOf(sum).toCharArray();
        List<String> outputList = new LinkedList<>();
        for (char c : sumChars) {
            outputList.add(PINYINS[Character.getNumericValue(c)]);
        }
        return String.join(" ", outputList);
    }

    public static String buildCountEachStringAlt(long digital) {
        return String.valueOf(String.valueOf(digital).chars().map(Character::getNumericValue).sum())
                .chars().map(Character::getNumericValue).mapToObj(c -> PINYINS[c]).collect(Collectors.joining(" "));
    }
}
