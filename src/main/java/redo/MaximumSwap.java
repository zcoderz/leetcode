package redo;

import java.util.Arrays;

public class MaximumSwap {

    public static void main(String [] args) {
        MaximumSwap max = new MaximumSwap();
        int v = max.maximumSwap(9973);
        System.out.println(v);
    }


    public int maximumSwap(int num) {
        int [] nums = new int[10];
        Arrays.fill(nums, -1);
        String strVal = String.valueOf(num);
        for (int i =0; i < strVal.length(); i++) {
            int ch = strVal.charAt(i) - '0';
            nums[ch] = i;
        }
        for (int i =0; i < strVal.length(); i++) {
            for (int j =9; j >= 0; j--) {
                if (nums[j] > i && ((j + '0') > strVal.charAt(i)) ) {
                    StringBuilder builder = new StringBuilder(strVal);
                    builder.setCharAt(nums[j], strVal.charAt(i));
                    char nw = (char) ('0' + j);
                    builder.setCharAt(i, nw);
                    return Integer.parseInt(builder.toString());
                }
            }
        }
        return num;
    }
}
