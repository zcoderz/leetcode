package frequent.medium;

/**
 * 48. Rotate Image
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 *
 * questions asks for rotating an image 90 degrees
 *
 * IMP-1
 */
public class RotateImage {

    /**
     * simple solution is to first transpose and then reverse
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        transpose(matrix);
        reflect(matrix);
    }

    public void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
    }

    public void reflect(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }

    /**
     * this is a very sharp solution.
     *
     * the idea is that you think of the matrix in 4 sub rectangles and shift each rectangle 90 degree
     * you'd need to write a matrix on a white board to conceptualize this. do this with a 3x3 matrix
     * Leet code has an excellent video describing this transform
     *
     * you then translate each box 90 degree by equation currRow = priorCol & currCol = N - priorCol -1 where N is
     * width of the matrix
     *
     * @param matrix
     */
    public void rotate4Groups(int[][] matrix) {
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
