package amazon.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * the code asks to find min moves needed to traverse the two dimensional array -1 is assumed as a standard move allowed
 * to next cell a number greater than index of the cell means you reach a ladder a number less than the target move
 * means you reached a snake, traverse back
 * <p>
 * first cell starts from last row and first col every other row the direction of traversal swaps between left and
 * right
 * <p>
 * this code is a great exercise for practicing coordinate management while traversing a 2-d array the requirements put
 * interesting quirks.....!
 */
public class SnakesAndLadder {

    public static void main(String[] args) {
        int[][] board = {{-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };

        SnakesAndLadder snakesAndLadder = new SnakesAndLadder();
        int dist = snakesAndLadder.snakesAndLadders(board);
        System.out.println(dist);
    }

    /**
     * use BFS to find the distance that needs to be travelled to reach the destination BFS is simpler to think about
     * for distance to target dont try DFS as it complicates the code a whole lot
     *
     * @param board
     * @return
     */
    public int snakesAndLadders(int[][] board) {
        int N = board.length;

        Map<Integer, Integer> dist = new HashMap();
        dist.put(1, 0);

        Queue<Integer> queue = new LinkedList();
        queue.add(1);
        dist.put(1, 0);

        //simple BFS based approach
        while (!queue.isEmpty()) {
            int s = queue.remove();
            if (s == N * N) {
                //terminate when target is reached
                return dist.get(s);
            }

            //repeat for 6 dice
            for (int s1 = s + 1; s1 <= s + 6 && s1 <= N * N; s1++) {
                //for each square get its correct index
                int rc = get(s1, N);
                int row = rc / N;
                int col = rc % N;

                //get the target as that specified as non -1 for ladder or square
                //when target cell -1, quantity this is as a unit move to the target cell
                int target = board[row][col] == -1 ? s1 : board[row][col];

                //dont process the distance if it was already processed previously (prevents cycles)
                if (!dist.containsKey(target)) {
                    dist.put(target, dist.get(s) + 1);
                    queue.add(target);
                }
            }
        }
        return -1;
    }

    /**
     * the method returns coordinates respective to the index of the square for the row it ensures that that we start
     * from the last row as based on the spec for the column it ensures that we swap left to right traversal direction
     * after each row
     *
     * @param s
     * @param N
     * @return
     */
    public int get(int s, int N) {
        int quotient = (s - 1) / N;
        int rem = (s - 1) % N;
        //we are starting traversal from the last index in the matrix
        int row = N - 1 - quotient;
        //(row%2 == (N-1) %2) ensures that we return rem as index for first row and every second row
        //and N-rem-1 (index from right direction) for other rows.
        int col = (row % 2 == (N - 1) % 2) ? rem : N - rem - 1;

        return row * N + col;
    }

}
