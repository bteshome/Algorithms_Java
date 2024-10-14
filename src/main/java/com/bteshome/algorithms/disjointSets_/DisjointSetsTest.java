package com.bteshome.algorithms.disjointSets_;

import java.util.Arrays;

public class DisjointSetsTest {
    public static void test() {
        /*System.out.println(DisjointSetsAlgorithms1.validPath(3, new int[][]{
                new int[]{0,1}, new int[] {1,2}, new int[] {2,0}
        }, 0, 2));
        System.out.println(DisjointSetsAlgorithms1.validPath(6, new int[][]{
                new int[]{0,1}, new int[] {0,2}, new int[] {3,5}, new int[] {5,4}, new int[] {4,3}
        }, 0, 5));*/

        /*System.out.println(DisjointSetsAlgorithms1.numberOfIslands(new char[][]{
                new char[]{'1','1','0','0','0'},
                new char[]{'1','1','0','0','0'},
                new char[]{'0','0','1','0','0'},
                new char[]{'0','0','0','1','1'}
        }));*/

        /*System.out.println(DisjointSetsAlgorithms1.numberOfIslandsII(3, 3, new int[][]{
                new int[]{0, 0},
                new int[]{0, 1},
                new int[]{1, 2},
                new int[]{2, 1}
        }));*/

        System.out.println(Arrays.toString(DisjointSetsAlgorithms2.findRedundantConnection(new int[][]{
                new int[]{1, 2},
                new int[]{1, 3},
                new int[]{2, 3}
        })));
    }
}
