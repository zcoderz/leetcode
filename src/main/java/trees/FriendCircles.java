package trees;

import java.util.HashSet;
import java.util.Set;

/**
 * Interesting problem
 */
public class FriendCircles {

    public static void main(String [] args) {
        int [][] matrix = {{1,1,0},
         {1,1,1},
         {0,1,1}};

        FriendCircles fc = new FriendCircles();
        int count = fc.findCircleNum(matrix);
        System.out.println(count);

    }

    public int findCircleNum(int[][] p) {
        int numPeople = p.length;
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < numPeople; i++) {
            if (!visited.contains(i)) {
                //each time we find a person who is not a friend, we increase the group count.
                //this helps ensure that groups are correctly calculated.
                dfs(i, p, numPeople, visited);
                count++;
            }
        }
        return count;
    }

    /**
     * DFS to find all people that are linked to the given person
     * @param i
     * @param p
     * @param people
     * @param visited
     */
    void dfs(int i, int[][] p, int people, Set<Integer> visited) {
        visited.add(i);
        for (int j = 0; j < people; j++) {
            //note that we are checking the row of person passed as first argument.
            //this ensures that once we find a person who is friend of current person
            //we can dfs through that person's friends.
            if (i != j && p[i][j] ==1 &&  !visited.contains(j)) {
                dfs(j, p, people, visited);
            }
        }
    }
}
