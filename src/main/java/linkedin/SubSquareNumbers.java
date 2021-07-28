package linkedin;


/**
 * 633. Sum of Square Numbers
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 *
 * Example 1:
 *
 * Input: c = 5
 * Output: true
 * Explanation: 1 * 1 + 2 * 2 = 5
 */
public class SubSquareNumbers {

    public static void main(String [] args) {
        SubSquareNumbers sub = new SubSquareNumbers();
        boolean check = sub.judgeSquareSum(4);
        System.out.println(check);
    }

    public boolean judgeSquareSum(int c) {
        for (int i =1; i*i < c; i++) {
            int v = i*i;
            int diff = c -v;
            double d = Math.sqrt(diff);
            if (d == (int) d) {
                return true;
            }

        }
        return false;
    }

}
