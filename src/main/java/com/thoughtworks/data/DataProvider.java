package com.thoughtworks.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DataProvider {

    private static List<Dish> getDishes() {
        return Arrays.asList(
                new Dish("ITEM0001", "黄焖鸡", 18.00),
                new Dish("ITEM0013", "肉夹馍", 6.00),
                new Dish("ITEM0022", "凉皮", 8.00),
                new Dish("ITEM0030", "冰粉", 2.00));
    }

    private static List<String> getHalfDishIds() {
        return Arrays.asList("ITEM0001", "ITEM0022");
    }

    public static HashSet<String> getHalfDishIdsSet() {
        return new HashSet<>(getHalfDishIds());
    }

    public static HashMap<String, Dish> getIdDishMap() {
        HashMap<String, Dish> idDishMap = new HashMap<>();
        for (Dish dish : getDishes()) {
            idDishMap.put(dish.getId(), dish);
        }
        return idDishMap;
    }
}
