package com.bteshome.algorithms.streams_;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;

public class StreamAlgorithms1 {
    /**
     * https://leetcode.com/problems/product-of-the-last-k-numbers/
     * */
    public static class ProductOfLastKNumbers {
        private List<Integer> products = new ArrayList<>();

        public void add(int num) {
            if (num == 0) {
                products = new ArrayList<>();
            } else if (products.isEmpty()) {
                products.add(num);
            } else {
                products.add(products.getLast() * num);
            }
        }

        public int getProduct(int k) {
            if (products.size() < k) {
                return 0;
            } else if (k == products.size()) {
                return products.getLast();
            } else {
                return products.getLast() / products.get(products.size() - k - 1);
            }
        }
    }

    /**
     * https://leetcode.com/problems/design-browser-history/
     * */
    public static class BrowserHistory {
        private List<String> history = new ArrayList<String>();
        private int current = 0;

        public BrowserHistory(String homepage) {
            history.add(homepage);
        }

        public void visit(String url) {
            history = history.subList(0, current + 1);
            history.add(url);
            current = history.size() - 1;
        }

        public String back(int steps) {
            while (steps > 0 && current > 0) {
                current--;
                steps--;
            }
            return history.get(current);
        }

        public String forward(int steps) {
            while (steps > 0 && current < history.size() - 1) {
                current++;
                steps--;
            }
            return history.get(current);
        }
    }

    /**
     * https://leetcode.com/problems/stock-price-fluctuation/
     * */
    public static class StockPrice {
        private final TreeMap<Integer, Integer> timestampPriceMap = new TreeMap<>();
        private final TreeMap<Integer, Integer> priceFrequencyMap = new TreeMap<>();

        public StockPrice() {
        }

        public void update(int timestamp, int price) {
            if (timestampPriceMap.containsKey(timestamp)) {
                int oldPrice = timestampPriceMap.get(timestamp);

                if (price == oldPrice) {
                    return;
                }

                if (priceFrequencyMap.get(oldPrice) == 1) {
                    priceFrequencyMap.remove(oldPrice);
                } else {
                    priceFrequencyMap.put(oldPrice, priceFrequencyMap.get(oldPrice) - 1);
                }

                if (priceFrequencyMap.containsKey(price)) {
                    priceFrequencyMap.put(price, priceFrequencyMap.get(price) + 1);
                } else {
                    priceFrequencyMap.put(price, 1);
                }
            } else {
                if (priceFrequencyMap.containsKey(price)) {
                    priceFrequencyMap.put(price, priceFrequencyMap.get(price) + 1);
                } else {
                    priceFrequencyMap.put(price, 1);
                }
            }

            timestampPriceMap.put(timestamp, price);
        }

        public int current() {
            return timestampPriceMap.lastEntry().getValue();
        }

        public int maximum() {
            return priceFrequencyMap.lastKey();
        }

        public int minimum() {
            return priceFrequencyMap.firstKey();
        }
    }
}
