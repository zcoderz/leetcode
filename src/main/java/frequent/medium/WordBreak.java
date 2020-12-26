package frequent.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class WordBreak {
    public static void main (String [] args) {
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
    Boolean [] memorization;


    public boolean wordBreak(String s, List<String> wordDict) {
        this.wordDict = new HashSet<>(wordDict);
        len = s.length();
        memorization = new Boolean[len+1];
        return processWordBreaks(s, 0);
    }

    /**
     * the solution is clever
     * 1. it tries to use memorization, this makes a huge difference in performance
     * 2. the idea to put dictionary words in a hashset and compare them with string sub strings
     * is clever
     * @param strOrig
     * @param iStart
     * @return
     */
    boolean processWordBreaks(String strOrig, int iStart) {

        if (iStart==len) return true;
        if (memorization[iStart] != null) {
            return memorization[iStart];
        }

        for (int i = iStart+1; i <= len; i++) {
            if (wordDict.contains(strOrig.substring(iStart, i))) {
                memorization[i]  = processWordBreaks(strOrig, i);
                if(memorization[i]) {
                    return memorization[i];
                }
            }
        }
        memorization[iStart] = false;
        return false;
    }

}
