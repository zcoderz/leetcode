package trees.wordladder;

import utils.Pair;

import java.util.*;

/**
 * A very interesting approach the word ladder problem. Work at the problem from both bottom up and top down via two
 * directional BFS
 */
public class WordLadderEfficient {

    public static void main(String[] args) {
        String strBeginWord = "hot";
        String strEndWord = "dog";
        List<String> strWords = new ArrayList<>();
        strWords.add("hot");
        strWords.add("cog");
        strWords.add("dog");
        strWords.add("tot");
        strWords.add("hog");
        strWords.add("hop");
        strWords.add("pot");
        strWords.add("dot");

//        String strBeginWord = "a";
//        String strEndWord = "c";
//        List<String> strWords = new ArrayList<>();
//        strWords.add("a");
//        strWords.add("b");
//        strWords.add("c");
        WordLadderEfficient wl = new WordLadderEfficient();


        int len = wl.ladderLength(strBeginWord, strEndWord, strWords);
        System.out.println("Ladder length = " + len);
    }

    /**
     * Do a BFS based search - i,e start from both ends. searching from both the bottom and top will help converge
     * quicker as you will cut down the unnecessary search paths since the two searches meet some where in the middle
     * thus reducing space and time complexity
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> wordMappings = new HashMap<>();
        boolean matchEnd = false;
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String modWord = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> words = wordMappings.computeIfAbsent(modWord, (l) -> new ArrayList<>());
                words.add(word);
            }
            if (!matchEnd && word.equals(endWord)) {
                matchEnd = true;
            }
        }
        if (!matchEnd) {
            return 0;
        }

        Queue<Pair<String, Integer>> leftQueue = new LinkedList<>();
        Queue<Pair<String, Integer>> rightQueue = new LinkedList<>();

        leftQueue.add(new Pair<>(beginWord, 1));
        rightQueue.add(new Pair<>(endWord, 1));

        Map<String, Integer> leftVisited = new HashMap<>();
        Map<String, Integer> rightVisited = new HashMap<>();
        leftVisited.put(beginWord, 1);
        rightVisited.put(endWord, 1);


        while (!leftQueue.isEmpty() || !rightQueue.isEmpty()) {
            Pair<String, Integer> lVal = leftQueue.poll();
            if (lVal != null) {
                for (int i = 0; i < lVal.first.length(); i++) {
                    String modWord = lVal.first.substring(0, i) + "*" + lVal.first.substring(i + 1);
                    List<String> mappings = wordMappings.get(modWord);
                    if (mappings == null) continue;
                    for (String word : mappings) {
                        if (leftVisited.containsKey(word)) {
                            continue;
                        }
                        Integer level = rightVisited.get(word);
                        if (level != null) {
                            return lVal.second + level;
                        }
                        leftVisited.put(word, lVal.second + 1);
                        leftQueue.add(new Pair<>(word, lVal.second + 1));
                    }
                }
            }

            Pair<String, Integer> rVal = rightQueue.poll();
            if (rVal != null) {
                for (int i = 0; i < rVal.first.length(); i++) {
                    String modWord = rVal.first.substring(0, i) + "*" + rVal.first.substring(i + 1);
                    List<String> mappings = wordMappings.get(modWord);
                    if (mappings == null) continue;
                    for (String word : mappings) {
                        if (rightVisited.containsKey(word)) {
                            continue;
                        }
                        Integer level = leftVisited.get(word);
                        if (level != null) {
                            return rVal.second + level;
                        }
                        rightVisited.put(word, rVal.second + 1);
                        rightQueue.add(new Pair<>(word, rVal.second + 1));
                    }
                }
            }
        }
        return 0;
    }


}
