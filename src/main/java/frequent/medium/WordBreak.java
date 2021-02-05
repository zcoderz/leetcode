package frequent.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. Word Break
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * IMP-1: Common question + questions such as this are frequent
 */
public class WordBreak {
    public static void main(String[] args) {
//        String strWord = "catskicatcats";
//        List<String> theWords = Arrays.asList("cats", "cat", "dog", "ski");
        String strWord = "leetcode";
        List<String> theWords = Arrays.asList("leet", "code");
        WordBreak wb = new WordBreak();
        boolean contains = wb.wordBreak(strWord, theWords);
        System.out.println(contains);
    }


    private HashSet<String> wordDict;
    int len;
    Boolean[] memorization;


    public boolean wordBreak(String s, List<String> wordDict) {
        this.wordDict = new HashSet<>(wordDict);
        len = s.length();
        memorization = new Boolean[len + 1];
        return processWordBreaks(s, 0);
    }

    /**
     * the solution is clever 1. it tries to use memorization, this makes a huge difference in performance 2. the idea
     * to put dictionary words in a hashset and compare them with string sub strings is clever
     *
     * @param strOrig
     * @param iStart
     * @return
     */
    boolean processWordBreaks(String strOrig, int iStart) {
        if (iStart == len) {
            //you reached the end of the string and hence processing is good!
            return true;
        }

        if (memorization[iStart] != null) {
            //if you had searched up to the index, return the previously calculated value
            return memorization[iStart];
        }

        for (int i = iStart + 1; i <= len; i++) {
            if (wordDict.contains(strOrig.substring(iStart, i))) {
                memorization[i] = processWordBreaks(strOrig, i);
                if (memorization[i]) {
                    return memorization[i];
                }
            }
        }
        memorization[iStart] = false;
        return false;
    }

    /**
     * A DP based solution from leetcode
     * description on leet code is clear.
     *
     * idea is that if you can find all sub segments of the string in word set then the whole must exist
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreakDP(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
