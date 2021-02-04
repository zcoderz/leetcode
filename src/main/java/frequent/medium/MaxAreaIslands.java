package frequent.medium;

/**
 * 695. Max Area of Island
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 *
 * Example 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 *
 * calculate max number of connected squares with a value of 1
 * connected is only horizontal or vertical
 *
 */
public class MaxAreaIslands {
    int rows ;
    int cols ;
    boolean [] visited ;
    int maxCount;
    int [][] grid;
    int [][] validMoves = {{0,1}, {0,-1}, {1,0}, {-1,0}};

    /**
     * calls into process area for each index in the grid
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows*cols];
        maxCount = 0;

        for (int i =0; i < rows; i++) {
            for (int j =0; j < cols; j++) {
                processArea(i, j, 0);
            }
        }
        return maxCount;
    }

    /**
     * do a dfs to check max connected 1s
     * @param row
     * @param col
     * @param count
     * @return
     */
    int processArea(int row, int col, int count) {
        int currIndex = getIndex(row, col);
        if ((grid[row][col] != 1) || visited[currIndex]) {
            return count; //if already processed return immediately
        }
        count = count + 1;
        maxCount = Math.max(count, maxCount); //adjust count
        visited[currIndex] = true; //mark visited

        for (int [] moves : validMoves) {
            int newRow = row + moves[0];
            int newCol = col + moves[1];
            int index = getIndex(newRow, newCol);
            //check whether you can visit the new index based on below conditions
            if (newRow >= rows | newRow < 0 || newCol >= cols || newCol < 0
                    || visited[index] ) {
                continue;
            }
            //move to the new cell and update count
            count = processArea(newRow, newCol, count);
        }
        //return count so that on other directions of traversal the updated count can be used
        return count;
    }

    /**
     * return an index that the row, col represents
     * @param row
     * @param col
     * @return
     */
    int getIndex(int row, int col) {
        return row * cols + col;
    }

}
