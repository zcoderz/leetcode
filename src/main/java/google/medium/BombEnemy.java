package google.medium;

import utils.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 361. Bomb Enemy
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
 * Note: You can only put the bomb at an empty cell.
 *
 * Example:
 *
 * Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
 * Output: 3
 * Explanation: For the given grid,
 *
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 *
 * Placing a bomb at (1,1) kills 3 enemies.
 *
 *
 */
public class BombEnemy {

    public static void main(String [] args) {
        char[][] grid = {{'0','E','0','0'},{'E','0','W','E'},{'0','E','0','0'}};
        BombEnemy be = new BombEnemy();
        int max = be.maxKilledEnemies(grid);
        System.out.println(max);
    }

    int max = 0;
    int rows;
    int cols;

    /**
     * this is a very clever implementation, idea was generated from leetcode.
     * idea is that you use dynamic programming - dont recompute once computed data.
     *
     * in this problem computation is horizontal and vertical ,
     * so store the cells bombed horizontally and do same for those bombed vertically
     *
     * and on each iteration just reuse the previously calculated info if possible.
     * when you hit a wall, you reset the row and col counts to 0 for # of cells bombed
     *
     * @param grid
     * @return
     */
    public int maxKilledEnemies(char[][] grid) {
        if (grid.length ==0) return 0;
        rows = grid.length;
        cols = grid[0].length;

        int rowCt = 0;
        int [] colCts = new int[cols];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 'W') {
                    continue;
                }

                //at the first column or when the prior column was a Wall , reset
                if ((col == 0) || grid[row][col-1] == 'W') {
                    rowCt = 0;
                    for (int i = col; i < cols; i++) {
                        if (grid[row][i] == 'W') {
                            break;
                        }
                        if (grid[row][i] == 'E') {
                            rowCt++;
                        }
                    }
                }

                //reset on first row or when prior row had a wall
                if ((row == 0) || grid[row-1][col] == 'W') {
                    colCts[col] = 0;
                    for (int i = row; i < rows; i++) {
                        if (grid[i][col] == 'W') {
                            break;
                        }
                        if (grid[i][col] == 'E') {
                            colCts[col]++;
                        }
                    }
                }

                if (grid[row][col] == '0') {
                    this.max = Math.max(max, rowCt + colCts[col]);
                }
            }
        }
        return max;
    }

}
