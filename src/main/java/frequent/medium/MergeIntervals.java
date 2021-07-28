package frequent.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. Merge Intervals
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * this is simple code to merge overlapping arrays
 *
 * IMP-1 : Common questions
 */
public class MergeIntervals {

    public static void main(String []args) {
        int [][]intervals = {{1,3},{2,6},{8,10},{15,18}};
        MergeIntervals mI = new MergeIntervals();
        int[][] out = mI.merge(intervals);
        for (int i = 0  ; i < out.length; i++) {
            System.out.println(out[i][0] + " : " + out[i][1]);
        }
    }

    public int[][] merge(int[][] intervals) {
        //sort the array
        Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[0]));
        List<int []> list = new ArrayList<>();
        int x = intervals[0][0];
        int y = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= y) {
                y = Math.max(y, intervals[i][1]); //pick the y of new segment if it overlaps the current one
            } else {
                addToList(list, x, y); // if new segment x is beyond current y then add the previous segment to list
                x = intervals[i][0];
                y = intervals[i][1];
            }
        }
        addToList(list, x, y); //last segment is missing via above . hence add
        return list.toArray(new int[list.size()][]);
    }

    //helper method to merge x, y to list
    void addToList(List<int []> list, int x, int y) {
        int [] arr = new int[2];
        arr[0] = x;
        arr[1] = y;
        list.add(arr);
    }
}
