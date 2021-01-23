package trees.wordladder;

import java.util.*;

/**
 *
 */
public class WordLetterTwoBiDirBFS {


    public static void main(String[] args) {

        String strBeginWord = "kiss";
        String strEndWord = "tusk";
        List<String> strWords = new ArrayList<>();
        //["miss","dusk","kiss","musk","tusk","diss","disk","sang","ties","muss"]
        strWords.add("miss");
        strWords.add("dusk");
        strWords.add("kiss");
        strWords.add("musk");
        strWords.add("tusk");
        strWords.add("diss");
        strWords.add("disk");
        strWords.add("sang");
        strWords.add("ties");
        strWords.add("muss");


//        String strBeginWord = "a";
//        String strEndWord = "c";
//        List<String> strWords = new ArrayList<>();
//        strWords.add("a");
//        strWords.add("b");
//        strWords.add("c");

//        String strBeginWord = "hit";
//        String strEndWord = "cog";
//        List<String> strWords = new ArrayList<>();
//
//        strWords.add("hot");
//        strWords.add("dot");
//        strWords.add("dog");
//        strWords.add("lot");
//        strWords.add("log");
//        strWords.add("cog");

        WordLetterTwoBiDirBFS wl = new WordLetterTwoBiDirBFS();
        List<List<String>> list = wl.findLadders(strBeginWord, strEndWord, strWords);

        for (List<String> l : list) {
            System.out.println(l);
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //build the possible mappings.
        Map<String, List<String>> wordMappings = new HashMap<>();
        for (String word : wordList) {
            if (word.equals(beginWord)) continue;
            for (int i = 0; i < word.length(); i++) {
                String modWord = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> words = wordMappings.computeIfAbsent(modWord, (l) -> new ArrayList<>());
                words.add(word);
            }
        }

        Set<String> vocabulary = new HashSet<>(wordList);
        if (!vocabulary.contains(endWord)) {
            return new ArrayList<>();
        }

        //a word may have more than one way to map to the next word, keeping these mappings in a set
        //ensures we process each mapping from source to target word only once
        Set<String> queue1 = new HashSet<>();
        Set<String> queue2 = new HashSet<>();
        queue1.add(beginWord);
        queue2.add(endWord);

        //the value in the map is a set instead of a list to ensure that we dont reprocess
        //a mapping from source to target word more than once
        Map<String, Set<String>> outMappings = new HashMap<>();

        boolean isForward = true;
        boolean found = false;

        int pathLength = 1;

        while (!queue1.isEmpty() && !found) {
            pathLength++; //increase length of path forward
            //remove words to be processed next and in the queue from vocab of words
            //this is so that cycles are avoided
            vocabulary.removeAll(queue1);
            Set<String> newQueue = new HashSet<>();
            for (String word : queue1) {
                //find possible mappings for the given word
                for (int i = 0; i < word.length(); i++) {
                    String modWord = word.substring(0, i) + "*" + word.substring(i + 1);
                    List<String> mappings = wordMappings.get(modWord);
                    if (mappings == null) continue;
                    for (String mapWord : mappings) {
                        //only process if the given word is included in our list of words to process
                        if (vocabulary.contains(mapWord)) {
                            if (queue2.contains(mapWord)) {
                                found = true; //if other queue has the mapped word than target has been found
                            }
                            newQueue.add(mapWord); //add the mapping
                            if (isForward) { //update mapping from source to destination word
                                Set<String> list = outMappings.computeIfAbsent(word, (l) -> new HashSet<>());
                                list.add(mapWord);
                            } else { //reverse for the end to forward BFS so mappings are in correct order
                                Set<String> list = outMappings.computeIfAbsent(mapWord, (l) -> new HashSet<>());
                                list.add(word);
                            }
                        }
                    }
                }
            }
            queue1 = newQueue;
            if (queue1.size() > queue2.size()) {
                //swap the queues to use the queue with the smaller size. this ensures we converge towards the
                //solution faster
                isForward = !isForward; //update forward direction every time we swap queues
                Set<String> temp = queue1;
                queue1 = queue2;
                queue2 = temp;
            }
        }

        if (!found) {
            return new ArrayList<>();
        }

        List<List<String>> ans = new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        path.add(beginWord);
        //back track to find the possible paths.
        backtrack(ans, path, beginWord, outMappings, endWord, pathLength);

        return ans;

    }

    /**
     * back track to find solutions to the problem given the mappings
     *
     * @param ans
     * @param path
     * @param currWord
     * @param mappings  these are mapping between source and mapped words that are leveraged to find the paths to end
     *                  word.
     * @param endWord
     * @param maxLength this is the length determined by the above BFS solution. need to stop when we reach this length
     */
    void backtrack(List<List<String>> ans, LinkedList<String> path, String currWord, Map<String, Set<String>> mappings,
                   String endWord, int maxLength) {
        if (path.size() > maxLength) {
            return;
        }
        if (currWord.equals(endWord)) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (mappings.containsKey(currWord)) {
            for (String word : mappings.get(currWord)) {
                path.add(word);
                backtrack(ans, path, word, mappings, endWord, maxLength);
                path.removeLast();
            }
        }
    }
}
