package trees.wordladder;

import java.util.*;

/**
 * 127. Word Ladder
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words such that:
 *
 * The first word in the sequence is beginWord.
 * The last word in the sequence is endWord.
 * Only one letter is different between each adjacent pair of words in the sequence.
 * Every word in the sequence is in wordList.
 * Given two words, beginWord and endWord, and a dictionary wordList, \return the number of words in the shortest
 * transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog" with 5 words.
 *
 * IMP-1 : Common question. See faster approach coded in WordLadderEfficient. Also check WordLatterTwo
 *
 * //this is an in efficient but simple solution to the WordLadder problem
 * //its slow because it does the dfs based search and thereby looks at all possible combinations
 * //whereas we are concerned with the shortest possible match which can be found via BFS
 * //see the code in WordLadderEfficient class which does a bi directional search via BFS and is thus
 * //more efficient
 *
 *
 */
public class WordLadder {

    Map<String, List<String>> mapToWords = new HashMap<>();
    int minLength = Integer.MAX_VALUE;
    Set<String> visitedWords = new HashSet<>();

    public static void main(String[] args) {
        String strBeginWord = "hit";
        String strEndWord = "cog";
        List<String> strWords = new ArrayList<>();
        strWords.add("hot");
        strWords.add("dot");
        strWords.add("dog");
        strWords.add("lot");
        strWords.add("log");
        strWords.add("cog");
        WordLadder wl = new WordLadder();


        int len = wl.ladderLength(strBeginWord, strEndWord, strWords);
        System.out.println("Ladder length = " + len);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //for the list of possible words update the mapping from
        //a candidate string to the given word
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                //* indicates as a substitution that can be used to move forward from prior step
                String modWord = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> list = mapToWords.computeIfAbsent(modWord, (l) -> new ArrayList<>());
                list.add(word);
            }
        }
        processWords(beginWord, endWord, 1);
        //if no mapping found return 0
        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }
        return minLength;
    }

    /**
     * move from start towards the end by finding mappings at each step forward when target word is reached update
     * depth
     *
     * @param currWord
     * @param targetWord
     * @param depth
     */
    void processWords(String currWord, String targetWord, int depth) {
        for (int i = 0; i < currWord.length(); i++) {
            String modWord = currWord.substring(0, i) + "*" + currWord.substring(i + 1);
            List<String> list = mapToWords.get(modWord);
            if (list == null) continue;
            for (String word : list) {
                if (visitedWords.contains(word)) {
                    continue;
                } else {
                    visitedWords.add(word);
                }

                if (word.equals(targetWord)) {
                    visitedWords.remove(word);
                    if (depth < minLength) {
                        minLength = depth + 1;
                        return;
                    }
                } else {
                    processWords(word, targetWord, depth + 1);
                    visitedWords.remove(word);
                }
            }
        }

    }

}
