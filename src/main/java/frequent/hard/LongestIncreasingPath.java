package frequent.hard;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 329. Longest Increasing Path in a Matrix
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down.
 * You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 *
 * IMP-2: Common question.
 */
public class LongestIncreasingPath {
    int columns;
    int rows;
    int maxCurrentPath = 0;
    int[][] matrix;

    int[][] maxDepthPerNode;

    Set<Integer> visting = new HashSet<>();

    public static void main(String[] args) {
        int[][] matrix = {{1}};//{{7,7,5},{2,4,6},{8,2,0}};//{{9,9,4},{6,6,8},{2,1,1}};
        LongestIncreasingPath l = new LongestIncreasingPath();
        l.longestIncreasingPath(matrix);
    }

    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;

    /**
     * this approach is from leet code. essentially same as the slow except that you cache nodes that you have already
     * calculated so that you reduce total number of calculations
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        m = matrix.length; n = matrix[0].length;
        int[][] cache = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans = Math.max(ans, dfs(matrix, i, j, cache));
        return ans;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j])
                cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
        }
        return ++cache[i][j];
    }

    /**
     * this is a clever solution from leet code.
     * essentially you order each cell based on how many surrounding vertices are greater than that cell
     * this gives a way to indicate how many other cells can you travel to from the given cell
     *
     * once you have the above ordering you start from cells with lowest ordering and move towards cells with ordering
     * one ahead of the current and so on until no more options exist
     *
     * @param grid
     * @return
     */
    public int longestIncreasingPathTopoSort(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        // padding the matrix with zero as boundaries
        // assuming all positive integer, otherwise use INT_MIN as boundaries
        int[][] matrix = new int[m + 2][n + 2];
        for (int i = 0; i < m; ++i)
            System.arraycopy(grid[i], 0, matrix[i + 1], 1, n);

        // calculate outdegrees
        int[][] outdegree = new int[m + 2][n + 2];
        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                for (int[] d: dirs)
                    if (matrix[i][j] < matrix[i + d[0]][j + d[1]])
                        outdegree[i][j]++;

        // find leaves who have zero out degree as the initial level
        n += 2;
        m += 2;
        List<int[]> leaves = new ArrayList<>();
        for (int i = 1; i < m - 1; ++i)
            for (int j = 1; j < n - 1; ++j)
                if (outdegree[i][j] == 0) leaves.add(new int[]{i, j});

        // remove leaves level by level in topological order
        int height = 0;
        while (!leaves.isEmpty()) {
            height++;
            List<int[]> newLeaves = new ArrayList<>();
            for (int[] node : leaves) {
                for (int[] d: dirs) {
                    int x = node[0] + d[0], y = node[1] + d[1];
                    if (matrix[node[0]][node[1]] > matrix[x][y])
                        if (--outdegree[x][y] == 0)
                            newLeaves.add(new int[]{x, y});
                }
            }
            leaves = newLeaves;
        }
        return height;
    }

    public int longestIncreasingPathSlow(int[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        if (this.rows == 0) {
            return 0;
        }
        this.columns = matrix[0].length;
        maxDepthPerNode = new int[this.rows][this.columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maxDepthPerNode[i][j] = -1;
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                visting.clear();
                dfsSlow(i, j, 1, -1);
            }
        }
        System.out.println(maxCurrentPath);
        return maxCurrentPath;
    }

    int dfsSlow(int i, int j, int depth, int prior) {
        if (i < 0 || j < 0 || i >= rows || j >= columns) {
            return -1;
        }

        int val = matrix[i][j];

        if (visting.contains(val)) {
            return -1;
        }

        if (val <= prior) {
            return -1;
        }

        if (maxDepthPerNode[i][j] == -1) {
            visting.add(val);
            int maxd = dfsSlow(i + 1, j, depth + 1, val);
            maxd = Math.max(maxd, dfsSlow(i - 1, j, depth + 1, val));
            maxd = Math.max(maxd, dfsSlow(i, j + 1, depth + 1, val));
            maxd = Math.max(maxd, dfsSlow(i, j - 1, depth + 1, val));
            maxDepthPerNode[i][j] = Math.max(maxd + 1, 1);
            depth = maxd + depth - 1;
            visting.remove(val);
        } else {
            depth = depth + maxDepthPerNode[i][j] - 1;
        }

        if (depth > maxCurrentPath) {
            maxCurrentPath = depth;
        }

        return maxDepthPerNode[i][j];
    }
}
