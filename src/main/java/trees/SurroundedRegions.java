package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * 130. Surrounded Regions
 *
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * Explanation:
 *
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board
 * are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the
 * border will be flipped to 'X'.
 * Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
public class SurroundedRegions {

    int rows;
    int cols;
    char[][] board;

    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


    public static void main(String[] args) {
        char[][] arr = {{'X', 'O', 'X'}, {'O', 'X', 'O'}, {'X', 'O', 'X'}};
        SurroundedRegions sO = new SurroundedRegions();
        sO.solve(arr);

    }

    /**
     * simple approach : traverse the Os at edge while converting them to P
     * traverse the bord and convert any Os to X
     * traverse the board and convert Ps to Os
     * @param board
     */
    public void solve(char[][] board) {
        rows = board.length;
        if (rows == 0) return;
        cols = board[0].length;
        this.board = board;
        List<int[]> borderWithO = borderTraverse();

        for (int[] coord : borderWithO) {
            if (board[coord[0]][coord[1]] == 'O') {
                traverse(coord[0], coord[1]);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; i++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; i < cols; i++) {
                if (board[i][j] == 'P') {
                    board[i][j] = 'O';
                }
            }
        }

    }


    public void traverse(int row, int col) {
        board[row][col] = 'P';

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                    board[newRow][newCol] == 'O') {
                traverse(newRow, newCol);
            }
        }
    }

    List<int[]> borderTraverse() {
        List<int[]> borderCordinates = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            if (board[0][i] == 'O') {
                borderCordinates.add(new int[]{0, i});
            }
        }
        for (int i = 0; i < cols; i++) {
            if (board[rows - 1][i] == 'O') {
                borderCordinates.add(new int[]{rows - 1, i});
            }
        }
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                borderCordinates.add(new int[]{i, 0});
            }
        }
        for (int i = 0; i < rows; i++) {
            if (board[i][cols - 1] == 'O') {
                borderCordinates.add(new int[]{i, cols - 1});
            }
        }
        return borderCordinates;
    }
}
