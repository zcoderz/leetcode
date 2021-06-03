package face_book.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
