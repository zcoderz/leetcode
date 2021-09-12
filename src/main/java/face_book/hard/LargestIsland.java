package face_book.hard;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 *
 * Return the size of the largest island in grid after applying this operation.
 *
 * An island is a 4-directionally connected group of 1s.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: grid = [[1,1],[1,0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 */
public class LargestIsland {

    int [][] moves = { {-1,0}, {1,0}, {0,1}, {0,-1}};

    public int largestIsland(int[][] grid) {
        int startColor = 2;
        int maxA = 0;
        Map<Integer, Integer> mapCt = new HashMap<>();
        for (int i =0; i < grid.length; i++) {
            for (int j =0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int sz = traverseGrid(i,j, grid, startColor);
                    mapCt.put(startColor++, sz);
                }
            }
        }
        mapCt.put(0,0); //sentinel
        for (int i =0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();
                    for (int[] move : moves) {
                        int row = i + move[0];
                        int col = j + move[1];
                        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
                            continue;
                        }
                        set.add(grid[row][col]);
                    }
                    int val = 0;
                    for (Integer color : set) {
                        val += mapCt.get(color);
                    }
                    maxA = Math.max(maxA, val+1);
                }
            }
        }
        //based on above maxA will only be 0 if the entire grid is full of 1s
        return maxA ==0 ? grid.length*grid.length : maxA;
    }

    int traverseGrid(int i, int j, int [][] grid, int color) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] !=1) {
            return 0;
        }
        grid[i][j] = color;
        int ans = 0;

        for (int[] move : moves) {
            int row = i + move[0];
            int col = j + move[1];
            ans += traverseGrid(row, col, grid, color);
        }
        return ans + 1;
    }

}
