package google.medium;

/**
 * 777. Swap Adjacent in LR String
 *
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one
 * occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and
 * the ending string end,return True if and only if there exists a sequence
 * of moves to transform one string to the other.
 *
 *
 * Example 1:
 *
 * Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * Output: true
 * Explanation: We can transform start to end following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 *
 * need to recognize constraints that must be true after the transform.
 * the transform could move L left and R right.
 *
 * need to recognize that transform when it shifts L left, moves it index further to left from starting string
 * there location of each l in end string must occur at or before that of the starting
 *
 * similarly the transform must have the location of the R, be at or after that of the starting string.
 * therefore location of each R in final string must occur at or after that in the original string.
 *
 * 1 <= start.length <= 104
 * start.length == end.length
 * Both start and end will only consist of characters in 'L', 'R', and 'X'.
 *
 *
 * X can be ignored......
 *
 */
public class SwapAdjacentInLRString {

    public static void main(String [] args) {

        SwapAdjacentInLRString swap = new SwapAdjacentInLRString();
        String start = "RXXLRXRXL", end = "XRLXXRRLX";
        boolean isPossible = swap.canTransform(start, end);
        System.out.println(isPossible);

        start = "XLLR"; end = "LXLX";
        isPossible = swap.canTransform(start, end);
        System.out.println(isPossible);

        start = "LLR"; end = "RRL";
        isPossible = swap.canTransform(start, end);
        System.out.println(isPossible);

        start = "LXXLXRLXXL"; end = "XLLXRXLXLX";
        isPossible = swap.canTransform(start, end);
        System.out.println(isPossible);
    }

    /**
     * clever solution from leetcode . the code essentially transforms such that L moves XL to LX (i,e left) and
     * RX moves XR (i.e right). So you are essentially iterating through source and target and ensuring that the
     * condition does indeed hold
     * @param start
     * @param end
     * @return
     */
    public boolean canTransform(String start, String end) {
        int n = start.length();
        // count X in start and end --> should be the same
        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (start.charAt(i) == 'X') count++;
            if (end.charAt(i) == 'X') count--;
        }
        if (count != 0) return false;
        int i = 0;
        int j = 0;

        while (i < n && j < n) {
            //skip X
            while (i < n && start.charAt(i) == 'X') i++;
            while (j < n && end.charAt(j) == 'X') j++;

            //if the last character of start or end was X then same must be true for both of them
            if (i == n || j == n) {
                return i == n && j == n;
            }
            if (start.charAt(i) != end.charAt(j)) return false;
            if (start.charAt(i) == 'L' && i < j) return false;
            if (start.charAt(i) == 'R' && i > j) return false;

            i++;
            j++;
        }
        return true;
    }


    public boolean canTransformSlow(String start, String end) {
        if (start.length() != end.length()) {
            return false;
        }
        String pplOrderS = start.replaceAll("X", "");
        String ppOrderE = end.replaceAll("X", "");
        if (!pplOrderS.equals(ppOrderE)) {
            return false;
        }
        int rFound = 0;
        int lFound = 0;
        for (int i =0; i < start.length() -1; i++) {
            if (start.charAt(i) == 'R') {
                rFound++;
            }
            if (end.charAt(i) == 'L') {
                lFound++;
            }
            if ( start.charAt(i) == 'L') {
                if (lFound == 0) {
                    return false;
                }
                lFound--;
            }
            if ( end.charAt(i) == 'R') {
                if (rFound == 0) {
                    return false;
                }
                rFound--;
            }
        }
        return true;
    }

}
