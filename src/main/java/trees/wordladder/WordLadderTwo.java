package trees.wordladder;

import utils.Pair;

import java.util.*;

public class WordLadderTwo {

    List<List<String>> out = new ArrayList<>();

    public static void main(String[] args) {

//        String strBeginWord = "kiss";
//        String strEndWord = "tusk";
//        List<String> strWords = new ArrayList<>();
//        //["miss","dusk","kiss","musk","tusk","diss","disk","sang","ties","muss"]
//        strWords.add("miss");
//        strWords.add("dusk");
//        strWords.add("kiss");
//        strWords.add("musk");
//        strWords.add("tusk");
//        strWords.add("diss");
//        strWords.add("disk");
//        strWords.add("sang");
//        strWords.add("ties");
//        strWords.add("muss");


        String strBeginWord = "hot";
        String strEndWord = "dog";
        List<String> strWords = new ArrayList<>();
        strWords.add("hot");
        strWords.add("dog");


        WordLadderTwo wl = new WordLadderTwo();
        List<List<String>> list = wl.findLadders(strBeginWord, strEndWord, strWords);

        for (List<String> l : list) {
            System.out.println(l);
        }
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
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> wordMappings = new HashMap<>();
        boolean matchEnd = false;
        for (String word : wordList) {
            if (word.equals(beginWord)) continue;
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
            return new ArrayList<>();
        }

        Queue<WordLink> leftQueue = new LinkedList<>();
        Queue<WordLink> rightQueue = new LinkedList<>();

        WordLink beg = new WordLink(beginWord, 1, null);
        WordLink end = new WordLink(endWord, 1, null);
        leftQueue.add(beg);
        leftQueue.add(null);
        rightQueue.add(end);
        rightQueue.add(null);

        Map<String, WordLink> leftVisited = new HashMap<>();
        Map<String, WordLink> rightVisited = new HashMap<>();
        leftVisited.put(beginWord, beg);
        rightVisited.put(endWord, end);

        int leftMin = Integer.MAX_VALUE;
        int rightMin = Integer.MAX_VALUE;

        Set<Pair<String, String>> mergedVals = new HashSet<>();
        int minLevel = Integer.MAX_VALUE;
        boolean found = false;
        boolean stopLeft = false;
        boolean stopRight = false;
        while ((!leftQueue.isEmpty() || !rightQueue.isEmpty())) {

            WordLink lVal = leftQueue.poll();
            if (found && lVal == null) {
                stopLeft = true;
            }
            if (lVal == null) {
                if (!found && leftQueue.size() > 0) {
                    leftQueue.add(null);
                }
            }
            if (!stopLeft && lVal != null) { //&& lVal.depth <= leftMin) {
                for (int i = 0; i < lVal.strWord.length(); i++) {
                    String modWord = lVal.strWord.substring(0, i) + "*" + lVal.strWord.substring(i + 1);
                    List<String> mappings = wordMappings.get(modWord);
                    if (mappings == null) continue;
                    for (String word : mappings) {
                        if (leftVisited.containsKey(word)) {
                            continue;
                        }
                        WordLink level = rightVisited.get(word);
                        if (level != null) {
                            int newLevel = lVal.depth + level.depth;
                            if (newLevel > minLevel) {
                                return out;
                            } else {
                                minLevel = newLevel;
                                Pair<String, String> p = new Pair<>(lVal.strWord, level.strWord);
                                if (!mergedVals.contains(p)) { // && level.depth <= rightMin) {
                                    //mergedVals.add(p);
                                    processLeftAndRight(lVal, level);
                                    leftMin = lVal.depth;
                                    rightMin = level.depth;
                                    found = true;
                                } else {
                                    int j = 1;
                                }
                            }
                        }

                        WordLink wl = new WordLink(word, lVal.depth + 1, lVal);
                        leftVisited.put(word, wl);
                        leftQueue.add(wl);
                    }
                }
            }

            WordLink rVal = rightQueue.poll();

            if (found && rVal == null) {
                stopRight = true;
            }
            if (lVal == null) {
                if (!found && rightQueue.size() > 0) {
                    rightQueue.add(null);
                }
            }


            if (!stopRight && rVal != null) { // && rVal.depth <= rightMin) {
                for (int i = 0; i < rVal.strWord.length(); i++) {
                    String modWord = rVal.strWord.substring(0, i) + "*" + rVal.strWord.substring(i + 1);
                    List<String> mappings = wordMappings.get(modWord);
                    if (mappings == null) continue;
                    for (String word : mappings) {
                        if (rightVisited.containsKey(word)) {
                            continue;
                        }
                        WordLink level = leftVisited.get(word);
                        if (level != null) {
                            int newLevel = rVal.depth + level.depth;
                            if (newLevel > minLevel) {
                                return out;
                            } else {
                                minLevel = newLevel;
                                Pair<String, String> p = new Pair<>(level.strWord, rVal.strWord);
                                if (!mergedVals.contains(p)) {// && level.depth <= leftMin) {
                                    rightMin = rVal.depth;
                                    leftMin = level.depth;
                                    //mergedVals.add(p);
                                    processLeftAndRight(level, rVal);
                                    found = true;
                                } else {
                                    int j = 1;
                                }
                            }
                        }
                        WordLink wl = new WordLink(word, rVal.depth + 1, rVal);
                        rightVisited.put(word, wl);
                        rightQueue.add(wl);
                    }
                }
            }
        }
        return out;
    }

    ;

    void processLeftAndRight(WordLink left, WordLink right) {
        List<String> list = new ArrayList<>();
        while (left != null) {
            list.add(left.strWord);
            left = left.previousWord;
        }
        Collections.reverse(list);
        while (right != null) {
            list.add(right.strWord);
            right = right.previousWord;
        }
        out.add(list);
    }

    static class WordLink {
        String strWord;
        Integer depth;
        WordLink previousWord;

        public WordLink(String strWord, Integer depth, WordLink previousWord) {
            this.strWord = strWord;
            this.depth = depth;
            this.previousWord = previousWord;
        }
    }
}
