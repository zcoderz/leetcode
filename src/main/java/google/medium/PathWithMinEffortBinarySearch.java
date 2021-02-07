package google.medium;

import java.util.Arrays;

/**
 * 1631. Path With Minimum Effort
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 *
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 *
 *
 * Example 3:
 *
 *
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 * IMP-1
 *
 * This question is more tricky than it seems.There is excellent explanation of the question on leetcode,
 * recommend reading that.
 *
 * I am solving the question via 3 approaches as below :
 *   1. Using a modified form of A* algo in this class
 *   2. Using union find
 *   3. Using binary search
 *
 *
 *  This code ran in 92 ms, interestingly its the best of the solutions here though not great.
 *
 *  IMP-1 : Path finding. see other solution for same problem as well
 */
public class PathWithMinEffortBinarySearch {

    public static void main(String [] args) {
        PathWithMinEffortBinarySearch path = new PathWithMinEffortBinarySearch();
        int [][] heights = {{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}};
        int theH = path.minimumEffortPath(heights);
        System.out.println(theH);

        int [][] heights1 = {{1,2,2},{3,8,2},{5,3,5}};
        theH = path.minimumEffortPath(heights1);
        System.out.println(theH);

        int [][] heights2 = {{3}};
        theH = path.minimumEffortPath(heights2);
        System.out.println(theH);

        int [][] heights3 = {{1,10,6,7,9,10,4,9}};
        theH = path.minimumEffortPath(heights3);
        System.out.println(theH);
    }

    int [] rowMoves = {1, -1, 0, 0};
    int [] colMoves = {0, 0, -1, 1};
    int target;
    int cols;
    int rows;
    boolean [] visited ;
    int [][] heights;
    public int minimumEffortPath(int[][] heights) {
        rows = heights.length;
        cols = heights[0].length;
        target = (rows * cols) -1;
        visited = new boolean[target+1];
        this.heights = heights;

        int low = 0;
        int high = (int) Math.pow(10, 6);

        while (low < high) {
            Arrays.fill(visited, false);
            int mid = (low + (high-low)/2);
            boolean works = dfsTraverse(0, 0, mid);
            if (works) {
                high = mid;
            } else {
                low = mid+1;
            }
        }

        return high;
    }

    public boolean dfsTraverse(int row, int col, int delta) {
        int cellIndex = row * cols + col;
        if (cellIndex == target) {
            return true;
        }
        visited[row*cols+col] = true;
        for (int i =0; i < rowMoves.length; i++) {
            int newR = row + rowMoves[i];
            int newC = col + colMoves[i];
            if (newR < 0 || newR >= rows || newC < 0 || newC >= cols) {
                continue;
            }
            int effort = Math.abs(heights[row][col] - heights[newR][newC]);
            int newIndex = newR*cols+newC;
            if (effort <= delta && !visited[newIndex]) {
                boolean matches = dfsTraverse(newR, newC, delta);
                if (matches) {
                    return true;
                }
            }
        }
        return false;
    }
}
