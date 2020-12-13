package search.binary_search;

/**
 * Given an array of integers that is already sorted in ascending order,
 * find two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that
 * they add up to the target, where index1 must be less than index2.
 */

public class ArraySearchTargetSum {

    public static void main(String [] args) {
        ArraySearchTargetSum as = new ArraySearchTargetSum();
        int [] nums = {2,3,4,7,11,17,18};
        int[] ind = as.twoSum(nums, 14);
        System.out.println(ind[0] + " , " +  ind[1]);
    }

    public int[] twoSum(int[] numbers, int target) {
        int left =0;
        int right = numbers.length-1;

        for (; left < right; left++) {
            int iLeft = numbers[left];
            int numToFind = target-iLeft;
            right = findIndex(numbers, numToFind, left+1, right);
            if (numbers[right]==numToFind) {
                int [] ret = new int[2] ; ret[0]= left+1; ret[1] = right+1;
                return ret;
            }
        }
        return null;
    }

    int findIndex(int[] numbers, int target, int left, int right) {

        while (left < right) {
            int mid = left + (right-left)/2;
            if (numbers[mid] < target) {
                left = mid+1;
            } else if (numbers[mid] > target) {
                right = mid-1;
            } else {
                return mid;
            }
        }
        if (numbers[left] > target) {
            return  left;
        } else {
            return Math.min(left+1, right);
        }
    }
}
