package amazon.medium;

import java.util.PriorityQueue;

/**
 * calculate min cost to merge sticks into a single stick where cost is sum of len stick 1 + len stick 2
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
