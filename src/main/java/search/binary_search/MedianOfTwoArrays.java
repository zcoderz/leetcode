package search.binary_search;


/**
 4. Median of Two Sorted Arrays

 Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

 Follow up: The overall run time complexity should be O(log (m+n)).


 Example 1:

 Input: nums1 = [1,3], nums2 = [2]
 Output: 2.00000
 Explanation: merged array = [1,2,3] and median is 2.
 Example 2:

 Input: nums1 = [1,2], nums2 = [3,4]
 Output: 2.50000
 Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

 IMP-1: Very Common question, excellent practice for binary search
 */
class MedianOfTwoArrays {

    public static void main(String[] args) {
        int[] nums1 = {7,8};
        int[] nums2 = {0,1,2};
        double med = findMedianSortedArrays(nums1, nums2);
        System.out.println(med);
    }

    /**
     * This is a very clever solution from leet code discussion.
     * see detailed description here :
     * https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/908004/Python-Binary-Search-(Diagram)
     * Essentially you are doing Binary Search on the smaller of the two arrays
     * while comparing Left,Right on each of the sub arrays in the two arrays
     * @param nums1
     * @param nums2
     * @return
     */
    static double  findMedianSortedArrays(int [] nums1, int [] nums2) {

        //do the binary search based off the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int lo =0; int hi = nums1.length;

        int totalSize = nums1.length + nums2.length;
        //adding 1 to totalSize so in case of odd numbers will pick the max number from left of the two partitions
        //for that to happen, right must be ahead of the median on one of the partitions
        //and thus the partition size needs to be include the right of the two partitions must be extended by 1

        //i,e for arrays [7,8] & [1,2,3] median is 3. therefore need to choose such that left of right array is
        //included in the partition. in this case, p1 is 0, p2 needs to be 3, for that to happen partition size must
        //be 3, which is (left Size + right Size + 1) / 2

        int partitionSize = (totalSize + 1) / 2;

        while (lo <= hi) {
            int p1 = (lo + hi) / 2;
            int p2 = partitionSize - p1;

            //having sentinels of Integer MIN and MAX help simplify boundary cases - i,e when median is at edge of an
            //array
            int numALeft = p1 > 0 ? nums1[p1-1] : Integer.MIN_VALUE;
            int numAright = p1 < nums1.length ? nums1[p1] : Integer.MAX_VALUE;

            int numBLeft = p2 > 0 ? nums2[p2-1] : Integer.MIN_VALUE;
            int numBRight = p2 < nums2.length ? nums2[p2] : Integer.MAX_VALUE;

            //at this point you are either moving p1 left or right
            //by moving lo right of p1 or hi left of p1
            if (numALeft > numBRight) {
                hi = p1 - 1; //moving p1 left
            } else if (numBLeft > numAright) {
                lo = p1 + 1; //moving p1 right
            } else {
                //the partitions at this point are correct
                if ((totalSize %2) ==0 ) {
                    return (Math.max(numALeft, numBLeft) + Math.min(numAright, numBRight)) / 2.0;
                } else {
                    return Math.max(numALeft, numBLeft);
                }
            }
        }
        return -1D;
    }
}
