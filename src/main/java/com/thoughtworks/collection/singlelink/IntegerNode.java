package com.thoughtworks.collection.singlelink;

import com.thoughtworks.collection.singlelink.SingleLinkNode;

public class IntegerNode implements SingleLinkNode<Integer> {
    private final Integer data;
    private SingleLinkNode<Integer> nextNode = null;

    public IntegerNode(Integer data) {
        this.data = data;
    }

    @Override
    public Integer getData() {
        return data;
    }

    @Override
    public void setNextNode(SingleLinkNode<Integer> nextNode) {
        this.nextNode = nextNode;
    }


    @Override
    public SingleLinkNode<Integer> getNextNode() {
        return nextNode;
    }
}
