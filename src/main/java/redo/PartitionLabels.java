package redo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionLabels {

    public static void main(String [] args) {
        String res = "ababcbacadefegdehijhklij";
        PartitionLabels partition = new PartitionLabels();
        List<Integer> r = partition.partitionLabels(res);
        int j = 1;
    }

    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> charOccurrence = new HashMap<>();
        for (int i =0; i < s.length(); i++) {
            charOccurrence.put(s.charAt(i), i);
        }
        int rightMost =  charOccurrence.get(s.charAt(0));
        int i = 1;
        int left = 0;
        while (i < s.length()) {
            if (rightMost < i) {
                int val = rightMost - left + 1;
                res.add(val);
                left = i;
            }
            rightMost = Math.max(rightMost, charOccurrence.get(s.charAt(i)));
            i++;
        }
        res.add(s.length() - left);
        return res;
    }

}
