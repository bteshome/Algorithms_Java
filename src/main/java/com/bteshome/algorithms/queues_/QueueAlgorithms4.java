package com.bteshome.algorithms.queues_;

import java.util.*;

public class QueueAlgorithms4 {
    /**
     * https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/
     * */
    public static int countStudentsUnableToEat(int[] students, int[] sandwiches) {
        if (students == null || sandwiches == null || students.length != sandwiches.length) {
            throw new IllegalArgumentException();
        }

        Queue<Integer> studentQueue = new LinkedList<>();
        Stack<Integer> sandwithStack = new Stack<>();

        for (int student : students) {
            studentQueue.add(student);
        }

        for (int i = sandwiches.length - 1; i >= 0 ; i--) {
            sandwithStack.push(sandwiches[i]);
        }

        while (!studentQueue.isEmpty()){
            int numStudentsWhoAteThisRound = 0;

            for (int i = 0; i < studentQueue.size(); i++) {
                int student = studentQueue.remove();
                int sandwich = sandwithStack.peek();
                if (student == sandwich) {
                    sandwithStack.pop();
                    numStudentsWhoAteThisRound++;
                } else {
                    studentQueue.add(student);
                }
            }

            if (numStudentsWhoAteThisRound == 0) {
                break;
            }
        }

        return studentQueue.size();
    }

    /**
     * https://leetcode.com/problems/time-needed-to-buy-tickets/
     * */
    public static int timeRequiredToBuy(int[] tickets, int k) {
        if (tickets == null || tickets.length == 0) {
            return 0;
        }

        if (k < 0 || k >= tickets.length) {
            throw new IllegalArgumentException("k out or range");
        }

        if (tickets[k] == 0) {
            return 0;
        }

        class Peron {
            private final int id;
            private int ticketsToBuy;

            public Peron(int id, int ticketsToBuy) {
                this.id = id;
                this.ticketsToBuy = ticketsToBuy;
            }

            public int getId() {
                return id;
            }

            public int getTicketsToBuy() {
                return ticketsToBuy;
            }

            public void decrementTicketsToBuy() {
                ticketsToBuy--;
            }
        }

        Queue<Peron> peopleQueue = new LinkedList<>();
        int timeNeeded = 0;

        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] > 0) {
                peopleQueue.add(new Peron(i, tickets[i]));
            }
        }

        while (!peopleQueue.isEmpty()) {
            var front = peopleQueue.remove();
            front.decrementTicketsToBuy();
            timeNeeded++;
            if (front.getTicketsToBuy() == 0) {
                if (front.getId() == k) {
                    return timeNeeded;
                }
            } else {
                peopleQueue.add(front);
            }
        }

        return timeNeeded;
    }
}
