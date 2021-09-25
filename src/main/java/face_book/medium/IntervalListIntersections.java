package face_book.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 986. Interval List Intersections
 * Medium
 *
 * 2779
 *
 * 68
 *
 * Add to List
 *
 * Share
 * You are given two lists of closed intervals,
 * firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj].
 * Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 *
 * The intersection of two closed intervals is a set of real numbers that are either empty or
 * represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 *
 *
 * Example 1:
 *
 *
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 */
public class IntervalListIntersections {


    public static void main(String [] args) {

        IntervalListIntersections intersections = new IntervalListIntersections();
        int [][] first = {{0,2},{5,10},{13,23},{24,25}};
        int [][] second =   {{1,5},{8,12},{15,24},{25,26}};

        int [][] arr = intersections.intervalIntersection(first, second);
        for (int []v: arr ) {
            System.out.println(v[0] + ", " + v[1]);
        }
    }

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int first = 0, second =0;
        List<int[]> ans = new ArrayList();

        while (first < firstList.length && second < secondList.length) {

            int low = Math.max(firstList[first][0], secondList[second][0]);
            int high = Math.min(firstList[first][1], secondList[second][1]);

            if (high >= low) {
                ans.add(new int[] {low, high});
            }

            if (firstList[first][1] > secondList[second][1]) {
                second++;
            } else {
                first++;
            }

        }

        return ans.toArray(new int[ans.size()][]);
    }
}
