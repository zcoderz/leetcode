package backtracking;

import java.util.*;

/**
 * 46. Permutations
 *
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * IMP-1 really simple but important exercise to understand permutations
 */
public class Permutations {

    public static void main(String [] args) {
        Permutations perm = new Permutations();
        int [] nums = {1,2,3};
        List<List<Integer>> retList = perm.permute(nums);
        for (List<Integer> list : retList) {
            System.out.println(list.toString());
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> arr = new ArrayList<Integer> ();
        for (int num : nums) {
            arr.add(num);
        }
        int length = arr.size();
        List<List<Integer>> retList = new ArrayList<>();
        execute(length, 0, arr, retList);
        return retList;
    }


    /**
     * The idea of calculating the permutation is that you swap the current index with the second index (i below).
     * you recuse forward in the permutation until you reach end of the collection
     * on back tracking you reverse (undo) the swap previously done
     * @param length
     * @param index
     * @param nums
     * @param retList
     */
    void execute(int length, int index, List<Integer> nums, List<List<Integer>> retList) {
        if (index == length) {
            retList.add(new ArrayList<>(nums));
        }
        for (int i = index; i < length; i++) {
            Collections.swap(nums, index, i);
            execute(length, index + 1, nums, retList);
            Collections.swap(nums, index, i);
        }
    }
}
