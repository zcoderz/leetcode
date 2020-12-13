package backtracking;

import java.util.*;

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

        int num = arr.size();
        List<List<Integer>> retList = new ArrayList<>();

        execute(num, 0, arr, retList);

        return retList;
    }

    void execute(int num, int index, List<Integer> nums, List<List<Integer>> retList) {
        if (index == num) {
            retList.add(new ArrayList<>(nums));
        }

        for (int i = index; i < num; i++) {
            Collections.swap(nums, index, i);
            execute(num, index + 1, nums, retList);
            Collections.swap(nums, index, i);
        }

    }

}
