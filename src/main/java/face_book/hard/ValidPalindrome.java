package face_book.hard;


/**
 * 1216. Valid Palindrome III
 *
 * Given a string s and an integer k, return true if s is a k-palindrome.
 *
 * A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcdeca", k = 2
 * Output: true
 * Explanation: Remove 'b' and 'e' characters.
 * Example 2:
 *
 * Input: s = "abbababa", k = 1
 * Output: true
 *
 * Todo: Do other DP based solutions. Question has a unique DP style
 */
public class ValidPalindrome {

    Integer [][] dp;
    public boolean isValidPalindrome(String s, int k) {
        dp = new Integer[s.length()][s.length()];
        return checkValidDP(0, s.length()-1, s) <= k;
    }

    int checkValidDP(int left, int right, String s) {
        if (left== right) {
            return 0;
        }
        if (right-left ==1) {
            return s.charAt(left) == s.charAt(right) ? 0: 1;
        }

        if (dp[left][right] != null) return dp[left][right];

        if (s.charAt(left) == s.charAt(right)) {
            dp[left][right] = checkValidDP(left+1, right-1, s);
        } else {
            dp[left][right] = Math.min(checkValidDP(left+1, right, s), checkValidDP(left, right-1, s)) + 1;
        }
        return dp[left][right];
    }

    boolean checkValid(int left, int right, String s, int k, int currDif) {
        if (currDif > k) return false;
        if (left >= right) return true;

        if (s.charAt(left) == s.charAt(right)) {
            return checkValid(left+1, right-1, s, k, currDif);
        } else {
            boolean isValid = checkValid(left, right-1, s, k, currDif+1);
            if (isValid) return true;
            return checkValid(left+1, right, s, k, currDif+1);
        }
    }

}
