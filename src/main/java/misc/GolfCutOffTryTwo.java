package misc;

import java.util.*;

public class GolfCutOffTryTwo {

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

        GolfCutOffTryTwo cutOff = new GolfCutOffTryTwo();
        int val = cutOff.cutOffTree(forest);
        System.out.println(val);
    }

    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList();
        for (int r = 0; r < forest.size(); ++r) {
            for (int c = 0; c < forest.get(0).size(); ++c) {
                int v = forest.get(r).get(c);
                if (v > 1) trees.add(new int[]{v, r, c});
            }
        }
        Collections.sort(trees, (a, b) -> Integer.compare(a[0], b[0]));
        int ans = 0, sr = 0, sc = 0;
        for (int[] tree : trees) {
            //int d = hadlocks(forest, sr, sc, tree[1], tree[2]);
            int d = calcDistance(sr, sc, tree[1], tree[2], forest);
            if (d < 0) return -1;
            ans += d;
            sr = tree[1];
            sc = tree[2];
        }
        return ans;
    }

    int calcDistance(int startX, int startY, int targetX, int targetY, List<List<Integer>> forest) {

        int rows = forest.size();
        int cols = forest.get(0).size();

        Set<Integer> visited = new HashSet<>();
        Deque<int[]> queue = new ArrayDeque<>();
        int[] currCord = new int[3];
        currCord[0] = 0; //no detour
        currCord[1] = startX;
        currCord[2] = startY;
        queue.add(currCord);

        while (!queue.isEmpty()) {
            currCord = queue.pollFirst();
            int detour = currCord[0];
            int currX = currCord[1];
            int currY = currCord[2];


            if (!visited.contains(currX * cols + currY)) {
                visited.add(currX * cols + currY);
                if (currX == targetX && currY == targetY) {
                    //reached destination
                    return Math.abs(startX - targetX) + Math.abs(startY - targetY) + 2 * detour;
                }

                for (int i = 0; i < 4; i++) {
                    int newX = currX + directions[i][0];
                    ;
                    int newY = currY + directions[i][1];
                    int newIndex = newX * cols + newY;
                    if (visited.contains(newIndex) || newX < 0 || newX >= rows ||
                            newY < 0 || newY >= cols || forest.get(newX).get(newY) == 0) {
                        continue;
                    }

                    boolean closer;

                    if (i == 0) {
                        //row moves forward
                        if (currX >= targetX) {
                            closer = false;
                        } else {
                            closer = true;
                        }
                    } else if (i == 1) {
                        if (currX > targetX) {
                            closer = true;
                        } else {
                            closer = false;
                        }
                    } else if (i == 2) {
                        if (currY >= targetY) {
                            closer = false;
                        } else {
                            closer = true;
                        }
                    } else {
                        if (currY > targetY) {
                            closer = true;
                        } else {
                            closer = false;
                        }
                    }

                    if (closer) {
                        queue.offerFirst(new int[]{detour, newX, newY});
                    } else {
                        queue.offerLast(new int[]{detour + 1, newX, newY});
                    }
                }
            }

        }
        return -1;
    }

}
