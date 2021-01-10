package google;

import java.util.ArrayList;

/**
 * 388. Longest Absolute File Path
 * Here, we have dir as the only directory in the root. dir contains two subdirectories, subdir1 and subdir2.
 * subdir1 contains a file file1.ext and subdirectory subsubdir1. subdir2 contains a subdirectory subsubdir2,
 * which contains a file file2.ext.
 *
 * In text form, it looks like this (with ⟶ representing the tab character):
 *
 * dir
 * ⟶ subdir1
 * ⟶ ⟶ file1.ext
 * ⟶ ⟶ subsubdir1
 * ⟶ subdir2
 * ⟶ ⟶ subsubdir2
 * ⟶ ⟶ ⟶ file2.ext
 * If we were to write this representation in code,
 * it will look like this: "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext".
 * Note that the '\n' and '\t' are the new-line and tab characters.
 *
 * Every file and directory has a unique absolute path in the file system, which is the order of directories that must
 * be opened to reach the file/directory itself, all concatenated by '/'s. Using the above example,
 * the absolute path to file2.ext is "dir/subdir2/subsubdir2/file2.ext". Each directory name consists of letters,
 * digits, and/or spaces. Each file name is of the form name.extension, where name and extension consist of letters,
 * digits, and/or spaces.
 *
 * Given a string input representing the file system in the explained format, return the length of the longest
 * absolute path to a file in the abstracted file system. If there is no file in the system, return 0.
 */
public class LongestAbsoluteFilePath {

    public static void main(String [] args) {
        LongestAbsoluteFilePath longest = new LongestAbsoluteFilePath();
        String input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        int len = longest.lengthLongestPath(input);
        System.out.println(len);


        input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        len = longest.lengthLongestPath(input);
        System.out.println(len);


        input = "file1.txt\nfile2.txt\nlongfile.txt";
        len = longest.lengthLongestPath(input);
        System.out.println(len);


        input = "abcd";
        len = longest.lengthLongestPath(input);
        System.out.println(len);


        input = "dir\n        file.txt";
        len = longest.lengthLongestPath(input);
        System.out.println(len);

    }

    /**
     * split the string by new line chars. get level based on # of tab chars
     * track length in an array of paths so far.
     * pretty simple approach
     * @param input
     * @return
     */
    public int lengthLongestPath(String input) {
        int maxPathSize = 0;
        String [] paths = input.split("\n");
        ArrayList<Integer> levelLengths = new ArrayList<>(); levelLengths.ensureCapacity(20);
        for (String path: paths) {
            int level = path.lastIndexOf("\t") + 1; //add 1 to handle case where the method returns -1
            String name = path.substring(level);
            int priorLength = level == 0? 0 : levelLengths.get(level-1);
            if (name.contains(".")) {
                int currLen = priorLength + name.length();
                maxPathSize = Math.max(maxPathSize, currLen);
            } else {
                if (levelLengths.size() < level+1) {
                    levelLengths.add(0);
                }
                levelLengths.set(level, priorLength + name.length() + 1);
            }
        }
        return maxPathSize;
    }

}
