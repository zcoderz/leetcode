package google.medium;

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * 792. Number of Matching Subsequences
 *
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
 *
 * Example :
 * Input:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * Output: 3
 * Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
 *
 * IMP-1: This is a very clever approach to the problem. Should practice this ; can be reused.
 */
public class NumMatchingsubSequences {

    public static void main(String [] args) {
        String str = "abcde";
        String [] words = {"a", "bb", "acd", "ace"};
        NumMatchingsubSequences num = new NumMatchingsubSequences();
        int sz = num.numMatchingSubseq(str, words);
        System.out.println(sz);
    }

    /**
     * very clever implementation from leetcode discussion by StefanPochmann
     * idea is that for each of the characters you have a list of iterators/indexes to each of the words
     *
     * you move the character in string left to right and similarly advance matching iterators with the given character
     * right. as you reach end for a word you record the event
     *
     * @param str
     * @param words
     * @return
     */
    public int numMatchingSubseq(String str, String[] words) {
        int size =0;
        List<StringCharacterIterator> [] listArr = new List [26] ;
        for (int i =0 ; i < 26; i++) {
            listArr[i] = new ArrayList<>();
        }
        for (String word : words) {
            listArr[word.charAt(0)-'a'].add(new StringCharacterIterator(word));
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            List<StringCharacterIterator> list = listArr[c-'a'];
            listArr[c-'a'] = new ArrayList<>();
            for (StringCharacterIterator iter : list) {
                char next  = iter.next();
                if (next == iter.DONE) {
                    size++;
                } else {
                    listArr[next-'a'].add(iter);
                }
            }
        }
        return size;
    }

}
