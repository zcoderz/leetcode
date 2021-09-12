package redo;

import java.util.*;

public class MinAreaRectangle {

    public static void main(String [] args) {
        //int [][] points = {{1,1},{1,3},{3,1},{3,3},{4,1},{4,3}};
        //int [][] points = {{1,1},{1,3},{3,1},{3,3},{2,2}};
        int [][] points = {{3,2},{3,1},{4,4},{1,1},{4,3},{0,3},{0,2},{4,0}};
        MinAreaRectangle minArea = new MinAreaRectangle();
        int area = minArea.minAreaRect(points);
        System.out.println(area);
    }

    public int minAreaRect(int[][] points) {
        TreeMap<Integer, List<Integer>> coordinates = new TreeMap<>();
        for (int [] point : points) {
            int x = point[0];
            int y = point[1];
            List<Integer> yCoords = coordinates.getOrDefault(x, new ArrayList<>());
            yCoords.add(y);
            coordinates.put(x, yCoords);
        }
        long  mask = (1L << 32) -1;
        long minArea = Long.MAX_VALUE;
        Map<Long, Integer> xBasedCords = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> coordinate: coordinates.entrySet()) {
            List<Integer> yPoints = coordinate.getValue();
            List<Long> yCoords = convertYPointsToCords(yPoints);
            for (Long cord : yCoords) {
                Integer priorX = xBasedCords.get(cord);
                if (priorX != null) {
                    int width = coordinate.getKey() - priorX;
                    long firstY = cord >> 32;
                    long secondY = cord & mask;
                    long yDist = secondY - firstY;
                    minArea = Math.min(minArea, yDist * width);
                }
                xBasedCords.put(cord, coordinate.getKey());
            }
        }
        return minArea == Long.MAX_VALUE ? 0: (int) minArea;
    }

    List<Long> convertYPointsToCords(List<Integer> yPoints) {
        Collections.sort(yPoints);
        List<Long> yLines = new ArrayList<>();
        for (int i =0; i < yPoints.size(); i++) {
            for (int j = i+1; j < yPoints.size(); j++) {
                long res = yPoints.get(i);
                res = res << 32;
                int p2 = yPoints.get(j);
                res += p2;
                yLines.add(res);
            }
        }
        return yLines;
    }
}
