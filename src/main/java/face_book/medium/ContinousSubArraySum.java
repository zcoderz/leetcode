package face_book.medium;

import java.util.HashMap;
import java.util.Map;

public class ContinousSubArraySum {


    public static void main(String [] args) {
        int [] arr = {23,2,4,6,7};
        int k = 6;
        ContinousSubArraySum continous = new ContinousSubArraySum();
        boolean valid = continous.checkSubarraySum(arr, k);
        System.out.println(valid);
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> modMap = new HashMap<>();
        modMap.put(0,-1); //in case mod is 0 --> i,e {2,4,6} with k = 6
        int sum = 0;
        for (int i =0; i < nums.length; i++) {
            sum = (k ==0) ? nums[i] : (sum + nums[i]) % k;
            if (modMap.containsKey(sum) && i-modMap.get(sum) > 1) {
                return true;
            }
            modMap.put(sum,  modMap.getOrDefault (sum, i));
        }
        return false;
    }
}
