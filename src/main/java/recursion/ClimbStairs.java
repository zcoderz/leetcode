package recursion;

import java.util.HashMap;
import java.util.Map;

public class ClimbStairs {

    private Map<Integer, Integer> countMap = new HashMap<>();

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
