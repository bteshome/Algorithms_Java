package com.bteshome.algorithms.disjointSets_;

public class Node<T> {
    private final T key;
    private Node<T> parent;
    private int rank;

    public Node(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}