package com.bteshome.algorithms.graphs_.bfs;

import com.bteshome.algorithms.graphs_.Graph;
import com.bteshome.algorithms.graphs_.State;
import com.bteshome.algorithms.graphs_.Vertex;

import java.util.*;

public class BFSAlgorithms4 {
    /*
    * https://leetcode.com/problems/water-and-jug-problem/
    * Beautiful!
    * */
    public static boolean canMeasureWater(int x, int y, int target) {
        record Entry(int x, int y) { }

        Graph<Entry> g = new Graph<>();
        Queue<Vertex<Entry>> q = new LinkedList<>();
        Set<Entry> visited = new HashSet<>();
        Entry start = new Entry(0, 0);

        g.addVertex(start);
        Vertex<Entry> startNode = g.getVertices().get(start);
        visited.add(start);
        q.offer(startNode);

        while (!q.isEmpty()) {
            Vertex<Entry> front = q.poll();
            Entry entry = front.getKey();
            if (entry.x + entry.y == target)
                return true;

            Entry pourX = new Entry(0, entry.y);
            if (!visited.contains(pourX)) {
                visited.add(pourX);
                q.offer(new Vertex<>(pourX));
            }

            Entry pourY = new Entry(entry.x, 0);
            if (!visited.contains(pourY)) {
                visited.add(pourY);
                q.offer(new Vertex<>(pourY));
            }

            Entry fillX = new Entry(x, entry.y);
            if (!visited.contains(fillX)) {
                visited.add(fillX);
                q.offer(new Vertex<>(fillX));
            }

            Entry fillY = new Entry(entry.x, y);
            if (!visited.contains(fillY)) {
                visited.add(fillY);
                q.offer(new Vertex<>(fillY));
            }

            Entry pourXtoY = new Entry(Math.max(0, entry.x + entry.y - y) , Math.min(y, entry.x + entry.y));
            if (!visited.contains(pourXtoY)) {
                visited.add(pourXtoY);
                q.offer(new Vertex<>(pourXtoY));
            }

            Entry pourYtoX = new Entry(Math.min(x, entry.x + entry.y), Math.max(0, entry.x + entry.y - x));
            if (!visited.contains(pourYtoX)) {
                visited.add(pourYtoX);
                q.offer(new Vertex<>(pourYtoX));
            }
        }

        return false;
    }
}
