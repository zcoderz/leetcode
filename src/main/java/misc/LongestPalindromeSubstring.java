package misc;

public class LongestPalindromeSubstring {

    public static void main(String [] args) {
        String s = "babad";
        String pal = longestPalindrome(s);
        return;
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() ==0) {
            return "";
        }
        int start = 0;
        int end = 0;
        for (int i =0; i < s.length(); i++) {
            int sizeOdd = centerPalindrome(s, i, i);
            int sizeEven = centerPalindrome(s, i, i+1);
            int maxLen = Math.max(sizeEven, sizeOdd);
            if (maxLen > end-start) {
                start = i - (maxLen-1)/2;
                end = start + maxLen;
            }

        }
        return s.substring(start, end);
    }
    //aaaa
    //0123
    //  aba
    //-10123
    static int  centerPalindrome(String s, int left, int right) {

        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

}
