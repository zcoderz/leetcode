package linkedin;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 47. Permutations II
 *
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 */
public class PermutationsTwo {

    public static void main(String [] args) {
        int []nums = {1,1,2};
        PermutationsTwo permTwo = new PermutationsTwo();
        List<List<Integer>> lists = permTwo.permuteUnique(nums);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int v = map.getOrDefault(num, 0) + 1;
            map.put(num, v);
        }

        List<List<Integer>> lists = new LinkedList<>();
        processPermutations(map, lists, new LinkedList<>(), nums.length);
        return lists;
    }

    void processPermutations(Map<Integer, Integer> map, List<List<Integer>> lists, LinkedList<Integer> currList,
                             int targetSz) {
        if (currList.size() == targetSz) {
            lists.add(new LinkedList<>(currList));
            return;
        }

        for (Map.Entry<Integer, Integer> mapEntry : map.entrySet()) {
            if (mapEntry.getValue() ==0) continue;
            int val = mapEntry.getValue();
            mapEntry.setValue(val-1);
            currList.add(mapEntry.getKey());
            processPermutations(map, lists, currList, targetSz);
            currList.removeLast();
            mapEntry.setValue(val);
        }
    }

}
