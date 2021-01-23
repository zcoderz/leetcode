package trees;

import java.util.ArrayList;
import java.util.List;

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
