package misc;


import java.util.HashSet;
import java.util.Set;

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

    public int longestIncreasingPath(int[][] matrix) {
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
                dfs(i, j, 1, -1);
            }
        }
        System.out.println(maxCurrentPath);
        return maxCurrentPath;
    }

    int dfs(int i, int j, int depth, int prior) {
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
            int maxd = dfs(i + 1, j, depth + 1, val);
            maxd = Math.max(maxd, dfs(i - 1, j, depth + 1, val));
            maxd = Math.max(maxd, dfs(i, j + 1, depth + 1, val));
            maxd = Math.max(maxd, dfs(i, j - 1, depth + 1, val));
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
