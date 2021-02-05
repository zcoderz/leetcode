package frequent.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. Partition Labels
 *
 * A string S of lowercase English letters is given. We want to partition this string into as many parts
 * as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 *
 *
 * Example 1:
 *
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 *
 * IMP-1: Common interview question
 */
public class PartitionLabels {

    /**
     *
     * problem asks to find max partitions on a string where partitions do not having overlapping characters
     *
     * this is a pretty cool problem. trick is to think abt last index for each letter
     *
     * @param str
     * @return
     */
    public List<Integer> partitionLabels(String str) {
        int [] index = new int[26];
        for (int i =0; i < str.length(); i++) {
            index[str.charAt(i)-'a'] = i; //creating a map that has last character index is key....
        }
        List<Integer> out = new ArrayList<>();
        int lastOccur = 0;
        int lastEnd = 0;
        for (int i=0; i < str.length(); i++) {
            lastOccur = Math.max(lastOccur, index[str.charAt(i)-'a']);
            if (i==lastOccur) {
                out.add(i+1-lastEnd);
                lastEnd = i + 1;
            }
        }
        return out;
    }

}
