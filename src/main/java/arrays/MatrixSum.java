package arrays;

/**
 * 747. Largest Number At Least Twice of Others
 * n a given integer array nums, there is always exactly one largest element.
 *
 * Find whether the largest element in the array is at least twice as much as every other number in the array.
 *
 * If it is, return the index of the largest element, otherwise return -1.
 *
 * Example 1:
 *
 * Input: nums = [3, 6, 1, 0]
 * Output: 1
 * Explanation: 6 is the largest integer, and for every other number in the array x,
 * 6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1,
 * col1) and lower right corner (row2, col2).
 */
public class MatrixSum {

    //precalculate sums and cache in this 2-d array
    int[][] dp;

    /**
     * very important to start indexes of cache as +1 offset of original row and col. this makes arithmetic for prior
     * row, col offsets much simpler and less error prone.
     * <p>
     * concept is that you create a matrix that has sum of values from coordinates 0,0 to the given cell then sum of the
     * given cell is simply : cell-sum - sum (one row before row1 and col2) (below) - sum (row 2 , one col left) (left)
     * + diagonal one below first row and col (this one gets subtracted twice) and hence need to add back
     *
     * @param matrix
     */
    public MatrixSum(int[][] matrix) {
        int cols = 0;
        if (matrix.length != 0) {
            cols = matrix[0].length;
            dp = new int[matrix.length + 1][cols + 1];

        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < cols; j++) {
                dp[i + 1][j + 1] = dp[i][j + 1] + dp[i + 1][j] - dp[i][j] + matrix[i][j];
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        MatrixSum matrixSum = new MatrixSum(matrix);
        int sum = matrixSum.sumRegion(2, 1, 4, 3); //-> 8
        System.out.println(sum);
        sum = matrixSum.sumRegion(1, 1, 2, 2); // -> 11
        System.out.println(sum);
        sum = matrixSum.sumRegion(1, 2, 2, 4);// -> 12
        System.out.println(sum);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}
