package com.bteshome.algorithms.graphs_;

import com.bteshome.algorithms.graphs_.bfs.BFSAlgorithms1;
import com.bteshome.algorithms.graphs_.bfs.BFSAlgorithms2;
import com.bteshome.algorithms.graphs_.dfs.DFSAlgorithms1;
import com.bteshome.algorithms.graphs_.dfs.DFSAlgorithms2;
import com.bteshome.algorithms.graphs_.dfs.DFSAlgorithms3;
import com.bteshome.algorithms.graphs_.dfs.DFSAlgorithms4;

import java.util.Arrays;

public class GraphTest {
    public static void test() {
        //System.out.println(DFSAlgorithms1.findJudge(3, new int[][]{new int[]{1,3}, new int[]{2, 3}, new int[]{3, 1}}));

        //System.out.println(DFSAlgorithms1.findCenterOfStarGraph(new int[][]{new int[]{1,2}, new int[]{2, 3}, new int[]{4, 2}}));

        //System.out.println(DFSAlgorithms1.validPath(3, new int[][]{new int[]{0, 1}, new int[]{1, 2}, new int[]{2, 0}}, 0, 2));
        //System.out.println(BFSAlgorithms1.validPath(3, new int[][]{new int[]{0, 1}, new int[]{1, 2}, new int[]{2, 0}}, 0, 2));

        //System.out.println(DFSAlgorithms2.courseSchedule(5, new int[][]{new int[]{1, 4}, new int[]{2, 4}, new int[]{3, 1}, new int[]{3, 2}}));
        //System.out.println(DFSAlgorithms2.courseSchedule(2, new int[][]{new int[]{1, 0}, new int[]{0, 1}}));

        /*System.out.println(Arrays.toString(DFSAlgorithms2.courseScheduleII(5, new int[][]{
                new int[]{1, 4}, new int[]{2, 4}, new int[]{3, 1}, new int[]{3, 2}})));*/

        /*System.out.println(DFSAlgorithms3.findMinHeightTreesTODO(6, new int[][]{
                new int[]{3, 0}, new int[]{3, 1}, new int[]{3, 2}, new int[]{3, 4}, new int[]{5, 4}}));*/

        /*System.out.println(DFSAlgorithms3.numIslands(new char[][]{
                new char[]{'1', '1', '1', '0', '0'},
                new char[]{'1', '1', '0', '1', '0'},
                new char[]{'1', '1', '0', '0', '0'},
                new char[]{'0', '0', '0', '1', '0'}
        }));*/
        /*System.out.println(BFSAlgorithms1.numIslands(new char[][]{
                new char[]{'1', '1', '1', '0', '0'},
                new char[]{'1', '1', '0', '1', '0'},
                new char[]{'1', '1', '0', '0', '0'},
                new char[]{'0', '0', '0', '1', '0'}
        }));*/

        /*var three = new DFSAlgorithms4.TreeNode(3);
        var two = new DFSAlgorithms4.TreeNode(2, three, null);
        var root = new DFSAlgorithms4.TreeNode(1, null, two);
        System.out.println(DFSAlgorithms4.inorderTraversal(root));*/

        System.out.println(BFSAlgorithms2.minMutation("AAAAAAAA", "CCCCCCCC",
                new String[]{"AAAAAAAA","AAAAAAAC","AAAAAACC","AAAAACCC","AAAACCCC","AACACCCC","ACCACCCC","ACCCCCCC","CCCCCCCA"}));
    }
}
