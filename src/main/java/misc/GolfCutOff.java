package misc;

import java.util.*;

public class GolfCutOff {


    public static class Pair<E> {
        E left;
        E right;
        Pair (E left, E right) {
            this.left = left;
            this.right = right;
        }

        public int hashCode () {
            return left.hashCode() + 7 * right.hashCode();
        }

        public boolean equals (Object right) {
            return ((Pair<E>) right).left.equals(this.left) && ((Pair<E>) right).right.equals(this.right);
        }

    }

    public Set<Pair<Integer>> notVisited = new HashSet<>();

    public static void main (String [] args) {
        //[[1,2,3],[0,0,4],[7,6,5]]
        List<List<Integer>> forest = new ArrayList<>();
        List<Integer> a1 = new ArrayList<>();
        a1.add(1); a1.add(2); a1.add(3);
        List<Integer> a2 = new ArrayList<>();
        a2.add(0); a2.add(0); a2.add(3);
        List<Integer> a3 = new ArrayList<>();
        a3.add(7); a3.add(6); a3.add(5);
        forest.add(a1); forest.add(a2); forest.add(a3);

        GolfCutOff cutOff = new GolfCutOff();
        int val =  cutOff.cutOffTree(forest);
        System.out.println(val);
    }

    public int cutOffTree(List<List<Integer>> forest) {
        int rows = forest.size();
        if (rows == 0) { return 0; }
        int columns = forest.get(0).size();
        markCells(rows, columns, forest);
        int tries = walkTheField(rows, columns, forest);

        if (notVisited.isEmpty()) {
            return tries;
        } else {
            return -1;
        }
    }

    int walkTheField(int rows, int columns, List<List<Integer>> forest) {
        Pair<Integer> coordinate = new Pair<>(0, 0);
        int tries  =0;

        while (coordinate != null) {
            notVisited.remove(coordinate);
            coordinate = findNextSpot(coordinate, forest);
            tries++;
        }

        return tries-1;
    }

    Pair<Integer> findNextSpot(Pair<Integer> currentSpot, List<List<Integer>> forest) {
        Pair<Integer> left = new Pair<>(currentSpot.left, currentSpot.right-1);
        Pair<Integer> right = new Pair<>(currentSpot.left, currentSpot.right+1);
        Pair<Integer> up = new Pair<>(currentSpot.left-1, currentSpot.right);
        Pair<Integer> down = new Pair<>(currentSpot.left+1, currentSpot.right);
        int minHeight = Integer.MAX_VALUE;
        List<Pair<Integer>> allCoordinates = new ArrayList<>();
        List<Pair<Integer>> possibleCoordinates = new ArrayList<>();
        allCoordinates.add(left); allCoordinates.add(right); allCoordinates.add(up); allCoordinates.add(down);
        for (Pair<Integer> coorindate : allCoordinates) {
            if (notVisited.contains(coorindate)) {
                possibleCoordinates.add(coorindate);
            }
        }

        Pair<Integer> theCoordinate = null;

        for (Pair<Integer> coordinate : possibleCoordinates) {
            int height = forest.get(coordinate.left).get(coordinate.right);
            if (height < minHeight) {
                theCoordinate = coordinate;
            }
        }
        return theCoordinate;
    }


    void markCells(int rows, int columns, List<List<Integer>> forest) {
        for (int i =0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int val = forest.get(i).get(j);
                if (val != 0) {
                    Pair<Integer> pair = new Pair<>(i, j);
                    notVisited.add(pair);
                }
            }
        }
    }
}


