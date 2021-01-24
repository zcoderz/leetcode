package google.medium;


import java.util.*;

/**
 * 1048. Longest String Chain
 * Given a list of words, each word consists of English lowercase letters.
 *
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make
 * it equal to word2.  For example, "abc" is a predecessor of "abac".
 *
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of
 * word_2, word_2 is a predecessor of word_3, and so on.
 *
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 *
 * IMP-1 : UQ- think is a real nice question to practice!
 */
public class LongestStringChain {

    public static void main(String [] args) {
//        String [] words = {"a","b","ba","bca","bda","bdca"};
//        LongestStringChain longest = new LongestStringChain();
//        //int len = longest.longestStrChain(words);
//        System.out.println(len);
//
//        longest.maxChainLen = 0;
//        String [] words2 = {"xbc","pcxbcf","xb","cxbc","pcxbc"};
//        //len = longest.longestStrChain(words2);
//        System.out.println(len);

        LongestStringChain longest = new LongestStringChain();
        String [] words3 = {"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr",
                "grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"};
        int len = longest.longestStrChain(words3);
        System.out.println(len);
    }

    int maxChainLen = 0;
    Set<String> processedStrings = new HashSet<>();

    /**
     * Sort the words
     * put them in a map by length and list of words
     * process from smallest length to largest for each of the lengths
     * @param words
     * @return
     */
    public int longestStrChain(String[] words) {
        //sort the words by length and then alphabetically
        Arrays.sort(words, ((first, second) -> {
            int lenA = first.length();
            int lenB = second.length();
            if (lenA == lenB) {
                return first.compareTo(second);
            } else {
                return Integer.compare(lenA, lenB);
            }

        }));

        //assign the letters into a map denoted by length and list of words for that length
        Map<Integer, List<String>> mapList = new TreeMap<>();
        for (String word : words) {
            int len = word.length();
            List<String> lWords = mapList.computeIfAbsent(len, (l) -> new ArrayList<>());
            lWords.add(word);
        }

        //process each level independently.
        int mapSize = mapList.size();
        int numProcessed = 0;
        for (Map.Entry<Integer, List<String>> entry : mapList.entrySet()) {
            //stop processing when not possible to extend the chain length due to current depth of levels
            if (maxChainLen >= (mapSize - numProcessed)) {
                break;
            }
            processLength(mapList, entry.getKey(), 0, "");
            numProcessed++;
        }
        return maxChainLen;
    }

    /**
     * recursively processes the strings in increasing order of length
     * @param mapList
     * @param level
     * @param lenSoFar
     * @param priorStr
     */
    void processLength(Map<Integer, List<String>> mapList, int level, int lenSoFar, String priorStr) {
        //maxChainLen = Math.max(lenSoFar, maxChainLen);
        if (lenSoFar > maxChainLen) {
            maxChainLen = lenSoFar;
        }
        List<String> strsAtLevel = mapList.get(level);

        if (strsAtLevel != null) {
            for (String str : strsAtLevel) {
                //if a string has previously been processed, dont reprocess
                //reprocess is possible if visit a level fresh while the string had previously been processed
                if (processedStrings.contains(str)) {
                    continue;
                }
                if (priorStr.isEmpty()) {
                    processedStrings.add(str);
                    processLength(mapList, str.length()+1, lenSoFar+1, str);
                } else if (match(priorStr, str)) {
                    processedStrings.add(str);
                    processLength(mapList, level+1, lenSoFar+1, str);
                }
            }
        }
    }

    /**
     * returns true if the two strings are diff by only one char
     * @param oldString
     * @param newString
     * @return
     */
    boolean match(String oldString, String newString) {
        int numMisMatches =0;
        int newStringLen = newString.length();
        int oldStrLen = oldString.length();
        for (int i =0, j = 0; i < newStringLen && j < oldStrLen; ) {
            if (! (oldString.charAt(j) == newString.charAt(i))) {
                if (numMisMatches ==1) {
                    return false;
                } else {
                    numMisMatches++;
                }
                i++;
            } else {
                i++;
                j++;
            }
        }
        return true;
    }

}
