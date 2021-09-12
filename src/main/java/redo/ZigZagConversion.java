package redo;

public class ZigZagConversion {

    public static void main(String [] args) {
        ZigZagConversion zig = new ZigZagConversion();
        String res = zig.convert("PAYPALISHIRING", 4);
        System.out.println(res);
    }

    public String convert(String s, int numRows) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j =i ; j < s.length(); ) {
                out.append(s.charAt(j));
                if (i > 0 &&  i < numRows-1) {
                    int z = 2 * numRows - 2 - 2*i + j;
                    if (z < s.length()) {
                        out.append(s.charAt(z));
                    }
                }
                j += 2 * numRows - 2;
            }
        }
        return out.toString();
    }
}
