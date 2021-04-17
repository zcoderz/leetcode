package frequent.hard;

import java.util.*;

/**
 * 315. Count of Smaller Numbers After Self
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example 1:
 *
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 * IMP-3: Common question
 */
public class CountSmaller {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = nums.length - 1; i >= 0; i--) {
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
