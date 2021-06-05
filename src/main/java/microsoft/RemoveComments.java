package microsoft;

import java.util.ArrayList;
import java.util.List;

public class RemoveComments {

    public static void main(String [] args) {
        String [] arr = {"abc /*ignore*/ def /*ignore*/ hmmm"};
        RemoveComments removeComments = new RemoveComments();
        List<String> out =  removeComments.removeCommentsFromLeetCode(arr);
        for (String str : out) {
            System.out.println(str);
        }
        // /*


//        String [] arr2 = {"a/*comment", "line", "more_comment*/b"};
//        List<String> out2 =  removeComments.removeComments(arr2);
//        for (String str : out2) {
//            System.out.println(str);
//        }
    }

    public List<String> removeCommentsFromLeetCode(String[] source) {
        List<String> result = new ArrayList<>();
        StringBuilder builder = null;
        boolean commentMode = false;

        for (int i = 0; i < source.length; i++) {
            String line = source[i];

            if (commentMode) {
                int index = line.indexOf("*/");

                if (index == -1) {
                    continue;
                }

                commentMode = false;
                line = line.substring(index + 2);
            } else {
                builder = new StringBuilder();
            }

            int lineComment = line.indexOf("//");
            int blockComment = line.indexOf("/*");

            if (lineComment == -1 && blockComment == -1) {
                builder.append(line);
            } else if (blockComment == -1 || blockComment > lineComment && lineComment >= 0) {
                builder.append(line, 0, lineComment);
            } else if (lineComment == -1 || lineComment > blockComment) {
                builder.append(line, 0, blockComment);
                source[i] = line.substring(blockComment + 2);
                commentMode = true;
                i--;
                continue;
            }

            if (builder.length() > 0) {
                result.add(builder.toString());
            }
        }

        return result;
    }

    public List<String> removeCommentsMySolution(String[] source) {
        List<String> output = new ArrayList<>();
        boolean blockComment = false;
        for (String str: source) {
            int iStart = 0;
            StringBuilder builder = new StringBuilder();
            boolean blockCommentAtStart = blockComment;
            while (iStart < str.length()) {
                if (blockComment) {
                    int index = str.indexOf("*/", iStart);
                    if (index != -1) {
                        iStart = index + 2;
                        blockComment = false;
                    } else {
                        iStart = str.length();
                    }
                } else {
                    int indexBlock = str.indexOf("/*", iStart);
                    int indexDouble = str.indexOf("//", iStart);
                    if (indexBlock != -1 && (indexDouble ==-1 || indexBlock < indexDouble)) {
                        builder.append(str, iStart, indexBlock);
                        iStart = indexBlock + 2;
                        blockComment = true;
                    } else if (indexDouble !=-1 ) {
                        builder.append(str, iStart, indexDouble);
                        iStart = str.length();
                    } else {
                        builder.append(str, iStart, str.length());
                        iStart = str.length();
                    }
                }
            }
            if (!builder.toString().isEmpty()) {
                if (blockCommentAtStart) {
                    int index = output.size()-1;
                    String strV = output.get(index);
                    strV += builder.toString();
                    output.set(index, strV);
                } else {
                    output.add(builder.toString());
                }
            }
        }
        return output;
    }
}
