package search.binary_search;

public class SortedRotatedArrayFindNum {

    public static void main(String [] args) {

        SortedRotatedArrayFindNum search = new SortedRotatedArrayFindNum();
        int [] nums = {4,5,6,7,0,1,2};

        int val = search.search(nums, 0);
        System.out.println(val);

    }
    /**
     * This question was previously solved in SearchRotated
     * but that solution does two passes on the array. Here
     * we solve this in one pass.
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        return binary_search(nums, 0, nums.length-1, target);
    }

    int binary_search(int[] nums, int low, int high, int target) {

        while (high >= low) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return  mid;
            }else if ((nums[low] > nums[mid])) {
                //rotation is on left
                if ((nums[mid] > target) || (target >= nums[low])) {
                    high = mid - 1;
                }   else {
                    low = mid + 1;
                }
            } else {
                //rotation is not on left
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid-1; //target is between mid and low
                } else {
                    low = mid +1; //target is on right
                }

            }

        }
        return -1;
    }

    int binary_search_complex(int[] nums, int low, int high, int target) {
       if (low == high) {
           if (nums[low] == target) {
               return low;
           } else {
               return -1;
           }
       }

       int mid = (low + high) / 2;

       //check if rotation is towards left
       boolean rotationLeft = false;
       if (nums[low] > nums[mid]) {
           rotationLeft = true;
       }

       if (rotationLeft && ((target >= nums[low]) || target < nums[mid])) {
           return binary_search(nums, low, mid-1, target);
       } else {

           if (target == nums[mid]) {
               return mid;
           } else if (target >= nums[low] && target < nums[mid]) {
               return binary_search(nums, low, mid-1, target);
           } else {
               return binary_search(nums, mid+1, high, target);
           }
       }

    }
}
