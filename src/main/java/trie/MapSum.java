package trie;

import utils.TrieNode;

import java.util.HashMap;
import java.util.Map;


/**
 * my original code was a little more complex than this
 * but then i took the logic from leetcode solution and copied it here
 * to emphasize and remind how simple this solution is
 *
 */
public class MapSum {

    public static void main(String args[]) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        mapSum.insert("ap", 2);
        mapSum.insert("apple", 5);
        mapSum.insert("apple", 1);
        int val = mapSum.sum("apple");
        System.out.println(val);
    }

    /**
     * see each trie node is a simple map with score as a variable in it
     */
    private static class Trie {
        int score;
        private Map<Character, Trie> childs = new HashMap();
    };

    private Trie root = new Trie();
    /** Initialize your data structure here. */
    public MapSum() {

    }

    Map<String, Integer> wordSum = new HashMap<>();

    /**
     * this code is beautifully concise. look the appropriate use of compute if absent
     * and then updating score while iterating the trie
     * @param key
     * @param val
     */
    public void insert(String key, int val) {
        int delta =val;
        if (wordSum.containsKey(key)) {
            delta = val- wordSum.get(key);
        }
        wordSum.put(key, val);
        Trie node = root;
        for (Character ch : key.toCharArray()) {
            node = node.childs.computeIfAbsent(ch, x -> new Trie());
            node.score += delta;
        }
    }

    /**
     * again very concise and beautifully written
     * @param prefix
     * @return
     */
    public int sum(String prefix) {
        Trie node = root;
        for (Character ch : prefix.toCharArray()) {
            node = node.childs.get(ch);
            if (node == null) {
                return 0;
            }
        }
        return node.score;
    }
}
