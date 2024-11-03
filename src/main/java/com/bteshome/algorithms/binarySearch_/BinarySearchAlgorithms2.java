package com.bteshome.algorithms.binarySearch_;

public class BinarySearchAlgorithms2 {
    /**
     * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
     * NOTE - this binary search based solution is relatively inefficient.
     * For a faster solution, take a look at the one in TwoPointers.
     */
    public static int[] twoSumII(int[] numbers, int target) {
        if (numbers == null) {
            return null;
        }

        for (int i = 0; i < numbers.length; i++) {
            int current = numbers[i];
            int other = target - current;
            if (other < current) {
                return null;
            }

            int low = i + 1;
            int high = numbers.length - 1;

            while (low <= high) {
                int mid = (low + high) / 2;
                if (numbers[mid] == other) {
                    return new int[]{i + 1, mid + 1};
                } else if (other < numbers[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }

        return null;
    }

    /**
     * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
     * TODO - this runs pretty fast. However, there is still room for improvement -
     *        the second phase of the search - expanding the range out - can be improved.
     */
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null) {
            return new int[]{-1, -1};
        }

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                low = mid;
                high = mid;
                break;
            }
        }

        while (low > 0 && nums[low - 1] == target) {
            low--;
        }

        while (high < nums.length - 1 && nums[high + 1] == target) {
            high++;
        }

        if (low <= high) {
            return new int[]{low, high};
        } else {
            return new int[]{-1, -1};
        }
    }

    /**
     * https://leetcode.com/problems/search-in-rotated-sorted-array/
     */
    public static int searchInRotatedSortedArray(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;
        int pivot = searchInRotatedSortedArray_SearchPivot(nums);

        int searchLeft = searchInRotatedSortedArray_SearchTarget(nums, target, 0, pivot - 1);
        if (searchLeft != -1) {
            return searchLeft;
        }

        return searchInRotatedSortedArray_SearchTarget(nums, target, pivot, nums.length - 1);
    }

    private static int searchInRotatedSortedArray_SearchPivot(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int mid = -1;

        while (low < high) {
            mid = (low + high) / 2;
            int midValue = nums[mid];
            int rightMostValue = nums[high];
            if (midValue > rightMostValue) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private static int searchInRotatedSortedArray_SearchTarget(int[] nums, int target, int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2;
            int midValue = nums[mid];
            if (target == midValue) {
                return mid;
            } else if (target < midValue) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }
}