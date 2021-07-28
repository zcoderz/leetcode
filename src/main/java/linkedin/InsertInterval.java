package linkedin;

import utils.Pair;

import java.util.*;

public class InsertInterval {

    public static void main(String [] args) {

        int [][] arr = {{1,2},{3,5},{6,7},{8,10},{12,16}};

        InsertInterval insert = new InsertInterval();
        int [] newInterval =  {4,8};
        int [][] ret = insert.insert(arr, newInterval);
        for (int [] v : ret) {
            System.out.println(v[0] + " , " +  v[1]);
        }
    }

    public int[][] insert(int[][] intervals, int [] newInterval) {
        Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[0]));
        LinkedList<int[]> list = new LinkedList<>();
        boolean inserted = false;
        for (int [] interval : intervals) {
            if (newInterval[0] <= interval[0]) {
                inserted = true;
                list.add(newInterval);
            }
            list.add(interval);
        }
        if (!inserted) list.add(newInterval);

        return merge(list.toArray(new int[list.size()][]));
    }


    public int[][] merge(int[][] intervals) {
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
