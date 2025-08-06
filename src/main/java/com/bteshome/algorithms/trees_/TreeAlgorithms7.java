package com.bteshome.algorithms.trees_;

public class TreeAlgorithms7 {
    /* https://leetcode.com/problems/flip-equivalent-binary-trees */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;
        if (root1.val != root2.val)
            return false;

        return  (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) ||
                (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }

    /* https://leetcode.com/problems/count-good-nodes-in-binary-tree */
    public int goodNodes(TreeNode root) {
        if (root == null)
            return 0;

        return goodNodes(root, root.val);
    }

    private int goodNodes(TreeNode root, int max) {
        if (root == null)
            return 0;

        int count = 0;

        if (root.val >= max) {
            count++;
            max = root.val;
        }

        count += goodNodes(root.left, max);
        count += goodNodes(root.right, max);

        return count;
    }
}
