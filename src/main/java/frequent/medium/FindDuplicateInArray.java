package frequent.medium;

/**
 *287. Find the Duplicate Number
 *
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 *
 * IMP-1: Tortoise and Hare problems are common. Need to practice this.
 *
 *
 * Solved via : Floyd's Tortoise and Hare (Cycle Detection)
 * This is an interesting problem. it says find the duplicate in array assuming only one duplicate exists can treat this
 * as a way to detect start of a cycle , assuming only one cycle in path
 * <p>
 * S = starting point, I = where cycle starts, F = distance between cycle and start, C = distance until cycle
 * repeats, m = intersection point of the two pointers (see note below) you run two pointers, where second pointer is
 * twice as fast as the other. since one is running twice as fast as other, at some point inside the cycle they will
 * intersect.
 * <p>
 * 2 * (F+m) = F + nC + m ==> F+m = nC (where N is number of loops the fast pointer has made around circle before
 * intersecting with slow pinter).
 * Image on leet code example describes it nicely
 * <p>
 *
 * Given F = nC -m above. you can start two pointers , going at same rate. one at S (start) and the other at m
 * (intersection point). After going the distance F, one of them will be at distance F and the other will be at F + m.
 * Since based on above F + m = nC , the second pointer would be at nC+F which is same as F.
 * <p>
 *
 */
public class FindDuplicateInArray {

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        FindDuplicateInArray fdc = new FindDuplicateInArray();
        int i = fdc.findDuplicate(nums);
        System.out.println(i);
    }

    public int findDuplicate(int[] nums) {
        int i = 0, j = 0;

        while (i != j || (i == 0)) {
            i = nums[i];
            j = nums[j];
            j = nums[j]; //move j twice as fast as i
        }

        //j based on above is at point m based on above logic
        i = 0; //move i to start
        while (i != j) {
            i = nums[i];
            j = nums[j];
        }
        return i;
    }
}
