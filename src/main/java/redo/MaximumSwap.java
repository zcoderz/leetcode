package redo;

import java.util.Arrays;

/**
 * 670. Maximum Swap
 * Medium
 *
 * 1820
 *
 * 102
 *
 * Add to List
 *
 * Share
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 *
 * Return the maximum valued number you can get.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 */
public class MaximumSwap {

    public int maximumSwap(int num) {
        int [] arr = new int[10];
        Arrays.fill(arr, -1);
        String strNum = String.valueOf(num);
        for (int i =0; i < 10; i++) {
            for (int j = strNum.length() - 1; j >= 0; j--) {
                if (strNum.charAt(j) == '0' + i) {
                    arr[i] = j;
                    break;
                }
            }
        }
        StringBuilder builder = new StringBuilder(strNum);
        for (int i =0; i < strNum.length(); i++) {
            char digit = strNum.charAt(i);
            int dVal = digit - '0';
            for (int highest =9; highest > dVal; highest--) {
                int loc = arr[highest];
                if (loc == -1 || loc <= i) {
                    continue;
                }
                builder.setCharAt(i, (char) ('0' +  highest));
                builder.setCharAt(loc, digit);
                return Integer.parseInt(builder.toString());
            }
        }
        return Integer.parseInt(builder.toString());
    }
}
