package google.medium;

/**
 * check LongestAbsoluteFilePath for a simple approach to the same problem. i initially wrote the below approach to
 * track directories and their contents in a more structured format however, this although correct is way more complex
 * to code in an interview
 */
public class LongestAbsoluteFilePathComplicated {

    int maxPathSize = 0;
    String input;
    int len;

    public static void main(String[] args) {
        LongestAbsoluteFilePathComplicated longest = new LongestAbsoluteFilePathComplicated();
        String input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        int len = longest.lengthLongestPath(input);
        System.out.println(len);
        longest.maxPathSize = 0;

        input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        len = longest.lengthLongestPath(input);
        System.out.println(len);
        longest.maxPathSize = 0;

        input = "file1.txt\nfile2.txt\nlongfile.txt";
        len = longest.lengthLongestPath(input);
        System.out.println(len);
        longest.maxPathSize = 0;

        input = "abcd";
        len = longest.lengthLongestPath(input);
        System.out.println(len);
        longest.maxPathSize = 0;

        input = "dir\n        file.txt";
        len = longest.lengthLongestPath(input);
        System.out.println(len);
        longest.maxPathSize = 0;
    }

    public int lengthLongestPath(String input) {
        this.input = input;
        this.len = input.length();
        processPaths(-1, "", -1);
        return maxPathSize;
    }

    /**
     * runs a dfs based approach to find path length of a file
     *
     * @param lastIndex
     * @param path
     * @param level
     * @return
     */
    int processPaths(int lastIndex, String path, int level) {
        int nextLevel = getNextLevel(lastIndex);
        if (nextLevel <= level) {
            return lastIndex;
        }
        if (nextLevel != 0) {
            //handle case where next level is one deeper than the prior, i,e current directory or file is a nested
            //sub content of the parent
            while (nextLevel > level) {
                String nextNodeName = getNextNodeName(lastIndex + nextLevel + 2);
                lastIndex += nextLevel + 1 + nextNodeName.length();
                if (!nextNodeName.contains(".")) {
                    lastIndex = processPaths(lastIndex, path + "/" + nextNodeName, nextLevel);
                } else {
                    maxPathSize = Math.max(maxPathSize, path.length() + 1 + nextNodeName.length());
                }
                nextLevel = getNextLevel(lastIndex);
            }
        } else {
            //handle case where the sub file or directory is at root level - i,e nextLevel == 0
            while (nextLevel == 0 && lastIndex < len - 1) {
                if (input.charAt(lastIndex + 1) == '\n') {
                    lastIndex++;
                }
                String nextNodeName = getNextNodeName(lastIndex + 1);
                if (nextNodeName.contains(".")) {
                    maxPathSize = Math.max(maxPathSize, path.length() + nextNodeName.length());
                    lastIndex += nextNodeName.length();
                } else {
                    lastIndex += nextNodeName.length();
                    lastIndex = processPaths(lastIndex, nextNodeName, 0);
                }
                nextLevel = getNextLevel(lastIndex);
            }
        }
        return lastIndex;
    }

    /**
     * returns the level of the path based on number of tabs in it
     *
     * @param lastIndex
     * @return
     */
    int getNextLevel(int lastIndex) {
        if (lastIndex >= len - 1) return 0;
        if (input.charAt(lastIndex + 1) != '\n') {
            return 0;
        }
        int start = lastIndex + 2;
        int level = 0;
        while (start < len && input.charAt(start) == '\t') {
            level++;
            start++;
        }
        return level;
    }

    /**
     * returns name of the next sub directory or file in path
     *
     * @param startIndex
     * @return
     */
    String getNextNodeName(int startIndex) {
        StringBuilder builder = new StringBuilder();
        int currIndex = startIndex;
        while (currIndex < len && (input.charAt(currIndex) != '\n' && input.charAt(currIndex) != '\t')) {
            builder.append(input.charAt(currIndex++));
        }
        return builder.toString();
    }

}
