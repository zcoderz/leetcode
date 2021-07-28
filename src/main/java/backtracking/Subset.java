package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * the algo using bits for the above is very neat. the backtracking solution is also great to understand backtracking
 *
 * IMP-1
 */
public class Subset {

    public static void main(String [] args) {

        int [] nums = {1, 2, 3};
        Subset subset = new Subset();
        List<List<Integer>> subsets = subset.subsetsBitmask(nums);
        for (List<Integer> set : subsets) {
            System.out.println(set);
        }

    }

    /**
     * a good example on back tracking
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsBacktrack(int[] nums) {
        List<List<Integer>> sets = new ArrayList<>();
        for (int i =0 ; i <= nums.length; i++) {
            backtrack(0, i, new ArrayList<>(), sets, nums);
        }
        return sets;
    }

    public void backtrack(int index, int targetSize, List<Integer> subSet, List<List<Integer>> allSubSets,
                          int [] nums) {
        if (subSet.size() == targetSize) {
            allSubSets.add(new ArrayList<>(subSet));
            return;
        }

        for (int i = index ; i < nums.length; i ++) {
            subSet.add(nums[i]);
            backtrack(i+1 , targetSize, subSet, allSubSets, nums);
            subSet.remove(subSet.size()-1);
        }
    }

    /**
     * this is an extremely neat solution. very interesting to learn it
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsBitmask(int[] nums) {
        int n = nums.length;
        List<List<Integer>> allSets = new ArrayList<>();
        //assume n is 2^3=8. 8 is bit 1000. 2^4 =16, 16 is bit 10000.
        //as you move from 8 to 16 you generate universe of bit combinations
        //0, 1, 10, 11, 100, 101. exclude the first char in the resulting string bit representation because it extends
        //beyond n, i.e 8 is 1000, you are only interested in the first 3 bits
        //use each index in bits that is 1 to indicate whether to include that number or not in your combination.
        //and you automatically get universe of combinations....
        for (int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n + 1); ++i) {
            String bitmask = Integer.toBinaryString(i).substring(1);
            List<Integer> curSet = new ArrayList<>();
            for (int j = 0; j  < n ; j++ ) {
                if (bitmask.charAt(j) == '1') {
                    curSet.add(nums[j]);
                }
            }
            allSets.add(curSet);
        }
        return allSets;
    }



    public List<List<Integer>> subsetsCascade(int[] nums) {
        List<List<Integer>> sets = new ArrayList<>();
        cascade(sets, nums);
        return sets;
    }

    void cascade(List<List<Integer>> sets, int [] nums) {
        List<Integer> empty = new ArrayList<>();
        sets.add(empty);

        for (int num: nums) {
            List<List<Integer>> newSets = new ArrayList<>();
            for (List<Integer> set : sets) {
                List<Integer> setCopy = new ArrayList<>(set);
                setCopy.add(num);
                newSets.add(setCopy);
            }
            sets.addAll(newSets);
        }

    }

}
