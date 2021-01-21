package google.medium;

/**
 * 393. UTF-8 Validation
 *
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
 *
 * For 1-byte character, the first bit is a 0, followed by its unicode code.
 * For n-bytes character, the first n-bits are all one's, the n+1 bit is 0,
 * followed by n-1 bytes with most significant 2 bits being 10.
 * This is how the UTF-8 encoding would work:
 *
 *    Char. number range  |        UTF-8 octet sequence
 *       (hexadecimal)    |              (binary)
 *    --------------------+---------------------------------------------
 *    0000 0000-0000 007F | 0xxxxxxx
 *    0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 *    0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 *    0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * Given an array of integers representing the data, return whether it is a valid utf-8 encoding.
 *
 * Note:
 * The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data.
 * This means each integer represents only 1 byte of data.
 *
 * Example 1:
 *
 * data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.
 *
 * Return true.
 * It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
 * Example 2:
 *
 * data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.
 *
 * Return false.
 * The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
 * The next byte is a continuation byte which starts with 10 and that's correct.
 * But the second continuation byte does not start with 10, so it is invalid.
 *
 */
public class UTF8Validation {

    public static void main(String [] args) {
        int [] arr = {197, 130, 1};
        UTF8Validation validation = new UTF8Validation();
        boolean res = validation.validUtf8(arr);
        System.out.println(res);

        String maxAmpStr = Integer.toBinaryString(250);
        String maxAmpStr1 = Integer.toBinaryString(145);
        int [] arr2 = {250};
        res = validation.validUtf8(arr2);
        System.out.println(res);
    }

    public boolean validUtf8(int[] data) {
        int continuation = 0;
        int sevenMask = 1 << 7;
        int sixMask = 1 << 6;
        for (int val : data) {
            if (continuation > 0) {
                //if continuation verify that the bits start with 10 via using the below & checks
                //on the first and second bit
                if ((val & sevenMask) ==0) {
                    return false;
                }
                if ((val & sixMask) !=0) {
                    return false;
                }
                continuation--;
            } else {
                //if it is not a continuation then either this mist be a single bit or start a new continuation
                int sevenMod = sevenMask;
                while ((val & sevenMod) != 0) {
                    continuation++;
                    sevenMod = sevenMod >>1;
                }
                //continuation of 1 and greater than 4 arent valid as per the rules
                if ((continuation ==1) || (continuation > 4)) return false;
                //decrement for remaining continuations
                continuation--;
                //in case of a single byte the above logic would decrement to negative, so cap it at 0
                continuation = Integer.max(continuation, 0);
            }
        }
        return continuation == 0 ;
    }

}
