package frequent.general.hard;

import java.util.*;

/**
 * Really interesting code that uses string offsets and dfs to figure out sentences that can be formed by the given
 * words
 */
public class WordBreak2 {

    public static void main(String [] args) {
        String strWord = "catsanddog";
        List<String> strings = Arrays.asList("cat","cats","and","sand","dog");
        WordBreak2 wb = new WordBreak2();
        strings = wb.wordBreak(strWord, strings);
        for (String word : strings) {
            System.out.println(word);
        }
    }

    private ArrayList[] dp ;
    private HashSet<String> wordSet;
    private int len ;
    private String sentence;
    private ArrayList<String> wordOut;

    /**
     * helper method to populate map of characters with characters in a string
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
        dp = new ArrayList [len+1];
        Arrays.setAll(dp,  ArrayList :: new);
        HashSet<Character> sentenceChars = new HashSet<>();
        updateCharSet(s, sentenceChars);

        HashSet<Character> dictChars = new HashSet<>();
        for (String word :  wordDict) {
            updateCharSet(word, dictChars);
        }
        //this is a quick check to exit early if no matches are possible
        boolean contains = dictChars.containsAll(sentenceChars);
        if (!contains ) return new ArrayList<>();

        //for each index in the string create a list that contains
        //starting indexes that can map to the given character
        for (int end =1; end <= len; end++) {
            ArrayList<Integer> aList = dp[end];
            for (int start =0; start < end; start++) {
                if (wordSet.contains(sentence.substring(start, end))) {
                    aList.add(start);
                }
            }
        }

        wordOut = new ArrayList<>();
        processDFS(len, "");
        return wordOut;
    }

    /**
     * process the words in dfs. prefer dfs because by nature of dfs you
     * are guaranteed to follow the  paths that get you to the end
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
