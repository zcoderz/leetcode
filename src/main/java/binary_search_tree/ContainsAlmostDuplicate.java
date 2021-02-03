package binary_search_tree;


import java.util.TreeSet;

/**
 * 220. Contains Duplicate III
 * this is a very elegant solution to a seemingly complicated problem.
 *
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that
 * the absolute difference between nums[i] and nums[j] is at most t and the absolute
 * difference between i and j is at most k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 *
 */
public class ContainsAlmostDuplicate {

    public static void main(String [] args) {
        ContainsAlmostDuplicate contains = new ContainsAlmostDuplicate();
        int [] nums = {-2147483648,2147483647};
        boolean found = contains.containsNearbyAlmostDuplicate(nums, 1, 1);
        System.out.println(found);
    }

    /**
     * add to set , check prior and next val if diff within threshold return success
     * keep size of set trimmed to contain only k elements
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            Long v1 = set.ceiling((long) val);
            if ((v1 != null) && ((v1-val) <= t))
                return true;
            Long v2 = set.floor((long) val);
            if ((v2 != null) && ( (val-v2) <= t))
                return true;
            set.add((long) nums[i]);
            int p = i -k;
            if (p >=0) {
                set.remove((long) nums[p]);
            }
        }

        return false;
    }

}
