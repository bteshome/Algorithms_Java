package com.bteshome.algorithms.tries_;

import java.util.HashMap;

public class Node {
    private final HashMap<Character, Node> children = new HashMap<>();
    private boolean isWord;

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public HashMap<Character, Node> getChildren() {
        return children;
    }
}
