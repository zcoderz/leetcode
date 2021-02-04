package frequent.medium;

/**
 * 91. Decode Ways
 *
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be mapped back into letters using the reverse of the mapping above
 * (there may be multiple ways). For example, "111" can have each of its "1"s be mapped into 'A's to make "AAA",
 * or it could be mapped to "11" and "1" ('K' and 'A' respectively) to make "KA".
 * Note that "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * the O(n) solution below while simple is hard to think about
 * easier approach is via recursion but problem with that is it times out on leet code
 *
 * IMP-1 : Real important question to practice
 */
public class DecodeString {

    public static void main(String []args) {
        DecodeString dS = new DecodeString();
        int count = dS.numDecodings("0");
        assert count == 0;
        count = dS.numDecodings("10");
        assert count == 1;
        count = dS.numDecodings("101");
        assert count == 1;
        count = dS.numDecodings("28");
        assert count == 1;

        count = dS.numDecodings("25");
        assert count == 2;

    }


    /**
     * this is a real elegant solution , picked from leet code.
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s== null || s.isEmpty()) {
            return  0;
        }
        int twoBack = 1; //initialize two back to 1
        int oneBack = 0; //initialize one back to 0 or 1 depending on first index
        if (s.charAt(0) != '0') {
            oneBack = 1;
        }
        //loop to end index while looking at one and two before.
        //add one or two before to current index depending on whether they classify
        for (int i =2 ; i <= s.length(); i++ ) {
            int v=0;
            //add prior count if prior is not 0; you cant move from 0 one forward
            if (s.charAt(i-1) != '0') {
                v += oneBack;
            }
            //add two prior if they are between 10 and 26, less than 9 signifies two index before was 0
            //more than 26 means greater than Z and hence doesnt have a mapping
            int val = Integer.parseInt(s.substring(i-2, i));
            if (val > 9 && val < 27) {
                v += twoBack;
            }
            //move the counters forward
            twoBack = oneBack;
            oneBack = v;
        }
        return oneBack;
    }

}
