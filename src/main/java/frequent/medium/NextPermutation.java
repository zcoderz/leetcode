package frequent.medium;

import java.util.Arrays;

public class NextPermutation {

    public static void main(String [] args) {
        int [] nums = {1,2};
        NextPermutation np = new NextPermutation();
        np.nextPermutation(nums);
        for (int i =0; i < nums.length; i++) {
            System.out.print(nums[i] + " , ");
        }
    }

    public void nextPermutation(int[] nums) {

        //step 1: find first index thats not in descending order from right
        int j = nums.length-1; boolean nonDesc = false;
        for (; j>0; j--) {
            if (nums[j-1] < nums[j]) {
                nonDesc = true;
                j--;
                break;
            }
        }
        if (!nonDesc) {
            Arrays.sort(nums);
            return;
        }


        //step 2: find first index from right thats higher than the index found above
        int i = nums.length-1;
        for (; i > j; i--) {
            if (nums[i] > nums[j]) {
                break;
            }
        }

        //step 3: swap i and j
        swap(nums, i, j);

        //step 4: the index right of this index are in descending order, change them to be in ascending order
        for (i =j+1, j = nums.length-1; j > i; j--,i++) {
            swap(nums, i, j);
        }
    }

    void swap(int [] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

}
