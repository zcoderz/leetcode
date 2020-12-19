package frequent.general.medium;

import java.util.List;

/**
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

}
