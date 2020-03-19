package com.thoughtworks.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class AddTest {

    @Test
    public void should_get_sum_of_evens() {
        //求leftBorder 和rightBorder之间的偶数和
        int leftBorder = 1;
        int rightBorder = 10;

        Add add = new Add();
        assertThat(add.getSumOfEvens(leftBorder, rightBorder)).isEqualTo(30);
        assertThat(add.getSumOfEvens(rightBorder, leftBorder)).isEqualTo(30);
    }

    @Test
    public void should_get_sum_of_odds() {
        //求leftBorder 和rightBorder之间的奇数和
        int leftBorder = 1;
        int rightBorder = 10;

        Add add = new Add();
        assertThat(add.getSumOfOdds(leftBorder, rightBorder)).isEqualTo(25);
        assertThat(add.getSumOfOdds(rightBorder, leftBorder)).isEqualTo(25);
    }

    @Test
    public void should_get_triple_and_add_two() {
        //求数组中每个元素的3倍加2的和
        Integer[] array = new Integer[]{1, 5, 7, 11, 35, 67};
        List<Integer> arrayList = Arrays.asList(array);

        Add add = new Add();

        assertThat(add.getSumTripleAndAddTwo(arrayList)).isEqualTo(390);
    }

    @Test
    public void should_get_triple_of_odd_and_add_two() {
        //求数组中奇数元素的3倍加2，偶数元素不变的数组
        Integer[] array = new Integer[]{1, 5, 7, 12, 11, 35, 54, 67, 70};
        List<Integer> arrayList = Arrays.asList(array);

        Integer[] result = new Integer[]{5, 17, 23, 12, 35, 107, 54, 203, 70};
        List<Integer> resultList = Arrays.asList(result);

        Add add = new Add();

        assertThat(add.getTripleOfOddAndAddTwo(arrayList)).isEqualTo(resultList);
    }

    @Test
    public void should_get_sum_of_processed_odd() {
        //求数组中奇数元素的3倍加5的和
        Integer[] array = new Integer[]{1, 5, 7, 12, 11, 35, 54, 67, 70};
        List<Integer> arrayList = Arrays.asList(array);
        Add add = new Add();

        assertThat(add.getSumOfProcessedOdds(arrayList)).isEqualTo(408);
    }

    @Test
    public void should_process_elements() {
        // 数组中前一个元素和后一个元素的和的3倍的数组
        Integer[] array = new Integer[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21};
        List<Integer> arrayList = Arrays.asList(array);

        Add add = new Add();

        Integer[] result = new Integer[]{12, 24, 36, 48, 60, 72, 84, 96, 108, 120};
        List<Integer> resultList = Arrays.asList(result);

        assertThat(add.getProcessedList(arrayList)).isEqualTo(resultList);
    }

    @Test
    public void should_return_median_of_even_index() {
        //求数组中所有偶数组成的数组的中位数
        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        List<Integer> arrayList = Arrays.asList(array);

        Add add = new Add();
        assertThat(add.getMedianOfEvenIndex(arrayList)).isEqualTo((double) 3);
    }

    @Test
    public void should_return_average_of_even_index() {
        //求数组中所有偶数的平均数
        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        List<Integer> arrayList = Arrays.asList(array);

        Add add = new Add();
        assertThat(add.getAverageOfEvenIndex(arrayList)).isEqualTo((double) 3);
    }

    @Test
    public void should_return_whether_include_special_element() {
        // 求数组中所有偶数组成的数组是否包含某特定的数specialElment
        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        List<Integer> arrayList = Arrays.asList(array);

        Add add = new Add();

        Integer existedElment = 3;
        assertThat(add.isIncludedInEvenIndex(arrayList, existedElment)).isEqualTo(false);

        Integer unexistedElment = 2;
        assertThat(add.isIncludedInEvenIndex(arrayList, unexistedElment)).isEqualTo(true);
    }

    @Test
    public void should_return_unrepeated_elements_from_evens() {
        //去除数组中所有偶数组成的数组的重复数
        Integer[] array = new Integer[]{1, 2, 3, 4, 1, 5};
        List<Integer> arrayList = Arrays.asList(array);

        Integer[] result = new Integer[]{2, 4};
        List<Integer> resultList = Arrays.asList(result);

        Add add = new Add();
        assertThat(add.getUnrepeatedFromEvenIndex(arrayList)).isEqualTo(resultList);
    }

    @Test
    public void should_sort_by_even_and_odd() {
        //排序数组 结果使数组偶数在递增在前，奇数递减在后
        Add add = new Add();

        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> arrayList = Arrays.asList(array);

        Integer[] result = new Integer[]{2, 4, 6, 8, 7, 5, 3, 1};
        List<Integer> resultList = Arrays.asList(result);

        assertThat(add.sortByEvenAndOdd(arrayList)).isEqualTo(resultList);
    }
}
