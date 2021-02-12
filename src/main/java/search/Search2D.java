package search;


/**
 * 240. Search a 2D Matrix II
 * Write an efficient algorithm that searches for a target value in an m x n integer matrix.
 * The matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 *
 * IMP-1: Very common question.
 * The pattern in sorted matrix search questions is that you search from edge of a matrix towards the destination
 * In this case since both the rows and columns are sorted , you start your search from the last row and first column
 */
public class Search2D {

    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows > 0) {
            int cols = matrix[0].length;
            if (cols == 0) return false;

            int theRow = rows - 1;
            int theCol = 0;

            while (theRow >= 0 && theCol < cols) {
                if (matrix[theRow][theCol] > target) {
                    theRow--;
                } else if (matrix[theRow][theCol] < target) {
                    theCol++;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

}
