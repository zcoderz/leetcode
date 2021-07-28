package strings;

/**
 * 3. Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * IMP-1: This is a very common and simple problem.
 */
public class LongestNonRepeatingStringChars {
    int longestP = 0;
    private int[] indexArr = new int[128];

    public static void main(String[] args) {
        LongestNonRepeatingStringChars lp = new LongestNonRepeatingStringChars();

        int iLen = lp.lengthOfLongestSubstring("pwwkew");
        System.out.println(iLen);
    }

    /**
     * this is a sliding window problem. you are looking for length between start index i and j its important to
     * remember to set index val starting with index 1 instead of 0 (i,e add 1 to j). this is so that we can identify
     * the array sizes correctly -> array size is (j-i + 1)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            //add 1 so that indexes are set to begin with 1. this simplifies finding size based on i and j
            //when i is the index where the substring started and j is the end index. add 1 to j -1 so as to
            //capture the starting index. i.e, in case of acda , starting a is at index 1, ending index is at 3, 3-1=2
            //so add 1 to 2 to get 3 where largest substring here is acd or cda
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

}
