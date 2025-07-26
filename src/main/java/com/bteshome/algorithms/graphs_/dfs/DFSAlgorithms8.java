package com.bteshome.algorithms.graphs_.dfs;

public class DFSAlgorithms8 {
    /* https://leetcode.com/problems/jump-game-iii */
    public static boolean canReachDfs(int[] arr, int start) {
        if (arr == null)
            return false;

        return canReachDfs(arr, start, new boolean[arr.length]);
    }

    private static boolean canReachDfs(int[] arr, int pos, boolean[] visited) {
        if (pos < 0 || pos >= arr.length || visited[pos])
            return false;
        if (arr[pos] == 0)
            return true;

        int jump = arr[pos];
        visited[pos] = true;

        return canReachDfs(arr, pos + jump, visited) ||
                canReachDfs(arr, pos - jump, visited);
    }

    public static boolean canReachDfsNoExtraStorage(int[] arr, int start) {
        if (arr == null)
            return false;

        return canReachDfsNoExtraStorageHelper(arr, start);
    }

    private static boolean canReachDfsNoExtraStorageHelper(int[] arr, int pos) {
        if (pos < 0 || pos >= arr.length || arr[pos] == -1)
            return false;
        if (arr[pos] == 0)
            return true;

        int jump = arr[pos];
        arr[pos] = -1;

        return canReachDfsNoExtraStorageHelper(arr, pos + jump) ||
                canReachDfsNoExtraStorageHelper(arr, pos - jump);
    }
}
