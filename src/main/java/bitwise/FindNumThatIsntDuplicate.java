package bitwise;

public class FindNumThatIsntDuplicate {
    public int singleNumber(int[] nums) {
        int total = 0;
        //a xor b xor a = b. based on this if we keep iterating xor through the list of numbers
        //we will find the non duplicate number.
        for (int num : nums) {
            total ^= num;
        }
        return total;
    }
}
