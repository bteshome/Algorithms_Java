package com.bteshome.algorithms.graphs_;

import java.util.HashMap;
import java.util.stream.Stream;

public class Graph<T> {
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
