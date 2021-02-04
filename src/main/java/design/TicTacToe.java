package design;

import java.util.Arrays;

/**
 * 348. Design Tic-Tac-Toe
 *
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 *
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves are allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Implement the TicTacToe class:
 *
 * TicTacToe(int n) Initializes the object the size of the board n.
 * int move(int row, int col, int player) Indicates that player with id player plays at the
 * cell (row, col) of the board. The move is guaranteed to be a valid move.
 * Follow up:
 * Could you do better than O(n2) per move() operation?
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
 * [[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
 * Output
 * [null, 0, 0, 0, 0, 0, 0, 1]
 *
 * Explanation
 * TicTacToe ticTacToe = new TicTacToe(3);
 * Assume that player 1 is "X" and player 2 is "O" in the board.
 * ticTacToe.move(0, 0, 1); // return 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 *
 * ticTacToe.move(0, 2, 2); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 *
 * ticTacToe.move(2, 2, 1); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 *
 * ticTacToe.move(1, 1, 2); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 *
 * ticTacToe.move(2, 0, 1); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 *
 * ticTacToe.move(1, 0, 2); // return 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 *
 * ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 *
 */
public class TicTacToe {

    int[][] grid;
    int n;

    int[] columns;
    int[] rows;
    int diagonal;
    int anti_diag;

    /**
     * Initialize your data structure here.
     */
    public TicTacToe(int n) {
        grid = new int[n][n];
        for (int[] gVal : grid) {
            Arrays.fill(gVal, 0);
        }
        this.n = n;
        columns = new int[n];
        Arrays.fill(columns, 0);
        rows = new int[n];
        Arrays.fill(rows, 0);
        diagonal = 0;
        anti_diag = 0;
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe(2);
        int val = ticTacToe.move(0, 1, 1);
        val = ticTacToe.move(1, 1, 2);
        val = ticTacToe.move(1, 0, 1);
        int j = 3;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either: 0: No one wins. 1: Player 1 wins. 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        grid[row][col] = player;
        if (validateSimplified(player, row, col)) return player;
        return 0;
    }

    private boolean validateSimplified(int player, int row, int col) {
        if (player == 2) player = -1;
        columns[col] += player;
        rows[row] += player;
        if (row == col) {
            diagonal += player;
        }
        if ((row + col) == n - 1) {
            anti_diag += player;
        }

        return (diagonal == n || anti_diag == n || columns[col] == n || rows[row] == n) ||
                (-1 * diagonal == n || -1 * anti_diag == n || -1 * columns[col] == n || -1 * rows[row] == n);
    }

    private boolean validate(int player, int row, int col) {
        boolean found = true;
        for (int i = 0; i < n; i++) {
            if (grid[i][col] != player) {
                found = false;
                break;
            }
        }
        if (found) return true;
        found = true;
        for (int i = 0; i < n; i++) {
            if (grid[row][i] != player) {
                found = false;
                break;
            }
        }
        if (found) return true;


        if (row == col) {
            found = true;
            for (int i = 0; i < n; i++) {
                if (grid[i][i] != player) {
                    found = false;
                    break;
                }
            }
        }
        if (found) return true;

        if ((row + col) == n - 1) {
            found = true;
            for (int i = 0; i < n; i++) {
                if (grid[i][n - i - 1] != player) {
                    found = false;
                    break;
                }
            }
        }
        return found;
    }
}
