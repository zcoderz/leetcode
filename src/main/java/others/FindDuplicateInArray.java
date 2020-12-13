package others;

/**
 * This is an interesting problem. it says find the duplicate in array assuming only one duplicate exists
 * can treat this as a way to detect start of a cycle , assuming only one cycle in path
 *
 * S = starting point
 * I = where cycle starts
 * F = distance between cycle and intersection
 * C = distance until cycle repeats
 * m = intersection point of the two pointers (see note below)
 * you run two pinters, where second pointer is twice as fast as the other.
 * since one is running twice as fast as other, at some point inside the cycle they will intersect.
 *
 * 2 * (F+m) = F + nC + m ==> F+m = nC (where N is number of loops the fast pointer has made around
 * circle before intersecting with slow pinter).
 *
 * Given F = nC -m above. you can start two pointers , going at same rate. one at S (start) and the other at
 * m (intersection point). After going the distance F, one of them will be at distance F and the other will be at F + m.
 * Since based on above F + m = nC , the second pointer would be at nC+F which is same as F.
 *
 *          ---m---
 *         |      |
 * S-------I      |C
 *     F  |       |
 *         -------
 */
public class FindDuplicateInArray {

    public static void main(String [] args) {
        int [] nums = {1,3,4,2,2};
        FindDuplicateInArray fdc = new FindDuplicateInArray();
        int i = fdc.findDuplicate(nums);
        System.out.println(i);
    }

    public int findDuplicate(int[] nums) {
        int i =0, j = 0;

        while (i!=j || (i==0)) {
            i = nums[i];
            j = nums[j]; j = nums[j]; //move j twice as fast as i
        }

        //j based on above is at point m based on above logic
        i = 0; //move i to start
        while (i!=j) {
            i = nums[i];
            j = nums[j];
        }
        return i;
    }
}
