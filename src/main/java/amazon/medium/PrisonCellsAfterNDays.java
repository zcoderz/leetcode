package amazon.medium;

import java.util.HashMap;
import java.util.Map;

public class PrisonCellsAfterNDays {

    public static void main (String [] args) {
        PrisonCellsAfterNDays cellsAfterNDays = new PrisonCellsAfterNDays();
        int [] cells = {0,1,0,1,1,0,0,1};
        int [] nC = cellsAfterNDays.prisonAfterNDays(cells, 7);
        cellsAfterNDays.printCells(nC);
    }

    void printCells (int [] cells) {
        for(int i = 0; i < cells.length; i++) {
            System.out.print(cells[i] + ",");
        }
        System.out.println();
    }

    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<Integer, Integer> bitCycleMap = new HashMap<>();
        int currBit = bitFromCells(cells);
        //printCells(getCellsFromBit(currBit, cells.length));
        for (int i =0; i < N; i++) {
            if (bitCycleMap.containsKey(currBit)) {
                N %= bitCycleMap.get(currBit);
            } else {
                bitCycleMap.put(currBit, i+1);
                currBit = nextBit(currBit);
                //System.out.println("At index " + (i));
                //printCells (getCellsFromBit(currBit, cells.length));

            }
        }
        return getCellsFromBit(currBit, cells.length);
    }

    int [] getCellsFromBit(Integer bit , int n) {
        int [] cells = new int[n];
        for (int i =n-1 ; i >= 0; i--) {
            cells[i] = bit & 1;
            bit = (bit >> 1);
        }
        return cells;
    }

    private int nextBit(int currBit) {
        int left = (currBit << 1);
        int right = (currBit >> 1);
        return ((~(left ^ right)) & 0x7e);
    }

    private int bitFromCells (int [] cells) {
        int val = 0;
        for (int cell : cells) {
            val = (val << 1);
            val = (val | cell);
        }
        return val;
    }
}
