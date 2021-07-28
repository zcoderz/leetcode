package google.medium;

import java.util.Arrays;

/**
 * 1477. Find Two Non-overlapping Sub-arrays Each With Target Sum
 *
 * Given an array of integers arr and an integer target.
 *
 * You have to find two non-overlapping sub-arrays of arr each with sum equal target.
 * There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.
 *
 * Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,2,2,4,3], target = 3
 * Output: 2
 * Explanation: Only two sub-arrays have sum = 3 ([3] and [3]). The sum of their lengths is 2.
 *
 * IMP-1: Extremely sharp question. Do this again.
 */
public class FindTwoNonOverlappingSubArraysWithTargetSum {

    public  static void main(String [] args) {
        FindTwoNonOverlappingSubArraysWithTargetSum nonOverlapping = new FindTwoNonOverlappingSubArraysWithTargetSum();
        int [] arr = {1,2,2,3,2,6,7,2,1,4,8};
        int len = nonOverlapping.minSumOfLengths(arr, 5);
        System.out.println(len);

        int [] arr2 = {3,1,1,1,5,1,2,1};
        len = nonOverlapping.minSumOfLengthsFast(arr2, 3);
        System.out.println(len);

    }

    /**
     * A very smart solution for the problem using prefix and suffix arrays
     * @param arr
     * @param target
     * @return
     */
    public int minSumOfLengths(int[] arr, int target) {
        int [] prefix = new int[arr.length]; //array to store min length while going left to right
        int [] suffix = new int[arr.length]; //array to store min length while going right to left
        int EDGE = Integer.MAX_VALUE/2;
        Arrays.fill(prefix, EDGE); //initialize an edge value to indicate not a solution at an index
        Arrays.fill(suffix, EDGE);
        int sum = 0;
        int left = 0;
        for (int i =0; i < arr.length; i++) {
            sum += arr[i];
            while (sum > target) {
                //this approach is critical. essentially go right, but if you cross target , start dropping items
                // from left until you become less than target. thus you will either match target or your sum will be
                // less than target
                sum -= arr[left++];
            }

            if (sum == target) {
                //calculate distance
                int len = i-left+1;
                //record in respective arrays
                prefix[i] = len;
                suffix[left] = len;
            }
        }

        //update prefix for min len going left to right
        for (int i =1; i < arr.length; i++) {
            prefix[i] = Math.min(prefix[i], prefix[i-1]);
        }
        //do same for suffix going right to left
        for (int i =arr.length-2; i >=0 ; i--) {
            suffix[i] = Math.min(suffix[i], suffix[i+1]);
        }

        int ans = Integer.MAX_VALUE;
        //calculate ans while iterating on arrays as best combination for prefix and suffix
        for (int i = 0; i < arr.length-1; i++) {
            ans = Math.min(ans, prefix[i] + suffix[i+1]);
        }
        return (ans > EDGE) ? -1 : ans;
    }

    /**
     * Extremely sharp solution similar to the one above, except that it uses a single array for same
     *
     * @param arr
     * @param target
     * @return
     */
    public int minSumOfLengthsFast(int[] arr, int target) {
        int [] best = new int[arr.length];
        int EDGE = Integer.MAX_VALUE/2;
        Arrays.fill(best, EDGE);
        int sum = 0;
        int left = 0;
        int ans = Integer.MAX_VALUE;
        for (int i =0; i < arr.length; i++) {
            sum += arr[i];
            while (sum > target) {
                sum -= arr[left++];
            }
            if (sum == target) {
                if (left > 0 && best[left - 1] != EDGE) {
                    ans = Math.min(ans, best[left - 1] + i - left + 1);
                }
                best[i] = i - left + 1;
            }
            if (i > 0) {
                best[i] = Math.min(best[i - 1], best[i]);
            }
        }
        return (ans > EDGE) ? -1 : ans;
    }

}
