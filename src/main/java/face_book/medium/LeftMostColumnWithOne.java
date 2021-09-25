package face_book.medium;

import java.util.List;

/**
 * 1428. Leftmost Column with at Least a One
 * A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.
 *
 * Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it.
 * If such an index does not exist, return -1.
 *
 * You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
 *
 * BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 * BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols],
 * which means the matrix is rows x cols.
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.
 * Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * For custom testing purposes, the input will be the entire binary matrix mat.
 * You will not have access to the binary matrix directly.
 *
 * Example 1:
 *
 *
 * Input: mat = [[0,0],[1,1]]
 * Output: 0
 * question asks to find the left most column in a matrix that has a one
 *
 * trick: diagonal traversal!
 */
public class LeftMostColumnWithOne {

    public interface BinaryMatrix {
      public int get(int row, int col);
      public List<Integer> dimensions() ;
    };

    /**
     * really simple solution.
     *
     * with matrix and search, always think of travelling the matrix in diagonal from one of the corners
     *
     * @param binaryMatrix
     * @return
     */
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int rows = dim.get(0) -1;
        int cols = dim.get(1) - 1;
        int leftMost = -1;
        while (cols >=0 && rows >=0) {
            int val = binaryMatrix.get(rows, cols);
            if (val ==1) {
                leftMost = cols;
                cols--;
            } else {
                rows--;
            }
        }
        return leftMost;
    }

    public int leftMostColumnWithOneBinarySearch(LeftMostColumnWithOne.BinaryMatrix binaryMatrix) {
        List<Integer> dims = binaryMatrix.dimensions();
        int rows = dims.get(0);
        int cols = dims.get(1);

        int leftMost = cols-1;
        int row = rows - 1;
        boolean found = false;
        while (row >= 0) {
            if (binaryMatrix.get(row, leftMost) != 1) {
                row--;
                continue;
            }
            found = true;
            int newLeft = 0;
            int newRight = leftMost;
            while (newLeft < newRight) {
                int mid = newLeft + (newRight - newLeft) / 2;
                if (binaryMatrix.get(row, mid) == 1) {
                    newRight=mid;
                } else {
                    newLeft=mid+1;
                }
            }
            leftMost = newRight;
            row--;
        }
        return found ? leftMost : -1;
    }

}
