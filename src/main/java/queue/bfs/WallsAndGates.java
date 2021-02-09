package queue.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 286. Walls and Gates
 * You are given an m x n grid rooms initialized with these three possible values.
 *
 * -1 A wall or an obstacle.
 * 0 A gate.
 * INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
 * Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 *
 * IMP-1: Very nice practice problem
 */
public class WallsAndGates {
    int[][] distance;
    int[][] rooms;
    int WALL = -1;
    int GATE = 0;
    int INF = 2147483647;
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int rows;
    private int cols;

    public static void main(String[] args) {
        int[][] matrix = {{2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}};
        WallsAndGates wg = new WallsAndGates();
        wg.wallsAndGates(matrix);
        for (int[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i] + ",");
            }
            System.out.println("");
        }

    }

    public void wallsAndGates(int[][] rooms) {
        rows = rooms.length;
        if (rows == 0) return;
        cols = rooms[0].length;
        distance = new int[rows][cols];
        this.rooms = rooms;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((rooms[i][j] != WALL) && ((rooms[i][j] != GATE))) {
                    int dist = calDistance(i, j);
                    rooms[i][j] = dist;
                }
            }
        }
    }

    /**
     * calculate distance to gate from a given room
     *
     * @param row
     * @param col
     * @return
     */
    int calDistance(int row, int col) {
        //reset the distances per iteration
        for (int[] dis : distance) {
            Arrays.fill(dis, 0);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        distance[row][col] = 0;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int dist = distance[cell[0]][cell[1]];
            for (int[] dir : directions) {
                int nRow = cell[0] + dir[0];
                int nCol = cell[1] + dir[1];
                //distance[nRow][nCol]==0 prevents cycles below
                if (nRow >= 0 && nRow < rows && nCol >= 0 && nCol < cols &&
                        rooms[nRow][nCol] != WALL && distance[nRow][nCol] == 0) {
                    distance[nRow][nCol] = dist + 1;
                    if (rooms[nRow][nCol] == GATE) {
                        return dist + 1;
                    } else {
                        queue.add(new int[]{nRow, nCol});
                    }
                }
            }
        }
        return INF;
    }
}
