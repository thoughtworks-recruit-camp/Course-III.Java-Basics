package com.thoughtworks;

import com.thoughtworks.data.DataProvider;
import com.thoughtworks.data.Dish;
import com.thoughtworks.data.Entry;

import java.util.HashMap;

public class OrderParser {
    private static HashMap<String, Dish> idDishMap = DataProvider.getIdDishMap();

    public static Order parseSelectedItems(String selectedItems) {
        Order order = new Order();
        String[] itemsArray = selectedItems.split(",");
        for (String item : itemsArray) {
            String[] idAndCount = item.split(" x ");
            order.addEntry(new Entry(idDishMap.get(idAndCount[0]), Integer.parseInt(idAndCount[1])));
        }
        return order;
    }
}
