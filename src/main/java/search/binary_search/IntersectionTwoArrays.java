package search.binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 350. Intersection of Two Arrays II
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 *
 */
public class IntersectionTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        List<Integer> intersection = new ArrayList<>();

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                intersection.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            }
        }

        int[] arr = new int[intersection.size()];
        for (i = 0; i < intersection.size(); i++) {
            arr[i] = intersection.get(i);
        }
        return arr;
    }
}
