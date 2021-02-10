package trie;

import utils.TrieNode;

/**
 * 211. Design Add and Search Words Data Structure
 * <p>
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 * <p>
 * Implement the WordDictionary class:
 * <p>
 * WordDictionary() Initializes the object. void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
 * word may contain dots '.' where dots can be matched with any letter.
 *
 * IMP-1 : Common trie related question
 */
public class AddAndSearchWords {
    TrieNode root = new TrieNode();

    /**
     * Initialize your data structure here.
     */
    public AddAndSearchWords() {

    }

    public static void main(String[] args) {
        AddAndSearchWords addSearch = new AddAndSearchWords();
        addSearch.addWord("bad");
        addSearch.addWord("dad");
        addSearch.addWord("mad");
        boolean found = addSearch.search("pad");
        System.out.println(found);
        found = addSearch.search("bad");
        System.out.println(found);
        found = addSearch.search(".ad");
        System.out.println(found);
        found = addSearch.search("b..");
        System.out.println(found);
        found = addSearch.search("b..d");
        System.out.println(found);
    }

    public void addWord(String word) {
        root.buildTrie(word);
    }

    public boolean search(String word) {
        return root.searchWithDot(0, word);
    }

}
