package frequent.hard;

import java.util.*;

/**
 * 675. Cut Off Trees for Golf Event
 *
 * You are asked to cut off all the trees in a forest for a golf event. The forest is represented as an m x n matrix. In this matrix:
 *
 * 0 means the cell cannot be walked through.
 * 1 represents an empty cell that can be walked through.
 * A number greater than 1 represents a tree in a cell that can be walked through, and this number is the tree's height.
 * In one step, you can walk in any of the four directions: north, east, south, and west. If you are standing in a cell with a tree, you can choose whether to cut it off.
 *
 * You must cut off the trees in order from shortest to tallest. When you cut off a tree, the value at its cell becomes 1 (an empty cell).
 *
 * Starting from the point (0, 0), return the minimum steps you need to walk to cut off all the trees. If you cannot cut off all the trees, return -1.
 *
 * You are guaranteed that no two trees have the same height, and there is at least one tree needs to be cut off.
 *
 * Example 1:
 *
 *
 * Input: forest = [[1,2,3],[0,0,4],[7,6,5]]
 * Output: 6
 * Explanation: Following the path above allows you to cut off the trees from shortest to tallest in 6 steps.
 *
 * IMP-3 : Nice question to practice
 * ToDo : checkout leet code's Approach #3: Hadlock's Algorithm [Accepted]
 */
public class GolfCutOff {

    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        //[[1,2,3],[0,0,4],[7,6,5]]
        List<List<Integer>> forest = new ArrayList<>();
        int[][] forestA = {{4557, 6199, 7461, 2554, 6132, 7471, 7103, 4290}, {2034, 2301, 6733, 6040, 2603, 5697, 9541, 6678}, {7308, 7368, 9618, 4386, 6944, 3923, 4754, 4294}, {0, 3016, 7242, 5284, 6631, 1897, 1767, 7603}, {2228, 0, 3625, 7713, 2956, 3264, 3371, 6124}, {9195, 7804, 2787, 0, 4919, 9304, 5161, 502}};
//        List<Integer> a1 = new ArrayList<>();
//        a1.add(1); a1.add(2); a1.add(3);
//        List<Integer> a2 = new ArrayList<>();
//        a2.add(0); a2.add(0); a2.add(0);
//        List<Integer> a3 = new ArrayList<>();
//        a3.add(7); a3.add(6); a3.add(5);
//        forest.add(a1); forest.add(a2); forest.add(a3);
        for (int i = 0; i < forestA.length; i++) {
            List<Integer> a1 = new ArrayList<>();
            forest.add(a1);
            for (int j = 0; j < forestA[0].length; j++) {
                a1.add(forestA[i][j]);
            }
        }

        GolfCutOff cutOff = new GolfCutOff();
        int val = cutOff.cutOffTree(forest);
        System.out.println(val);
    }


    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    /**
     * Sort the trees by height. then walk to each next tree
     * return total distance at end. if walk isnt possible return -1
     *
     * could walk (calc distance via any of A*, BFS
     * @param forest
     * @return
     */
    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList();
        for (int r = 0; r < forest.size(); ++r) {
            for (int c = 0; c < forest.get(0).size(); ++c) {
                int v = forest.get(r).get(c);
                if (v > 1) trees.add(new int[]{v, r, c});
            }
        }

        Collections.sort(trees, Comparator.comparingInt(a -> a[0]));

        int ans = 0, sr = 0, sc = 0;
        for (int[] tree: trees) {
            int d = dist(forest, sr, sc, tree[1], tree[2]);
            if (d < 0) return -1;
            ans += d;
            sr = tree[1]; sc = tree[2];
        }
        return ans;
    }

    /**
     * simple bfs approach to calculate distance
     * @param forest
     * @param sr
     * @param sc
     * @param tr
     * @param tc
     * @return
     */
    public int dist(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int R = forest.size(), C = forest.get(0).size();
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[]{sr, sc, 0});
        boolean[][] seen = new boolean[R][C];
        seen[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == tr && cur[1] == tc) return cur[2];
            for (int di = 0; di < 4; ++di) {
                int r = cur[0] + dr[di];
                int c = cur[1] + dc[di];
                if (0 <= r && r < R && 0 <= c && c < C &&
                        !seen[r][c] && forest.get(r).get(c) > 0) {
                    seen[r][c] = true;
                    queue.add(new int[]{r, c, cur[2]+1});
                }
            }
        }
        return -1;
    }

}
