package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 336. Palindrome Pairs
 *
 * Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list,
 * so that the concatenation of the two words words[i] + words[j] is a palindrome.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 *
 *
 * IMP-1: This is a hard but interesting problem. code approach is unique , so recommend understanding this in
 * detail
 *
 * The explanation on leet code is very nice for this problem : https://leetcode.com/problems/palindrome-pairs/solution/
 *
 * The idea is that you build tries of reversed string.
 *
 * Map current string to trie and then handle following cases :
 * 1. exact match is found - i,e bat / tab
 * 2. word has a palindrome left after match with trie - i,e bataa / tab
 * 3. trie has one or more palindromes left after match - i,e tab / bataa , batcc
 */
public class PalindromePairs {


    List<List<Integer>> results;
    private String[] words;
    private Trie root;

    public static void main(String[] args) {
        //String []words = {"bat","tab","cat"};
        //String [] words = {"abcd","dcba","lls","s","sssll"};
        String[] words = {"a", ""};
        PalindromePairs pals = new PalindromePairs();
        List<List<Integer>> result = pals.palindromePairs(words);

        for (List<Integer> res : result) {
            System.out.println(res);
        }

    }

    /**
     * checking whether the given string is a palindrome
     *
     * @param str
     * @return
     */
    public boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (j > i) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            j--;
            i++;
        }
        return true;
    }

    /**
     * construct trie
     */
    void buildTrie() {
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            //add items in reverse to trie so that while walking the tree you match in reverse
            //to identify palindrome
            word = new StringBuilder(word).reverse().toString();
            Trie node = root;
            for (int j = 0; j < word.length(); j++) {
                //identify that there is a remaining palindrome after this node
                if (isPalindrome(word.substring(j))) {
                    node.palindromes.add(i);
                }
                Character ch = word.charAt(j);
                node = node.child.computeIfAbsent(ch, k -> new Trie());
            }
            node.wordId = i;
        }
    }

    /**
     * process the palindrome while handling the cases described above
     */
    void processPalindrome() {
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Trie node = root;
            for (int j = 0; j < word.length(); j++) {
                if ((node.wordId != -1) && isPalindrome(word.substring(j))) {
                    //word has a palindrome left after match : case 2
                    List<Integer> item = new ArrayList<>();
                    item.add(i);
                    item.add(node.wordId);
                    results.add(item);
                }
                Character ch = word.charAt(j);
                node = node.child.get(ch);
                if (node == null) break;
            }
            if (node == null) {
                continue;
            }
            if (node.wordId != -1 && node.wordId != i) {
                //word had an exact palindrome match : case 1
                List<Integer> item = new ArrayList<>();
                item.add(i);
                item.add(node.wordId);
                results.add(item);
            }
            for (Integer k : node.palindromes) {
                //tree has one or more palindromes left : case 3
                List<Integer> item = new ArrayList<>();
                item.add(i);
                item.add(k);
                results.add(item);
            }
        }
    }

    /**
     * main method is modularized where it breaks the work up into several smaller methods
     *
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        this.words = words;
        root = new Trie();
        results = new ArrayList<>();
        buildTrie();
        processPalindrome();
        return results;
    }

    private static class Trie {
        Map<Character, Trie> child = new HashMap<>();
        // used to contain id of words that form palindrome after this trie node
        List<Integer> palindromes = new ArrayList<>();
        // id of the word
        int wordId = -1;
    }

}
