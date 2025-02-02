package arrays;

import java.util.*;

/**
 * 49. Group Anagrams
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * See another anagrams question in AnagramsInAString
 *
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        GroupAnagrams anagrams = new GroupAnagrams();
        String strA = "cat";
        String strB = "act";
        String strC = "dog";
        String strD = "tac";
        String strE = "mat";
        String[] arrStr = {strA, strB, strC, strD, strE};

        List<List<String>> theStr = anagrams.groupAnagrams(arrStr);

        for (List<String> strings : theStr) {
            System.out.println(strings);
        }
    }

    /**
     * Really easy approach here, sort the strings . Once sorted they will by definition be the same sorted value if an
     * anagram collect the list of sorted anagrams in a hash map. return that back.
     *
     * @param strings
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strings) {
        Map<String, List<String>> anagrams = new HashMap<>();

        for (String str : strings) {
            char[] sortedArray = str.toCharArray();
            Arrays.sort(sortedArray);
            String strSortedCopy = new String(sortedArray);
            if (!anagrams.containsKey(strSortedCopy)) {
                anagrams.put(strSortedCopy, new LinkedList<>());
            }
            List<String> lString = anagrams.get(strSortedCopy);
            lString.add(str);
        }
        List<List<String>> lAnagras = new ArrayList<>();
        for (Map.Entry<String, List<String>> mapEntry : anagrams.entrySet()) {
            lAnagras.add(mapEntry.getValue());
        }
        return lAnagras;
    }
}
