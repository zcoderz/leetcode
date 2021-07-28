package redo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SubArraySum {

   public static void main(String [] args) {
       SubArraySum sum = new SubArraySum();
       boolean v = sum.checkSubarraySum(new int[] {23,2,5,6,7}, 6);
       System.out.println(v);
   }


    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int currSum = 0;
        for (int i =0; i < nums.length; i++) {
            currSum += nums[i];
            int v = currSum % k;
            if (map.containsKey(v) && (i - map.get(v)  > 1)) {
                return true;
            }
            map.put(v, map.getOrDefault(v, i));
        }

        return false;
    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        int sum = 0;
        for (int i =0; i < nums.length; i++) {
            sum += nums[i];
            int diff = sum-k;
            count += map.getOrDefault(diff, 0);
            int v = map.getOrDefault(sum, 0) + 1;
            map.put(sum, v);
        }
        return count;
    }



    public int subarraySumSlow(int[] nums, int k) {
        int count = 0;
        for (int end = 0; end < nums.length; end++) {
            int sum = 0;
            for (int i = end; i < nums.length; i++) {
                sum += nums[i];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}
