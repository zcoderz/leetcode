package redo;

import java.util.ArrayList;
import java.util.List;

public class IntervalIntersectTwo {

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int firstIndex=0, secondIndex = 0;
        List<int []> intersect = new ArrayList<>();

        while (firstIndex < firstList.length && secondIndex < secondList.length) {
            int left = Math.max(firstList[firstIndex][0], secondList[secondIndex][0]);
            int right = Math.min(firstList[firstIndex][1], secondList[secondIndex][1]);
            if (left <= right) {
                intersect.add(new int[] {left, right});
            }

            if (firstList[firstIndex][1] < secondList[secondIndex][1]) {
                firstIndex++;
            } else {
                secondIndex++;
            }

        }

        int [][] ret = new int[intersect.size()][2];

        for (int i = 0; i < intersect.size(); i++) {
            ret[i] = intersect.get(i);
        }

        return ret;
    }
}
