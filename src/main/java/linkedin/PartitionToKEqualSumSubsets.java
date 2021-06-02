package linkedin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PartitionToKEqualSumSubsets {

    public static void main(String [] args) {
        int [] nums = {4,3,2,3,5,2,1};
        int k = 4;

        PartitionToKEqualSumSubsets partition = new PartitionToKEqualSumSubsets();
        boolean works = partition.canPartitionKSubsets(nums, k);
        System.out.println(works);

        int [] nums2 = {1,2,3,4};
        int k2 = 3;

        PartitionToKEqualSumSubsets partition1 = new PartitionToKEqualSumSubsets();
        boolean works1 = partition1.canPartitionKSubsets(nums2, k2);
        System.out.println(works1);


        PartitionToKEqualSumSubsets partition3 = new PartitionToKEqualSumSubsets();
        int [] nums3 = {1,1,1,1,2,2,2,2,2};
        int k3 = 2;
        PartitionToKEqualSumSubsets partition2 = new PartitionToKEqualSumSubsets();
        boolean works2 = partition1.canPartitionKSubsets(nums3, k3);
        System.out.println(works2);
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int v : nums) {
            sum += v;
        }

        if (sum % k !=0) {
            return false;
        }

        Arrays.sort(nums);

        int target = sum/k;
        Set<Integer> visited = new HashSet<>();
        return processArr(nums, target, 0, k, nums.length-1, visited);

    }

    boolean processArr(int [] nums, int target, int currSum, int sets, int startIndex, Set<Integer> visited) {
        if (sets == 0) {
            return true;
        }


        for (int i = startIndex; i >= 0; i--) {
            if (visited.contains (i) || (nums[i] + currSum) > target) {
                continue;
            } else if (nums[i] + currSum == target) {
                visited.add(i);
                return processArr(nums, target, 0, sets-1, nums.length-1, visited);
            } else {
                visited.add(i);
                boolean works =  processArr(nums, target, currSum + nums[i], sets, i-1, visited);
                if (works) {
                    return true;
                }
                visited.remove(i);
            }


        }

        return false;
    }

}
