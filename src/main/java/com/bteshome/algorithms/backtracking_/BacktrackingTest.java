package com.bteshome.algorithms.backtracking_;

public class BacktrackingTest {
    public static void test() {
        //System.out.println(BacktrackingAlgorithms.generateParenthesis(3));
        //System.out.println(BacktrackingAlgorithms.letterCombinations("2"));

        //System.out.println(BacktrackingAlgorithms2.permute1(new int[]{1, 2, 3}));
        //System.out.println(BacktrackingAlgorithms2.permute2(new int[]{1, 2, 3}));

        //System.out.println(BacktrackingAlgorithms2.combine(4, 2));

        //System.out.println(BacktrackingAlgorithms2.combinationSum(new int[]{2,3,6,7}, 7));

        //System.out.println(BacktrackingAlgorithms3.wordSearch(new char[][]{{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}}, "SEE"));
        //System.out.println(BacktrackingAlgorithms3.wordSearchII(new char[][]{{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}}, new String[]{"oath","pea","eat","rain"}));

        //System.out.println(BacktrackingAlgorithms4.subsets(new int[]{1, 2, 3}));

        //System.out.println(BacktrackingAlgorithms4.restoreIpAddresses("25525511135"));

        /*var node1 = new BacktrackingAlgorithms5.TreeNode(2);
        var node2 = new BacktrackingAlgorithms5.TreeNode(2);
        var root = new BacktrackingAlgorithms5.TreeNode(1, node1, node2);
        System.out.println(BacktrackingAlgorithms5.pathSum(root, 3));*/

        //System.out.println(BacktrackingAlgorithms5.combinationSum3(3, 9));

        /*var grid = new int[][]{
                new int[]{1,0,0,0}, new int[]{0,0,0,0}, new int[]{0,0,2,-1}
        };
        System.out.println(BacktrackingAlgorithms6.uniquePathsIII(grid));*/

        /*var board = new char[][]{
                new char[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        BacktrackingAlgorithms6.solveSudoku(board);*/

        //System.out.println(BacktrackingAlgorithms7.wordLadder("hit", "cog", List.of("hot","dot","dog","lot","log","cog")));

        //System.out.println(BacktrackingAlgorithms7.wordLadderII("hit", "cog", List.of("hot","dot","dog","lot","log","cog")));

        /*System.out.println(BacktrackingAlgorithms8.wordBreakII("catsanddog", List.of("cat","cats","and","sand","dog")));
        System.out.println(BacktrackingAlgorithms8.wordBreakII2("catsanddog", List.of("cat","cats","and","sand","dog")));*/

        //System.out.println(BacktrackingAlgorithms9.nQueensII(4));

        var graph = new int[][]{
                new int[]{1, 2},
                new int[]{3},
                new int[]{3},
                new int[]{}
        };
        System.out.println(BacktrackingAlgorithms9.allPathsSourceTarget(graph));
    }
}
