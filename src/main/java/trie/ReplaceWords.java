package trie;

import utils.TrieNode;

import java.util.List;

public class ReplaceWords {

    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode trieRoot = new TrieNode();
        trieRoot.buildTrie(dictionary.toArray());
        StringBuilder strToReturn = new StringBuilder();

        for (String strWord : sentence.split(" ")) {
            TrieNode node = trieRoot;
            boolean isWord = false;
            for (int i = 0; i < strWord.length(); i++) {
                Character ch = strWord.charAt(i);
                node = node.getNode(ch);
                if (node != null && node.getWord() != null) {
                    break;
                } else if (node == null) {
                    break;
                }
            }
            if ((node != null) && (node.getWord() != null)) {
                strToReturn.append(node.getWord()).append(" ");
            } else {
                strToReturn.append(strWord).append(" ");
            }
        }
        if (strToReturn.length() > 0) {
            return strToReturn.substring(0, strToReturn.length() - 1);
        } else {
            return "";
        }
    }
}
