package linkedin;


/**
 * 647. Palindromic Substrings
 * Given a string s, return the number of palindromic substrings in it.
 *
 * A string is a palindrome when it reads the same backward as forward.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 *
 * Example 1:
 *
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 */
public class CountPalindromicSubStrings {

    public static void main(String [] args) {
        CountPalindromicSubStrings countPalindromic = new CountPalindromicSubStrings();
        int v = countPalindromic.countSubstrings("aaa");
        System.out.println(v);
    }

    public int countSubstrings(String s) {
        int ans=0;
        for (int i = 0; i < s.length(); i++) {
            ans += countPalindrome(i, i, s);
            ans += countPalindrome(i, i+1, s);
        }
        return ans;
    }


    int countPalindrome(int left, int right, String str) {
        int ans = 0;
        for (; left >=0 && right < str.length(); left--, right++) {
            if (str.charAt(left) != str.charAt(right)) {
                break;
            }
            ans++;
        }
        return ans;
    }
}
