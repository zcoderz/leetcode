package google.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 288. Unique Word Abbreviation
 * <p>
 * The abbreviation of a word is a concatenation of its first letter, the number of characters between the first and
 * last letter, and its last letter. If a word has only two characters, then it is an abbreviation of itself.
 * <p>
 * For example:
 * <p>
 * dog --> d1g because there is one letter between the first letter 'd' and the last letter 'g'. internationalization
 * --> i18n because there are 18 letters between the first letter 'i' and the last letter 'n'. it --> it because any
 * word with only two characters is an abbreviation of itself.
 */
public class UniqueWordAbbreviations {

    Map<String, Set<String>> mapWords = new HashMap<>();

    public UniqueWordAbbreviations(String[] dictionary) {
        for (String word : dictionary) {
            String key = getKey(word);
            Set<String> list = mapWords.computeIfAbsent(key, (l) -> new HashSet<>());
            list.add(word);
        }
    }

    public static void main(String[] args) {
        String[] words = {"deer", "door", "cake", "card"};
        UniqueWordAbbreviations uwa = new UniqueWordAbbreviations(words);
        boolean isUnique = uwa.isUnique("dear");
        System.out.println(isUnique);
        isUnique = uwa.isUnique("cart");
        System.out.println(isUnique);
        isUnique = uwa.isUnique("cane");
        System.out.println(isUnique);
        isUnique = uwa.isUnique("make");
        System.out.println(isUnique);
    }

    public boolean isUnique(String word) {
        String key = getKey(word);
        Set<String> words = mapWords.get(key);
        if ((words == null)) {
            return true;
        } else if (words.size() == 1) {
            String theW = words.iterator().next();
            return theW.equals(word);
        } else {
            return false;
        }
    }

    private String getKey(String word) {
        StringBuilder key = new StringBuilder();
        if (word.length() <= 2) {
            key.append(word);
        } else {
            key.append(word.charAt(0)).append(word.length() - 2).append(word.charAt(word.length() - 1));
        }
        return key.toString();
    }
}
