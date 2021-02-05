package frequent.medium;


/**
 * 6. ZigZag Conversion
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 *
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * print characters in a string in zig zag pattern
 *
 * IMP-1 : This is a tricky problem - its basically deriving mathematical equations based on patterns
 */
public class ZigZagConversion {

    public static void main(String [] args) {
        ZigZagConversion zigZagConversion = new ZigZagConversion();
        String res = zigZagConversion.convert("A", 1);
        System.out.println(res);
    }

    /**
     * concept is that cycles repeat every N*2-2, where N is number of rows
     * first and last rows have one iteration of repeat every loop
     * other rows have two repeats , one every N*2-2 and the second after 2*(N*2-2) - i.
     * the second repeat is essentially calculated as next repeat of first row - how far curr row is off of first row
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        //cycle is twice number of rows -2. every row except first and last gets two repeats
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            //repeat every cycleLen
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    //this repeat is for rows except first and last
                    //its :  next repeat of first row which is j + cycleLen and then subtract how far
                    //this row is from the first row.
                    ret.append(s.charAt(j + cycleLen - i));
                }
            }
        }
        return ret.toString();
    }

    /**
     * Efficient but complicated
     * @param s
     * @param numRows
     * @return
     */
    public String convertComplicated(String s, int numRows) {
        int rowNum = 1;
        StringBuilder builder = new StringBuilder();

        while (rowNum <= numRows) {
            if (rowNum == 1 || rowNum == numRows) {
                int startIndex = 0;
                if (rowNum ==numRows) {
                    startIndex = numRows-1;
                }
                while (startIndex < s.length()) {
                    builder.append(s.charAt(startIndex));
                    int diff =   2*(numRows-2) +2; //case for first and last row
                    startIndex += (diff ==0? 1: diff); //special case when there is only one row
                }
            } else {
                int startIndex = rowNum - 1;
                int direction = 0;
                while (startIndex < s.length()) {
                    builder.append(s.charAt(startIndex));
                    //characters in mid rows displacement varies based on whether repeat is occurring
                    //after visiting top row or bottom row.
                    if (direction%2==0) {
                        startIndex += 2* ((numRows-1)-rowNum) + 2;
                    } else {
                        startIndex += 2* (rowNum-2) + 2;
                    }
                    direction++;
                }
            }
            rowNum++;
        }
        return builder.toString();
    }

}
