package com.bteshome.algorithms.heap_;

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
            var top = availability.remove();
            if (buffer.length() >= 2 && top.type == buffer.charAt(buffer.length() - 1) && top.type == buffer.charAt(buffer.length() - 2)) {
                if (availability.isEmpty()) {
                    break;
                }
                var secondTop = availability.remove();
                buffer.append(secondTop.type);
                secondTop.decrementCountAvailable();
                if (secondTop.countAvailable > 0) {
                    availability.add(secondTop);
                }
                availability.add(top);
            } else {
                buffer.append(top.type);
                top.decrementCountAvailable();
                if (top.countAvailable > 0) {
                    availability.add(top);
                }
            }
        }

        return buffer.toString();
    }


}
