package frequent.medium;


/**
 * print characters in a string in zig zag pattern
 */
public class ZigZagConversion {

    public static void main(String [] args) {
        ZigZagConversion zigZagConversion = new ZigZagConversion();
        String res = zigZagConversion.convert("A", 1);
        System.out.println(res);
    }

    public String convert(String s, int numRows) {
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
