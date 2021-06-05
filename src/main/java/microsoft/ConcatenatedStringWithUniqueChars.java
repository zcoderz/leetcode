package microsoft;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConcatenatedStringWithUniqueChars {


    public static  void main(String [] args) {
        String [] arr = {"cha","r","act","ers"};
        ConcatenatedStringWithUniqueChars uniqueChars = new ConcatenatedStringWithUniqueChars();
        int len = uniqueChars.maxLength(List.of(arr));
        System.out.println(len);
    }


    public int maxLength(List<String> arr) {
        int [] ans = new int[1];
        int [][] bitMask = new int[arr.size()][2];

        for (int i = 0; i < arr.size(); i++) {
            int mask = 0;
            for (char ch : arr.get(i).toCharArray()) {
                int val = ch - 'a';
                int valShift = 1 << val;
                if (((mask & valShift) >> val) == 1) {
                    mask =0; //if string has repeats then set length of current bit and it mask as 0
                    break;
                }
                mask = mask | valShift;
            }
            bitMask[i][0] = mask;
            bitMask[i][1] = mask ==0 ? 0: arr.get(i).length();
        }

        runDfs(bitMask, ans, arr.size(), 0, 0, 0);
        return ans[0];
    }

    void runDfs(int [][] bitMask, int [] ans, int totalStrings, int len, int currBit, int startIndex) {
        if (len > ans[0]) {
            ans[0] = len;
        }
        for (int i = startIndex; i < totalStrings; i++) {
            if ((bitMask[i][0] & currBit) != 0) continue;
            int newBit = currBit | bitMask[i][0];
            runDfs(bitMask, ans, totalStrings, len + bitMask[i][1], newBit, i+1);
        }
    }


    public int maxLengthSimpleButSlow(List<String> arr) {
        return  processLength("", arr, 0);
    }

    int processLength(String concat, List<String> arr, int start) {
        int maxLength = concat.length();
        if (start == arr.size()) return maxLength;
        for (int i=start; i < arr.size(); i++) {
            String str = concat + arr.get(i);
            if (isUnique(str)) {
                maxLength = Math.max(maxLength, processLength(str, arr, start+1));
            }
        }
        return maxLength;
    }

    boolean isUnique(String str) {
        Set<Character> chSet = new HashSet<>();
        for (char ch: str.toCharArray()) {
            if (!chSet.add(ch)) {
                return false;
            }
        }
        return true;
    }
}
