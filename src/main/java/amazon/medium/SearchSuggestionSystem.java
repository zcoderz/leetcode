package amazon.medium;

import utils.TrieNode;

import java.util.ArrayList;
import java.util.List;

public class SearchSuggestionSystem {

    public static void main(String [] args) {
        String [] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";
        SearchSuggestionSystem search = new SearchSuggestionSystem();
        List<List<String>> llist = search.suggestedProducts(products, searchWord);
        for (List<String> l : llist) {
            System.out.println(l);
        }

    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        TrieNode node = new TrieNode();
        node.buildTrie(products);
        List<List<String>> list = new ArrayList<>();
        for (int i = 1; i <= searchWord.length(); i++) {
            String str = searchWord.substring(0,i);
            list.add(search(node, 0, str));
        }
        return list;
    }

    private List<String> search(TrieNode node, int index, String word) {
        if (index == word.length()) {
            List<String> list = new ArrayList<>(3);
            getThreeWordsFrom(node, list);
            return list;
        }
        Character ch = word.charAt(index);
        TrieNode childNode = node.getChildNode(word.charAt(index));
        if (childNode == null) {
            return new ArrayList<>();
        }
        return search(childNode, index + 1, word);
    }

    void getThreeWordsFrom(TrieNode node, List<String> list) {
        if (list.size() == 3) {
            return;
        }
        if (node.getWord() != null) {
            list.add(node.getWord());
        }
        for (TrieNode childNode : node.getAllChildNodes().values()) {
            getThreeWordsFrom(childNode, list);
            if (list.size() == 3) {
                return;
            }
        }
    }
}
