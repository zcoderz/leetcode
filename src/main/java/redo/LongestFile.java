package redo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LongestFile {

    public static void main(String [] args) {
        String str =  "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        LongestFile longest = new LongestFile();
        int sz = longest.lengthLongestPath(str);
        System.out.println(sz);
    }

    int maxLen = 0;
    public int lengthLongestPath(String input) {
        String [] res = input.split("\n");
        LinkedList<Integer> levels = new LinkedList<>();

        for (String str: res) {
            int currLevel = 0;
            int index = 0;
            while (str.charAt(index) == '\t') {
                currLevel++;
                index++;
            }
            while (levels.size() > currLevel) {
                levels.removeLast();
            }

            String name = str.substring(index);
            levels.add(name.length());
            if (name.contains(".")) {
               maxLen = Math.max(maxLen, computeLength(levels));
            }
        }
        return maxLen;
    }

    int computeLength(LinkedList<Integer> levels) {
        int totalLen = 0;
        for (int len : levels) {
            totalLen += len + 1;
        }
        return totalLen-1;
    }
}
