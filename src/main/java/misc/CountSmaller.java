package misc;

import java.util.*;

public class CountSmaller {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = nums.length-1; i >= 0; i--) {
            Map<Integer, Integer> numLower = map.headMap(nums[i], false);
            if (null != numLower) {
                int count = 0;
                for (Integer iNum : numLower.values()) {
                    count += iNum;
                }
                counts.add(count);
            } else {
                counts.add(0);
            }
            int iCount = map.getOrDefault(nums[i], 0);
            iCount++;
            map.put(nums[i], iCount);
        }
        Collections.reverse(counts);
        return counts;
    }
}
