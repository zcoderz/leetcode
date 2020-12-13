package backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    public static void main(String [] args) {
        GenerateParenthesis gp = new GenerateParenthesis();
        int num = 3;
        List<String> strList = gp.generateParenthesis(num);
        for(String str: strList) {
            System.out.println(str);
        }
    }

    public List<String> generateParenthesis(int num) {

        List<String> strList = new ArrayList<>();
        recurse("", num, strList,0,0);

        return strList;
    }

    public void recurse(String str, int num, List<String> strList, int open, int close) {
        if (open ==num && close==num) {
            strList.add(str);
            return;
        }

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
