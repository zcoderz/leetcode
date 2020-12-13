package misc;

public class BitBasedAdd {

    public static void main (String [] args) {
        int y = -12;
        int x = -8;

        BitBasedAdd bitBasedAdd = new BitBasedAdd();
        int sum = bitBasedAdd.getSum(y, x);
        System.out.println(sum);
    }

    public int getSum(int a, int b) {
        int x = Math.abs(a);
        int y = Math.abs(b);

        int sign = a >=0 ? 1 : -1;
        if (y > x) {
            return getSum(b, a);
        } else {
            if (a * b > 0) {
                while (y !=0) {
                    int s = x ^ y; // add the xor bits (bits unique in each num)
                    int c = (x & y) << 1; //find the carry bits and shift them left by 1 to identify the carry
                    x = s;
                    y = c;
                }
            } else {
                //subtract
                while (y !=0) {
                    int s = x ^ y; // add the xor bits (bits unique in each num)
                    //find the borrow bit and shift them right by 1 to identify the borrow
                    //the off bits in x when & with y identify the bits where borrow is needed.
                    //since xor would merge these bits from y into x (above), we need to shift the ~x & y one left
                    //to identify the borrow number.
                    int c = ((~x) & y) << 1;
                    x = s;
                    y = c;
                }
            }
        }
        return sign*x;
    }

}
