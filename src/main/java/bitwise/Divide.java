package bitwise;

/**
 * 29. Divide Two Integers
 * <p>
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod
 * operator.
 * <p>
 * Return the quotient after dividing dividend by divisor.
 * <p>
 * The integer division should truncate toward zero, which means losing its fractional part. For example,
 * truncate(8.345) = 8 and truncate(-2.7335) = -2.
 * <p>
 * This problem requires A LOT OF FOCUS! best to start with simple test cases
 */
public class Divide {

    public static void main(String[] args) {
        Divide div = new Divide();
        int res = div.divide(-2147483648, -3);
        System.out.println(res);
    }

    /**
     * you could simplify your life by writing this code assuming only positive numbers and then as a second step handle
     * overflow and negative number conditions. for me thinking on the number line rightwards (positive) is mentally
     * much simpler when justifying the conditions....
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        //Special case: overflow. (-2^32 / -1) = 2^32 which is outside the bounds of int as max int is 2^32-1
        //treat this as a special case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        //use neg as a multiplier to adjust sign of final quotient.
        //if both dividend and divisors are negative than quotient is positive , else if one is negative its neg
        //if both are positive, its positive.
        int neg = (divisor < 0) ? -1 : 1;
        neg = (dividend < 0) ? -neg : neg;

        //change dividend and divisor to negative numbers
        //this is so you could handle the case of division of Integer.MIN_VALUE
        dividend = (dividend < 0) ? dividend : -dividend;
        divisor = (divisor < 0) ? divisor : -divisor;

        int aggPower = 0;
        int remDividend = dividend;
        while (true) {
            int power = 0;
            int origRemaining = remDividend;
            while (remDividend < 0) {
                int divisorAgg = divisor << power;
                remDividend = origRemaining - divisorAgg;
                power++;
            }
            //because the comparison was actually made at the power before the last incremented one
            //decrement the power by one
            power--;
            if ((remDividend + divisor) <= 0) {
                int val = aggPower;
                if (power >= 0) {
                    val += (1 << power);
                    if (remDividend + divisor < 0) {
                        val--; //remove one from power to get the rem dividend in negative territory
                    }
                }
                return neg == -1 ? -val : val;
            }
            //remove the last power as it decrements it by power of 2 too far
            // i,e 24 * 2 = 48, which if too far out, wed need to note down the 24
            // and start again from multiples of 2,4,8...
            power--;
            //adjust the remainder to remove the part that was subtracted out in the above loop
            //i,e remove divisor << power
            remDividend = origRemaining - (divisor << power);
            //note that powers need to be aggregated as multiples of 2
            aggPower += (1 << power);
        }
    }
}
