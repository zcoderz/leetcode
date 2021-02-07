package hash_maps;

import java.util.HashMap;
import java.util.Map;

/**
 * 37. Sudoku Solver
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 * Explanation: The input board is shown above and the only valid solution is shown below:
 *
 * IMP-3: Common question
 */
public class Sudoku {
    
    public static void main(String [] args) {
        
        Sudoku sudoku = new Sudoku();
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'}, 
                {'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        
        boolean isValid = sudoku.isValidSudoku(board);
        System.out.println(isValid);
    }
    
    public boolean isValidSudoku(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        Map[] rowsMap = new  HashMap [rows];
        Map[] colsMap = new HashMap [cols];
        Map[] boxesMap = new HashMap [rows];

        for (int i =0; i < rows; i++) {
            rowsMap[i] = new HashMap<Character, Integer> ();
            colsMap[i] = new HashMap<Character, Integer> ();
            boxesMap[i] = new HashMap<Character, Integer> ();
        }

        for (int i =0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                //System.out.println(board[i][j]);
                int count = (Integer) rowsMap[i].getOrDefault(board[i][j], 0);
                count++;
                if (count > 1) return false;
                rowsMap[i].put(board[i][j], count);

                count = (Integer) colsMap[j].getOrDefault(board[i][j], 0);
                count++;
                if (count > 1) {
                    return false;
                }
                colsMap[j].put(board[i][j], count);

                int boxNum = i/3 *3 + j/3; //increase the box number at offsets of 3
                count = (Integer) boxesMap[boxNum].getOrDefault(board[i][j], 0);
                count++;
                if (count > 1) return false;
                boxesMap[boxNum].put(board[i][j], count);

            }
        }

        return true;
    }

}
