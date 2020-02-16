package com.thoughtworks.promotion;

import com.thoughtworks.CheckOut;
import com.thoughtworks.Order;

public interface Promotion {
    CheckOut makeCheckOut(Order order);
}
