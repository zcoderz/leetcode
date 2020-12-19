package frequent.general.medium;

import utils.Pair;

import java.util.*;

public class MinKnightMoves {

    public static void main(String [] args) {
        MinKnightMoves minK = new MinKnightMoves();
        int num = minK.minKnightMoves(2, 1);
        System.out.println(num);
    }

    Map<Pair<Integer,Integer>, Integer> coordinateMoves = new HashMap<>();
    Set<Pair<Integer, Integer>> visiting = new HashSet<>();

    public int minKnightMoves(int x, int y) {
        return processMinKnightMoves(0, 0, x, y);
    }

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
        coordinateMoves.put(new Pair<>(currX, currY), minDistance);
        return minDistance;
    }


    List<Pair<Integer, Integer>> getPossibleMoves(int currX, int currY, int targetX, int targetY) {
        double currDistance = calcDistance(currX, currY, targetX, targetY);
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        int [][] signs = {{1,1}, {-1,-1}, {1, -1}, {-1, 1}};
        int [] possibleDirs = {1, 2};

        for (int j = 0; j <= 1; j++) {
            int x = possibleDirs[j];
            int y = 1;
            if (x == 1) {
                y = 2;
            }
            for (int i = 0; i < signs.length; i++) {
                int[] sign = signs[i];
                int y1 = y * sign[1];
                int x1 = x * sign[0];
                int newX = currX + x1;
                int newY = currY + y1;
                if (calcDistance(newX, newY, targetX, targetY) <= currDistance) {
                    Pair<Integer, Integer> p = new Pair<>(newX, newY);
                    pairs.add(p);
                }
            }
        }
        return pairs;
    }

    double calcDistance (int currX, int currY, int targetX, int targetY) {
        int xDiff = targetX - currX;
        int yDiff = targetY - currY;
        return Math.pow(Math.pow(xDiff, 2) + Math.pow(yDiff, 2) , 0.5);
    }
}
