package redo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    public static void main(String [] args) {
        String str = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        WordBreak wb = new WordBreak();
        boolean res = wb.wordBreak("leetcode" , wordDict);
        System.out.println(res);
    }

    int [] memorization;
    Set<String> dict;
    public boolean wordBreak(String str, List<String> wordDict) {
        memorization = new int[str.length()];
        Arrays.fill(memorization, -1);
        dict = new HashSet<>(wordDict);
        return processCheck(str, 0, str.length());
    }

    boolean processCheck(String str, int left, int right) {
        if (left == right || memorization[left] == 1) {
            return true;
        } else if (memorization[left] == 0) {
            return false;
        }

        for (int i = left; i <= right; i++) {
            if (dict.contains(str.substring(left, i))) {
                boolean ret = processCheck(str, i, right);
                if (ret) {
                    memorization[left] = 1;
                    return true;
                }
            }
        }
        memorization[left] = 0;
        return false;
    }
}
