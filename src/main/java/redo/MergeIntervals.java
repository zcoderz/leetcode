package redo;

import java.util.*;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> orderedIntervals = new ArrayList<>();

        for (int [] interv : intervals) {
            int last = orderedIntervals.size()-1;
            if (orderedIntervals.isEmpty()) {
                orderedIntervals.add(new int[] {interv[0], interv[1]});
            } else if (orderedIntervals.get(last)[1] >= interv[0]) {
                orderedIntervals.get(last)[1] = Math.max(orderedIntervals.get(last)[1], interv[1]);
            } else {
                orderedIntervals.add(new int[] {interv[0], interv[1]});
            }
        }
        return orderedIntervals.toArray(new int[orderedIntervals.size()][]);
    }
}
