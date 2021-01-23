package google.medium;


/**
 * 809. Expressive Words
 * <p>
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii". In these
 * strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
 * <p>
 * For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of
 * the following extension operation: choose a group consisting of characters c, and add some number of characters c to
 * the group so that the size of the group is 3 or more.
 * <p>
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get
 * "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get
 * "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension
 * operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
 * <p>
 * Given a list of query words, return the number of words that are stretchy.
 * <p>
 * <p>
 * <p>
 * Example: Input: S = "heeellooo" words = ["hello", "hi", "helo"] Output: 1 Explanation: We can extend "e" and "o" in
 * the word "hello" to get "heeellooo". We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3
 * or more.
 */
public class ExpressiveWords {

    public static void main(String[] args) {
        String str = "aaabbbaaa";
        String[] words = {"aba"};
        ExpressiveWords ew = new ExpressiveWords();
        int ct = ew.expressiveWords(str, words);
        System.out.println(ct);
    }

    public int expressiveWords(String str, String[] words) {
        int strLen = str.length();
        int matches = 0;
        for (String word : words) {
            int strIndex = 0;
            int wordIndex = 0;
            int wordLen = word.length();
            char wChar;
            char sChar;
            int match = 1;
            while (wordIndex < wordLen && strIndex < strLen) {
                if (str.charAt(strIndex) != word.charAt(wordIndex)) {
                    match = 0;
                    break;
                }
                int strCt = 1;
                sChar = str.charAt(strIndex++);
                while (strIndex < strLen && str.charAt(strIndex) == sChar) {
                    strIndex++;
                    strCt++;
                }
                int wCt = 1;
                wChar = word.charAt(wordIndex++);
                while (wordIndex < wordLen && word.charAt(wordIndex) == wChar) {
                    wordIndex++;
                    wCt++;
                }
                if ((wCt != strCt) && ((strCt < wCt) || (strCt < 3))) {
                    match = 0;
                    break;
                }
            }
            if ((strIndex == strLen) && (wordIndex == wordLen)) {
                matches += match;
            }
        }
        return matches;
    }
}
