package com.thoughtworks.collection;

import com.thoughtworks.collection.singlelink.SingleLink;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Reduce {

    List<Integer> arrayList;

    public Reduce(List<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public int getMaximum() {
        return arrayList.stream().mapToInt(Integer::intValue)
                .max().orElseThrow(IllegalArgumentException::new);
    }

    public int getMinimum() {
        return arrayList.stream().mapToInt(Integer::intValue)
                .min().orElseThrow(IllegalArgumentException::new);

    }

    public double getAverage() {
        return arrayList.stream().mapToInt(Integer::intValue)
                .average().orElseThrow(IllegalArgumentException::new);
    }

    public double getOrderedMedian() {
        List<Integer> sortedList = arrayList.stream()
                .sorted()
                .collect(Collectors.toList());
        int len = sortedList.size();
        return len % 2 == 0
                ? (double) (sortedList.get(len / 2 - 1) + sortedList.get(len / 2)) / 2
                : (double) sortedList.get((len - 1) / 2);
    }

    public int getFirstEven() {
        return arrayList.stream()
                .filter(x -> x % 2 == 0)
                .findFirst().orElse(-1);
    }

    public int getIndexOfFirstEven() {
        return IntStream.range(0, arrayList.size())
                .filter(i -> arrayList.get(i) % 2 == 0)
                .findFirst().orElse(-1);
    }

    public boolean isEqual(List<Integer> arrayList2) {
        return Objects.equals(arrayList, arrayList2);
    }

    //实现接口SingleLink，然后再此函数内使用
    public Double getMedianInLinkList(SingleLink<Integer> singleLink) {
        arrayList.forEach(singleLink::addTailPointer);
        int size = singleLink.size();
        int preMidIndex = size / 2 - 1;
        return size % 2 == 0
                ? (double) (singleLink.getNodeData(preMidIndex) + singleLink.getNodeData(preMidIndex + 1)) / 2
                : (double) singleLink.getNodeData(preMidIndex + 1);
    }

    public int getLastOdd() {
        return IntStream.rangeClosed(1, arrayList.size())
                .map(ri -> arrayList.size() - ri)
                .map(arrayList::get)
                .filter(x -> x % 2 != 0)
                .findFirst().orElse(0);
    }

    public int getIndexOfLastOdd() {
        return IntStream.rangeClosed(1, arrayList.size())
                .map(ri -> arrayList.size() - ri)
                .filter(i -> arrayList.get(i) % 2 != 0)
                .findFirst().orElse(0);
    }
}
