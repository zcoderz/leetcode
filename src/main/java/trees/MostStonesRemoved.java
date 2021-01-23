package TreesAndGraph;

import utils.Pair;

import java.util.*;

public class MostStonesRemoved {

    Map<Integer, List<Integer>> xCordMap = new HashMap<>();
    Map<Integer, List<Integer>> yCordMap = new HashMap<>();
    List<Pair<Integer, Integer>> points = new ArrayList<>();
    Map<Pair<Integer, Integer>, Integer> pointDepth = new HashMap<>();
    Integer maxD = 0;

    public static void main(String[] args) {
        MostStonesRemoved mostStonesRemoved = new MostStonesRemoved();

        int[][] stones = {{3, 2}, {3, 1}, {4, 4}, {1, 1}, {0, 2}, {4, 0}};
        //{{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        mostStonesRemoved.removeStones(stones);

    }

    public int removeStones(int[][] stones) {
        for (int i = 0; i < stones.length; i++) {
            Integer x = stones[i][0];
            Integer y = stones[i][1];
            List<Integer> yCordinates = xCordMap.getOrDefault(x, new ArrayList<>());
            yCordinates.add(y);
            xCordMap.put(x, yCordinates);
            List<Integer> xCordinates = yCordMap.getOrDefault(y, new ArrayList<>());
            xCordinates.add(x);
            yCordMap.put(y, xCordinates);
            Pair<Integer, Integer> p = new Pair(x, y);
            points.add(p);
        }
        Set<Pair<Integer, Integer>> visiting = new HashSet<>();
        for (Pair p : points) {
            visiting.clear();
            visiting.add(p);
            Integer maxD = process(p, visiting);
            if (maxD > this.maxD) {
                this.maxD = maxD;
            }
        }
        System.out.println(maxD - 1);
        return maxD - 1;
    }

    Integer process(Pair<Integer, Integer> p, Set<Pair<Integer, Integer>> visting) {
        List<Integer> yCords = xCordMap.get(p.first);
        List<Integer> xCords = yCordMap.get(p.second);
        int maxD = 0;
        for (Integer yCord : yCords) {
            if (yCord.equals(p.second)) {
                continue;
            }
            Pair pTmp = new Pair<>(p.first, yCord);
            if (visting.contains(pTmp)) continue;
            visting.add(pTmp);
            maxD = Math.max(maxD, process(pTmp, visting));
            visting.remove(pTmp);
        }

        for (Integer xCord : xCords) {
            if (xCord.equals(p.first)) {
                continue;
            }
            Pair pTmp = new Pair<>(xCord, p.second);
            if (visting.contains(pTmp)) continue;
            visting.add(pTmp);
            maxD = Math.max(maxD, process(pTmp, visting));
            visting.remove(pTmp);
        }
        //pointDepth.put(p, maxD+1);
        return maxD + 1;
    }

}
