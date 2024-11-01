package com.bteshome.algorithms.linkedlist_;

import java.util.List;

public class LinkedListAlgorithms2 {
    /**
     * https://leetcode.com/problems/add-two-numbers/
     * */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = null;
        ListNode current = null;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            if (sum > 9) {
                carry = sum / 10;
                sum = sum % 10;
            } else {
                carry = 0;
            }

            ListNode newNode = new ListNode(sum);

            if (head == null) {
                head = newNode;
                current = newNode;
            } else {
                current.next = newNode;
                current = newNode;
            }
        }

        return head;
    }

    /**
     * https://leetcode.com/problems/odd-even-linked-list/
     * */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode oddCurrent = head;
        ListNode evenHead = head.next;
        ListNode evenCurrent = head.next;
        ListNode current = head.next.next;
        int index = 3;

        while (current != null) {
            if (index % 2 == 1) {
                oddCurrent.next = current;
                oddCurrent = current;
                evenCurrent.next = null;
            } else {
                evenCurrent.next = current;
                evenCurrent = current;
                oddCurrent.next = null;
            }
            current = current.next;
            index++;
        }

        oddCurrent.next = evenHead;

        return head;
    }

    /**
     * https://leetcode.com/problems/intersection-of-two-linked-lists/
     * NOTE - this can be solved in O(n + m) time and O(n) space using hash table.
     *        This solution, however, runs in O(n + m) time and O(1) space
     * */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int countA = 0;
        int countB = 0;

        ListNode headA2 = headA;
        ListNode headB2 = headB;

        while (headA2 != null) {
            headA2 = headA2.next;
            countA++;
        }

        while (headB2 != null) {
            headB2 = headB2.next;
            countB++;
        }

        int countDifference;
        ListNode longerList;
        ListNode shorterList;

        if (countA >= countB) {
            countDifference = countA - countB;
            longerList = headA;
            shorterList = headB;
        } else {
            countDifference = countB - countA;
            longerList = headB;
            shorterList = headA;
        }

        while (countDifference > 0) {
            longerList = longerList.next;
            countDifference--;
        }

        while (longerList != null && shorterList != null) {
            if (longerList == shorterList) {
                return longerList;
            }
            longerList = longerList.next;
            shorterList = shorterList.next;
        }

        return null;
    }
}