package trees;

import java.util.*;

public class MostStonesRemovedTryTwo {

    public static void main(String [] args) {
        MostStonesRemovedTryTwo mostStonesRemoved = new MostStonesRemovedTryTwo();
        int [][] stones = {{3,2},{3,1},{4,4},{1,1},{0,2},{4,0}};
        int val = mostStonesRemoved.removeStones(stones);
        System.out.println(val);
    }


    public int removeStones(int[][] stones) {
        Map<Integer, List<Integer>> sameRows = new HashMap<>();
        Map<Integer, List<Integer>> sameCols = new HashMap<>();
        List<Integer>[] graph = new List[stones.length];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < stones.length; i++) {
            int x = stones[i][0];
            int y = stones[i][1];

            List<Integer> sRlist = sameRows.getOrDefault(x, new ArrayList<>());
            sameRows.put(x, sRlist);
            List<Integer> sClist = sameCols.getOrDefault(y, new ArrayList<>());
            sameCols.put(y, sClist);

            for(Integer iVal : sRlist) {
                graph[iVal].add(i);
                graph[i].add(iVal);
            }

            for(Integer iVal : sClist) {
                graph[iVal].add(i);
                graph[i].add(iVal);
            }

            sRlist.add(i);
            sClist.add(i);
        }

        boolean [] visited = new boolean[stones.length];
        int countOfConnected = 0;

        for (int j = 0; j < stones.length; j++) {
            if (!visited[j]) {
                dfs(j, graph, visited);
                countOfConnected++;
            }
        }

        return stones.length-countOfConnected;
    }

    void dfs(int i, List<Integer>[] graph, boolean[] visited) {
        visited[i] = true;

        for (Integer adj : graph[i]) {
            if (!visited[adj]) {
                dfs(adj, graph, visited);
            }
        }

    }

}
