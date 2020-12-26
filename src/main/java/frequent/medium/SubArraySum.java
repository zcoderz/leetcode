package frequent.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubArraySum {

    public static void main(String [] args) {
        //int [] nums = {1,2,3};
        //int [] nums = {3};
        int [] nums = {1,2,-3,3};
        SubArraySum sum = new SubArraySum();
        int v = sum.subarraySum(nums, 3);
        System.out.println(v);
    }

    /**
     * simple and beautiful implementation for calculating number of sub arrays that add to given sum
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        //add 0 so that as sub arrays add up to the number in question the diff between sum and k
        //would be 0 which needs to be calculated
        map.put(0, 1);
        //this loop is much simpler than the below complicated implementation
        //because you are building the map as you are iterating through the array
        //hence the count of sums in the map up to now only include indices we have seen so far
        //as we can have negative numbers in process they can create scenarios where
        //including sums on the right of the index can give incorrect answers
        //i,e if looking for sum as 8
        //in below when on index 3, we are looking for #3 which can occur on indexes 4 and 1
        //including that on index 4 will give incorrect numbers as 0-4 doesnt add to 8
        //[0,1,2,3,4,5]
        //[1,2,3,5,-8,8]

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            //use map to keep track # of sub arrats that add to the sum we are looking for
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    /**
     * more complicated implementation as we have to remember the sum's index locations
     * @param nums
     * @param k
     * @return
     */
    public int subarraySumExtraComplicated(int[] nums, int k) {

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        map.computeIfAbsent(0, (l) -> new ArrayList<>()).add(-1);
        int sum = 0;
        for (int i =0; i < nums.length; i++) {
            sum += nums[i];
            List<Integer> val = map.computeIfAbsent(sum, (l) -> new ArrayList<>());
            val.add(i);
            nums[i] = sum;
        }

        int numArr = 0;
        for (int j = nums.length-1; j >= 0; j--) {
            sum = nums[j];
            int lookupV = sum - k;
            List<Integer> v = map.get(lookupV);
            if (v != null) {
                    int iIndex = v.size() - 1;
                    while (iIndex >= 0 && v.get(iIndex) >= j) {
                        iIndex--;
                    }
                    numArr += iIndex + 1;
            }

        }
        return numArr;
    }
}
