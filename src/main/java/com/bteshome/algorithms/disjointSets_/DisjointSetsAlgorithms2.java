package com.bteshome.algorithms.disjointSets_;

import java.util.*;
import java.util.stream.Stream;

public class DisjointSetsAlgorithms2 {
    /**
     * https://leetcode.com/problems/redundant-connection
     * */
    public static int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return new int[0];
        }

        var sets = new DisjointSetForest<Integer>();
        for (int i = 1; i <= edges.length; i++) {
            sets.makeSet(i);
        }

        for (int i = 0; i < edges.length; i++) {
            var edge = edges[i];
            var set1 = sets.findSet(edge[0]);
            var set2 = sets.findSet(edge[1]);
            if (set1 == set2) {
                return edge;
            }
            sets.union(edge[0], edge[1]);
        }

        return new int[0];
    }

    /*
    * https://leetcode.com/problems/accounts-merge/
    * */
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null || accounts.isEmpty())
            return accounts;

        class Account {
            private final String name;
            private TreeSet<String> emails = new TreeSet<>();

            public Account(String name) {
                this.name = name;
            }

            public List<String> toList() {
                List<String> list = new ArrayList<>();
                list.add(name);
                list.addAll(emails);
                return list;
            }
        }

        Map<String, Account> accountMap = new HashMap<>();
        DisjointSetForest<String> forest = new DisjointSetForest<>();

        for (List<String> accountInfo : accounts) {
            for (int i = 1; i < accountInfo.size(); i++) {
                forest.makeSet(accountInfo.get(i));
                if (i > 1) {
                    String email = forest.findSet(accountInfo.get(i)).getKey();
                    String previousEmail = forest.findSet(accountInfo.get(i - 1)).getKey();
                    forest.union(previousEmail, email);
                }
            }
        }

        for (List<String> accountInfo : accounts) {
            String name = accountInfo.get(0);
            String primaryEmail = forest.findSet(accountInfo.get(1)).getKey();
            if (!accountMap.containsKey(primaryEmail))
                accountMap.put(primaryEmail, new Account(name));
            Account account = accountMap.get(primaryEmail);
            account.emails.addAll(accountInfo.subList(1, accountInfo.size()));
        }

        return accountMap.values()
                .stream()
                .map(Account::toList)
                .toList();
    }
}
