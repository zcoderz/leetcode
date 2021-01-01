package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public  class TrieNode {
    //keeping the child chars in a tree map so that the child nodes
    //can be retrieved in a sorted order. helps with some questions.
    private Map<Character, TrieNode> nodeMap = new TreeMap<>();
    private String word = null;

    boolean containsChar(final Character character) {
        return nodeMap.containsKey(character);
    }

    public void addNode(Character ch) {
        if (!nodeMap.containsKey(ch)) {
            nodeMap.put(ch, new TrieNode());
        }
    }

    public TrieNode getNode (Character ch) {
        return nodeMap.get(ch);
    }

    public String getWord() {
        return word;
    }

    public void setWord (String strWord) {
        this.word = strWord;
    }

    public Set<Character> getChildChars() {
        return nodeMap.keySet();
    }

    public void removeChar(Character c) {
        nodeMap.remove(c);
    }

    public TrieNode getChildNode(Character ch) {
        return nodeMap.get(ch);
    }

    public Map<Character, TrieNode> getAllChildNodes() {
        return nodeMap;
    }
    public void buildTrie(Object [] words) {
        for (Object word : words) {
            buildTrie((String) word);
        }
    }

    public void buildTrie(String [] words) {
        for (String word : words) {
            buildTrie(word);
        }
    }

    public void buildTrie(String word) {
        buildTrie(word, 0);
    }

    private void buildTrie(String word, int index) {
        if (word.length() <= index) {
            return;
        }

        Character ch = word.charAt(index);
        addNode(ch);
        TrieNode child = getNode(ch);

        if (word.length()-1 == index) {
            child.setWord(word);
        } else {
            child.buildTrie(word, index+1);
        }
    }

    public boolean search(String word) {
        return search(0, word, false);
    }

    public boolean searchPrefix(String word) {
        return search(0, word, true);
    }

    private boolean search(int index, String word, boolean prefixOnly) {
        if(prefixOnly && index == word.length()-1 ) {
            return nodeMap.containsKey(word.charAt(index));
        }
        if (index == word.length()) {
            return this.word != null;
        }
        TrieNode node = nodeMap.get(word.charAt(index));
        if (node == null) {
            return false;
        } else {
            return node.search(index+ 1, word, prefixOnly);
        }
    }

}