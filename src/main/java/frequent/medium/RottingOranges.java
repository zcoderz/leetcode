package frequent.medium;

import utils.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 994. Rotting Oranges
 *
 * You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1.
 *
 * Example 1:
 *
 *
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * IMP-3
 * Simple BFS problem
 */
public class RottingOranges {

    public static void main(String [] args) {
        //int [][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        int [][] grid = {{1,2}};
        RottingOranges ro = new RottingOranges();
        int turns = ro.orangesRotting(grid);
        System.out.println(turns);

    }

    int [][] moves = { {1,0}, {0,1}, {-1,0}, {0,-1} };

    /**
     * here is a simple BFS implementation
     * we use a visited set to mark the cells that have been visited already so as to not modify the original matrix
     * fresh is another set that contains points for fresh oranges. could have just used a counter in place
     * of a set to count number of fresh oranges left
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        Set<Pair<Integer, Integer>> fresh = new HashSet<>();
        LinkedList<Pair<Integer, Integer>> queue = new LinkedList<>();

        int rows = grid.length;
        int cols = grid[0].length;

        for (int i =0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    fresh.add(new Pair<>(i, j));
                } else if (grid[i][j] == 2) {
                    queue.add(new Pair<>(i, j));
                }
            }
        }

        int loops = 0;
        while (!queue.isEmpty()) {
            boolean looped = false;
            int size = queue.size();

            for (int i =0; i < size; i++) {
                Pair<Integer, Integer> point = queue.poll();
                for (int j = 0; j < moves.length; j++) {
                    int [] move = moves[j];

                    int newX = point.first + move[0];
                    int newY = point.second + move[1];

                    if (newX >= rows || newX < 0 || newY < 0 || newY >= cols) {
                        continue;
                    }

                    Pair<Integer, Integer> p = new Pair<>(newX, newY);
                    if (!visited.contains(p) && grid[newX][newY] == 1) {
                        if (!looped) {
                            loops++;
                            looped = true;
                        }
                        fresh.remove(p);
                        visited.add(p);
                        queue.add(p);
                    }
                }

            }
        }
        //if any fresh oranges left return -1
        if (!fresh.isEmpty()) {
            return -1;
        }
        return loops;
    }
}
