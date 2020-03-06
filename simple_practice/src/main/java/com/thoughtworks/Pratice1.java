package com.thoughtworks;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Pratice1 {

    /**
     * 统计字符串中每个字符出现的次数并打印
     * 例如 aabbc 的统计结果为:
     * a出现2次
     * b出现2次
     * c出现1次
     */
    public static void countChar(String string) {
        Map<Character, Integer> counter = new HashMap<>();
        for (Character character : string.toCharArray()) {
            counter.putIfAbsent(character, 0);
            counter.put(character, counter.get(character) + 1);
        }
        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            System.out.printf("%c出现%d次\n", entry.getKey(), entry.getValue());
        }
    }

    public static void countCharAlt(String string) {
        Map<Character, Integer> counter = new HashMap<>();
        string.chars().forEach(c -> counter.put((char) c, counter.getOrDefault((char) c, 0) + 1));
        counter.forEach((key, value) -> System.out.printf("%c出现%d次\n", key, value));
    }

    public static void countCharAlt2(String string) {
        Map<Integer, Long> counter = string.chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        counter.forEach((key, value) -> System.out.printf("%c出现%d次\n", key, value));
    }
}