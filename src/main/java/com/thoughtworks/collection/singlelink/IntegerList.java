package com.thoughtworks.collection.singlelink;

import java.util.Optional;

public class IntegerList implements SingleLink<Integer> {
    private SingleLinkNode<Integer> headerNode;
    private SingleLinkNode<Integer> tailNode;
    private int cacheIndex = -1;
    private SingleLinkNode<Integer> cacheNode;
    private int size = 0;

    public IntegerList() {
    }

    @Override
    public Integer getHeaderData() {
        return Optional.ofNullable(headerNode).map(SingleLinkNode::getData).orElse(null);
    }

    @Override
    public Integer getTailData() {
        return Optional.ofNullable(tailNode).map(SingleLinkNode::getData).orElse(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean delete(int index) {
        if (isNotValidIndex(index)) {
            return false;
        }

        if (cacheIndex == index) {
            cacheIndex = -1;
            cacheNode = null;
        }
        if (cacheIndex > index) {
            cacheIndex--;
        }
        if (index == size - 1) {
            return deleteLast();
        }
        if (index == 0) {
            return deleteFirst();
        }
        return deleteByIndex(index);
    }

    @Override
    public boolean deleteFirst() {
        if (!isEmpty()) {
            headerNode = headerNode.getNextNode();
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteLast() {
        if (!isEmpty()) {
            if (size == 1) {
                headerNode = tailNode = null;
            } else {
                SingleLinkNode<Integer> newTailNode = getNode(size - 2);
                newTailNode.setNextNode(null);
                tailNode = newTailNode;
            }
            size--;
            return true;
        }
        return false;
    }

    private boolean deleteByIndex(int index) {
        SingleLinkNode<Integer> prevNode = getNode(index - 1);
        prevNode.setNextNode(prevNode.getNextNode().getNextNode());
        size--;
        return true;
    }

    @Override
    public void addHeadPointer(Integer item) {
        IntegerNode newHeaderNode = new IntegerNode(item);
        if (isEmpty()) {
            headerNode = tailNode = newHeaderNode;
        } else {
            newHeaderNode.setNextNode(headerNode);
            headerNode = newHeaderNode;
        }
        size++;

        if (cacheIndex != -1) {
            cacheIndex++;
        }
    }

    @Override
    public void addTailPointer(Integer item) {
        IntegerNode newTailNode = new IntegerNode(item);
        if (isEmpty()) {
            headerNode = tailNode = newTailNode;
        } else {
            tailNode.setNextNode(newTailNode);
            tailNode = newTailNode;
        }
        size++;
    }

    private SingleLinkNode<Integer> getNode(int index) {
        SingleLinkNode<Integer> startNode;
        int range;
        if (cacheIndex > -1 && cacheIndex <= index) {
            startNode = cacheNode;
            range = index - cacheIndex;
        } else {
            startNode = headerNode;
            range = index;
        }
        SingleLinkNode<Integer> curNode = startNode;
        for (int i = 0; i < range; i++) {
            curNode = curNode.getNextNode();
        }
        cacheIndex = index;
        cacheNode = curNode;
        return curNode;
    }

    @Override
    public Integer getNodeData(int index) {
        if (isNotValidIndex(index)) {
            return null;
        }
        return getNode(index).getData();
    }

    private boolean isNotValidIndex(int index) {
        return (index < 0 || index >= size);
    }
}
