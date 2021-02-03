package amazon.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 909. Snakes and Ladders
 *
 * On an N x N board, the numbers from 1 to N*N are written boustrophedonically starting from the bottom left of
 * the board, and alternating direction each row.  For example, for a 6 x 6 board, the numbers are written as follows:
 *
 *
 * You start on square 1 of the board (which is always in the last row and first column).
 * Each move, starting from square x, consists of the following:
 *
 * You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or x+6, provided this number is <= N*N.
 * (This choice simulates the result of a standard 6-sided die roll: ie., there are always at most 6 destinations,
 * regardless of the size of the board.)
 * If S has a snake or ladder, you move to the destination of that snake or ladder.  Otherwise, you move to S.
 * A board square on row r and column c has a "snake or ladder" if board[r][c] != -1.
 * The destination of that snake or ladder is board[r][c].
 *
 * Note that you only take a snake or ladder at most once per move: if the destination to a snake or ladder is the
 * start of another snake or ladder, you do not continue moving.  (For example, if the board is `[[4,-1],[-1,3]]`,
 * and on the first move your destination square is `2`, then you finish your first move at `3`,
 * because you do not continue moving to `4`.)
 *
 * Return the least number of moves required to reach square N*N.  If it is not possible, return -1.
 *
 * Example 1:
 *
 * Input: [
 * [-1,-1,-1,-1,-1,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,35,-1,-1,13,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,15,-1,-1,-1,-1]]
 * Output: 4
 * Explanation:
 * At the beginning, you start at square 1 [at row 5, column 0].
 * You decide to move to square 2, and must take the ladder to square 15.
 * You then decide to move to square 17 (row 3, column 5), and must take the snake to square 13.
 * You then decide to move to square 14, and must take the ladder to square 35.
 * You then decide to move to square 36, ending the game.
 * It can be shown that you need at least 4 moves to reach the N*N-th square, so the answer is 4.
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
