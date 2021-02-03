package amazon.medium;

import utils.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * 694. Number of Distinct Islands
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Count the number of distinct islands. An island is considered to be the same as another if and only if one
 * island can be translated (and not rotated or reflected) to equal the other.
 *
 * Example 1:
 * 11000
 * 11000
 * 00011
 * 00011
 * Given the above grid map, return 1.
 *
 * find number of distinct islands where islands are denoted by a connect set of cells that are 1
 * <p>
 * this is similar to the number of islands problem except that you keep track of the direction in which you are going.
 * also when you reverse from a direction keep track of the reversal.
 * <p>
 * add the directions travelled into a set where direction is denoted by a string and hence you get unique set of
 * directions.
 */
public class NumDistinctIslands {

    //move in a clockwise direction
    String[] moveNames = {"R", "D", "L", "U"};
    String[] moveReverse = {"L", "U", "R", "D"};
    int[] rowM = {0, -1, 0, 1};
    int[] colM = {1, 0, -1, 0};
    int rows;
    int cols;
    Set<Pair<Integer, Integer>> visitedSet = new HashSet<>();
    Set<String> uniqueIslands = new HashSet<>();

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}};

        int[][] grid2 = {{1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}};

        NumDistinctIslands numDistinctIslands = new NumDistinctIslands();
        int islands = numDistinctIslands.numDistinctIslands(grid2);
        System.out.println(islands);
    }

    public int numDistinctIslands(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Pair<Integer, Integer> pair = new Pair<>(i, j);
                if (!visitedSet.contains(pair)) {
                    if (grid[i][j] == 1) {
                        String islandConfig = processMove(i, j, "", grid);
                        uniqueIslands.add(islandConfig);
                    }
                }
            }
        }
        return uniqueIslands.size();
    }

    String processMove(int row, int col, String pattern, int[][] grid) {
        Pair<Integer, Integer> origP = new Pair<>(row, col);
        visitedSet.add(origP);
        for (int i = 0; i < 4; i++) {
            int nRow = row + rowM[i];
            int nCol = col + colM[i];
            if (nRow >= rows || nRow < 0 || nCol >= cols || nCol < 0) {
                continue; //out of bounds
            }
            Pair<Integer, Integer> pair = new Pair<>(nRow, nCol);
            if (!visitedSet.contains(pair)) {
                if (grid[nRow][nCol] == 1) {
                    pattern += moveNames[i];
                    pattern = processMove(nRow, nCol, pattern, grid);
                    pattern += moveReverse[i];
                }
            }
        }
        return pattern;
    }
}
