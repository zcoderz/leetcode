package bitwise;

/**
 * 29. Divide Two Integers
 *
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod
 * operator.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * The integer division should truncate toward zero, which means losing its fractional part. For example,
 * truncate(8.345) = 8 and truncate(-2.7335) = -2.
 *
 * This problem requires A LOT OF FOCUS! best to start with simple test cases
 *
 * IMP-1: Facebook asks this questions often. The approach is tricky
 */
public class Divide {

    public static void main(String[] args) {
        Divide div = new Divide();
        //int res = div.divide(-2147483648, -3);
        int res = div.divide(78, 2);
        System.out.println(res);
    }

    private static int HALF_INT_MIN = -1073741824;

    /**
     * you could simplify your life by writing this code assuming only positive numbers and then as a second step handle
     * overflow and negative number conditions. for me thinking on the number line rightwards (positive) is mentally
     * much simpler when justifying the conditions....
     *
     */
    public int divide(int dividend, int divisor) {
        //Special case: overflow. (-2^31 / -1) = 2^31 which is outside the bounds of int as max int is 2^31-1
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
        //this is so you could handle the case of division of Integer.MIN_VALUE.
        //Integer.MIN_VALUE has no equivalent in positive space as -ive values have an extra value in integers.
        //specifically it is the -2147483648
        dividend = (dividend < 0) ? dividend : -dividend;
        divisor = (divisor < 0) ? divisor : -divisor;

        //trying to fix the highest power of two and the highest divisor multiple (highest double) that can be used
        //>= HALF_INT_MIN is to avoid overflow (we are working in negatives so need to check only the negative side)
        int highestDouble = divisor;
        int highestPowerOfTwo = -1;
        while (highestDouble >= HALF_INT_MIN && dividend <= highestDouble + highestDouble) {
            highestPowerOfTwo += highestPowerOfTwo;
            highestDouble += highestDouble;
        }

        //keep subtracting highest double from dividend until dividend is less than the divisor
        int quotient = 0;
        while (dividend <= divisor) {
            if (dividend <= highestDouble) {
                quotient += highestPowerOfTwo;
                dividend -= highestDouble;
            }
            /* We know that these are always even, so no need to worry about the
             * annoying "bit-shift-odd-negative-number" case. */
            highestPowerOfTwo >>= 1;
            highestDouble >>= 1;
        }

        //adjust to return the quotient based on neg sign
        if (neg != 1) {
            return -quotient;
        }
        return quotient;
    }
}
