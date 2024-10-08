package com.bteshome.algorithms.graphs_;

import java.util.HashMap;
import java.util.stream.Stream;

enum State {
    Unvisited,
    Visiting,
    Visited
}

class Vertex<T> {
    private final T key;
    private final HashMap<T, Vertex<T>> neighbors = new HashMap<>();
    private final HashMap<T, Edge<T>> outgoingEdges = new HashMap<>();
    private final HashMap<T, Edge<T>> incomingEdges = new HashMap<>();
    private State state;

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

class Edge<T> {
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

class Graph<T> {
    private final HashMap<T, Vertex<T>> vertices = new HashMap<>();
    private final boolean isDirected;

    public Graph() {
        this(true);
    }

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public HashMap<T, Vertex<T>> getVertices() {
        return vertices;
    }

    public Stream<Edge<T>> getEdges() {
        return vertices.values().stream().flatMap(v -> v.getOutgoingEdges().values().stream());
    }

    public Vertex<T> addVertex(T key) {
        if (!vertices.containsKey(key)) {
            vertices.put(key, new Vertex<>(key));
        }
        return vertices.get(key);
    }

    public void addEdge(T from, T to) {
        Vertex<T> fromVertex = addVertex(from);
        Vertex<T> toVertex = addVertex(to);

        addEdge(fromVertex, toVertex);

        if (!isDirected) {
            addEdge(toVertex, fromVertex);
        }
    }

    private void addEdge(Vertex<T> fromVertex, Vertex<T> toVertex) {
        fromVertex.getNeighbors().put(toVertex.getKey(), toVertex);
        Edge<T> edge = new Edge<>(fromVertex, toVertex);
        fromVertex.getOutgoingEdges().put(toVertex.getKey(), edge);
        toVertex.getIncomingEdges().put(fromVertex.getKey(), edge);
    }
}

