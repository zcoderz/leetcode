package redo;

public class RotateImage {

    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int tmp = matrix[i] [j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        int cols = matrix[0].length-1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int otherJ = cols -j;
                int tmp = matrix[i] [j];
                matrix[i][j] = matrix[i][otherJ];
                matrix[j][otherJ] = tmp;
            }
        }
    }

}
