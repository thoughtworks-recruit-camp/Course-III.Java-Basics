package com.thoughtworks;

import java.util.Arrays;

public class ArrayPractice2 {

  /**
   * 把给定数组中的最大值保存到数组中的第1个元素且原第一个数换到最大值的位置
   */
  public static int[] exchange() {
    int[] array = new int[]{10, 8, 1, 7, 0, 20, 16, 19};
    int maxValue = 0;
    int maxIndex = 0;
    int swap = 0;
    for (int i = 0, len = array.length; i < len; i++) {
      if (array[i] > maxValue) {
        maxIndex = i;
        maxValue = array[i];
      }
    }
    swap = array[0];
    array[0] = maxValue;
    array[maxIndex] = swap;
    return array;
  }
}
