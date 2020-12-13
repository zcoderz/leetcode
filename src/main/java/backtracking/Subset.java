package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    int subSetSize;


    public static void main(String [] args) {

        int [] nums = {1, 2, 3};
        int n = nums.length;
        List<List<Integer>> allSets = new ArrayList<>();
        for (int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n + 1); ++i) {
            // generate bitmask, from 0..00 to 1..11
            String bitmask = Integer.toBinaryString(i);
            List<Integer> curSet = new ArrayList<>();
            for (int j = 0; j  < n ; j++ ) {
                if (bitmask.charAt(j) == '1') {
                    curSet.add(nums[j]);
                }
            }
            allSets.add(curSet);
        }
        Subset subset = new Subset();

        List<List<Integer>> subsets = subset.subsetsBacktrack(nums);
        for (List<Integer> set : subsets) {
            System.out.println(set);
        }

    }


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
