package backtracking;

import dynamic.CanJump;

import java.util.HashSet;
import java.util.Set;

/**
 * 79. Word Search
 * Given an m x n board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells are
 * horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 *
 * IMP-1 : Very common question. Imp to practice.
 */
public class WordSearch {

    public static void main(String [] args) {
        WordSearch ws = new WordSearch();
        char [][] arr = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        boolean res = ws.exist(arr, word);
        System.out.println(res);
    }
    //right, left, up, down
    int [] rowMoves = {0, 0, -1, 1};
    int [] colMoves = {1, -1, 0, 0};

    public boolean exist(char[][] board, String word) {
        for (int i = 0 ; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (traverse(i, j, board, word, 0)) {
                    return  true;
                }
            }
        }
        return false;
    }

    public boolean traverse(int row, int col, char [][] board, String word, int wordIndex) {
        if (wordIndex >= word.length()) {
            return true;
        }
        char ch = word.charAt(wordIndex);
        if (row < 0 || col <0 || col == board[0].length || row == board.length ||
                board[row][col] != ch) {
            return false;
        }
        board[row][col]='#';
        for (int i =0 ; i < 4; i++) {
            int nCol = col + colMoves[i];
            int nRow = row + rowMoves[i];
            if (traverse(nRow, nCol, board, word, wordIndex + 1)) {
                board[row][col] = ch;
                return  true;
            }
        }
        board[row][col] = ch;
        return false;
    }
}
