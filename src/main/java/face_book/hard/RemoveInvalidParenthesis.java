package face_book.hard;

import java.util.ArrayList;
import java.util.List;


/**
 * 301. Remove Invalid Parentheses
 * Given a string s that contains parentheses and letters,
 * remove the minimum number of invalid parentheses to make the input string valid.
 *
 * Return all the possible results. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 * Example 2:
 *
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 * Example 3:
 *
 * Input: s = ")("
 * Output: [""]
 *
 * IMP-1 : Very clever solution.
 * TODO --> Add to the website.
 * Approach taken from :
 * https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution
 */
public class RemoveInvalidParenthesis {

    public static void main(String [] args) {
        RemoveInvalidParenthesis rem = new RemoveInvalidParenthesis();
        String test = "()())()((";
        List<String> res = rem.removeInvalidParentheses(test);
        for (String val : res) {
            System.out.println(val);
        }
    }

    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        fixParenthesis(0, 0, '(', ')', res, s);
        return res;
    }

    void fixParenthesis(int lastIndexChecked, int lastIndexSubstituted, char positive, char negative,
                        List<String> res, String str) {
        int balance = 0;
        int currIndex = lastIndexChecked;
        //simple check to calculate balance between number of positive and negative chars
        while (currIndex < str.length() && balance >= 0) {
            Character ch = str.charAt(currIndex++);
            if (ch == positive) balance++;
            if (ch == negative) balance--;
        }

        if (balance >= 0) {
            //balance >=0 means that you have at least satisfied the case where you have less negative chars than the
            //number of positive chars. it could be the case that balance is 0.
            if (positive == '(') {
                //need to check balance to ensure we dont have extra '('.
                //can be done by reversing string and treating '(' as negative
                StringBuilder builder = new StringBuilder(str);
                String val = builder.reverse().toString();
                fixParenthesis(0, 0, ')', '(', res, val);
            } else {
                //when you get here balance has been checked both directions (open and close) so balance must be 0
                StringBuilder builder = new StringBuilder(str);
                String val = builder.reverse().toString();
                res.add(val);
            }
        } else {
            for (int i =lastIndexSubstituted; i < currIndex ; i++) {
                //run (i == lastIndexSubstituted || str.charAt(i-1) != negative) to avoid duplicates
                //if (str.charAt(i) == negative && (i == lastIndexSubstituted || str.charAt(i-1) != negative)) {
                //for example for  "()())()((" , "()(" -----   "))"  -----  "()(("
                //the case for these two "))" need to be treated the same, otherwise duplicates will be counted
                //if (str.charAt(i) == negative) {
                if (str.charAt(i) == negative && (i == lastIndexSubstituted || str.charAt(i-1) != negative)) {
                    String val = str.substring(0, i) + str.substring(i+1);
                    fixParenthesis(currIndex-1, i, positive, negative, res, val);
                }
            }
        }
    }
}
