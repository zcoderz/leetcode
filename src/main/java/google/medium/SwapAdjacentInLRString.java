package google.medium;

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


    public boolean canTransform(String start, String end) {
        if (start.length() != end.length()) {
            return false;
        }
        //check countX is same in both strings
        int countX =0;
        for (int i =0; i < start.length(); i++) {
            if (start.charAt(i) == 'X') {
                countX++;
            }
        }
        for (int i =0; i < end.length(); i++) {
            if (end.charAt(i) == 'X') {
                countX--;
            }
        }
        if (countX != 0) {
            return false;
        }
        int i = 0;
        int j = 0;
        int len = start.length();

        while (i < len && j < len) {
            while (start.charAt(i) == 'X') {
                i++;
            }
            while(end.charAt(j) == 'X') {
                j++;
            }
            if ((start.charAt(i) == 'L') && ((end.charAt(j) != 'L') || j > i)) {
                return false;
            }
            if ((end.charAt(j) == 'R') && ((start.charAt(i) != 'R') || i > j)) {
                return false;
            }
            i++; j++;
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
