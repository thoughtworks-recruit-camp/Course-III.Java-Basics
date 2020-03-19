package com.thoughtworks.collection;

import com.thoughtworks.collection.singlelink.IntegerList;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ReduceTest {

    @Test
    public void should_get_maximum_of_list() {
        // 获取数组中的最大值
        Integer[] array = new Integer[]{1, 5, 7, 2, 8, 9, 3, 2};
        List<Integer> arrayList = Arrays.asList(array);

        Reduce reduce = new Reduce(arrayList);

        assertThat(reduce.getMaximum()).isEqualTo(9);
    }

    @Test
    public void should_get_minimum_of_list() {
        //获取数组中的最小值
        Integer[] array = new Integer[]{9, 4, 5, 10, 34, 2, 1, 10, 16};
        List<Integer> arrayList = Arrays.asList(array);

        Reduce reduce = new Reduce(arrayList);

        assertThat(reduce.getMinimum()).isEqualTo(1);
    }

    @Test
    public void should_get_average_of_list() {
        // 获取数组的平均值
        Integer[] array = new Integer[]{12, 34, 56, 78, 90, 21};
        List<Integer> arrayList = Arrays.asList(array);

        Reduce reduce = new Reduce(arrayList);

        assertThat(reduce.getAverage()).isEqualTo(48.5);
    }

    @Test
    public void should_get_element_in_middle_position_with_order_elements() {
        // 获取数组中位数
        Integer[] array = new Integer[]{1, 1, 1, 2, 3};
        List<Integer> arrayList = Arrays.asList(array);
        Reduce reduce = new Reduce(arrayList);

        assertThat(reduce.getOrderedMedian()).isEqualTo(1);


        Integer[] evenArray = new Integer[]{1, 1, 2, 3};
        List<Integer> EvenArrayList = Arrays.asList(evenArray);
        Reduce evenReduce = new Reduce(EvenArrayList);

        assertThat(evenReduce.getOrderedMedian()).isEqualTo(1.5);
    }

    @Test
    public void should_get_element_in_middle_position_in_linkList() {
        //获取单链表中的中位数
        Integer[] array = new Integer[]{1, 4, 6, 2, 3, 10, 9, 8, 11, 2, 19, 30};

        assertThat(new Reduce(Arrays.asList(array))
                .getMedianInLinkList(new IntegerList()))
                .isEqualTo(9.5);

        assertThat(new Reduce(Arrays.asList(Arrays.copyOf(array, 11)))
                .getMedianInLinkList(new IntegerList()))
                .isEqualTo(10);
    }

    @Test
    public void should_return_first_even_element() {
        //获取数组中第一个偶数
        Integer[] array = new Integer[]{1, 11, 27, 20, 4, 9, 15};
        List<Integer> arrayList = Arrays.asList(array);

        Reduce reduce = new Reduce(arrayList);
        assertThat(reduce.getFirstEven()).isEqualTo(20);
    }

    @Test
    public void should_return_index_of_first_even_element() {
        //获取数组中第一个偶数的下标
        Integer[] array = new Integer[]{1, 11, 27, 20, 4, 9, 15, 4, 1, 11};
        List<Integer> arrayList = Arrays.asList(array);

        Reduce reduce = new Reduce(arrayList);
        assertThat(reduce.getIndexOfFirstEven()).isEqualTo(3);
    }

    @Test
    public void should_return_last_even_element() {
        //获取数组中最后一个奇数
        Integer[] array = new Integer[]{1, 11, 27, 20, 4, 9, 15};
        List<Integer> arrayList = Arrays.asList(array);

        Reduce reduce = new Reduce(arrayList);
        assertThat(reduce.getLastOdd()).isEqualTo(15);
    }

    @Test
    public void should_return_index_of_last_even_element() {
        //获取数组中最后一个奇数的下标
        Integer[] array = new Integer[]{1, 4, 27, 20, 4, 9, 15, 4, 1, 11};
        List<Integer> arrayList = Arrays.asList(array);

        Reduce reduce = new Reduce(arrayList);
        assertThat(reduce.getIndexOfLastOdd()).isEqualTo(9);
    }

    @Test
    public void can_judge_whether_is_equal() {
        //判断两个数组是否相等5
        Integer[] array = new Integer[]{1, 4, 27, 20, 4, 9, 15, 4, 1, 11};
        List<Integer> arrayList = Arrays.asList(array);

        Integer[] differentArray = new Integer[]{1, 4, 27, 20, 4, 9, 15, 4, 1};
        List<Integer> differentArrayList = Arrays.asList(differentArray);

        Reduce reduce = new Reduce(arrayList);
        assertThat(reduce.isEqual(arrayList)).isEqualTo(true);
        assertThat(reduce.isEqual(differentArrayList)).isEqualTo(false);
    }
}
