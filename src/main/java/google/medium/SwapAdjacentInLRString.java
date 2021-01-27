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

        start = "LX XL XR LX XL"; end = "XL LX  RX LX LX";
        isPossible = swap.canTransform(start, end);
        System.out.println(isPossible);
    }


    //"XL" with "LX", or replacing one occurrence of "RX" with "XR"
    public boolean canTransform(String start, String end) {
        if (start.length() != end.length()) {
            return false;
        }

        StringBuilder builder = new StringBuilder(start);
        int len = start.length();

        for (int i =0; i < len; i++) {
            if (builder.charAt(i) == end.charAt(i)) {
                continue;
            }
            if (i == len-1) {
                return false;
            }
            if ((builder.charAt(i+1) == end.charAt(i)) && (builder.charAt(i) == 'X'|| builder.charAt(i+1) == 'X')) {
                char ch = builder.charAt(i);
                builder.setCharAt(i, builder.charAt(i+1));
                builder.setCharAt(i+1, ch);
            } else {
                return false;
            }
        }

        return true;

    }

}
