package com.bteshome.algorithms.graphs_;

public class Edge<T> {
    private final Vertex<T> from;
    private final Vertex<T> to;

    public Edge(Vertex<T> from, Vertex<T> to) {
        this.from = from;
        this.to = to;
    }

    public Vertex<T> getTo() {
        return to;
    }

    public Vertex<T> getFrom() {
        return from;
    }
}
