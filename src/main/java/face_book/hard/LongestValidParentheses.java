package face_book.hard;

public class LongestValidParentheses {

    public static void main(String [] args) {
        LongestValidParentheses longest = new LongestValidParentheses();
        int sz = longest.longestValidParentheses("()(()");
        System.out.println(sz);
    }

    public int longestValidParentheses(String s) {
        int maxLen = 0;
        int balance=0;

        for (int i =0, start = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                balance++;
            } else if (s.charAt(i) == ')') {
                balance--;
            }
            if (balance==0) {
                maxLen = Math.max(maxLen, i-start+1);
            } else if (balance < 0) {
                balance = 0;
                start = i+1;
            }
        }
        balance=0;
        for (int i =s.length()-1, start = s.length()-1; i >=0 ; i--) {
            if(s.charAt(i) == '(') {
                balance--;
            } else if (s.charAt(i) == ')') {
                balance++;
            }
            if (balance==0) {
                maxLen = Math.max(maxLen, start-i+1);
            } else if (balance < 0) {
                balance = 0;
                start = i-1;
            }
        }

        return maxLen;
    }


    int removed = 0;
    public int longestValidParenthesesW(String s) {
        String oneWayBalanced = checkBalance(s, '(', ')');
        StringBuilder builder = new StringBuilder(oneWayBalanced);
        checkBalance(builder.reverse().toString(), ')', '(');
        return s.length()-removed;
    }

    String checkBalance(String str, char pos, char neg) {
        StringBuilder builder = new StringBuilder();
        int balance = 0;
        for (char c: str.toCharArray()) {
            if (c == pos) {
                balance++;
            } else if (c == neg) {
                balance--;
            }
            if (balance >= 0) {
                builder.append(c);
            } else {
                removed++;
                balance= 0;
            }
        }
        return builder.toString();
    }

}
