package google.medium;

import utils.Pair;

import java.util.*;

/**
 * 939. Minimum Area Rectangle
 * <p>
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides
 * parallel to the x and y axes.
 * <p>
 * If there isn't any rectangle, return 0.
 * <p>
 * check MinAreaRectangle for a simpler and more efficient solution for the same problem.
 */
public class MinAreaRectangleComplicated {

    TreeMap<Integer, TreeSet<Integer>> coordinateMap = new TreeMap<>();
    TreeMap<Integer, TreeSet<Pair<Integer, Integer>>> coordinateMapPairs = new TreeMap<>();
    PairComparator comparator = new PairComparator();
    int minArea = Integer.MAX_VALUE;

    public static void main(String[] args) {
//        int [][] cords = {{1,1},{1,3},{3,1},{3,3},{2,2}};
//        MinAreaRectangle min = new MinAreaRectangle();
//        int area = min.minAreaRect(cords);
//        System.out.println(area);

        int[][] cords2 = {{1, 1}, {1, 3}, {3, 1}, {3, 3}, {4, 1}, {4, 3}};
        MinAreaRectangleComplicated min2 = new MinAreaRectangleComplicated();
        int area2 = min2.minAreaRect(cords2);
        System.out.println(area2);


    }

    public int minAreaRect(int[][] points) {
        for (int[] point : points) {
            TreeSet<Integer> yCoordinates = coordinateMap.computeIfAbsent(point[0], (l) -> new TreeSet<>());
            yCoordinates.add(point[1]);
        }

        for (Map.Entry<Integer, TreeSet<Integer>> entry : coordinateMap.entrySet()) {
            TreeSet<Pair<Integer, Integer>> ySides = convertYCoordinatesToPairs(entry.getValue());
            coordinateMapPairs.put(entry.getKey(), ySides);
        }

        for (Map.Entry<Integer, TreeSet<Pair<Integer, Integer>>> entry : coordinateMapPairs.entrySet()) {
            TreeSet<Pair<Integer, Integer>> pairs = entry.getValue();

            NavigableMap<Integer, TreeSet<Pair<Integer, Integer>>> rightSideMap =
                    coordinateMapPairs.tailMap(entry.getKey(), false);

            if (!rightSideMap.isEmpty()) {
                for (Pair<Integer, Integer> yCords : pairs) {
                    int yLen = yCords.second - yCords.first;

                    for (Map.Entry<Integer, TreeSet<Pair<Integer, Integer>>> rightEntry : rightSideMap.entrySet()) {
                        if ((yLen * (rightEntry.getKey() - entry.getKey())) >= minArea) {
                            break;
                        }
                        if (rightEntry.getValue().contains(yCords)) {
                            minArea = Math.min(minArea, yLen * (rightEntry.getKey() - entry.getKey()));
                        }
                    }
                }
            }
        }
        if (minArea == Integer.MAX_VALUE) {
            return 0;
        }
        return minArea;
    }

    TreeSet<Pair<Integer, Integer>> convertYCoordinatesToPairs(TreeSet<Integer> coordinates) {
        TreeSet<Pair<Integer, Integer>> list = new TreeSet<>(comparator);
        for (Integer cord : coordinates) {
            NavigableSet<Integer> upperCords = coordinates.tailSet(cord, false);
            for (Integer upperCord : upperCords) {
                list.add(Pair.of(cord, upperCord));
            }
        }
        return list;
    }

    static class PairComparator implements Comparator<Pair<Integer, Integer>> {
        @Override
        public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
            int y1Len = o1.second - o1.first;
            int y2Len = o2.second - o2.first;

            if (y1Len == y2Len) {
                return Integer.compare(o1.first, o2.first);
            } else {
                return Integer.compare(y1Len, y2Len);
            }
        }
    }

}
