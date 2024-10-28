package com.bteshome.algorithms.heap_;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapAlgorithms5 {
    /**
     * https://leetcode.com/problems/longest-happy-string/?envType=daily-question&envId=2024-10-16
     * */
    public static String longestHappyString(int a, int b, int c) {
        class Entry {
            private final char type;
            private int countAvailable;

            public Entry(char type, int countAvailable) {
                this.type = type;
                this.countAvailable = countAvailable;
            }

            public int getCountAvailable() {
                return countAvailable;
            }

            public void decrementCountAvailable() {
                countAvailable--;
            }
        }

        var availability = new PriorityQueue<Entry>((e1, e2) -> e2.countAvailable - e1.countAvailable);
        var buffer = new StringBuilder();

        if (a > 0) {
            availability.add(new Entry('a', a));
        }

        if (b > 0) {
            availability.add(new Entry('b', b));
        }

        if (c > 0) {
            availability.add(new Entry('c', c));
        }

        while (!availability.isEmpty()) {
            while (!availability.isEmpty()) {
                var top = availability.remove();
                if (buffer.length() >= 2 && top.type == buffer.charAt(buffer.length() - 1) && top.type == buffer.charAt(buffer.length() - 2)) {
                    if (availability.isEmpty()) {
                        break;
                    }
                    var secondTop = availability.remove();
                    buffer.append(secondTop.type);
                    if (secondTop.countAvailable > 1) {
                        secondTop.decrementCountAvailable();
                        availability.add(secondTop);
                    }
                }
                buffer.append(top.type);
                if (top.countAvailable > 1) {
                    top.decrementCountAvailable();
                    availability.add(top);
                }
            }
        }

        return buffer.toString();
    }

    /**
     * https://leetcode.com/problems/maximum-bags-with-full-capacity-of-rocks/
     * */
    public static int maximumBagsWithFullCapacity(int[] capacity, int[] rocks, int additionalRocks) {
        if (capacity == null || rocks == null) {
            return 0;
        }

        if (rocks.length != capacity.length) {
            throw new IllegalArgumentException("arrays rocks and capacity have different lengths");
        }

        if (additionalRocks < 0) {
            throw new IllegalArgumentException("additionalRocks is negative");
        }

        class Entry {
            private final int id;
            private int availableSpace;

            public Entry(int id, int availableSpace) {
                this.id = id;
                this.availableSpace = availableSpace;
            }

            public int getId() {
                return id;
            }

            public int getAvailableSpace() {
                return availableSpace;
            }

            public void placeRocks(int space) {
                availableSpace -= space;
            }
        }

        var orderingByAvailableSpace = new PriorityQueue<Entry>(Comparator.comparingInt(Entry::getAvailableSpace));

        for (int i = 0; i < rocks.length; i++) {
            if (rocks[i] > capacity[i]) {
                throw new IllegalArgumentException("bag has more than its capacity");
            } else if (rocks[i] < capacity[i]) {
                orderingByAvailableSpace.add(new Entry(i, capacity[i] - rocks[i]));
            }
        }

        while (!orderingByAvailableSpace.isEmpty()) {
            if (additionalRocks == 0) {
                break;
            }
            Entry top = orderingByAvailableSpace.remove();
            int rocksToPlace = Math.min(additionalRocks, top.getAvailableSpace());
            top.placeRocks(rocksToPlace);
            if (top.getAvailableSpace() > 0) {
                orderingByAvailableSpace.add(top);
            }
            additionalRocks -= rocksToPlace;
        }

        return rocks.length - orderingByAvailableSpace.size();
    }
}
