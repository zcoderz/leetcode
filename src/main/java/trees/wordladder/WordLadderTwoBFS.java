package trees.wordladder;

import java.util.*;

public class WordLadderTwoBFS {

    public static void main (String [] args) {
        String strBeginWord = "hit";
        String strEndWord = "cog";
        List<String> strWords = new ArrayList<>();
        strWords.add("hot");
        strWords.add("dot");
        strWords.add("dog");
        strWords.add("lot");
        strWords.add("log");
        strWords.add("cog");
        WordLadderTwoBFS two = new WordLadderTwoBFS();
        List<List<String>> combs = two.findLadders(strBeginWord, strEndWord, strWords);
        int i = 1;
    }

    List<List<String>> combList = new ArrayList<>();
    Map<String, List<String>> wordKeyMap = new HashMap<>();
    int minLength = Integer.MAX_VALUE;
    static class WordDepth {
        String strWord;
        Integer depth;
        List<String> previousWords;
        public WordDepth(String strWord, Integer depth, List<String> previousWords) {
            this.strWord = strWord;
            this.depth = depth;
            this.previousWords = previousWords;
            previousWords.add(strWord);
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return combList;
        }
        for (String strWord: wordList) {
            for (int i = 0; i < strWord.length(); i++) {
                String strModWord = strWord.substring(0,i) + "*" + strWord.substring(i+1);
                List<String> strWordList = wordKeyMap.getOrDefault(strModWord, new ArrayList<>());
                strWordList.add(strWord);
                wordKeyMap.put(strModWord, strWordList);
            }
        }

        findPath(beginWord, endWord);
        return combList;
    }

    void findPath(String strWord, String strEndWord) {
        Queue<WordDepth> wordQueue = new LinkedList<>();
        WordDepth wordDepth = new WordDepth(strWord, 1, new LinkedList<> ());
        wordQueue.add(wordDepth);
        Set<String> visited = new HashSet<>();
        visited.add(strWord);
        while (!wordQueue.isEmpty()) {
            int size = wordQueue.size();
            Set<String> visitedLevel = new HashSet<>();
            while (size > 0) {
                size--;
                wordDepth = wordQueue.poll();
                if (wordDepth.depth > minLength) {
                    continue;
                }
                strWord = wordDepth.strWord;
                for (int i = 0; i < strWord.length(); i++) {
                    String strModWord = strWord.substring(0, i) + "*" + strWord.substring(i + 1);
                    List<String> strWords = wordKeyMap.get(strModWord);
                    if (null != strWords) {
                        for (String strLinkedWord : strWords) {
                            if (strLinkedWord.equals(strEndWord)) {
                                constructPath(wordDepth.previousWords, strEndWord);
                            } else if ((wordDepth.depth  < minLength) &&
                                    (!(visited.contains(strLinkedWord)))) {
                                //(!(wordDepth.previousWords.contains(strLinkedWord)))) {

                                visitedLevel.add(strLinkedWord);
                                WordDepth newWordDepth = new WordDepth(strLinkedWord, wordDepth.depth+1,
                                        new LinkedList<>(wordDepth.previousWords));
                                wordQueue.add(newWordDepth);
                            }
                        }
                    }
                }
                visited.addAll(visitedLevel);
            }
        }
    }



    void constructPath(List<String> pathSoFar, String strEndWord) {
        pathSoFar.add(strEndWord);
        int length = pathSoFar.size();
        if (length < minLength) {
            combList.clear();
            combList.add(new ArrayList<>(pathSoFar));
            minLength = length;
        } else if (length == minLength) {
            combList.add(new ArrayList<>(pathSoFar));
        }
    }

}
