package com.thoughtworks.collection.singlelink;

public interface SingleLinkNode<T> {
    T getData();

    void setNextNode(SingleLinkNode<T> nextNode);

    SingleLinkNode<T> getNextNode();
}
