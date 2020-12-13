package trees;


import utils.Pair;

import java.util.HashSet;
import java.util.Set;

public class NumIslands {
    int countIslands = 0;
    int xSize ;
    int ySize;
    Set<Pair<Integer,Integer>>  pairs = new HashSet<>();
    char[][] grid;
    public int numIslands(char[][] grid) {
        this.grid = grid;
        xSize = grid.length;
        if (xSize == 0) {
            return 0;
        }
        ySize = grid[0].length;
        for (int i =0; i < xSize ; i++) {
            for (int j = 0; j < ySize; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                Pair<Integer, Integer> pair = new Pair(i,j);
                if (pairs.contains(pair)) {
                    continue;
                }
                countIslands++;
            }
        }
        return countIslands;
    }

    void dfs (Integer i, Integer j) {
        if (i>= xSize || j>= ySize || grid[i][j] == '0') {
            return;
        }
        Pair<Integer, Integer> pair = new Pair(i,j);
        pairs.add(pair);
        dfs(i+1, j);
        dfs (i, j+1);
    }
}
