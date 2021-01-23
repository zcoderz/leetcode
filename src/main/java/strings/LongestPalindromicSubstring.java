package strings;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        String strVal = longestPalindromicSubstring.longestPalindrome("mepspez");
        System.out.println(strVal);

    }

    public String longestPalindrome(String s) {
        int index = 0;
        int start = 0, end = 0;
        while (index < s.length()) {
            int len = palindrome(s, index, index);
            int len1 = palindrome(s, index, index + 1);
            len = Math.max(len, len1);
            if (len > (end - start)) {
                //(len-1)/2 , subtracting the extra 1 for start from length
                //for example for string : abba , if you are at index 1 , start is at 0 and end is at 3.
                //length comes back as 4. (4-1)/2 rounds down to 1 and this enables the correct
                // start index to be calculated
                //for aba, when you are at index 1, this works fine . start = 1 - (3-1)/2, end = 1 + (3/2)
                start = index - (len - 1) / 2;
                end = index + len / 2;
            }
            index++;
        }

        return s.substring(start, end + 1);
    }

    /**
     * the method returns the physical left of the string representing the palindrome
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private int palindrome(String s, int left, int right) {
        //right < s.length() && left >= 0 deliberately move one more off the starting and ending length
        //in return subtract so as to return the correct physical length
        while (right < s.length() && left >= 0 && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
