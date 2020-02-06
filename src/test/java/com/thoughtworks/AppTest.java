package com.thoughtworks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AppTest {

  @Test
  public void bestCharge_should_use_half_promotion() {
    String result = App.bestCharge("ITEM0001 x 1,ITEM0013 x 2,ITEM0022 x 1");

    String excepted = "============= 订餐明细 =============\n"
        + "黄焖鸡 x 1 = 18元\n"
        + "肉夹馍 x 2 = 12元\n"
        + "凉皮 x 1 = 8元\n"
        + "-----------------------------------\n"
        + "使用优惠:\n"
        + "指定菜品半价(黄焖鸡，凉皮)，省13元\n"
        + "-----------------------------------\n"
        + "总计：25元\n"
        + "===================================";
    assertEquals(excepted, result);
  }

  @Test
  public void bestCharge_should_use_full_discount_promotion() {
    String result = App.bestCharge("ITEM0013 x 4,ITEM0022 x 1");

    String excepted = "============= 订餐明细 =============\n"
        + "肉夹馍 x 4 = 24元\n"
        + "凉皮 x 1 = 8元\n"
        + "-----------------------------------\n"
        + "使用优惠:\n"
        + "满30减6元，省6元\n"
        + "-----------------------------------\n"
        + "总计：26元\n"
        + "===================================";
    assertEquals(excepted, result);
  }

  @Test
  public void bestCharge_should_not_use_promotion() {
    String result = App.bestCharge("ITEM0013 x 4");

    String excepted = "============= 订餐明细 =============\n"
        + "肉夹馍 x 4 = 24元\n"
        + "-----------------------------------\n"
        + "总计：24元\n"
        + "===================================";
    assertEquals(excepted, result);
  }
}
