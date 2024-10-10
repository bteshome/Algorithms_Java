package com.bteshome.algorithms.graphs_;

import java.util.HashMap;

public class Vertex<T> {
    private final T key;
    private final HashMap<T, Vertex<T>> neighbors = new HashMap<>();
    private final HashMap<T, Edge<T>> outgoingEdges = new HashMap<>();
    private final HashMap<T, Edge<T>> incomingEdges = new HashMap<>();
    private State state;
    private int rank;

    public Vertex(T key) {
        this.key = key;
        this.state = State.Unvisited;
    }

    public T getKey() {
        return key;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public HashMap<T, Vertex<T>> getNeighbors() {
        return neighbors;
    }

    public HashMap<T, Edge<T>> getOutgoingEdges() {
        return outgoingEdges;
    }

    public HashMap<T, Edge<T>> getIncomingEdges() {
        return incomingEdges;
    }

    @Override
    public String toString() {
        return key.toString();
    }
}
