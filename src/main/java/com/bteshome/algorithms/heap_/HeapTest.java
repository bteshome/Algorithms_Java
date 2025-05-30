package com.bteshome.algorithms.heap_;

import com.bteshome.algorithms.arrays_.ArrayAlgorithms2;
import com.bteshome.algorithms.backtracking_.BacktrackingAlgorithms9;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

public class HeapTest {
    public static void test() {
        //System.out.println(HeapAlgorithms1.lastStoneWeight(new int[]{2,7,4,1,8,1}));

        /*System.out.println(Arrays.toString(HeapAlgorithms1.kWeakestRows(new int[][] {
                new int[]{1,1,0,0,0},
                new int[]{1,1,1,1,0},
                new int[]{1,0,0,0,0},
                new int[]{1,1,0,0,0},
                new int[]{1,1,1,1,1}
        }, 3)));*/

        /*var averages = HeapAlgorithms2.highFive(new int[][] {
                new int[]{1,91},
                new int[]{1,92},
                new int[]{2,93},
                new int[]{2,97},
                new int[]{1,60},
                new int[]{2,77},
                new int[]{1,65},
                new int[]{1,87},
                new int[]{1,100},
                new int[]{2,100},
                new int[]{2,76}
        });
        for (int[] average : averages) {
            System.out.println(Arrays.toString(average));
        }*/

        //System.out.println(HeapAlgorithms3.reorganizeString("bbbbaaaaababaababab"));
        //System.out.println(HeapAlgorithms3.reorganizeString("vvvlo"));

        //System.out.println(HeapAlgorithms5.longestHappyString(1, 1, 7));

        //System.out.println(HeapAlgorithms4.taskScheduler(new char[]{'A','A','A','B','B','B'}, 2));

        //System.out.println(HeapAlgorithms5.maximumBagsWithFullCapacity(new int[]{2,3,4,5}, new int[]{1,2,4,4}, 2));

        /*System.out.println(HeapAlgorithms9.meetingRoomsII(new int[][] {
                new int[]{0,30},
                new int[]{5,10},
                new int[]{15,20}
        }));*/

        //System.out.println(HeapAlgorithms6TODO.rearrangeStringKDistanceApart("aabbcc", 3));
        //System.out.println(HeapAlgorithms7TODO.rearrangeStringKDistanceApart("aabbcc", 3));

        //System.out.println(HeapAlgorithms8TODO.numberOfPeopleAwareOfSecret(6, 2, 4));

        //System.out.println(HeapAlgorithms8TODO.maxTaskAssign(new int[]{5,9,8,5,9}, new int[]{1,6,4,2,6}, 1, 5));
        //System.out.println(HeapAlgorithms8TODO.maxTaskAssign(new int[]{3,2,1}, new int[]{0,3,3}, 1, 1));
        //System.out.println(HeapAlgorithms8TODO.maxTaskAssign(new int[]{5,4}, new int[]{0,0,0}, 1, 5));
        //System.out.println(HeapAlgorithms8TODO.maxTaskAssign(new int[]{10,15,30}, new int[]{0,10,10,10,10}, 3, 10));
        //System.out.println(HeapAlgorithms8TODO.maxTaskAssign(new int[]{3,2,1, 5, 7, 4, 11, 11111111}, new int[]{0,3,3, 3, 17, 2, 4, 5}, 2, 2));

        //System.out.println(Arrays.toString(HeapAlgorithms9.kthSmallestPrimeFraction(new int[]{1,2,3,5}, 3)));

        /*var matrix = new int[][]{
                new int[]{1,5,9},
                new int[]{10,11,13},
                new int[]{12,13,15}
        };*/
        /*var matrix2 = new int[][]{
                new int[]{1,2},
                new int[]{1,3}
        };*/
        //System.out.println(HeapAlgorithms9.kthSmallestInSortedMatrix(matrix, 8));
        //System.out.println(HeapAlgorithms9.kthSmallestInSortedMatrix(matrix2, 2));

        //System.out.println(HeapAlgorithms10.kSmallestPairs(new int[]{1,2,4,5,6}, new int[]{3,5,7,9}, 3));
        //System.out.println(HeapAlgorithms10.kSmallestPairs(new int[]{1,2,4,5,6}, new int[]{3,5,7,9}, 6));
        //System.out.println(HeapAlgorithms10.kSmallestPairs2(new int[]{1,7,11}, new int[]{2,4,6}, 3));

        //System.out.println(Arrays.toString(HeapAlgorithms10.topKFrequent(new int[]{1,1,1,2,2,3},2)));

        //System.out.println(HeapAlgorithms10.nthUglyNumber(10));

        //System.out.println(HeapAlgorithms11.longestSubarray(new int[]{10,1,2,4,7,2}, 5));

        /*System.out.println(HeapAlgorithms11.maximumTotalImportanceOfRoads(5, new int[][]{
                new int[]{0,1},
                new int[]{1,2},
                new int[]{2,3},
                new int[]{0,2},
                new int[]{1,3},
                new int[]{2,4}}));*/

        //System.out.println(HeapAlgorithms12.findClosestElements(new int[]{1,2,3,4,5}, 4, 3));

        /*System.out.println(HeapAlgorithms12.maxEvents(new int[][]{
                new int[]{1,2},
                new int[]{2,3},
                new int[]{3,4}
        }));*/
       /* System.out.println(HeapAlgorithms12.maxEvents3(new int[][]{
                new int[]{1,5},
                new int[]{1,5},
                new int[]{1,5},
                new int[]{2,3},
                new int[]{2,3}
        }));*/
        /*System.out.println(HeapAlgorithms12.maxEvents(new int[][]{
                new int[]{1,4},
                new int[]{4,4},
                new int[]{2,2},
                new int[]{3,4},
                new int[]{1,1}
        }));*/
        /*System.out.println(HeapAlgorithms12.maxEvents3(new int[][]{
                new int[]{1,2},
                new int[]{1,2},
                new int[]{3,3},
                new int[]{1,5},
                new int[]{1,5}
        }));*/
        /*System.out.println(HeapAlgorithms12.maxEvents3(new int[][]{
                new int[]{1,10},
                new int[]{2,2},
                new int[]{2,2},
                new int[]{2,2},
                new int[]{2,2}
        }));*/
        /*System.out.println(HeapAlgorithms12.maxEventsAttended(new int[][]{
                new int[]{7,11},
                new int[]{7,11},
                new int[]{7,11},
                new int[]{9,10},
                new int[]{9,11}
        }));*/

        /*System.out.println(Arrays.toString(HeapAlgorithms13.getTaskProcessingOrder(new int[][]{
                new int[]{1,2},
                new int[]{2,4},
                new int[]{3,2},
                new int[]{4,1}
        })));
        System.out.println(Arrays.toString(HeapAlgorithms13.getTaskProcessingOrder(new int[][]{
                new int[]{7,10},
                new int[]{7,12},
                new int[]{7,5},
                new int[]{7,4},
                new int[]{7,2}
        })));*/
        /*System.out.println(Arrays.toString(HeapAlgorithms13.getTaskProcessingOrder(new int[][]{
                new int[]{5,2},
                new int[]{7,2},
                new int[]{9,4},
                new int[]{6,3},
                new int[]{5,10},
                new int[]{1,1}
        })));*/

        //System.out.println(HeapAlgorithms13.furthestBuilding(new int[]{4,2,7,6,9,14,12}, 5, 1));
        //System.out.println(HeapAlgorithms13.furthestBuildingYouCanReach(new int[]{14,3,19,3}, 17, 0));

        /*System.out.println(HeapAlgorithms14.smallestChair(new int[][]{
                new int[]{1,4},
                new int[]{2,3},
                new int[]{4,6}
        }, 1));*/
        /*System.out.println(HeapAlgorithms14.smallestChair(new int[][]{
                new int[]{3,10},
                new int[]{1,5},
                new int[]{2,6}
        }, 0));*/

        /*System.out.println(HeapAlgorithms14.carPooling(new int[][]{
                new int[]{2,1,5},
                new int[]{3,3,7}
                }, 4));*/
        /*System.out.println(HeapAlgorithms14.carPooling(new int[][]{
                new int[]{2,1,5},
                new int[]{3,3,7}
        }, 5));*/

        //System.out.println(HeapAlgorithms15.maximalScoreAfterKOperations(new int[]{10,10,10,10,10}, 5));


    }
}
