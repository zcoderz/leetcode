package arrays;

public class IncreasingTripletSubsequence {

    public static void main(String[] args) {
        IncreasingTripletSubsequence tripletSubsequence = new IncreasingTripletSubsequence();
        int[] arr = {1, 1, -2, 6};
        boolean bFound = tripletSubsequence.increasingTriplet(arr);
        System.out.println(bFound);
    }

    /**
     * tricky problem because you think , you break the sequence when you see 1 in {2, 11, 1, 12} but you dont care,
     * because 1 is still less than 11 and you found two numbers smallest and second smallest which are really defined
     * by second smallest is bigger than the smallest. then when you see any number greater than smallest and second
     * smallest you finish.
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num < first) {
                first = num;
            } else if (num < second && num > first) {
                second = num;
            } else if (num > second) {
                return true;
            }
        }
        return false;
    }
}
