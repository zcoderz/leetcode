package google.medium;

/**
 * 562. Longest Line of Consecutive One in Matrix
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix.
 * The line could be horizontal, vertical, diagonal or anti-diagonal.
 * Example:
 * Input:
 * [[0,1,1,0],
 *  [0,1,1,0],
 *  [0,0,0,1]]
 * Output: 3
 */
public class LongestLine {

    public static void main(String [] args) {
        int [][] matrix = {{0,1,1,0},
                            {0,1,1,0},
                            {0,0,0,1}};
        LongestLine longestLine = new LongestLine();
        int len = longestLine.longestLine(matrix);
        System.out.println(len);
    }

    public int longestLine(int[][] matrix) {
        int longest = 0;
        int rows = matrix.length;
        if (rows ==0) {
            return 0;
        }
        int cols = matrix[0].length;
        int [][] dp = new int[cols][4];
        //order 0 horizontal, 1, vertical , 2 diagonal , 3 anti diagonal

        for (int row = 0; row < rows; row++) {
            int priorDiagonal = 0;
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 1) {
                    dp[col][0] = ((col) > 0) ? dp[col-1][0] + 1 : 1;  //horizontal
                    dp[col][1] = (row > 0) ? dp[col][1] + 1 : 1; //vertical
                    int tmp = dp[col][2];
                    dp[col][2] = ((row > 0) && (col > 0)) ? priorDiagonal + 1 : 1;
                    priorDiagonal =  tmp ;
                    dp[col][3] = ((row > 0) && ((col+1) < cols)) ? dp[col+1][3] + 1 : 1;
                    longest = Math.max(dp[col][2], Math.max(dp[col][1], Math.max(dp[col][0], Math.max(longest,
                            dp[col][3]))));
                } else {
                    priorDiagonal = dp[col][2];
                    dp[col][0] = dp[col][1] = dp[col][2]  = dp[col][3] = 0;
                }
            }
        }
        return longest;
    }
}
