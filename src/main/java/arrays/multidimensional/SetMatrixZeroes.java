package arrays.multidimensional;

public class SetMatrixZeroes {

    public static void main(String [] args) {

        SetMatrixZeroes matrixZeros = new SetMatrixZeroes();
        int [][] nums = {{1}, {0}};
        matrixZeros.setZeroes(nums);

        System.out.println(nums);
    }

    public void setZeroes(int[][] matrix) {
        boolean firstCol = false;
        int rows = matrix.length;
        int cols = 0;
        if (rows > 0) {
            cols = matrix[0].length;
        } else {
            return;
        }


        //mark first header index in row/col to 0 if needed
        for (int iRow = 0; iRow < rows; iRow++) {
            //identify whether rows in first col need updating
            //use a special variable for the column so that the fact that first color has 0s
            //can be distinguished sperately from the first row.
            if (matrix[iRow][0] == 0) {
                firstCol = true;
            }

            for (int iCol = 1 ; iCol < cols; iCol++) {
                if ((matrix[iRow][iCol]) == 0) {
                    matrix[0][iCol] = 0;
                    matrix[iRow][0] = 0;
                }
            }
        }


        //update rows/cols starting from index 1 to 0 if indeed needed
        for (int iRow = 1; iRow < rows; iRow++) {
            for (int iCol = 1 ; iCol < cols; iCol++) {
                if ((matrix[iRow][0] == 0) || matrix[0][iCol] ==0) {
                    matrix[iRow][iCol] = 0;
                }
            }
        }

        //treat first row special
        if (matrix[0][0] == 0) {
            //update first row to 0
            for (int iCol = 1 ; iCol < cols; iCol++) {
                matrix[0][iCol] = 0;
            }
        }

        //treat first column special.
        if (firstCol) {
            //update first col to 0
            for (int iRow =0; iRow < rows; iRow++) {
                matrix[iRow][0] = 0;
            }
        }
    }
}
