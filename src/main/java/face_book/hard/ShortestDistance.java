package face_book.hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 317. Shortest Distance from All Buildings
 *
 * You are given an m x n grid grid of values 0, 1, or 2, where:
 *
 * each 0 marks an empty land that you can pass by freely,
 * each 1 marks a building that you cannot pass through, and
 * each 2 marks an obstacle that you cannot pass through.
 * You want to build a house on an empty land that reaches all buildings in the shortest total travel distance.
 * You can only move up, down, left, and right.
 *
 * Return the shortest travel distance for such a house.
 * If it is not possible to build such a house according to the above rules, return -1.
 *
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 *
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * Output: 7
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
 * So return 7.
 *
 */
public class ShortestDistance {

    public static void main(String [] args) {
        int [][] grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        ShortestDistance shortest = new ShortestDistance();
        int res = shortest.shortestDistance(grid);
        System.out.println(res);
    }

    public int shortestDistance(int[][] grid) {
        int minDistance = Integer.MAX_VALUE;
        int [][] totalGrid = new int[grid.length][grid[0].length];
        int [][] moves = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        int target = 0;
        for (int i =0; i < grid.length; i++) {
            for (int j =0; j < grid[0].length; j++) {
                int dist = 0;
                if (grid[i][j] == 1) {
                    minDistance = Integer.MAX_VALUE;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[] {i, j});
                    while (!queue.isEmpty()) {
                        dist++;
                        int sz = queue.size();
                        for (int k =0; k < sz; k++) {
                            int[] currCell = queue.poll();
                            for (int[] move : moves) {
                                int newRow = currCell[0] + move[0];
                                int newCol = currCell[1] + move[1];
                                if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length) {
                                    continue;
                                }
                                if (grid[newRow][newCol] == target) {
                                    totalGrid[newRow][newCol] += dist;
                                    minDistance = Math.min(minDistance, totalGrid[newRow][newCol]);
                                    queue.add(new int[] {newRow, newCol});
                                    grid[newRow][newCol] = target -1;
                                }
                            }
                        }
                    }
                    target--;
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}
