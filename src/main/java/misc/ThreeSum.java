package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static void main(String [] args) {
        int nums[] = {-1,0,1,2,-1,-4};
        List<List<Integer>> theList = threeSum(nums);
        return ;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        Integer firstNum;
        for (int i =0; i < nums.length; i++) {
            firstNum = nums[i];
            if(firstNum >= 0) {
                break;
            }
            int front = i + 1;
            int back = nums.length -1;
            int target = -firstNum;
            while (front < back) {
                int sum = nums[front] + nums[back];
                if(sum > target) {
                    back--;
                } else if (sum < target) {
                    front++;
                } else {
                    List<Integer> arr = new ArrayList<Integer>();
                    arr.add(firstNum); arr.add(nums[front]); arr.add(nums[back]);
                    triplets.add(arr);
                    while(front < back && nums[front] == arr.get(1)) {
                        front++;
                    }
                    while(front < back && nums[back] == arr.get(2)) {
                        back--;
                    }
                }
            }

            while(i+1 < nums.length && nums[i] == nums[i+1]) {
                i++;
            }
        }

        return triplets;
    }
}
