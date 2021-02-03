package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses
 * Simple problem to generate combination of valid parenthesis
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 */
public class GenerateParenthesis {

    public static void main(String [] args) {
        GenerateParenthesis gp = new GenerateParenthesis();
        int num = 3;
        List<String> strList = gp.generateParenthesis(num);
        for(String str: strList) {
            System.out.println(str);
        }
    }

    /**
     * main call that calls into recursive function
     * @param num max number of open parenthesis
     * @return
     */
    public List<String> generateParenthesis(int num) {

        List<String> strList = new ArrayList<>();
        recurse("", num, strList,0,0);
        return strList;
    }

    /**
     * recuse to gather the combinations.
     * @param str
     * @param num
     * @param strList
     * @param open
     * @param close
     */
    public void recurse(String str, int num, List<String> strList, int open, int close) {
        if (open ==num && close==num) { //when both open and close reach the number , exit
            strList.add(str);
            return;
        }

        //first recurse to open and then close.
        if (open < num )
            recurse(str+'(' , num, strList, open+1, close);

        if(close < open)
            recurse(str+')' , num, strList, open, close+1);

    }

    public List<String> generateParenthesisComplex(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesisComplex(c))
                    for (String right: generateParenthesisComplex(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }

}
