package logic;


/**
 * Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.
 *
 * A string s is said to be one distance apart from a string t if you can:
 *
 * Insert exactly one character into s to get t.
 * Delete exactly one character from s to get t.
 * Replace exactly one character of s with a different character to get t.
 */
public class IsOneEditDistance {

    public static void main(String [] args) {
        String strA = "a", strB = "";
        IsOneEditDistance isOne = new IsOneEditDistance();
        boolean check = isOne.isOneEditDistance(strA, strB);
        System.out.println(check);

        strA = "abcd";
        strB = "";
        check = isOne.isOneEditDistance(strA, strB);
        System.out.println(check);


        strA = "abcd";
        strB = "abed";
        check = isOne.isOneEditDistance(strA, strB);
        System.out.println(check);

        strA = "abe";
        strB = "abed";
        check = isOne.isOneEditDistance(strA, strB);
        System.out.println(check);

    }


    public boolean isOneEditDistance(String s, String t) {
        //store lengths in variables so you dont end up recalling the same method again and again
        int sLen = s.length();
        int tLen = t.length();
        final int absDiffStrLen = Math.abs(sLen - tLen);
        //if diff in length is more than one char than they must not be one edit distance apart
        if (absDiffStrLen > 1) {
            return false;
        }
        boolean same = true;
        int i =0, j=0;


        while (i < sLen && j < tLen) {
            if (s.charAt(i) == t.charAt(j)) {
                i++; j++; //same chars move forward
            } else {
                if (!same) {
                    return false; //if two chars are diff then return false
                }
                same = false;
                if (sLen == tLen) {
                    i++; j++; //if their length is same then move both forward
                } else if (sLen > tLen) {
                    i++; //move the longer string forward
                } else {
                    j++;
                }
            }
        }

        //if identical than return false
        if (same) {
            //if no diff found and you reached here make sure that the strings are exactly one distance apart
            return absDiffStrLen == 1;
        }
        return true;
    }
}
