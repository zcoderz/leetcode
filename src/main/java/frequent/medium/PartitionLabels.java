package frequent.medium;

import java.util.ArrayList;
import java.util.List;

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
