package amazon.medium;

import java.util.HashMap;
import java.util.Map;

public class SnakesAndLadder {

    public static void main (String [] args) {
        int [][] board = {{-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,15,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                };

        SnakesAndLadder snakesAndLadder = new SnakesAndLadder();
        int dist = snakesAndLadder.snakesAndLadders(board);
        System.out.println(dist);
    }

    private Map<Integer, Integer> cellMinimum = new HashMap<>();
    private int[][] board;
    int cols;
    int rows;
    public int snakesAndLadders(int[][] board) {
        cols = board[0].length - 1;
        rows = board.length -1;
        int currMin = 0;
        for (int row = rows; row > -1; row--) {
            for (int col = cols ; col > -1; col--) {
                int index = (row) * (cols+1) + (col+1);
                if (board[row][col] == -1) {
                    cellMinimum.put(index, currMin++);
                } else if (board[row][col] > index) {
                    int target = board[row][col];
                    currMin = Math.min(cellMinimum.get(target), currMin-1) + 1;
                    cellMinimum.put(index, currMin++);
                } else if (board[row][col] < index) {
                    int target= board[row][col];
                    int distance = processSnake(index, target);
                    currMin = distance + 1;
                    cellMinimum.put(index, currMin++);
                }
            }
        }
        return currMin;
    }

    int processSnake(int currIndex, int target) {
        int currRow = currIndex /  cols;
        int currCol = currIndex % cols;

        int targetRow = target / cols;
        int targetCol = target % cols;
        int maxDist = 1000000;
        int currDist  = 0;
        for (int row = currRow; row <= targetRow; row++) {
            for (int col = currCol; col <= targetCol; col++) {
                if (board[row][col] > targetCol) {
                    int newMaxDist = currDist + cellMinimum.get(board[row][col]);
                    maxDist = Math.min(newMaxDist, maxDist);
                    currDist++;
                }
            }
        }
        return maxDist;
    }
}
