package trees;

import java.util.Stack;

public class DecodeString {


    public static void main(String [] args) {
        String strVal = "3[a2[c]]";
        DecodeString dS = new DecodeString();
        strVal = dS.decodeString(strVal);
        System.out.println(strVal);
    }

    Stack<String> stringStack = new Stack<>();
    public String decodeString(String s) {
        String strPrior = "";
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                stringStack.push(strPrior);
                strPrior = "";
            } else if (s.charAt(i) == ']') {
                String concatVal = strPrior;
                int val = -1;
                while (val == -1) {
                    String strIntVal = stringStack.pop();
                    val = getNum(strIntVal);
                    if (val == -1) {
                        concatVal = strIntVal + concatVal;
                    } else {
                        concatVal = repeat(concatVal, val);
                        stringStack.push(concatVal);
                    }
                }
                strPrior="";
            } else {
                String newC = "" + s.charAt(i);
                boolean isStringSplit = stringSplit(newC, strPrior);
                if (isStringSplit) {
                    stringStack.push(strPrior);
                    strPrior = newC;
                } else {
                    strPrior = strPrior + s.charAt(i);
                }
            }
        }
        return simplify(strPrior);
    }

    boolean stringSplit(String strA, String strB) {
        int numA = getNum(strA);
        int numB = getNum(strB);
        return ((numA == -1 && numB > 0) || (numB == -1 && numA > 0));
    }

    String repeat (String strVal , int num) {
        String strRetVal = strVal;
        for (int i = 0; i < num-1; i++) {
            strRetVal = strRetVal + strVal;
        }
        return strRetVal;
    }

    String simplify(String strPrior) {
        String strBuffer = strPrior;
        while (!stringStack.isEmpty()) {
            String strVal = stringStack.pop();
            strBuffer = strVal + strBuffer;
        }
        return strBuffer;
    }

    Integer getNum(String strVal) {
        try {
            return Integer.parseInt(strVal);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
