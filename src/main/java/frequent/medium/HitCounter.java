package frequent.medium;

import java.util.*;

/**
 * method requests to return number of hits within the last 5 mins
 * the method was assuming that calls are always monotonically increasing in time so could user a queue
 * for a more optimized solution but the below approach is more generic
 *
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
