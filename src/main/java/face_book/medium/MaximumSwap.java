package face_book.medium;

import java.util.Arrays;

public class MaximumSwap {


    public static void main(String [] args) {
        MaximumSwap ms = new MaximumSwap();
        int res = ms.maximumSwap(2736);
        System.out.println(res);
    }

    public int maximumSwap(int num) {
        char [] strVal = Integer.toString(num).toCharArray();
        int [] arr = new int[10];
        Arrays.fill(arr, -1);
        for (int i=0; i < strVal.length; i++) {
            int j = strVal[i] - '0';
            arr[j]=i;
        }

        for (int i =0; i < strVal.length; i++) {
            int n = strVal[i]- '0';
            for (int j=9; j> n;j--) {
                if (arr[j] > i) {
                    char tmp = strVal[i];
                    strVal[i]= strVal[arr[j]];
                    strVal[arr[j]] = tmp;
                    return Integer.parseInt(String.copyValueOf(strVal));
                }
            }
        }
        return num;
    }
}
