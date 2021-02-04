package frequent.medium;

import java.util.*;

/**
 * 362. Design Hit Counter
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 *
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are
 * being made to the system in chronological order (ie, the timestamp is monotonically increasing).
 * You may assume that the earliest timestamp starts at 1.
 *
 * It is possible that several hits arrive roughly at the same time.
 *
 * Example:
 *
 * HitCounter counter = new HitCounter();
 *
 * // hit at timestamp 1.
 * counter.hit(1);
 *
 * // hit at timestamp 2.
 * counter.hit(2);
 *
 * // hit at timestamp 3.
 * counter.hit(3);
 *
 * // get hits at timestamp 4, should return 3.
 * counter.getHits(4);
 *
 * // hit at timestamp 300.
 * counter.hit(300);
 *
 * // get hits at timestamp 300, should return 4.
 * counter.getHits(300);
 *
 * // get hits at timestamp 301, should return 3.
 * counter.getHits(301);
 * Follow up:
 * What if the number of hits per second could be very large? Does your design scale?
 *
 * method requests to return number of hits within the last 5 mins
 * the method was assuming that calls are always monotonically increasing in time so could user a queue
 * for a more optimized solution but the below approach is more generic
 *
 * IMP-1 : Common question
 */
public class HitCounter {

    public static void main(String [] args) {
        HitCounter counter = new HitCounter();
        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        int count = counter.getHits(4);
        System.out.println(count);
        //counter.hit(4);
        counter.hit(300);
        count = counter.getHits(300);
        System.out.println(count);

        count = counter.getHits(301);
        System.out.println(count);
    }


    TreeMap<Integer, Integer> set = new TreeMap<>();

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int count = set.getOrDefault(timestamp, 0);
        set.put(timestamp, count+1);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int prior = timestamp - (60*5);
        //search the lower and higher edges to find the count.
        //ideally would have used a single method that would have returned the number of rows in between the
        //time stamp and prior.
        SortedMap<Integer, Integer> less = set.headMap(timestamp, true);
        SortedMap<Integer, Integer> tailMap =  less.tailMap(prior);
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : tailMap.entrySet()) {
            if (entry.getKey().equals(prior)) continue;
            count += entry.getValue();
        }
        return count;
    }

}
