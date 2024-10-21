package com.bteshome.algorithms.stacks_;

import java.util.HashMap;
import java.util.Stack;

public class StackAlgorithms1 {
    /**
     * https://leetcode.com/problems/implement-queue-using-stacks/
     * */
    private static class MyQueue {
        private final Stack<Integer> s1 = new Stack<>();
        private final Stack<Integer> s2 = new Stack<>();

        public MyQueue() { }

        public void push(int x) {
            s1.push(x);
        }

        public int pop() {
            return peekOrPop(true);
        }

        public int peek() {
            return peekOrPop(false);
        }

        private int peekOrPop(boolean pop) {
            if (s1.isEmpty()) {
                throw new RuntimeException("queue empty");
            }
            while (s1.size() > 1) {
                s2.push(s1.pop());
            }
            int val = pop ? s1.pop() : s1.peek();
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
            return val;
        }

        public boolean empty() {
            return s1.isEmpty();
        }
    }

    /**
     * https://leetcode.com/problems/valid-parentheses/
     * */
    public static boolean validParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        if (s.length() % 2 != 0) {
            return false;
        }

        var symbols = new HashMap<Character, Character>();
        symbols.put('(', ')');
        symbols.put('{', '}');
        symbols.put('[', ']');

        var closedCount = 0;
        var open = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (symbols.containsKey(c)) {
                if ((open.size() + closedCount) == (s.length() / 2)) {
                    return false;
                }
                open.push(c);
            } else {
                if (open.isEmpty()) {
                    return false;
                }
                if (!symbols.get(open.pop()).equals(c)) {
                    return false;
                }
                closedCount++;
            }
        }

        return open.isEmpty();
    }

    /**
     * https://leetcode.com/problems/palindrome-linked-list/
     * */
    public static boolean palindromeLinkedList(ListNode head) {
        if (head == null) {
            return true;
        }

        var reversed = new Stack<Integer>();
        ListNode head2 = head;
        var count = 0;

        while (head2 != null) {
            reversed.push(head2.val);
            head2 = head2.next;
            count++;
        }

        for (int i = 0; i < count / 2; i++) {
            if (head.val != reversed.pop()) {
                return false;
            }
            head = head2.next;
        }

        return true;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/submissions/1426532040/
     * */
    public static String removeAdjacentDuplicates(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        // NOTE - here using StringBuilder as stack.
        var buffer = new StringBuilder(s.length());

        for (int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (!buffer.isEmpty() && buffer.charAt(buffer.length() - 1) == c) {
                buffer.deleteCharAt(buffer.length() - 1);
            } else {
                buffer.append(c);
            }
        }

        return buffer.toString();
    }
}
