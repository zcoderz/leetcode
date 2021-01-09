package arrays;

public class MatrixSum {

    public static void main(String [] args) {
        int [][] matrix = {
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

    int [][] dp;
    public MatrixSum(int[][] matrix) {
        int cols = 0;
        if (matrix.length !=0) {
            cols = matrix[0].length;
            dp = new int[matrix.length][cols];

        }
        for (int i =0; i < matrix.length; i++) {
            for (int j =0; j < cols; j++) {
                int priorRow = (i==0) ? 0 : dp[i-1][j];
                int priorCol = (j==0) ? 0 : dp[i][j-1];
                int priorDiagonal = 0;
                if (priorCol != 0 && priorRow != 0) {
                    priorDiagonal = dp[i-1][j-1];
                }
                dp[i][j]= priorRow + priorCol - priorDiagonal + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int belowSum = 0;
        int leftSum = 0;
        int priorRow = row1-1;
        int priorCol = col1-1;
        int diagSum = 0;
        if (priorCol >= 0 && priorRow >= 0) {
            diagSum = dp[priorRow][priorCol];
        }
        if (priorRow >=0) {
            belowSum = dp[priorRow][col2];
        }
        if (priorCol >=0) {
            leftSum = dp[row2][priorCol];
        }
        return dp[row2][col2]-belowSum-leftSum+diagSum;
    }
}
