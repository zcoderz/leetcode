package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 119. Pascal's Triangle II
 *
 * Given an integer rowIndex, return the rowIndexth row of the Pascal's triangle.
 *
 * Notice that the row index starts from 0.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Follow up:
 *
 * Could you optimize your algorithm to use only O(k) extra space?
 *
 *
 *
 * Example 1:
 *
 * Input: rowIndex = 3
 * Output: [1,3,3,1]
 *
 */
public class PascalsTriangle {

    public static void main(String[] args) {
        PascalsTriangle pt = new PascalsTriangle();
        List<Integer> list = pt.getRow(3);
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    /**
     * this is an easy problem but remember to store value in prior row of left col was critical took me sometime to get
     * this right :(
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        int size = rowIndex + 1; //rowIndex starts from 0
        Integer[] arList = new Integer[size];
        Arrays.fill(arList, 0);
        arList[0] = 1; //code the first triangle row as 1
        for (int i = 2; i <= size; i++) { //start from the second row
            int j = 0;
            arList[j] = 1;
            int left = 1; //left is the value to remember for cell before the current
            while (j + 1 < i) { //fill each row with the right values, i is the last cell in row
                j++;
                //this is imp to remember.
                //we need to store the value that was there in given index before its updated
                //this is because the prior value is the value for the row before the current
                //and is needed to populate the next index.
                int tmp = arList[j];
                arList[j] += left; //left is the value of cell one row above and left which was previously stored
                left = tmp;
            }
            arList[i - 1] = 1;
        }
        return Arrays.asList(arList);
    }

    /**
     * this is leet code's solution much simpler.....
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRowSimple(int rowIndex) {
        List<Integer> row =
                new ArrayList<Integer>(rowIndex + 1) {
                    {
                        add(1);
                    }
                };

        for (int i = 0; i < rowIndex; i++) {
            for (int j = i; j > 0; j--) { //j terminates at first index
                //because they go right to left, dont need to remember value before update
                row.set(j, row.get(j) + row.get(j - 1));
            }
            row.add(1); //add one at end
        }

        return row;
    }

}
