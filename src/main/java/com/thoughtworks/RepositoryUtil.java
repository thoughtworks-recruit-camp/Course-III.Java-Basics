package com.thoughtworks;

import java.util.Collection;

public class RepositoryUtil {
    public static void printList(Collection<?> list) {
        for (Object element : list) {
            System.out.println(element);
        }
    }
}
