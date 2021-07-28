package redo;

import java.util.LinkedList;
import java.util.List;

public class IntervalListIntersection {

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int iAIndex = 0;
        int iBIndex = 0;
        List<int []> list = new LinkedList<>();
        while (iAIndex < firstList.length && iBIndex < secondList.length)  {
            int left = Math.max(firstList[iAIndex][0], secondList[iBIndex][0]);
            int right = Math.min(firstList[iAIndex][1], secondList[iBIndex][1]);

            if (right >= left) {
                list.add(new int[] {left, right} );
            }

            if (firstList[iAIndex][1] < secondList[iBIndex][1]) {
                iAIndex++;
            } else {
                iBIndex++;
            }

        }
        int [][] res = new int[list.size()][2];
        int i = 0;
        for (int [] v : list) {
            res[i++] = v;
        }
        return res;
    }
}
