package misc;

import java.util.HashSet;
import java.util.Set;

public class StonesRemoved {

    public static void main(String [] args) {
        int[][]stones = {{0,1},{1,0}};
        StonesRemoved removed = new StonesRemoved();
        int j = removed.removeStones(stones);
        System.out.println(j);
    }

    public int removeStones(int[][] stones) {
        DJS djs = new DJS(10000);
        for (int i = 0 ; i < stones.length; i++) {
            djs.union(stones[i][0], stones[i][1]);
        }
        Set<Integer> hashStones = new HashSet<>();
        for (int i=0; i < stones.length; i++) {
            hashStones.add(djs.find(stones[i][0]));
        }
        return stones.length-hashStones.size();
    }

    public class DJS {
        int [] parent;
        int [] rank;

        public DJS (int n) {
            parent = new int [n];
            rank = new int [n];
            for (int i =0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            while (parent[x] != x) {
                x = parent[x];
            }
            return x;
        }

        boolean union (int x, int y) {
            int parentx = find(x);
            int parenty = find(y);

            if (parentx == parenty) {
                return false;
            }

            int rx = rank[parentx];
            int ry = rank[parenty];

            if (rx > ry) {
                parent[parenty] = parentx;
            } else if (ry > rx) {
                parent[parentx] = parenty;
            } else {
                parent[parentx] = parenty;
                rank[parenty]++;
            }

            return true;
        }


    }

}



