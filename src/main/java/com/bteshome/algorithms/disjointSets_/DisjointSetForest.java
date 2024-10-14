package com.bteshome.algorithms.disjointSets_;

import java.util.HashMap;
import java.util.Set;

public class DisjointSetForest<T> {
    private final HashMap<T, Node<T>> nodes = new HashMap<>();
    private int numUnionsOperations = 0;

    public int getNumberOfDisjointSets() {
        return nodes.size() - numUnionsOperations;
    }

    public Set<T> getKeys() {
        return nodes.keySet();
    }

    public void makeSet(T key)
    {
        if (nodes.containsKey(key)) {
            return;
        }

        var node = new Node<T>(key);
        node.setParent(node);
        node.setRank(0);
        nodes.put(key, node);
    }

    public void union(T key1, T key2)
    {
        var set1 = findSet(key1);
        var set2 = findSet(key2);

        if (set1 == null || set2 == null) {
            return;
        }

        if (set1 == set2) {
            return;
        }

        if (set1.getRank() > set2.getRank()) {
            set2.setParent(set1);
        }
        else if (set1.getRank() < set2.getRank()) {
            set1.setParent(set2);
        }
        else
        {
            set1.setParent(set2);
            set2.setRank(set2.getRank() + 1);
        }

        numUnionsOperations++;
    }

    public Node<T> findSet(T key)
    {
        if (!nodes.containsKey(key))
            return null;

        return findSet(nodes.get(key));
    }

    private Node<T> findSet(Node<T> node)
    {
        if (node != node.getParent()) {
            node.setParent(findSet(node.getParent()));
        }
        return node.getParent();
    }
}


