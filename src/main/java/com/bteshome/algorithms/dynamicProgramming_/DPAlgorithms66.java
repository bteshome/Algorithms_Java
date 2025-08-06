package com.bteshome.algorithms.dynamicProgramming_;

import java.util.*;

public class DPAlgorithms66 {
    /**
     * https://www.hackerrank.com/challenges/kingdom-division/problem
     * - the tree DP solution is accepted
     * - the other two are not. here just for comparison.
     * */
    public static class KingdomDivisionWithTreeDPTopDown {
        private static final long MOD = 1000000007;

        public static int kingdomDivision(int n, List<List<Integer>> roads) {
            if (n < 2)
                return 0;

            List<Integer>[] tree = buildTree(n, roads);
            Integer[][][] dp = new Integer[n + 1][3][3];
            long ways = kingdomDivision(tree, 1, 0, 1, 0, dp);
            return (int)((ways * 2) % MOD);
        }

        private static long kingdomDivision(List<Integer>[] tree, int city, int parent, int partitionNum, int parentPartitionNum, Integer[][][] dp) {
            if (tree[city].size() == 1 && tree[city].get(0) == parent)
                return partitionNum == parentPartitionNum ? 1 : 0;
            if (dp[city][partitionNum][parentPartitionNum] != null)
                return dp[city][partitionNum][parentPartitionNum];

            long ways = 1;

            for (int child : tree[city]) {
                if (child == parent)
                    continue;
                long ways1 = kingdomDivision(tree, child, city, partitionNum, partitionNum, dp);
                long ways2 = kingdomDivision(tree, child, city, 3 - partitionNum, partitionNum, dp);
                long childWays = (ways1 + ways2) % MOD;
                ways = (ways * childWays) % MOD;
            }

            if (partitionNum != parentPartitionNum) {
                long invalidWays = 1;

                for (int child : tree[city]) {
                    if (child == parent)
                        continue;
                    long childWays = kingdomDivision(tree, child, city, 3 - partitionNum, partitionNum, dp);
                    invalidWays = (invalidWays * childWays) % MOD;
                }

                ways = (ways - invalidWays + MOD) % MOD;
            }

            dp[city][partitionNum][parentPartitionNum] = (int)ways;

            return dp[city][partitionNum][parentPartitionNum];
        }

        private static List<Integer>[] buildTree(int n, List<List<Integer>> roads) {
            List<Integer>[] tree = new List[n + 1];

            for (int i = 1; i <= n; i++)
                tree[i] = new ArrayList<>();

            for (List<Integer> road : roads) {
                int from = road.get(0);
                int to = road.get(1);
                tree[from].add(to);
                tree[to].add(from);
            }

            return tree;
        }
    }

    public static class KingdomDivisionWithBacktrackingAndAndHashing {
        private static final long MOD = 1000000007;

        public static int kingdomDivision(int n, List<List<Integer>> roads) {
            if (n < 2)
                return 0;

            List<Integer>[] tree = buildTree(n, roads);
            boolean[] partition = new boolean[n + 1];

            return kingdomDivision(n, tree, 1, partition, new HashMap<>());
        }

        private static List<Integer>[] buildTree(int n, List<List<Integer>> roads) {
            List<Integer>[] tree = new List[n + 1];

            for (int i = 1; i <= n; i++)
                tree[i] = new ArrayList<>();

            for (List<Integer> road : roads) {
                int from = road.get(0);
                int to = road.get(1);
                tree[from].add(to);
                tree[to].add(from);
            }

            return tree;
        }

        private static int kingdomDivision(int n, List<Integer>[] tree, int city, boolean[] partition, Map<Long, Integer> dp) {
            if (city == n + 1)
                return isSafe(n, partition, tree) ? 1 : 0;

            int partitionHash = hashPartition(partition, city);
            long key = ((long) city << 32) | (partitionHash & 0xFFFFFFFFL);
            if (dp.containsKey(key))
                return dp.get(key);

            long ways = 0;

            partition[city] = true;
            ways = (ways + kingdomDivision(n, tree, city + 1, partition, dp)) % MOD;

            partition[city] = false;
            ways = (ways + kingdomDivision(n, tree, city + 1, partition, dp)) % MOD;

            dp.put(key, (int) ways);

            return (int) ways;
        }

        private static boolean isSafe(int n, boolean[] partition, List<Integer>[] tree) {
            boolean[] safe = new boolean[n + 1];

            for (int city = 1; city <= n; city++) {
                if (safe[city])
                    continue;

                for (int neighbor : tree[city]) {
                    if (partition[city] == partition[neighbor]){
                        safe[city] = true;
                        safe[neighbor] = true;
                    }
                }

                if (!safe[city])
                    return false;
            }

            return true;
        }

        private static int hashPartition(boolean[] partition, int end) {
            int hash = 1;
            for (int i = 1; i < end; i++) {
                hash = hash * 31 + (partition[i] ? 1 : 0);
                hash = hash & 0x7FFFFFFF;
            }
            return hash;
        }
    }

    public static class KingdomDivisionWithBacktrackingAndTreeSet {
        private static final long MOD = 1000000007;

        public static int kingdomDivision(int n, List<List<Integer>> roads) {
            if (n < 2)
                return 0;

            List<Integer>[] tree = buildTree(n, roads);
            NavigableSet<Integer> partition1 = new TreeSet<>();
            Set<Integer> partition2 = new HashSet<>();

            return kingdomDivision(n, tree, 1, partition1, partition2, new HashMap<>());
        }

        private static int kingdomDivision(int n, List<Integer>[] tree, int city, NavigableSet<Integer> partition1, Set<Integer> partition2, Map<String, Integer> dp) {
            if (city == n + 1) {
                return (isSafe(n, partition1, tree) &&
                        isSafe(n, partition2, tree)) ? 1 : 0;
            }

            String key = "%s,%s".formatted(city, partition1);

            if (dp.containsKey(key))
                return dp.get(key);

            long ways = 0;

            partition1.add(city);
            ways = (ways + kingdomDivision(n, tree, city + 1, partition1, partition2, dp)) % MOD;
            partition1.remove(city);

            partition2.add(city);
            ways = (ways + kingdomDivision(n, tree, city + 1, partition1, partition2, dp)) % MOD;
            partition2.remove(city);

            dp.put(key, (int) ways);

            return (int) ways;
        }

        private static List<Integer>[] buildTree(int n, List<List<Integer>> roads) {
            List<Integer>[] tree = new List[n + 1];

            for (int i = 1; i <= n; i++)
                tree[i] = new ArrayList<>();

            for (List<Integer> road : roads) {
                int from = road.get(0);
                int to = road.get(1);
                tree[from].add(to);
                tree[to].add(from);
            }

            return tree;
        }

        private static boolean isSafe(int n, Set<Integer> partition, List<Integer>[] tree) {
            boolean[] safe = new boolean[n + 1];

            for (int city : partition) {
                if (safe[city])
                    continue;

                for (int neighbor : tree[city])
                    if (partition.contains(neighbor)) {
                        safe[city] = true;
                        safe[neighbor] = true;
                    }

                if (!safe[city])
                    return false;
            }

            return true;
        }
    }
}