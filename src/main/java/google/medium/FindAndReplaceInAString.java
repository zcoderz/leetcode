package google.medium;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * 833. Find And Replace in String To some string S, we will perform some replacement operations that replace groups of
 * letters with new ones (not necessarily the same size).
 *
 * Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is
 * that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not,
 * we do nothing.
 *
 * For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because
 * "cd" starts at position 2 in the original string S, we will replace it with "ffff".
 *
 * Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well as
 * another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the original
 * string S[2] = 'c', which doesn't match x[0] = 'e'.
 *
 * All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for
 * example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.
 *
 * Example 1:
 * Input: S = "abcd", indexes = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"] Output: "eeebffff" Explanation:
 * "a" starts at index 0 in S, so it's replaced by "eee". "cd" starts at index 2 in S, so it's replaced by "ffff".
 * IMP-3 nice practice question
 */
public class FindAndReplaceInAString {

    public static void main(String[] args) {
        String str = "vmokgggqzp";
        int[] indexes = {3, 5, 1};
        String[] sources = {"kg", "ggq", "mo"};
        String[] targets = {"s", "so", "bfr"};
        FindAndReplaceInAString findAndReplace = new FindAndReplaceInAString();
        String newStr = findAndReplace.findReplaceString(str, indexes, sources, targets);
        System.out.print(newStr);
    }

    /**
     * here is a simpler and more efficient approach from leet code. see another solution coded below as well
     *
     * @param str
     * @param indexes
     * @param sourcesP
     * @param targetsP
     * @return
     */
    public String findReplaceString(String str, int[] indexes, String[] sourcesP, String[] targetsP) {
        //for each index in the original string, record whether a match exists
        //if match exists record the index where transformation strings exist
        int len = str.length();
        int[] matches = new int[len];
        Arrays.fill(matches, -1); //-1 indicates no match
        //for each possible match candidate run through the below logic
        for (int i = 0; i < indexes.length; i++) {
            int index = indexes[i];
            String source = sourcesP[i];
            if (str.startsWith(source, index)) {
                matches[index] = i;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); ) {
            if (matches[i] > -1) {
                int matchIndex = matches[i];
                String source = sourcesP[matchIndex];
                String target = targetsP[matchIndex];
                builder.append(target);
                i += source.length();
            } else {
                builder.append(str.charAt(i));
                i++;
            }
        }
        return builder.toString();
    }


    /**
     * this is an interesting problem. below is a way that i came up with a solution but this  is more complicated than
     * the other solution also listed above that is from leetcode.
     *
     * @param str
     * @param indexes
     * @param sourcesP
     * @param targetsP
     * @return
     */
    public String findReplaceStringUQ(String str, int[] indexes, String[] sourcesP, String[] targetsP) {
        //the question doesn't clearly state but the index locations are out of order
        //so a replacement at a later index can come after a prior one
        //hence idea is to sort the replacement indexes and their corresponding strings so that they are
        //at the right indexes
        //use a treemap to sort by index location where value is the original index in the array
        TreeMap<Integer, Integer> arrOrders = new TreeMap<>();
        for (int i = 0; i < indexes.length; i++) {
            arrOrders.put(indexes[i], i);
        }
        //here the source, target and indexes are reordered based on order lowest to greatest index
        String[] sources = new String[sourcesP.length];
        String[] targets = new String[targetsP.length];
        int j = 0; //iterate through the indexes and order them
        for (Map.Entry<Integer, Integer> treeMapEntry : arrOrders.entrySet()) {
            sources[j] = sourcesP[treeMapEntry.getValue()]; //get source string at the original index
            targets[j] = targetsP[treeMapEntry.getValue()]; //get target index at the original index
            indexes[j++] = treeMapEntry.getKey(); //each index gets the key from tree (which is sorted)
        }
        //logically build the new string while transforming as necessary
        StringBuilder builder = new StringBuilder();
        int transformIndex = 0;
        for (int i = 0; i < str.length(); ) {
            if (transformIndex < indexes.length && i == indexes[transformIndex]) {
                String source = sources[transformIndex];
                if (str.startsWith(source, i)) {
                    builder.append(targets[transformIndex]);
                    i += source.length();
                } else {
                    builder.append(str.charAt(i++));
                }
                transformIndex++;
            } else {
                builder.append(str.charAt(i));
                i++;
            }
        }
        return builder.toString();
    }
}
