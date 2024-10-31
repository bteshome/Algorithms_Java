package com.bteshome.algorithms.linkedlist_;

import java.util.Stack;

public class LinkedListAlgorithms1 {
    /**
     * https://leetcode.com/problems/delete-node-in-a-linked-list/
     * */
    public static void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }

        if (node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    /**
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
     * */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1) {
            return null;
        }

        int listLength = 0;
        ListNode temp = head;

        while (temp != null) {
            listLength++;
            temp = temp.next;
        }

        if (n >= listLength) {
            return head.next;
        } else {
            int iterationsFromLeft = listLength - n - 1;
            ListNode current = head;

            while (iterationsFromLeft > 0) {
                current = current.next;
                iterationsFromLeft--;
            }

            if (current.next != null) {
                current.next = current.next.next;
            }

            return head;
        }
    }

    /**
     * https://leetcode.com/problems/reverse-linked-list/
     * */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode previous = null;

        while (true) {
            var next = head.next;
            head.next = previous;
            previous = head;
            if (next == null) {
                break;
            }
            head = next;
        }

        return head;
    }

    /**
     * https://leetcode.com/problems/merge-two-sorted-lists/
     * */
    public static ListNode mergeTwoListsNoExtraMemory(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val <= list2.val) {
            list1.next = mergeTwoListsNoExtraMemory(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoListsNoExtraMemory(list1, list2.next);
            return list2;
        }
    }

    public static ListNode mergeTwoListsWithExtraMemory(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode head = null;

        if (list1.val <= list2.val) {
            head = new ListNode(list1.val);
            list1 = list1.next;
        } else {
            head = new ListNode(list2.val);
            list2 = list2.next;
        }

        ListNode current = head;

        while (true) {
            if (list1 == null) {
                current.next = list2;
                break;
            } else if (list2 == null) {
                current.next = list1;
                break;
            } else {
                if (list1.val <= list2.val) {
                    current.next = new ListNode(list1.val);
                    list1 = list1.next;
                } else {
                    current.next = new ListNode(list2.val);
                    list2 = list2.next;
                }
                current = current.next;
            }
        }

        return head;
    }


}
