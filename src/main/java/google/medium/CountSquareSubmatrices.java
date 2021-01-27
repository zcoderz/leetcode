package google.medium;

/**
 * 1277. Count Square Submatrices with All Ones
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 *
 */
public class CountSquareSubmatrices {

    public static void main(String [] args) {
        int [][] matrix = {
                            {0,1,1,1},
                            {1,1,1,1},
                            {0,1,1,1}
                          };
        CountSquareSubmatrices count = new CountSquareSubmatrices();
        int ctSquares = count.countSquares(matrix);
        System.out.println(ctSquares);
    }

    /**
     * matrix count is dependent on the left, below , and diagonal left below squares
     * so use the ct of those cells to determine whether a cell increases total ct by 1 or more
     * @param matrix
     * @return
     */
    public int countSquares(int[][] matrix) {
        int numSubMatrices = 0;
        int cols = matrix[0].length;
        for (int i =0; i < matrix.length; i++) {
            for (int j =0; j < cols; j++) {
                if (i > 0 && j > 0 && matrix[i][j] != 0) {
                    //if cell below, left and diagonally left are all non zero then take the min of that and aggregate
                    //to current cell
                    matrix[i][j] += Math.min(Math.min(matrix[i-1][j], matrix[i-1][j-1]), matrix[i][j-1]);
                }
                numSubMatrices += matrix[i][j];
            }
        }
        return numSubMatrices;
    }
}
