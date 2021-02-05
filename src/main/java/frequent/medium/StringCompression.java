package frequent.medium;

/**
 * 443. String Compression
 * Given an array of characters chars, compress it using the following algorithm:
 *
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 *
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately, but instead be stored in the input character array chars.
 * Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 *
 * After you are done modifying the input array, return the new length of the array.
 *
 *
 * Follow up:
 * Could you solve it using only O(1) extra space?
 *
 *
 *
 * Example 1:
 *
 * Input: chars = ["a","a","b","b","c","c","c"]
 * Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
 *
 */
public class StringCompression {

    public static void main(String [] args) {

        //char [] ch = {'a','a', 'b', 'b', 'b', 'c'};
        char [] ch = {'a', 'b', 'b', 'b','b', 'b','b', 'b','b', 'b','b', 'b','b', 'b','b', 'b','b', 'b','b', 'b', 'c'};
        StringCompression strComp = new StringCompression();
        int i = strComp.compressFast(ch);
        for (int j = 0; j < i; j++) {
            System.out.print(ch[j]);
        }
    }

    /**
     * the method compresses chars by reducing repeats
     * it uses O(1) space where it leverages the space in orig array for comrpession
     * @param chars
     * @return
     */
    public int compressFast(char[] chars) {
        int priorNo = 0;
        int priorIndex = 0;
        char priorChar = chars[0];

        for (char ch : chars) {
            if (ch != priorChar) {
                priorIndex = processCharCopy(chars, priorIndex, priorNo, priorChar);
                priorNo = 0;
                priorChar = ch;
            }
            priorNo++;
        }
        priorIndex = processCharCopy(chars, priorIndex, priorNo, priorChar);
        return priorIndex;
    }

    /**
     * copy chars into orig array
     * @param chars
     * @param priorIndex
     * @param priorNo
     * @param priorChar
     * @return
     */
    int processCharCopy(char[] chars, int priorIndex, int priorNo, char priorChar) {
        chars[priorIndex++] = priorChar;
        if (priorNo > 1) {
            String strInt = Integer.toString(priorNo);
            for(int j = 0; j < strInt.length();j++) {
                chars[priorIndex++] = strInt.charAt(j);
            }
        }
        return priorIndex;
    }
}
