package google.medium;


/**
 * 1254. Number of Closed Islands
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of
 * 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 *
 * Return the number of closed islands.
 *
 * IMP-1 : Number islands and its variations is a common question
 * The implementation is super simple but the approach towards this solution needs to be super clever
 *
 * The approach is to start at the edges, left, top, right, bottom and invalidate any cells that are connected to
 * edges which start with a 0. based on this the land (0) left must be part of a surrounded island.
 *
 * The approach is different because for similar problems you would start from a land (0) which is valid and look
 * outwards to validate whether indeed its surrounded on sides by 1s. but that becomes a very complicated problem
 * due to corners
 *
 *
 */
public class NumberOfClosedIslands {

    public static void main(String [] args) {
        int [][] grid = {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
        NumberOfClosedIslands num = new NumberOfClosedIslands();
        int islands = num.closedIsland(grid);
        System.out.println(islands);
    }

    int cols;
    int rows;
    int [][] grid;

    /**
     * find closed islands
     * @param grid
     * @return
     */
    public int closedIsland(int[][] grid) {
        int islands = 0;
        cols = grid[0].length;
        rows = grid.length;
        this.grid = grid;
        //invalidate cells from top and bottom rows that have a land
        for (int i = 0; i < cols; i++) {
            dfs(0, i);
            dfs(rows-1, i);
        }
        //invalidate cells from left and right cols that have a land
        for (int i = 0; i < rows; i++) {
            dfs(i, 0);
            dfs(i, cols-1);
        }
        //check inner cells
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < cols; j++) {
                if (grid[i][j] ==0) {
                    dfs(i, j);
                    islands++;
                }
            }
        }
        return islands;
    }

    /**
     * regular dfs
     * @param row
     * @param col
     */
    void dfs(int row, int col) {
        if ( (row < 0) || (row >= rows) || (col < 0) || (col >= cols) || grid[row][col] !=0) {
            return;
        }
        grid[row][col] = -1; //mark as visited
        dfs(row+1, col); //down
        dfs(row-1, col); //up
        dfs(row, col+1); //right
        dfs(row, col-1); //left
    }
}
