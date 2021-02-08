package frequent.medium;


/**
 * 5. Longest Palindromic Substring
 * Given a string s, return the longest palindromic substring in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 *
 * IMP-1 : Very common question
 */
public class LongestPalindromeSubstring {

    public static void main(String[] args) {
        String s = "babad";
        String pal = longestPalindrome(s);
        System.out.println(pal);
    }

    /**
     * palindromes are based off a center. so try to figure out the palindrome length based off of its size from
     * start to end via starting off of a center index
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int sizeOdd = centerPalindrome(s, i, i);
            int sizeEven = centerPalindrome(s, i, i + 1);
            int maxLen = Math.max(sizeEven, sizeOdd);
            if (maxLen > end - start) {
                start = i - (maxLen - 1) / 2;
                end = start + maxLen;
            }
        }
        return s.substring(start, end);
    }

    static int centerPalindrome(String s, int left, int right) {

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

}
