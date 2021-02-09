package recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * 70. Climbing Stairs
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * IMP-1: Simple question . The principle here gets reused in so many questions. Best to make sure you understand this
 * completely.
 */
public class ClimbStairs {

    private Map<Integer, Integer> countMap = new HashMap<>();


    /**
     * Simple approach : recurse to 0 stairs via 1 or 2 while storing possible combinations in a container for later
     * reuse
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n==0) {
            return 1;
        } else if (n < 0) {
            return 0;
        } else {
            Integer ways = countMap.get(n);
            if (ways != null) {
                return ways;
            }
            int one = climbStairs(n-1);
            int two = climbStairs(n-2);
            ways = one + two;
            countMap.put(n, ways);
            return ways;
        }
    }
}
