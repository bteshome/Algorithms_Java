package com.bteshome.algorithms.trees_;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeAlgorithms4 {
    static final String NULL = "null";

    // TODO - unfinished.
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode front = q.poll();

            if (!builder.isEmpty()) {
                builder.append(',');
            }

            if (front == null) {
                builder.append(NULL);
            } else {
                builder.append(front.val);
                q.offer(front.left);
                q.offer(front.right);
            }
        }

        return builder.toString();
    }

    // Decodes your encoded data to tree.
    /*public TreeNode deserialize(String data) {
        data = data.substring(1, data.length() - 1);
        var tokens = data.split(",");
    }*/
}
