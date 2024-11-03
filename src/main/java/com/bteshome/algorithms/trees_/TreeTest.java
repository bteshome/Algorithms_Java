package com.bteshome.algorithms.trees_;

import com.bteshome.algorithms.queues_.QueueAlgorithms5;

public class TreeTest {
    public static void test() {
        /*var root = new TreeNode(1);
        var two = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        var two2 = new TreeNode(2, new TreeNode(4), new TreeNode(3));
        root.left = two;
        root.right = two2;
        System.out.println(TreeAlgorithms1.isSymmetric(root));*/

        /*var root = new TreeNode(1);
        var two = new TreeNode(2, new TreeNode(4), null);
        var three = new TreeNode(3, null, new TreeNode(5));
        root.left = two;
        root.right = three;
        System.out.println(TreeAlgorithms1.zigzagLevelOrder(root));*/

        /*var root = TreeAlgorithms2.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        System.out.println(root.val);*/

        /*var root = new TreeNode(5);
        var four = new TreeNode(4);
        //var one = new TreeNode(1, null, new TreeNode(2));
        var two = new TreeNode(2);
        var one = new TreeNode(1, two, null);
        var three = new TreeNode(3, one, four);
        var six = new TreeNode(6);
        root.left = three;
        root.right = six;
        //System.out.println(TreeAlgorithms3.inorderSuccessor(root, four).val);
        System.out.println(TreeAlgorithms3.inorderSuccessor(root, two).val);*/

        var root = new TreeNode(5);
        var four = new TreeNode(4);
        var two = new TreeNode(2);
        var one = new TreeNode(1, two, null);
        var three = new TreeNode(3, one, four);
        var six = new TreeNode(6);
        root.left = three;
        root.right = six;
        System.out.println(TreeAlgorithms2.inorderTraversalIterative(root));

    }
}
