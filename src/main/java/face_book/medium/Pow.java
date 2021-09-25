package face_book.medium;


/**
 * 50. Pow(x, n)
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 * Example 1:
 *
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 */
public class Pow {

    public static void main(String [] args) {
        Pow p = new Pow();
        double v = p.myPow(2, 10);
        System.out.println(v);
    }

    public double myPow(double x, int nVal) {
        long n = nVal;
        if (n < 0) {
            n *= -1;
            x = 1/x;
        }
        //return powRecursion(x, n);
        return powIter(x, n);
    }

    double powRecursion(double x , long n) {
        if (n ==0) {
            return 1;
        }
        double val = powRecursion(x, n/2);
        if (n % 2==0) {
            return val * val;
        } else {
            return val*val*x;
        }
    }

    double powIter(double x, long n) {
        double ans=1;
        double currentVal = x;
        for(; n >0; n=n/2) {
            if (n%2==1) {
                ans = currentVal * ans;
                //currentVal = currentVal * ans;
            }
            currentVal = currentVal*currentVal;
        }

        return ans;
    }
}
