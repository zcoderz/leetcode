package redo;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersection {

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int firstSize = firstList.length;
        int secondSize = secondList.length;
        int firstIndex = 0;
        int secondIndex = 0;
        List<Integer []> list = new ArrayList<>();

        while (firstIndex < firstSize && secondIndex < secondSize) {
            int startP = Math.max(firstList[firstIndex][0], secondList[secondIndex][0]);
            int endP = Math.min(firstList[firstIndex][1], secondList[secondIndex][1]);

            if (startP <= endP) {
                list.add(new Integer[] {startP, endP});
            }

            if (firstList[firstIndex][1] <= endP) {
                firstIndex++;
            } else {
                secondIndex++;
            }
        }

        int [][] res = new int[list.size()] [2];
        for (int i =0; i < list.size(); i++) {
            res[i] = new int[2];
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }
}
