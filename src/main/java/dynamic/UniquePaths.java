package dynamic;

import java.util.Arrays;

/**
 * 62. Unique Paths
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 * Another excellent DP practice question
 */
public class UniquePaths {


    public int uniquePaths(int m, int n) {
        int[][] arr = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(arr[i], 1);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
            }
        }

        return arr[m - 1][n - 1];
    }

}
