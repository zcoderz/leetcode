package trie;

import utils.TrieNode;

import java.util.List;

/**
 * 648. Replace Words
 * In English, we have a concept called root, which can be followed by some other word to form another
 * longer word - let's call this word successor. For example, when the root "an" is followed by the
 * successor word "other", we can form a new word "another".
 *
 * Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces,
 * replace all the successors in the sentence with the root forming it.
 * If a successor can be replaced by more than one root, replace it with the root that has the shortest length.
 *
 * Return the sentence after the replacement.
 *
 *
 *
 * Example 1:
 *
 * Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 */
public class ReplaceWords {

    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode trieRoot = new TrieNode();
        trieRoot.buildTrie(dictionary.toArray());
        StringBuilder strToReturn = new StringBuilder();

        for (String strWord : sentence.split(" ")) {
            TrieNode node = trieRoot;
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
