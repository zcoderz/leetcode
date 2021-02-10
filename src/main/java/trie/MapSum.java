package trie;

import java.util.HashMap;
import java.util.Map;


/**
 * 677. Map Sum Pairs
 * Implement the MapSum class:
 *
 * MapSum() Initializes the MapSum object.
 * void insert(String key, int val) Inserts the key-val pair into the map.
 * If the key already existed, the original key-value pair will be overridden to the new one.
 * int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 *
 *
 * Example 1:
 *
 * Input
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * Output
 * [null, null, 3, null, 5]
 *
 * Explanation
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 *
 *
 */
public class MapSum {

    Map<String, Integer> wordSum = new HashMap<>();
    private Trie root = new Trie();

    /**
     * Initialize your data structure here.
     */
    public MapSum() {

    }

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
     * this code is beautifully concise. look the appropriate use of compute if absent and then updating score while
     * iterating the trie
     *
     * @param key
     * @param val
     */
    public void insert(String key, int val) {
        int delta = val;
        if (wordSum.containsKey(key)) {
            delta = val - wordSum.get(key);
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
     *
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

    /**
     * see each trie node is a simple map with score as a variable in it
     */
    private static class Trie {
        int score;
        private Map<Character, Trie> childs = new HashMap();
    }
}
