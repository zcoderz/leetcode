package trie;

import java.util.*;

/**
 * 425. Word Squares
 * Given a set of words (without duplicates), find all word squares you can build from them.
 *
 * A sequence of words forms a valid word square if the kth row and column read the exact same string,
 * where 0 ≤ k < max(numRows, numColumns).
 *
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads
 * the same both horizontally and vertically.
 *
 * b a l l
 * a r e a
 * l e a d
 * l a d y
 * Note:
 * There are at least 1 and at most 1000 words.
 * All words will have the exact same length.
 * Word length is at least 1 and at most 5.
 * Each word contains only lowercase English alphabet a-z.
 * Example 1:
 *
 * Input:
 * ["area","lead","wall","lady","ball"]
 *
 * Output:
 * [
 *   [ "wall",
 *     "area",
 *     "lead",
 *     "lady"
 *   ],
 *   [ "ball",
 *     "area",
 *     "lead",
 *     "lady"
 *   ]
 * ]
 *
 * Explanation:
 * The output consists of two word squares. The order of output does not matter
 * (just the order of words in each word square matters).
 */
public class WordSquares {

    String[] words;
    private Trie root = new Trie();
    private Integer len;

    public static void main(String[] args) {
        WordSquares ws = new WordSquares();
        String[] words = {"area", "lead", "wall", "lady", "ball"};
        List<List<String>> wordSqs = ws.wordSquares(words);

        for (List<String> wordSq : wordSqs) {
            System.out.println(wordSq);
        }
    }

    /**
     * construct the tree
     *
     * @param word
     * @param index
     */
    void buildTrie(String word, int index) {
        Trie node = root;
        for (Character c : word.toCharArray()) {
            node = node.childs.computeIfAbsent(c, k -> new Trie());
            node.words.add(index);
        }
    }

    /**
     * method returns words (indexes) that start with the given prefix iterate through the trie to find last trie node
     * with last word index that by design will contain the words matching that prefix
     *
     * @param strPrefix
     * @return
     */
    List<Integer> wordsFromPrefix(String strPrefix) {
        Trie node = root;
        for (Character c : strPrefix.toCharArray()) {
            node = node.childs.get(c);
            if (node == null) {
                return new ArrayList<>();
            }
        }
        return node.words;
    }

    /**
     * This is a very clever implementation.
     * For a word square : the letters in the square must be symmetric around the center.
     * the logic is that you build a word prefix based on letters in a list of words at a given index denoted by index
     * in the array. use a trie to get list of words that match the prefix.
     * for each word that matches , repeat the above via including that word in the list to be searched (backtrack)
     *
     * i,e words (ball, area, lead, lady)
     * start with : ball at index 1
     * 1st call get area as it starts with prefix a (constructed via ball's first index)
     * 2nd call get lead as it starts with prefix le (constructed via ball and area second index)
     * 3rd call get lady as it starts with prefix lad (constructed via ball, area and lead's third index)
     * now you have the 4 words processed which equals the size of words started with and hence a square is formed
     *
     * Very neat!
     * @param wordList
     * @param index
     * @param result
     */
    void backTrack(LinkedList<String> wordList, int index, List<List<String>> result) {
        if (index == len) {
            result.add((List<String>) wordList.clone()); //match found add to result
            return;
        }

        //build prefix based on current list of words and given index
        StringBuilder prefix = new StringBuilder();
        for (String word : wordList) {
            prefix.append(word.charAt(index));
        }

        //given prefix return matching words
        List<Integer> words = wordsFromPrefix(prefix.toString());

        //for given words that matched the prefix continue iterating forward
        for (Integer iW : words) {
            wordList.addLast(this.words[iW]); //add to end so in backtrack you can remove the last added
            backTrack(wordList, index + 1, result);
            wordList.removeLast(); //remove last word when back tracking
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        if (words.length == 0) {
            return new ArrayList<>();
        }

        this.len = words[0].length();
        this.words = words;
        //1. build trie
        for (int i = 0; i < words.length; i++) {
            buildTrie(words[i], i);
        }
        List<List<String>> results = new ArrayList<>();
        //back track the list of words to process
        for (String word : words) {
            LinkedList<String> list = new LinkedList<>();
            list.addLast(word);
            backTrack(list, 1, results);
        }
        return results;
    }

    private static class Trie {
        Map<Character, Trie> childs = new HashMap<>();
        private List<Integer> words = new ArrayList<>();

    }

}
