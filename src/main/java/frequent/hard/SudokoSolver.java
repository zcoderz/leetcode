package frequent.hard;

import java.util.*;

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
 * an interesting back tracking problem.
 * i use here sets to simplify the code but doing set copies slows down the code which is needed when doing
 * set union and intersection. a more efficient approach is to use arrays in row/cols for each of the integer values.
 * however, the set approach is more neat in implementation and can be applied to a bigger variety of problems.
 */
public class SudokoSolver {
    
    public static void main(String [] args) {
        char [][] matrix = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        SudokoSolver sudokoSolver = new SudokoSolver();
        sudokoSolver.solveSudoku(matrix);
        for (int i = 0; i < sudokoSolver.rows; i++) {
            for (int c =0; c < sudokoSolver.cols; c++) {
                System.out.print(matrix[i][c]);
            }
            System.out.println();
        }
    }
    
    Set<Integer> [] rowsData;
    Set<Integer> [] colData;
    Set<Integer> [] boxData;
    int rows;
    int cols;
    int totalCells;
    Set<Integer> untouchableCells = new HashSet<>();
    Integer [] possArr = {1,2,3,4,5,6,7,8,9};
    Set<Integer> possibles = new HashSet<>();
    char[][] board;


    public void solveSudoku(char[][] board) {
        this.board = board;
        possibles.addAll(Arrays.asList(possArr));
        rows = board.length;
        if (rows == 0) return;
        cols = board[0].length;
        if (cols == 0) return;
        totalCells = rows*cols;
        rowsData = new HashSet [rows];
        colData = new HashSet [cols];
        boxData = new HashSet [rows];
        // the problem defines that we are passed a grid of size 9x9
        for (int i = 0; i < rows; i++) {
            rowsData[i] = new HashSet<>();
        }
        for (int i = 0; i < cols; i++) {
            colData[i] = new HashSet<>();
        }
        for (int i = 0; i < rows; i++) {
            boxData[i] = new HashSet<>();
        }

        //fill the sets with data
        for (int row =0; row < rows; row++) {
            for (int col=0; col < cols; col++) {
                char c = board[row][col];
                if (c != '.') {
                    int index = (row) * cols + col;
                    untouchableCells.add(index);
                    int v = c-'0';
                    rowsData[row].add(v);
                    colData[col].add(v);
                    int box = (row/3) * 3 + col/3;
                    boxData[box].add(v);
                }
            }
        }
        recurseSudoku(0);
    }

    boolean recurseSudoku(int index) {
        if (index >= totalCells) {
            return true;
        } else if (untouchableCells.contains(index)) {
            return recurseSudoku(index+1);
        } else {
            int row = index/cols;
            int col = index % cols;
            int box = (row/3) * 3 + col/3;
            Set<Integer> set = new HashSet<>(rowsData[row]);
            set.addAll(colData[col]);
            set.addAll(boxData[box]);
            Set<Integer> possibles = new HashSet<>(this.possibles);
            possibles.removeAll(set);
            for (Integer possible : possibles) {
                board[row][col]= Integer.toString(possible).charAt(0);
                rowsData[row].add(possible);
                colData[col].add(possible);
                boxData[box].add(possible);
                boolean solution = recurseSudoku(index+1);
                if (solution) return true;
                rowsData[row].remove(possible);
                colData[col].remove(possible);
                boxData[box].remove(possible);
                board[row][col]= '.';//reset
            }
            return false;
        }
    }
}
