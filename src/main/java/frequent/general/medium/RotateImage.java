package frequent.general.medium;

/**
 * questions asks for rotating an image 90 degrees
 */
public class RotateImage {

    /**
     * this is a very sharp solution.
     *
     * the idea is that you think of the matrix in 4 sub rectangles and shift each rectangle 90 degree
     * you'd need to write a matrix on a white board to conceptualize this. do this with a 3x3 matrix, where
     * first rectangle is (0,0) & (1,0) , second is (0,1) & (0,2) , third is (1,2) & (2,2), 4th is (2,0) & (2,1)
     *
     * you then translate each box 90 degree by equation currCol = priorRow & currRow = N - priorCol -1 where N is
     * width of the matrix
     *
     * Another solution is to transpose the matrix and then reverse each row.
     * transpose =  matrix[j][i] = matrix[i][j];
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int rowMax = matrix.length;
        for (int i =0; i < rowMax/2 + rowMax%2; i++) {
            for (int j = 0; j < rowMax/2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[rowMax-j-1][i];
                matrix[rowMax-j-1][i] = matrix[rowMax-i-1][rowMax-j-1];
                matrix[rowMax-i-1][rowMax-j-1] = matrix[j][rowMax-i-1];
                matrix[j][rowMax-i-1] = tmp;
            }
        }
    }


}
