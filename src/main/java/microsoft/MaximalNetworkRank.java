package microsoft;

import java.util.Arrays;

public class MaximalNetworkRank {

    public static void main(String [] args) {
        int [][] roads = {{0,1},{0,3},{1,2},{1,3}};
        MaximalNetworkRank max = new MaximalNetworkRank();
        int sz = max.maximalNetworkRank(4, roads);
        System.out.println(sz);
    }

    public int maximalNetworkRank(int n, int[][] roads) {
        int [] cities = new int[n];
        boolean [][] connected = new boolean[n][n];

        for (int i =0; i < n; i++) {
            Arrays.fill(connected[i], false);
        }

        for (int i = 0; i < roads.length; i++) {
            cities[roads[i][0]]++;
            cities[roads[i][1]]++;
            connected[roads[i][0]][roads[i][1]] = true;
            connected[roads[i][1]][roads[i][0]] = true;
        }

        int max = 0;

        for (int i =0; i < cities.length; i++) {
            for (int j =0; j < cities.length; j++) {
                if (i==j) continue;
                int total = cities[i] + cities[j];
                if (connected[i][j]) {
                    total--;
                }
                max = Math.max(max, total);
            }
        }

        return max;
    }
}
