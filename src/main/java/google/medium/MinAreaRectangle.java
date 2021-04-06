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
 * IMP-1: this is a line sweep based approach that's common in questions
 * such as the one below that ask area of rectangle
 */
public class    MinAreaRectangle {

    TreeMap<Integer, List<Integer>> coordinateMap = new TreeMap<>();
    int minArea = Integer.MAX_VALUE;

    public static void main(String[] args) {
//        int [][] cords = {{1,1},{1,3},{3,1},{3,3},{2,2}};
//        MinAreaRectangle min = new MinAreaRectangle();
//        int area = min.minAreaRect(cords);
//        System.out.println(area);

//        int [][] cords2 = {{1,1},{1,3},{3,1},{3,3},{4,1},{4,3}};
//        MinAreaRectangle min2 = new MinAreaRectangle();
//        int area2 = min2.minAreaRect(cords2);
//        System.out.println(area2);

        int[][] cords3 = {{3, 2}, {0, 0}, {3, 3}, {3, 4}, {4, 4}, {2, 1}, {4, 3}, {1, 0}, {4, 1}, {0, 2}};
        MinAreaRectangle min3 = new MinAreaRectangle();
        int area3 = min3.minAreaRect(cords3);
        System.out.println(area3);

    }

    public int minAreaRect(int[][] points) {
        //group the coordinates into a collections of y coordinates per each X axis
        for (int[] point : points) {
            List<Integer> yCoordinates = coordinateMap.computeIfAbsent(point[0], (l) -> new ArrayList<>());
            yCoordinates.add(point[1]);
        }
        HashMap<Pair<Integer, Integer>, Integer> xyMap = new HashMap<>();
        //move across the X axis from left to right
        for (Map.Entry<Integer, List<Integer>> entry : coordinateMap.entrySet()) {
            ArrayList<Pair<Integer, Integer>> ySides = convertYCoordinatesToPairs(entry.getValue());
            for (Pair<Integer, Integer> pair : ySides) {
                //for each y1,y2 pair see if that pair was previously seen, if seen calculate the area and update
                //if its the minimum area
                Integer priorX = xyMap.get(pair);
                if (priorX != null) {
                    int area = ((entry.getKey() - priorX) * (pair.second - pair.first));
                    minArea = Math.min(minArea, area);
                }
                xyMap.put(pair, entry.getKey());
            }
        }
        if (minArea == Integer.MAX_VALUE) {
            return 0;
        }
        return minArea;
    }

    /**
     * convert the y coordinates into pairs so that the logic for rectangle calculation stays simple
     *
     * @param coordinates
     * @return
     */
    ArrayList<Pair<Integer, Integer>> convertYCoordinatesToPairs(List<Integer> coordinates) {
        //need to sort so that pair.second is after pair.first
        Collections.sort(coordinates);
        ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < coordinates.size(); i++) {
            for (int j = i + 1; j < coordinates.size(); j++) {
                list.add(Pair.of(coordinates.get(i), coordinates.get(j)));
            }
        }
        return list;
    }
}
