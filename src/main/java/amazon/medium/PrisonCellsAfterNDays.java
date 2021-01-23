package amazon.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * this is a very interesting problem and should be practiced carefully
 * <p>
 * suggest do this again!
 */
public class PrisonCellsAfterNDays {

    public static void main(String[] args) {
        PrisonCellsAfterNDays cellsAfterNDays = new PrisonCellsAfterNDays();
        //int [] cells = {0,1,0,1,1,0,0,1};
        int[] cells = {1, 0, 0, 1, 0, 0, 1, 0};
        int[] nC = cellsAfterNDays.prisonAfterNDays(cells, 1000000000);
        cellsAfterNDays.printCells(nC);
    }

    void printCells(int[] cells) {
        for (int i = 0; i < cells.length; i++) {
            System.out.print(cells[i] + ",");
        }
        System.out.println();
    }

    /**
     * returns transformation in cells after moves that's passed as param transformation is given by logic in nextBit
     * method below
     *
     * @param cells
     * @param movesLeft
     * @return
     */
    public int[] prisonAfterNDays(int[] cells, int movesLeft) {
        Map<Integer, Integer> bitCycleMap = new HashMap<>();
        int currBit = bitFromCells(cells); //this is the current bit
        boolean cycleFound = false;
        while (movesLeft > 0) {
            //if cycle not already found and the current bit was already seen then
            if (!cycleFound && bitCycleMap.containsKey(currBit)) {
                //calculate length of the cycle
                int cycleLength = bitCycleMap.get(currBit) - movesLeft;
                //since the moves will repeat every cycle length mod cycle length with moves left
                movesLeft %= cycleLength;
                cycleFound = true;
            }

            if (!cycleFound) {
                bitCycleMap.put(currBit, movesLeft);
            }
            if (movesLeft != 0) {
                //its possible that a cycle caused the moves left to transform from non zero to zero
                //hence move to next bit only if you havent reached number of moves already
                currBit = nextBit(currBit);
                movesLeft--;
            }
        }
        return getCellsFromBit(currBit, cells.length);
    }

    /**
     * method gets cells from bit
     *
     * @param bit
     * @param n
     * @return
     */
    int[] getCellsFromBit(Integer bit, int n) {
        int[] cells = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            cells[i] = bit & 1;
            bit = (bit >> 1);
        }
        return cells;
    }

    /**
     * method returns bit transformation of the current bit based on below logic 1 if both left and right neighbors are
     * 1 or 0, otherwise bit is 0
     *
     * @param currBit
     * @return
     */
    private int nextBit(int currBit) {
        int left = (currBit << 1); //you get the left cell if you left shift
        int right = (currBit >> 1); //you get the right cell if you right shift
        //left ^ right returns 1 only if left ro right is 1, we want opposite of that hence not
        //above logic causes first and last bit to never be 1 .
        // hence we and with 0x7e which is 01111110 ( max cells in requirement are 8)
        return ((~(left ^ right)) & 0x7e);
    }

    /**
     * method returns a bit representation of cells
     *
     * @param cells
     * @return
     */
    private int bitFromCells(int[] cells) {
        int val = 0;
        for (int cell : cells) {
            val = (val << 1); //each iteration shift prior left
            val = (val | cell); //add bit for current cell
        }
        return val;
    }
}
