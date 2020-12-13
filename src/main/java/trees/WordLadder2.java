package trees;



import utils.Pair;

import java.util.*;

public class WordLadder2 {
    public static void main(String [] args) {

        String strBeg = "hit";
        String strEnd = "cog";
        List<String> words = new ArrayList();
        words.add("hot");
        words.add("dot");
        words.add("dog");
        words.add("lot");
        words.add("log");
        words.add("cog");
        WordLadder ladder = new WordLadder();

        System.out.println(ladder.ladderLength(strBeg, strEnd, words));

    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        Queue<Pair<String, Integer>> leftQueue = new LinkedList<>();
        leftQueue.add(new Pair<>(beginWord, 1));
        Queue<Pair<String, Integer>> rightQueue = new LinkedList<>();
        rightQueue.add(new Pair(endWord, 1));
        Map<String, Integer> leftVisited = new HashMap<>();
        Map<String, Integer> rightVisited = new HashMap<>();
        leftVisited.put(beginWord, 1);
        rightVisited.put(endWord, 1);
        return calcLadderLength(leftQueue , rightQueue, endWord, wordList, leftVisited, rightVisited) + 1;
    }

    int processQueue(Pair<String, Integer> theW, Map<String, Integer> processedA,
                     Map<String, Integer> processedB, Map<String, List<String>> wordMap,
                     Queue<Pair<String, Integer>> theQ) {

        for (int i = 0; i < theW.first.length(); i ++) {
            String strMod = theW.first.substring(0, i) + "*" + theW.first.substring(i + 1);
            for (String word : wordMap.getOrDefault(strMod, new ArrayList<>())) {
                if (processedB.containsKey(word)) {
                    return theW.second + processedB.get(word);
                }
                if (!processedA.containsKey(word)) {
                    theQ.add(new Pair<>(word, theW.second + 1));
                    processedA.put(word, theW.second + 1);
                }
            }
        }
        return -1;
    }

    public int calcLadderLength(Queue<Pair<String, Integer>> leftQ , Queue<Pair<String, Integer>> rightQ,
                                String endWord, List<String> wordList, Map<String, Integer> leftVisited,
                                Map<String, Integer> rightVisited) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Map<String, List<String>> wordMap = new HashMap<>();


        for (String word: wordList) {
            for (int i = 0; i < word.length(); i ++) {
                String strMod = word.substring(0, i) + "*" + word.substring(i+1);
                List<String> words = wordMap.getOrDefault(strMod, new LinkedList<>());
                words.add(word);
                wordMap.put(strMod, words);
            }
        }

        while (!leftQ.isEmpty() && !rightQ.isEmpty()) {
            Pair<String, Integer> theW = leftQ.poll();
            if (null != theW) {
                int val = processQueue(theW, leftVisited, rightVisited, wordMap, leftQ);
                if (val != -1) {
                    return val;
                }
            }

            theW = rightQ.poll();
            if (null != theW) {
                int val = processQueue(theW, rightVisited, leftVisited, wordMap, rightQ);
                if (val != -1) {
                    return val;
                }
            }

        }

        return 0;
    }
}
