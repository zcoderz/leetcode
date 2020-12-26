package frequent.medium;

import java.util.Arrays;

/**
 * interesting question to calculate size of squares
 */
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        if (matrix.length==0) return 0;
        int cols = matrix[0].length;
        //allocate a new array, set it to be 1 row and col more than the orig , this makes offset calculations easier
        int [][] matrixCopy = new int[matrix.length+1][cols+1];
        for (int[] ints : matrixCopy) {
            Arrays.fill(ints, 0);
        }
        int maxSquares = 0;
        //start with row and col 1
        for (int iRow =1; iRow <= matrix.length; iRow++) {
            for (int iCol =1; iCol <= cols; iCol++) {
                //look at the original matrix by subtracting 1 from row and col
                if (matrix[iRow-1][iCol-1] != '1') continue;
                //you only need the 3 cells indicated below, this can done via using
                //the copy as one dimensional array than 2 dimensional.
                //you can get left , down from the one dim array. for diag left you can keep track it in a
                //variable.....
                int leftCell = matrixCopy[iRow][iCol-1];
                int downCell = matrixCopy[iRow-1][iCol];
                int digLeft = matrixCopy[iRow-1][iCol-1];
                //your max Sq is min of adjacent sq values + 1 for the current
                //draw this on a white board and it will make sense
                int maxSq = Math.min(Math.min(leftCell, downCell), digLeft);
                maxSq++; //increment max square value
                matrixCopy[iRow][iCol] = maxSq; //set the calculated value in current matrix copy
                maxSquares = Math.max(maxSq, maxSquares); //update max
            }
        }
        return maxSquares*maxSquares;
    }
}
