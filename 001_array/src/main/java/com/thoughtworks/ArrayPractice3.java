package com.thoughtworks;

import java.util.Arrays;

public class ArrayPractice3 {

  /**
   * 完成本方法实现功能: 将一个整型数组array中值0的元素去掉，并把剩下的元素组成新的数组
   */
  public static int[] filterZero(int[] array) {
    int[] result = new int[array.length];
    int curIndex = 0;
    int itemCount = 0;
    for (int value : array) {
      if (value != 0) {
        result[curIndex] = value;
        curIndex++;
        itemCount++;
      }
    }
    return Arrays.copyOfRange(result, 0, itemCount);
  }
}
