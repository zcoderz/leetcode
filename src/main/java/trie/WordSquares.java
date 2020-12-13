package trie;

import java.util.*;

public class WordSquares {

    public static void main(String [] args) {
        WordSquares ws = new WordSquares();
        String [] words = {"area","lead","wall","lady","ball"};
        List<List<String>> wordSqs =  ws.wordSquares(words);

        for (List<String> wordSq: wordSqs) {
            System.out.println(wordSq);
        }
    }

    private static class Trie {
        Map<Character, Trie> childs = new HashMap<>();
        private List<Integer> words = new ArrayList<>();

    }

    private Trie root = new Trie();
    private Integer len;
    String [] words;

    /**
     * construct the tree
     * @param word
     * @param index
     */
    void buildTrie(String word, int index) {
        Trie node = root;
        for(Character c : word.toCharArray()) {
            node = node.childs.computeIfAbsent(c , k -> new Trie());
            node.words.add(index);
        }
    }

    /**
     * method returns words (indexes) that start with the given prefix
     * iterate through the trie to find last trie node with last word index
     * that by design will contain the words matching that prefix
     * @param strPrefix
     * @return
     */
    List<Integer> wordsFromPrefix(String strPrefix) {
        Trie node = root;
        for (Character c: strPrefix.toCharArray()) {
            node = node.childs.get(c);
            if (node == null) {
                return new ArrayList<>();
            }
        }
        return  node.words;
    }

    /**
     * this a tricky one!
     * note that the word square will be symmetric through the diagonal
     * therefore we can traverse through the words in a backtracking style
     * where we keep building prefixes based on words until the desired length is reached
     *
     * if a match isnt found we backtrack
     *
     * @param wordList
     * @param index
     * @param result
     */
    void backTrack (LinkedList<String> wordList, int index, List<List<String>> result) {
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
        if (words.length==0) {
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
        for (String word: words) {
            LinkedList<String> list = new LinkedList<>();
            list.addLast(word);
            backTrack(list, 1, results);
        }
        return results;
    }

}
