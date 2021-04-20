package google.medium;

import utils.graph.generic.UnionFindNode;
import java.util.*;

/**
 * 1631. Path With Minimum Effort
 *
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
 *   The below solution ran on leetcode in 101ms
 *   which is similar to the A* implementation for the same problem.
 *   The time complexity here is O(N*M*(Log (N*M))). This is similar to the A* problem.
 *   See proof of the time complexity for union find here :
 *   https://en.wikipedia.org/wiki/Disjoint-set_data_structure#Proof_of_O(log*(n))_time_complexity_of_Union-Find
 *
 *  IMP-1 : Path finding. see other solution for same problem as well
 */
public class PathWithMinimumEffortUnionFind {

    public static void main(String [] args) {
        PathWithMinimumEffortUnionFind path = new PathWithMinimumEffortUnionFind();
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
        int [] rowMoves = {1, 0};
        int [] colMoves = {0, 1};
        ArrayList<Edge> edges = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int cellId = (row*cols) + col;
                for (int k =0; k < rowMoves.length; k++) {
                    int newR = row + rowMoves[k];
                    int newC = col + colMoves[k];
                    if (newR < 0 || newR >= rows || newC < 0 || newC >= cols) {
                        continue;
                    }
                    int weight = Math.abs(heights[row][col] - heights[newR][newC]);
                    int newCellId = (newR*cols) + newC;
                    Edge edge = new Edge(cellId, newCellId, weight);
                    edges.add(edge);
                }
            }
        }
        int lastCell =  (rows * cols) - 1;
        edges.sort(Comparator.comparingInt(l -> l.weight));
        Map<Integer, UnionFindNode<Integer>> unionFindNodeMap = new HashMap<>();
        for (Edge edge : edges) {
            UnionFindNode<Integer> leftNode = unionFindNodeMap.computeIfAbsent(edge.leftCell,
                    (l) -> new UnionFindNode<>(edge.leftCell));
            UnionFindNode<Integer> rightNode = unionFindNodeMap.computeIfAbsent(edge.rightCell,
                    (l) -> new UnionFindNode<>(edge.rightCell));
            leftNode.union(rightNode);
            UnionFindNode<Integer> first = unionFindNodeMap.get(0);
            UnionFindNode<Integer> last = unionFindNodeMap.get(lastCell);
            if ((first != null) && (last != null) && (first.find() == last.find())) {
                return edge.weight;
            }
        }
        return 0;
    }

    public static class Edge {
        int leftCell;
        int rightCell;
        int weight;
        public Edge(int left, int right, int weight) {
            leftCell = left;
            rightCell = right;
            this.weight = weight;
        }
    };
}
