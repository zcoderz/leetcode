package redo;

public class ValidUtf {

    public static void main(String [] args) {
        ValidUtf valid = new ValidUtf();
        int [] data = {145};
        boolean isV = valid.validUtf8(data);
        System.out.println(isV);
    }

    public boolean validUtf8(int[] data) {
        int carry = 0;
        for (int val : data) {
            String str = Integer.toBinaryString(val);
            System.out.println(str);
            if (carry > 0) {
                int maskA = 1 << 7;
                int maskB = 1 << 6;
                if (! ( (val & maskA) > 0 && (val &maskB) ==0)) {
                    return false;
                }
                carry--;
            } else {
                int mask = 1 << 7;
                while ((mask & val) > 0) {
                    carry++;
                    mask = mask >> 1;
                }
                carry--;
                carry = Math.max(carry, 0);
            }
        }
        return carry == 0;
    }

}
