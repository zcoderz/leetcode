package backtracking;

import dynamic.CanJump;

import java.util.HashSet;
import java.util.Set;

public class WordSearch {

    public boolean exist(char[][] board, String word) {
        char ch = word.charAt(0);
        for (int i = 0 ; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (board[i][j] == ch) {
                    if (traverse(i, j, board, word, 1) == true) {
                        return  true;
                    }
                }
            }
        }
        return false;
    }

    public boolean traverse(int row, int col, char [][] board, String word, int wordIndex) {
        if (wordIndex >= word.length()) return true;

        char ch = word.charAt(wordIndex);
        int rightCol = col+1;
        int leftCol = col-1;
        int rowUp = row - 1;
        int rowDown = row + 1;
        if (leftCol < 0 || rowUp <0 || rightCol >= board[0].length || rowDown >= board.length ||
                board[row][col] != word.charAt(wordIndex)) {
            return false;
        }
        //right, left, up, down
        int [] rowMoves = {0, 0, -1, 1};
        int [] colMoves = {1, -1, 0, 0};

        board[row][col]='#';

        for (int i =0 ; i < 4; i++) {
            int nCol = col + colMoves[i];
            int nRow = row = rowMoves[i];
            if (traverse(nRow, nCol, board, word, wordIndex + 1) == true) {
                //return without cleanup
                return  true;
            }
        }

        board[row][col] = word.charAt(wordIndex);
        
        return false;

    }
}
