package redo;

public class NumIslands {


    //boolean [][] cells = new boolean[302][302];

    int rows;
    int cols;
    int [][] moves = { {0,1} , {0,-1}, {1, 0}, {-1, 0}};
    public int numIslands(char[][] grid) {
        if (grid.length ==0 ) return 0;
        int islands = 0;
        rows = grid.length;
        cols = grid[0].length;


        for (int i = 0; i < rows; i++) {
            for (int j =0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    traverse(grid, i, j);
                }
            }
        }
        return islands;
    }

    void traverse(char [][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] == '0') {
            return;
        }
        grid[row][col] = '0';
        for (int[] move: moves ) {
            int nR = row + move[0];
            int nC = col + move[1];
            traverse(grid, nR, nC);
        }
    }

}
