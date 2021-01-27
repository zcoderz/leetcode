package google.medium;

import java.util.*;


/**
 * 1296. Divide Array in Sets of K Consecutive Numbers
 *
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array
 * into sets of k consecutive numbers
 * Return True if it is possible. Otherwise, return False.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,3,4,4,5,6], k = 4
 * Output: true
 * Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
 *
 */
public class DivideArrayInSet {

    public static void main(String [] args) {
        DivideArrayInSet divide = new DivideArrayInSet();
        int [] nums = {1,1,1,2,2,2,3,3,3};
        int  k = 3;
        boolean pos = divide.isPossibleDivideFaster(nums, k);
        System.out.println(pos);

        int [] nums2 = {1,2,3,4};
        int  k1 = 3;
        boolean pos1 = divide.isPossibleDivide(nums2, k1);
        System.out.println(pos1);
    }

    /**
     * use a tree map to manage count and the fact that its ordered
     * @param nums
     * @param k
     * @return
     */
    public boolean isPossibleDivide(int[] nums, int k) {
        TreeMap<Integer, Integer> treeNums = new TreeMap<>();
        for (int num : nums) {
            int ct = treeNums.getOrDefault(num, 0);
            treeNums.put(num, ct+1);

        }

        while (!treeNums.isEmpty()) {
            int val;
            int start = 0;
            int prior = Integer.MIN_VALUE;
            while (start < k && !treeNums.isEmpty()) {
                if (prior == Integer.MIN_VALUE) {
                    val = treeNums.firstKey();
                } else {
                    val = prior + 1;
                }
                Integer count = treeNums.get(val);
                if (count == null) {
                    return false;
                }
                count--;
                if (count == 0) {
                    treeNums.remove(val);
                } else {
                    treeNums.put(val, count);
                }
                start++;
                prior = val;
            }
            if (start != k) {
                return false;
            }
        }
        return true;
    }

    /**
     * although the Big(O) time below is O(Nlog(N)) similar to that of the above, but the fact
     * that we use a hash map practically runs in a much faster solution.
     * the solution is fairly smart in that it keeps track of how many indices to move the incoming array forward
     * and intelligently keeps track of the next start so we can keep utilizing the hash map efficiently
     * @param nums
     * @param k
     * @return
     */
    public boolean isPossibleDivideFaster(int[] nums, int k) {
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for(int i = 0; i<nums.length; ) {
            int inc = 0;
            int num = nums[i];
            Integer nextBegin = null;
            while(inc == 0 || nextBegin != null) {
                if(nextBegin != null) {
                    num = nextBegin;
                }
                nextBegin = null;
                for(int j = 1; j<=k; j++) {
                    inc++;
                    Integer count = map.get(num);
                    if(count != null && count > 0) {
                        map.put(num, count-1);
                        if(count - 1 != 0 && nextBegin == null) {
                            nextBegin = num;
                        }
                    } else {
                        return false;
                    }
                    num++;
                }

            }
            i += inc;
        }
        return true;
    }

    /**
     * the below code marginally passes timeout on leetcode.
     * expect the slowness occurs due to leet code having multiple duplicate numbers in
     * their test case array so the code has to
     * traverse the linked list multiple times .
     * but the solution is very straightforward
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean isPossibleDivideTimesout(int[] nums, int k) {
        Arrays.sort(nums);
        List<Integer> listNums = new LinkedList<>();
        for (int num : nums) {
            listNums.add(num);
        }
        while (!listNums.isEmpty()) {
            ListIterator<Integer> iter = listNums.listIterator();
            int start = 0;
            int prior = Integer.MIN_VALUE;
            while (start < k && iter.hasNext()) {
                int val = iter.next();
                if (prior == Integer.MIN_VALUE || val == prior+1) {
                    prior= val;
                    iter.remove();
                    start++;
                }
            }
            if (start != k) {
                return false;
            }
        }
        return true;
    }
}
