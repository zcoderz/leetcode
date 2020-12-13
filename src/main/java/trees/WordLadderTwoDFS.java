package trees;

import java.util.*;

public class WordLadderTwoDFS {

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
        WordLadderTwoDFS two = new WordLadderTwoDFS();
        List<List<String>> combs = two.findLadders(strBeginWord, strEndWord, strWords);
        int i = 1;
    }

    List<List<String>> combList = new ArrayList<>();
    Map<String, List<String>> wordKeyMap = new HashMap<>();
    int minLength = Integer.MAX_VALUE;
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

        Stack<String> stackWords = new Stack<>(); stackWords.add(beginWord);
        Set<String>  strVisitedSet = new HashSet<>(); strVisitedSet.add(beginWord);
        findPath(beginWord, endWord, stackWords, strVisitedSet);
        return combList;
    }

    void findPath(String strWord, String strEndWord, Stack<String> pathSoFar, Set<String> visitedSet) {
        for (int i = 0; i < strWord.length(); i++) {
            String strModWord = strWord.substring(0, i) + "*" + strWord.substring(i + 1);
            List<String> strWords = wordKeyMap.get(strModWord);
            if (null != strWords) {
                for (String strLinkedWord : strWords) {
                    if (visitedSet.contains(strLinkedWord)) {
                        continue;
                    }
                    if (strLinkedWord.equals(strEndWord)) {
                        pathSoFar.add(strEndWord);
                        constructPath(pathSoFar, strEndWord);
                        pathSoFar.pop();
                        return;
                    } else {
                        pathSoFar.add(strLinkedWord);
                        visitedSet.add(strLinkedWord);
                        findPath(strLinkedWord, strEndWord, pathSoFar, visitedSet);
                        pathSoFar.pop();
                        visitedSet.remove(strLinkedWord);
                    }
                }
            }
        }
    }

    void constructPath(List<String> pathSoFar, String strEndWord) {
        int length = pathSoFar.size() + 1;
        if (length < minLength) {
            combList.clear();
            combList.add(new ArrayList<>(pathSoFar));
            minLength = length;
        } else if (length == minLength) {
            combList.add(new ArrayList<>(pathSoFar));
        }
    }

}
