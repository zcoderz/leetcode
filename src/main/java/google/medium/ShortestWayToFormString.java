package google.medium;


import java.util.HashSet;
import java.util.Set;

/**
 * 1055. Shortest Way to Form String
 * <p>
 * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no
 * deletions).
 * <p>
 * Given two strings source and target, return the minimum number of subsequences of source such that their
 * concatenation equals target. If the task is impossible, return -1.
 */
public class ShortestWayToFormString {

    public static void main(String[] args) {
        ShortestWayToFormString shortest = new ShortestWayToFormString();
        String source = "abc", target = "abcbc";
        int out = shortest.shortestWay(source, target);
        System.out.println(out);
        source = "abc";
        target = "acdbc";
        out = shortest.shortestWay(source, target);
        System.out.println(out);
        source = "xyz";
        target = "xzyxz";
        out = shortest.shortestWay(source, target);
        System.out.println(out);
    }

    /**
     * simple approach : - first check whether transform is possible - then run source to target by moving source
     * indexes ahead. (could optimize further by recording sub arrays in target that required a single pass on source
     * thus if the pattern repeats, we just move source ahead by that many indexes. this would be advantageous in case
     * of large repetitive patterns but infact slow if patterns are distinct)
     *
     * @param source
     * @param target
     * @return
     */
    public int shortestWay(String source, String target) {
        //check first whether the transformation is possible
        Set<Character> origSourceChars = new HashSet<>();
        for (Character ch : source.toCharArray()) {
            origSourceChars.add(ch);
        }
        Set<Character> targetChars = new HashSet<>();
        for (Character ch : target.toCharArray()) {
            targetChars.add(ch);
        }

        for (Character ch : targetChars) {
            if (!origSourceChars.contains(ch)) {
                return -1;
            }
        }

        int passes = 0;
        int targetLen = target.length();
        int sourceLen = source.length();

        int currIndex = 0;
        while (currIndex < targetLen) {
            int sourceIndex = 0;
            passes++;
            while ((sourceIndex < sourceLen) && (currIndex < targetLen)) {
                if (source.charAt(sourceIndex++) == target.charAt(currIndex)) {
                    currIndex++;
                }
            }
        }
        return passes;
    }

}
