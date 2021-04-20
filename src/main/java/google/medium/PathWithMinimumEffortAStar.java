package google.medium;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1631. Path With Minimum Effort
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
 * where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0),
 * and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
 * You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
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
 *   The below solution ran on leetcode in 105ms
 *   The time complexity is O(N*M*(Log (N*M))) N*M cells and sorting them in the priority queue takes log (N*M)
 *
 *  IMP-1 : Path finding. see other solution for same problem as well
 */
public class PathWithMinimumEffortAStar {


    public static void main(String [] args) {
        PathWithMinimumEffortAStar path = new PathWithMinimumEffortAStar();
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

    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        int totalCells = (rows * cols);
        Cell [] cells = new Cell[totalCells];
        for (int i =0 ; i < totalCells; i++) {
            cells[i] = new Cell(i);
        }

        PriorityQueue<Cell> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(l -> l.maxEffortToReachCell)
        );

        cells[0].maxEffortToReachCell = 0;
        int [] rowMoves = {1, -1, 0, 0};
        int [] colMoves = {0, 0, -1, 1};

        priorityQueue.add(cells[0]);
        while (!priorityQueue.isEmpty()) {
            Cell theCell = priorityQueue.poll();
            int row = theCell.cellId / cols;
            int col = (theCell.cellId % cols);
            for (int i = 0; i < rowMoves.length; i++) {
                int newR = row + rowMoves[i];
                int newC = col + colMoves[i];
                if (newR < 0 || newR >= rows || newC < 0 || newC >= cols) {
                    continue;
                }
                int effort = Math.abs(heights[row][col] - heights[newR][newC]);
                effort = Math.max(theCell.maxEffortToReachCell, effort);
                int newCellId = (newR*cols) + newC;
                if (cells[newCellId].maxEffortToReachCell > effort) {
                    cells[newCellId].maxEffortToReachCell = effort;
                    priorityQueue.add(cells[newCellId]);
                }
            }
        }
        return cells[totalCells-1].maxEffortToReachCell;
    }

    public static class Cell {
        Integer cellId;
        Integer maxEffortToReachCell;
        Cell(Integer cellId) {
            this.cellId = cellId;
            maxEffortToReachCell = Integer.MAX_VALUE;
        }

        public Integer getCellId() {
            return cellId;
        }

        public void setCellId(Integer cellId) {
            this.cellId = cellId;
        }

        public Integer getMaxEffortToReachCell() {
            return maxEffortToReachCell;
        }

        public void setMaxEffortToReachCell(Integer maxEffortToReachCell) {
            this.maxEffortToReachCell = maxEffortToReachCell;
        }
    }
}
