package search;

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
