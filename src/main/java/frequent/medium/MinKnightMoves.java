package frequent.medium;

import utils.Pair;

import java.util.*;

/**
 * 1197. Minimum Knight Moves
 *
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 *
 * A knight has 8 possible moves it can make, as illustrated below.
 * Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 *
 *
 *
 * Return the minimum number of steps needed to move the knight to the square [x, y].
 * It is guaranteed the answer exists.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 2, y = 1
 * Output: 1
 * Explanation: [0, 0] → [2, 1]
 *
 */
public class MinKnightMoves {

    public static void main(String [] args) {
        MinKnightMoves minK = new MinKnightMoves();
        int num = minK.minKnightMoves(2, 112);
        System.out.println(num);
    }

    Map<Pair<Integer,Integer>, Integer> coordinateMoves = new HashMap<>();
    Set<Pair<Integer, Integer>> visiting = new HashSet<>();
    int steps=0;
    public int minKnightMoves(int x, int y) {
        if (x==0 && y==0) return 0;
        processMinKnightMovesBFS(0, 0, x, y);
        return steps;
    }

    /**
     * BFS is the preferred approach as opposed to DFS where you would waste extra cycles that arent relevant
     * @param currX
     * @param currY
     * @param targetX
     * @param targetY
     */
    void processMinKnightMovesBFS(int currX, int currY, int targetX, int targetY) {
        LinkedList<Pair<Integer, Integer>> queue = new LinkedList<>();
        Pair<Integer, Integer> currP = new Pair<>(currX, currY);
        queue.add(currP);
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i =0; i< size; i++) {
                Pair<Integer, Integer> newP = queue.poll();
                List<Pair<Integer, Integer>> moves =  getPossibleMoves(newP.first, newP.second, targetX, targetY);
                for (Pair<Integer, Integer> move : moves) {
                    //keep track of visiting so as to prevent cycles
                    if (visiting.contains(move)) continue;
                    else visiting.add(move);
                    if (move.first==targetX && move.second==targetY) {
                        return;
                    } else {
                        queue.add(move);
                    }
                }
            }
        }
    }

    /**
     * here is a DFS solution for same. Don't do it. BFS is much easier for min search questions....
     * @param currX
     * @param currY
     * @param targetX
     * @param targetY
     * @return
     */
    int processMinKnightMoves(int currX, int currY, int targetX, int targetY) {
        if (currX == targetX && currY == targetY) {
            return 0;
        }
        int minDistance = Integer.MAX_VALUE;
        List<Pair<Integer, Integer>> moves =  getPossibleMoves(currX, currY, targetX, targetY);
        for (Pair<Integer, Integer> move : moves) {
            Integer newD = coordinateMoves.get(move);
            boolean alreadyVisiting = visiting.contains(move);
            if (newD == null && !alreadyVisiting) {
                visiting.add(move);
                newD = processMinKnightMoves(move.first, move.second, targetX, targetY);
                visiting.remove(move);
            }
            if (newD != null && newD != Integer.MAX_VALUE) {
                newD = newD + 1;
            }

            if (!alreadyVisiting) {
                minDistance = Math.min(minDistance, newD);
            }
        }
        //memorize a target cell's distance for future calculations
        coordinateMoves.put(new Pair<>(currX, currY), minDistance);
        return minDistance;
    }


    List<Pair<Integer, Integer>> getPossibleMoves(int currX, int currY, int targetX, int targetY) {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        //there are 8 directions that can be drawn on a coordinate plane with 4 quadrants
        int [][] dirs = {{1,2}, {2,1}, {-1, 2}, {-2, 1}, {-1,-2}, {-2, -1}, {1,-2}, {2, -1}};
        int targetedQuadrant = getPossibleQuadrants(currX, currY, targetX, targetY);
        if (targetedQuadrant == -1) {
            //process all directions
            for (int i =0; i < dirs.length; i++) {
                Pair<Integer, Integer> pair = new Pair<>(currX+dirs[i][0], currY+dirs[i][1]);
                pairs.add(pair);
            }
        } else {
            //direction in given quadrant
            int index = targetedQuadrant * 2;
            for (int i =index; i <= index+1; i++) {
                Pair<Integer, Integer> pair = new Pair<>(currX+dirs[i][0], currY+dirs[i][1]);
                pairs.add(pair);
            }
        }

        return pairs;
    }

    /**
     * divide the directions in to 4 quadrants. if close to the target return all directions
     * otherwise return only directions that are in quadrant towards the target
     * @param currX
     * @param currY
     * @param targetX
     * @param targetY
     * @return
     */
    int getPossibleQuadrants(int currX, int currY, int targetX, int targetY) {
        if (Math.abs(targetX-currX) > 2 || Math.abs(targetY-currY) > 2) {
            boolean xPos = false;
            if (targetX > currX) {
                xPos = true;
            }
            boolean yPos = false;
            if (targetY > currY) {
                yPos = true;
            }
            if (xPos && yPos) {
                return 0;
            } else if (!xPos && yPos) {
                return 1;
            } else if (!xPos) {
                return 2;
            } else {
                return 3;
            }
        } else {
            return -1; //sentinel for all quadrants
        }
    }

}
