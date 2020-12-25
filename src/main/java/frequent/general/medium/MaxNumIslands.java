package frequent.general.medium;

/**
 * calculate max number of connected squares with a value of 1
 * connected is only horizontal or vertical
 *
 */
public class MaxNumIslands {
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
        if ((grid[row][col] != 1)|| visited[currIndex]) {
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
            if (newRow >= rows | newRow < 0 || newCol >= cols || newCol <0
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
