package com.thoughtworks.collection.singlelink;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.fest.assertions.api.Assertions.assertThat;

public class IntegerListTest {
    private void addAll(SingleLink<Integer> link, List<Integer> list) {
        list.forEach(link::addTailPointer);
    }

    @Test
    public void test_single_node_linked_list() {
        SingleLink<Integer> link1 = new IntegerList();
        link1.addHeadPointer(1);
        SingleLink<Integer> link2 = new IntegerList();
        link2.addTailPointer(1);
        assertThat(link1.getHeaderData())
                .isEqualTo(link1.getTailData())
                .isEqualTo(link2.getHeaderData())
                .isEqualTo(link2.getTailData());
        link1.deleteLast();
        link2.deleteLast();
        assertThat(link1.size()).isEqualTo(link2.size()).isEqualTo(0);
    }

    @Test
    public void test_empty_linked_list() {
        SingleLink<Integer> link = new IntegerList();
        Random random = new Random();
        assertThat(link.deleteFirst()).isEqualTo(link.deleteLast()).isFalse();
        assertThat(link.delete(random.nextInt())).isFalse();

        assertThat(link.getHeaderData()).isEqualTo(link.getTailData()).isNull();
        assertThat(link.getNodeData(random.nextInt())).isNull();
    }

    @Test
    public void test_invalid_index() {
        SingleLink<Integer> link1 = new IntegerList();
        Random random = new Random();
        addAll(link1, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        int greaterIndex = random.nextInt() + 9;
        int lesserIndex = -random.nextInt() - 1;
        assertThat(link1.delete(greaterIndex)).isEqualTo(link1.delete(lesserIndex)).isFalse();
        assertThat(link1.size()).isEqualTo(10);
        assertThat(link1.getNodeData(greaterIndex)).isEqualTo(link1.getNodeData(lesserIndex)).isNull();
    }

    @Test
    public void test_serial_actions() {
        SingleLink<Integer> link1 = new IntegerList();
        addAll(link1, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        link1.addHeadPointer(0);  // 0 ~ 9
        link1.deleteLast();  // 0 ~ 8
        link1.delete(8);  // 0 ~ 7
        link1.delete(5);// 0~4, 6~7
        link1.deleteFirst(); // 1~4, 6, 7
        link1.delete(0); // 2~4, 6, 7
        link1.addHeadPointer(-1);  // -1, 2, 3, 4, 6, 7
        link1.addTailPointer(-8);  // -1, 2, 3, 4, 6, 7, -8
        assertThat(link1.size()).isEqualTo(7);
        assertThat(link1.getHeaderData()).isEqualTo(link1.getNodeData(0)).isEqualTo(-1);
        assertThat(link1.getTailData()).isEqualTo(link1.getNodeData(6)).isEqualTo(-8);
    }
}
