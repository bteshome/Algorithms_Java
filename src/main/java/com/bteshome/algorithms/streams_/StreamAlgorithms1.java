package com.bteshome.algorithms.streams_;

import java.util.ArrayList;
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
}
