package TreesAndGraph;

import java.util.*;

public class MostStonesRemoved {

    public static void main(String [] args) {
        MostStonesRemoved mostStonesRemoved = new MostStonesRemoved();

        int [][] stones = {{3,2},{3,1},{4,4},{1,1},{0,2},{4,0}};
                //{{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        mostStonesRemoved.removeStones(stones);

    }

    class Pair<T, U> {
        T fst;
        U snd;
        Pair(T a, U b) {
            this.fst = a;
            this.snd = b;
        }

        public int hashCode() {
            return fst.hashCode() + 7 * snd.hashCode();
        }

        public boolean equals(Object pP) {
            Pair p = (Pair) pP;
            return (p.snd == this.snd) && (p.fst == fst);
        }
    }

    Map<Integer, List<Integer>> xCordMap = new HashMap<>();
    Map<Integer, List<Integer>> yCordMap = new HashMap<>();
    List<Pair<Integer, Integer>> points = new ArrayList<>();
    Map<Pair<Integer, Integer>, Integer> pointDepth = new HashMap<>();
    Integer maxD = 0;

    public int removeStones(int[][] stones) {
        for (int i = 0; i < stones.length; i++) {
            Integer x = stones[i][0];
            Integer y = stones[i][1];
            List<Integer> yCordinates =  xCordMap.getOrDefault(x, new ArrayList<>());
            yCordinates.add(y);
            xCordMap.put(x, yCordinates);
            List<Integer> xCordinates =  yCordMap.getOrDefault(y, new ArrayList<>());
            xCordinates.add(x);
            yCordMap.put(y, xCordinates);
            Pair<Integer, Integer> p  = new Pair(x, y);
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
        System.out.println(maxD-1);
        return maxD-1;
    }

    Integer process(Pair<Integer, Integer> p, Set<Pair<Integer, Integer>> visting) {
        List<Integer> yCords =  xCordMap.get(p.fst);
        List<Integer> xCords =  yCordMap.get(p.snd);
        int maxD = 0;
//        if (visting.contains(p)) {
//            return 0;
//        }
//        visting.add(p);
        // if (pointDepth.containsKey(p)) {
        //     return pointDepth.get(p);
        // }

        for (Integer yCord : yCords) {
            if (yCord.equals(p.snd)) {
                continue;
            }
            Pair pTmp = new Pair<>(p.fst, yCord);
            if (visting.contains(pTmp)) continue;
            visting.add(pTmp);
            maxD = Math.max(maxD, process(pTmp, visting));
            visting.remove(pTmp);
        }

        for (Integer xCord : xCords) {
            if (xCord.equals(p.fst)) {
                continue;
            }
            Pair pTmp = new Pair<>(xCord, p.snd);
            if (visting.contains(pTmp)) continue;
            visting.add(pTmp);
            maxD = Math.max(maxD, process(pTmp, visting));
            visting.remove(pTmp);
        }
        //pointDepth.put(p, maxD+1);
        return maxD+1;
    }

}
