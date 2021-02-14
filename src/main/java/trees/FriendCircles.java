package trees;

import java.util.HashSet;
import java.util.Set;

/**
 * 547. Number of Provinces
 * There are n cities. Some of them are connected, while some are not.
 * If city a is connected directly with city b, and city b is connected directly with city c,
 * then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city
 * are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 *
 * IMP-1: Very common question. This question is same as that of StonesRemoved.
 * In below code we applied DFS, StonesRemoved was done via union find. Union find will be more efficient than dfs
 * The below approach will run in O(N^2) whereas union find runs in O(n log(n))
 *
 */
public class FriendCircles {

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}};

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
     *
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
            if (i != j && p[i][j] == 1 && !visited.contains(j)) {
                dfs(j, p, people, visited);
            }
        }
    }
}
