package binary_search_tree;


import java.util.TreeSet;

/**
 * this is a very elegant solution to a seemingly complicated problem.
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
