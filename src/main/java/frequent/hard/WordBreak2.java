package frequent.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 140. Word Break II
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 *
 * IMP-1 : Interesting question. Makes you think of how you can create a path that can reach the end of the string
 * using words. Start dfs traversal from end , this ensures you traverse less paths -i,e those that possibly get to
 * the end.
 */
public class WordBreak2 {

    private ArrayList[] dp;
    private HashSet<String> wordSet;
    private int len;
    private String sentence;
    private ArrayList<String> wordOut;

    public static void main(String[] args) {
        String strWord = "catsanddog";
        List<String> strings = Arrays.asList("cat", "cats", "and", "sand", "dog");
        WordBreak2 wb = new WordBreak2();
        strings = wb.wordBreak(strWord, strings);
        for (String word : strings) {
            System.out.println(word);
        }
    }

    /**
     * helper method to populate map of characters with characters in a string
     *
     * @param s
     * @param charSet
     */
    private void updateCharSet(String s, HashSet<Character> charSet) {
        for (int i = 0; i < s.length(); ++i)
            charSet.add(s.charAt(i));
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        this.sentence = s;
        this.len = s.length();
        wordSet = new HashSet<>(wordDict);
        dp = new ArrayList[len + 1];
        Arrays.setAll(dp, ArrayList::new);
        HashSet<Character> sentenceChars = new HashSet<>();
        updateCharSet(s, sentenceChars);

        HashSet<Character> dictChars = new HashSet<>();
        for (String word : wordDict) {
            updateCharSet(word, dictChars);
        }
        //this is a quick check to exit early if no matches are possible
        boolean contains = dictChars.containsAll(sentenceChars);
        if (!contains) return new ArrayList<>();

        //for each index in the string create a list that contains
        //starting indexes that can map to the given characters
        for (int end = 1; end <= len; end++) {
            ArrayList<Integer> aList = dp[end];
            for (int start = 0; start < end; start++) {
                if (wordSet.contains(sentence.substring(start, end))) {
                    aList.add(start);
                }
            }
        }

        wordOut = new ArrayList<>();
        //start from the end , while traversing back to start because you limit the traversal paths that way
        processDFS(len, "");
        return wordOut;
    }

    /**
     * process the words in dfs. prefer dfs because by nature of dfs you are guaranteed to follow the  paths that get
     * you to the end
     *
     * @param offset
     * @param str
     */
    void processDFS(int offset, String str) {
        if (offset == 0) {
            wordOut.add(str.strip());
        }
        ArrayList<Integer> arr = dp[offset];
        for (int iStart : arr) {
            String strWord = sentence.substring(iStart, offset);
            strWord = strWord + " " + str;
            processDFS(iStart, strWord);
        }
    }

}
