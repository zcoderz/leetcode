package redo;

import java.util.*;

public class ThreeSum {

    public static void main(String [] args) {
        ThreeSum three = new ThreeSum();
        int [] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> res = three.threeSum(nums);
        int j = 1;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Set<Integer> dups = new HashSet<>();
        Set<List<Integer>> res = new HashSet<>();
        Map<Integer, Integer> alreadySeen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (dups.add(nums[i])) {
                for (int j = i + 1; j < nums.length; j++) {
                    int val = -1 * (nums[i] + nums[j]);
                    if (alreadySeen.containsKey(val) && alreadySeen.get(val) == i) {
                        Integer [] arr = {nums[i], nums[j], val};
                        Arrays.sort(arr);
                        res.add(Arrays.asList(arr));
                    }
                    alreadySeen.put(nums[j], i);
                }
            }
        }
        return new ArrayList<>(res);
    }
}
