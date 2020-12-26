package frequent.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * simple problem to move the bot in spiral order
 */
public class SpiralMatrix {

    public static void main(String [] args) {
        int [][] matrix = {
                {1,2,3},{4,5,6}, {7,8,9}
        };
        SpiralMatrix sp = new SpiralMatrix();
        List<Integer> out = sp.spiralOrder(matrix);
        for (Integer val : out) {
            System.out.println(val);
        }

    }


    //right, down, left, up
    int [] dirRows = {0, 1, 0, -1};
    int [] dirCols = {1, 0, -1, 0};
    int currDir = 0;
    int [][] matrix;
    List<Integer> out = new ArrayList<>();
    int currRow = 0;
    int currCol = 0;
    int maxRow;
    int maxCol;

    /**
     * moves the bot in spiral order.
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        this.matrix = matrix;
        maxRow = matrix.length;
        if (maxRow ==0) return out;
        maxCol = matrix[0].length;
        out.add(matrix[0][0]);  matrix[0][0] = Integer.MAX_VALUE;
        processMovements();
        return out;
    }

    /**
     * continues processing movements until they are no longer possible
     */
    void processMovements() {
        while (true) {
            boolean move = moveForward();
            if (!move) {
                currDir = (currDir+1) % 4;
                move = moveForward();
                if (!move) {
                    return; //have finished the traversals
                }
            }
        }
    }

    /**
     * move forward in current cell if possible
     * @return if move was success or not
     */
    boolean moveForward() {
        int newRow = currRow + dirRows[currDir];
        int newCol = currCol + dirCols[currDir];

        if (newRow >= maxRow || newCol >= maxCol || newRow < 0 || newCol < 0 //check out of bounds
                || matrix[newRow][newCol] == Integer.MAX_VALUE) { //check whether revisiting a cell
            return false;
        }

        this.currRow = newRow;
        this.currCol = newCol;
        out.add(matrix[currRow][currCol]);
        matrix[currRow][currCol] = Integer.MAX_VALUE;
        return true;
    }
}
