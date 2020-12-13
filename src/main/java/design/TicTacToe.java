package design;

import java.util.Arrays;

public class TicTacToe {

    int [][] grid;
    int n;

    int [] columns;
    int [] rows;
    int    diagonal;
    int    anti_diag;

    public static void main(String [] args) {
        TicTacToe ticTacToe = new TicTacToe(2);
        int val = ticTacToe.move(0,1,1);
        val = ticTacToe.move(1,1,2);
        val = ticTacToe.move(1,0,1);
        int j = 3;
    }



    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        grid = new int[n][n];
        for (int [] gVal : grid) {
            Arrays.fill(gVal, 0);
        }
        this.n = n;
        columns = new int[n]; Arrays.fill(columns, 0);
        rows = new int[n]; Arrays.fill(rows, 0);
        diagonal = 0;
        anti_diag = 0;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        grid[row][col]= player;
        if (validateSimplified(player, row, col)) return player;
        return 0;
    }

    private boolean validateSimplified(int player, int row, int col) {
        if (player == 2) player = - 1;
        columns[col] += player;
        rows[row] += player;
        if (row == col) {
            diagonal += player;
        }
        if ((row + col) == n - 1) {
            anti_diag += player;
        }

        return (diagonal == n || anti_diag == n || columns[col] == n || rows[row] == n) ||
                (-1*diagonal == n || -1*anti_diag == n || -1*columns[col] == n || -1*rows[row] == n);
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
