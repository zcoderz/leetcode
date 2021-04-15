package bitwise;

/**
 * IMP-2 : Good practice question for bit based arithmetic
 */
public class BinaryBitBasedAdd {

    public static void main(String[] args) {
        int y = 12;
        int x = -7;

        BinaryBitBasedAdd bitBasedAdd = new BinaryBitBasedAdd();
        int sum = bitBasedAdd.getSum(y, x);
        System.out.println(sum);
    }

    public int getSum(int a, int b) {
        int x = Math.abs(a);
        int y = Math.abs(b);

        int sign = a >= 0 ? 1 : -1;
        if (y > x) {
            return getSum(b, a);
        } else {
            if (a * b > 0) {
                //add
                while (y != 0) {
                    int s = x ^ y; // add the xor bits (bits unique in each num)
                    int c = (x & y) << 1; //find the carry bits and shift them left by 1 to identify the carry
                    x = s;
                    y = c;
                }
            } else {
                //subtract
                while (y != 0) {
                    //this one is tricky , logic is :
                    // 1. that you run XOR. merge the unique bits.
                    // 2. you find the borrow bits and repeat the process. borrow is simply (~x & y) << 1
                    // 3. i,e think of 8-4 (1000 - 0100)
                    //    1st step you merge 8 & 4 to 1100 and you calculate borrow as: (~8&4)=0100, left shift to 1000
                    //    2nd iteration you work on 1100 & 1000. xor gives 0100. borrow comes out as 0 and you are done
                    // think of 12-7, steps would be 11-6 in first iteration, second 13-8 and last 5 with no borrow
                    // borrow is left shifting until it becomes 0

                    int s = x ^ y; // add the xor bits (bits unique in each num)

                    //find the borrow bit and shift them left by 1 to identify the borrow
                    //the off bits in x when & with y identify the bits where borrow is needed.
                    //since xor would merge these bits from y into x (above), we need to shift the ~x & y one left
                    //to identify the borrow number.
                    int c = ((~x) & y) << 1;
                    x = s;
                    y = c;
                }
            }
        }
        return sign * x;
    }

}
