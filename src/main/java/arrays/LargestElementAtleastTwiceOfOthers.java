package arrays;

public class LargestElementAtleastTwiceOfOthers {

    public int dominantIndex(int[] nums) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        int index = 0;
        for (int i =0 ; i < nums.length; i++) {
            int num = nums[i];
            if (num > largest) {
                index = i;
                secondLargest = largest;
                largest = num;
            }  else if (num > secondLargest) {
                secondLargest = num;
            }
        }

        return largest >= 2* secondLargest ? index : -1;
    }

}
