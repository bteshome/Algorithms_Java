package com.bteshome.algorithms.tries_;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Node {
    private final Map<Character, Node> children = new TreeMap<>();
    private boolean isWord;

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public Map<Character, Node> getChildren() {
        return children;
    }
}
