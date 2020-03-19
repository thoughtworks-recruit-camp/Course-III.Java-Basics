package com.thoughtworks.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InverseReduceTest {
    @Test
    public void should_be_divided_to_smaller_number() {
        //根据给定数字无限分割至等于0(提示：9-2=7,7-2=5 ...)
        Random random = mock(Random.class);
        when(random.nextInt(3)).thenReturn(2);

        InverseReduce inverseReduce = new InverseReduce(random);

        Integer[] result = new Integer[]{7, 5, 3, 1};
        List<Integer> resultList = Arrays.asList(result);

        assertThat(inverseReduce.divideToSmaller(9)).isEqualTo(resultList);
        assertThat(inverseReduce.divideToSmallerAlt(9)).isEqualTo(resultList);
    }
}

