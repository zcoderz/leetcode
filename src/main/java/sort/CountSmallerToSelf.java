package sort;

import utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 315. Count of Smaller Numbers After Self
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 * https://leetcode.com/explore/interview/card/top-interview-questions-hard/118/trees-and-graphs/851/discuss/76584/Mergesort-solution
 *
 * IMP-1 : Very clever use of merge sort. Can be applied to other problems. Good to practice..
 */
public class CountSmallerToSelf {

    public static void main(String[] args) {
        CountSmallerToSelf ms = new CountSmallerToSelf();
        //int[] nums = {10, 1, 2, 5, 7, 3, 4, 44, 99};
        int[] nums = {2,5,3,1};
        List<Integer> count =  ms.countSmaller(nums);
        for (Integer num : count) {
            System.out.println(num);
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Pair<Integer, Integer>[] arr = new Pair [nums.length];
        Integer[] smaller = new Integer[nums.length];
        Arrays.fill(smaller, 0);
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new Pair<>(i, nums[i]);
        }
        mergeSort(arr, smaller);
        res.addAll(Arrays.asList(smaller));
        return res;
    }

    /**
     * Very clever solution using merge sort originally found by Stefan Pochmann
     *
     *
     * @param arr
     * @param smaller
     * @return
     */
    private Pair<Integer, Integer>[] mergeSort(Pair<Integer, Integer>[] arr, Integer[] smaller) {
        if (arr.length <= 1) {
            return arr;
        }
        int mid = arr.length / 2;
        Pair<Integer, Integer>[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid), smaller);
        Pair<Integer, Integer>[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length), smaller);
        for (int i = 0, j = 0; i < left.length || j < right.length;) {
            //pick the smaller element from the left or right array
            if (j == right.length || i < left.length && left[i].second <= right[j].second) {
                //when we enter this case left is less than right or we have reached end of right
                //so we pick the left value and use the index of the left value 'left[i].first' and update it by j
                //updating by j because j is the number of values on the right that were less than the given value
                arr[i + j] = left[i];
                smaller[left[i].first] += j;
                i++;
            } else {
                //will enter this case when right is less than left, in this case we pick the right value
                //and increment count of j , incrementing j as it signifies how many values in right array
                //were less than the given index in left array
                arr[i + j] = right[j];
                j++;
            }
        }
        return arr;   
    }


}