package redo;

import frequent.medium.LeftMostColumnWithOne;

import java.util.List;

public class LeftMostColWithAtleastAOne {


    public int leftMostColumnWithOne(LeftMostColumnWithOne.BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int rows = dim.get(0) -1;
        int cols = dim.get(1) - 1;
        int row = rows;
        int leftMostOne = cols;
        boolean found = false;
        while (row >= 0) {
            int left =0;
            int right = leftMostOne;
            if (binaryMatrix.get(row, leftMostOne) !=0) {
                while (right >= left) {
                    int mid = left + (right - left) / 2;
                    int v = binaryMatrix.get(row, mid);
                    if (v == 1) {
                        leftMostOne = mid;
                        found = true;
                        right = mid-1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
            row--;
        }
        if (!found) return -1;
        return leftMostOne;
    }
}
