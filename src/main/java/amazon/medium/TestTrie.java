package amazon.medium;

import utils.TrieNode;

public class TestTrie {

    public static void main(String [] args) {
        TrieNode trie = new TrieNode();

        trie.buildTrie("apple");
        boolean res = trie.search("apple");   // returns true
        System.out.println(res);
        res =trie.search("app");     // returns false
        System.out.println(res);
        res = trie.searchPrefix("app"); // returns true
        System.out.println(res);
        trie.buildTrie("app");
        res = trie.search("app");     // returns true
        System.out.println(res);
    }
}
