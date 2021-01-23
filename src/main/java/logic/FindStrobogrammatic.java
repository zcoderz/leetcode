package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down). Find all
 * strobogrammatic numbers that are of length = n.
 */
public class FindStrobogrammatic {

    //single numbers that are symmetric
    char[] odd = {'1', '8', '0'};
    //pairs that are symmetric at 180 degree
    char[][] even = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
    List<String> retList = new ArrayList<>();

    public static void main(String[] args) {
        FindStrobogrammatic fstr = new FindStrobogrammatic();
        fstr.findStrobogrammatic(3);
        System.out.println(fstr.retList);
    }

    public List<String> findStrobogrammatic(int n) {
        char[] buffer = new char[n];
        processChars(buffer, 0, n - 1);
        return retList;
    }

    /**
     * recurse through the combinations to find all that would work. this is again a real simple solution but coming up
     * fresh with it is very tricky! someone else at leetcode came up with below in the discussion....
     *
     * @param buffer
     * @param left
     * @param right
     */
    void processChars(char[] buffer, int left, int right) {
        if (left > right) {
            retList.add(new String(buffer));
            return;
        }
        if (left == right) {
            for (Character ch : odd) {
                buffer[left] = ch;
                processChars(buffer, left + 1, right - 1);
            }
        } else {
            for (char[] ch : even) {
                //skip numbers that would start with 0
                if (ch[0] == '0' && left == 0) continue;
                buffer[left] = ch[0];
                buffer[right] = ch[1];
                processChars(buffer, left + 1, right - 1);
            }
        }
    }
}
