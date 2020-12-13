package trees;

import java.util.*;

public class WordLadder {
    public class WordLevel {
        String strWord;
        Integer level;

        public WordLevel(String strWord, Integer level) {
            this.strWord= strWord;
            this.level = level;
        }

        public String getStrWord() {
            return strWord;
        }

        public Integer getLevel() {
            return level;
        }
    }

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
        Queue<WordLevel> leftQueue = new LinkedList<>();
        leftQueue.add(new WordLevel(beginWord, 0));
        Queue<WordLevel> rightQueue = new LinkedList<>();
        rightQueue.add(new WordLevel(endWord, 0));
        return calcLadderLength(leftQueue , rightQueue, endWord, wordList) + 1;
    }

    public int calcLadderLength(Queue<WordLevel> left , Queue<WordLevel> right, String endWord, List<String> wordList) {
        Map<String, List<String>> wordMap = new HashMap<>();
        Map<String, Integer> leftVisited = new HashMap<>();
        Map<String, Integer> rightVisited = new HashMap<>();

        for (String word: wordList) {
            for (int i = 0; i < word.length(); i ++) {
                String strMod = word.substring(0, i) + "*" + word.substring(i+1);
                List<String> words = wordMap.getOrDefault(strMod, new LinkedList<>());
                words.add(word);
                wordMap.put(strMod, words);
            }
        }

        while (!left.isEmpty() && !right.isEmpty()) {
            WordLevel leftWord = left.poll();
            if (leftWord != null) {
                String strLeftWord = leftWord.strWord;
                for (int i = 0; i < strLeftWord.length(); i ++) {
                    String strMod = strLeftWord.substring(0, i) + "*" + strLeftWord.substring(i + 1);
                    List<String> mappedWords =  wordMap.get(strMod);
                    if (null != mappedWords) {
                        for (String mWord : mappedWords) {
                            if (leftVisited.containsKey(mWord)) {
                                continue;
                            }
                            Integer rLevel = rightVisited.get(mWord);
                            if (null != rLevel) {
                                return leftWord.level + rLevel ;
                            }
                            left.add(new WordLevel(mWord, leftWord.level + 1));
                            leftVisited.put(mWord, leftWord.level + 1);
                        }
                    }
                }
            }


            WordLevel rightWord = right.poll();
            if (rightWord != null) {
                String strRightWord = rightWord.strWord;
                for (int i = 0; i < strRightWord.length(); i ++) {
                    String strMod = strRightWord.substring(0, i) + "*" + strRightWord.substring(i + 1);
                    List<String> mappedWords =  wordMap.get(strMod);
                    if (null != mappedWords) {
                        for (String mWord : mappedWords) {
                            if (rightVisited.containsKey(mWord)) {
                                continue;
                            }
                            Integer lLevel = leftVisited.get(mWord);
                            if (null != lLevel) {
                                return rightWord.level + lLevel ;
                            }
                            right.add(new WordLevel(mWord, leftWord.level + 1));
                            rightVisited.put(mWord, rightWord.level + 1);
                        }
                    }
                }
            }

        }


        return 0;

    }
}
