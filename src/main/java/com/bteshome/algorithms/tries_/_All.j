
import java.util.HashMap;

class Node {
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

class Trie {
    private final Node root = new Node();

    public Node getRoot() {
        return root;
    }

    public void insert(String word) {
        Node current = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!current.getChildren().containsKey(c)) {
                current.getChildren().put(c, new Node());
            }
            current = current.getChildren().get(c);
        }

        current.setWord(true);
    }

    public boolean search(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!current.getChildren().containsKey(c)) {
                return false;
            }
            current = current.getChildren().get(c);
        }
        return current.isWord();
    }
}

