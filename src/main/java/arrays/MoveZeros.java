package arrays;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class MoveZeros {


    public static void main(String [] args) {
        MoveZeros mz = new MoveZeros();
        int [] nums = {0,1,0,3,12};
        mz.moveZeroes(nums);
        for(Integer num: nums) {
            System.out.println(num);
        }
    }
    /**
     * idea here is to move the zero items to the right of the array while keeping relative order of the non zero
     * elements
      *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int end = nums.length;
        int zeroIndex = 0;

        for(int i =0; i < end; i++) {
            if (nums[i] !=0 && nums[zeroIndex]==0) {
                //swap the elements of the i with the 0 index.
                //this will move zero to the right and non zero to left
                //ultimately zeros will be moved to the right of the array
                nums[zeroIndex++] = nums[i];
                nums[i] = 0;
            } else if (nums[zeroIndex] !=0 ) {
                zeroIndex++; //move the zeroth index forward to the next 0th index.
            }
        }
    }

}
