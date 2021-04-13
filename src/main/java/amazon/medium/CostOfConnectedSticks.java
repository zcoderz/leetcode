package amazon.medium;

import java.util.PriorityQueue;

/**
 * 1167. Minimum Cost to Connect Sticks
 * You have some number of sticks with positive integer lengths. These lengths are given as an array sticks,
 * where sticks[i] is the length of the ith stick.
 * You can connect any two sticks of lengths x and y into one stick by paying a cost of x + y. You must connect all
 * the sticks until there is only one stick remaining.
 * Return the minimum cost of connecting all the given sticks into one stick in this way.

 * Example 1:

 * Input: sticks = [2,4,3]
 * Output: 14
 * Explanation: You start with sticks = [2,4,3].
 * 1. Combine sticks 2 and 3 for a cost of 2 + 3 = 5. Now you have sticks = [5,4].
 * 2. Combine sticks 5 and 4 for a cost of 5 + 4 = 9. Now you have sticks = [9].
 * There is only one stick left, so you are done. The total cost is 5 + 9 = 14.
 * Example 2:

 * Input: sticks = [1,8,3,5]
 * Output: 30
 * Explanation: You start with sticks = [1,8,3,5].
 * 1. Combine sticks 1 and 3 for a cost of 1 + 3 = 4. Now you have sticks = [4,8,5].
 * 2. Combine sticks 4 and 5 for a cost of 4 + 5 = 9. Now you have sticks = [9,8].
 * 3. Combine sticks 9 and 8 for a cost of 9 + 8 = 17. Now you have sticks = [17].
 * There is only one stick left, so you are done. The total cost is 4 + 9 + 17 = 30.
 *
 IMP-2
 */


public class CostOfConnectedSticks {

    public static void main(String[] args) {
        int[] lens = {1, 8, 3, 5};
        //int [] lens = {3354,4316,3259,4904,4598,474,3166,6322,8080,9009};
        CostOfConnectedSticks cS = new CostOfConnectedSticks();
        int cost = cS.connectSticks(lens);
        System.out.println(cost);
    }

    /**
     * use a pq to track min stick. get two smallest from pq and work on them
     * <p>
     * problem is a lil tricky where you have to remember that the new stick created by merging the previous two
     * smallest is just another stick which needs to be merged based on order determined by its length relative to other
     * sticks. cant just sort and calculate left to right......
     *
     * @param sticks
     * @return
     */
    public int connectSticks(int[] sticks) {
        int cost = 0;
        if (sticks.length <= 1) return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : sticks) {
            pq.add(i);
        }
        while (pq.size() >= 2) {
            int s1 = pq.remove();
            int s2 = pq.remove();
            int len = s1 + s2;
            pq.add(len);
            cost += len;
        }
        return cost;
    }
}
