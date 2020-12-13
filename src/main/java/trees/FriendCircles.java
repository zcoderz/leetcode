package trees;

import java.util.HashSet;
import java.util.Set;

public class FriendCircles {

    public int findCircleNum(int[][] p) {
        int numPeople = p.length;
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < numPeople; i++) {
            if (!visited.contains(i)) {
                dfs(i, p, numPeople, visited);
                count++;
            }
        }
        return count;
    }

    void dfs(int i, int[][] p, int people, Set<Integer> visited) {
        visited.add(i);
        for (int j = 0; j < people; j++) {
            if (i != j && p[i][j] ==1 &&  !visited.contains(j)) {
                dfs(j, p, people, visited);
            }
        }
    }
}
