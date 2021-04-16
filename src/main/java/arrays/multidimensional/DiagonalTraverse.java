package arrays.multidimensional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 498. Diagonal Traverse
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
 *
 *
 *
 * Example:
 *
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 *
 * IMP-3
 *
 */
public class DiagonalTraverse {

    public static void main(String[] args) {

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        DiagonalTraverse diagonalTraverse = new DiagonalTraverse();

        int[] tmp = diagonalTraverse.findDiagonalOrder(matrix);
        for (int num : tmp) {
            System.out.println(num);
        }
    }


    /**
     * this is a simpler approach than having the indexes traverse based on edges. but its slower because you are
     * reversing collection and using a place holder collection to store the diagonal values
     *
     * @param matrix
     * @return
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        int rows = matrix.length;
        if (rows > 0) {
            int cols = matrix[0].length;
            int size = rows * cols;
            int[] out = new int[size];
            if (cols == 0) return out;
            int index = 0;

            List<Integer> vals = new ArrayList<>();

            for (int di = 0; di < rows + cols; di++) {
                int row = 0;
                int col;
                vals.clear();

                col = Math.min(di, cols - 1);
                if (col == cols - 1) {
                    row = di - col;
                }

                while (row < rows && col >= 0) {
                    vals.add(matrix[row][col]);
                    row++;
                    col--;
                }
                if (di % 2 == 0) {
                    Collections.reverse(vals);
                }

                for (int val : vals) {
                    out[index++] = val;
                }
            }
            return out;
        }
        return new int[0];
    }

    public int[] findDiagonalOrderEdgeBased(int[][] matrix) {
        int rows = matrix.length;
        if (rows > 0) {
            int cols = matrix[0].length;
            int size = rows * cols;
            int[] out = new int[size];
            if (cols == 0) return out;

            int index = 0;

            int row = 0, col = 0;
            boolean upDirection = true;


            do {
                out[index++] = matrix[row][col];
                if (upDirection) {
                    if (row > 0 && col < cols - 1) {
                        row--;
                        col++;
                    } else if (col < cols - 1) {
                        col++; //shift one col to right and change direction down
                        upDirection = false;
                    } else if (col == cols - 1) {
                        row++; //wrap to next row
                        upDirection = false;
                    }
                } else {
                    if (row < rows - 1 && col > 0) {
                        row++;
                        col--;
                    } else if (col == 0 && row < rows - 1) {
                        row++; //shift one row down and change direction up
                        upDirection = true;
                    } else if (row == rows - 1) {
                        col++; //wrap to next col
                        upDirection = true;
                    }
                }
                if (row >= rows || col >= cols || row < 0 || col < 0) break;

            } while ((row != matrix.length - 1) || (col != cols - 1));
            out[index] = matrix[row][col]; //last col
            return out;
        }
        return new int[0];
    }

}
