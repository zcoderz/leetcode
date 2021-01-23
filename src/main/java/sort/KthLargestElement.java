package sort;

import java.util.Random;

public class KthLargestElement {

    Random random = new Random();

    public static void main(String[] args) {
        KthLargestElement kthLargestElement = new KthLargestElement();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int val = kthLargestElement.findKthLargest(nums, k);
    }

    public int findKthLargest(int[] nums, int k) {

        return searchKSmallest(0, nums.length - 1, nums.length - k, nums);
    }

    public int searchKSmallest(int left, int right, int kthSmallest, int[] nums) {
        if (left == right) {
            return nums[left];
        }

        int pivot = left + random.nextInt(right - left);

        int partitionIndex = partition(left, right, pivot, nums);

        if (partitionIndex == kthSmallest) {
            return nums[partitionIndex];
        } else if (partitionIndex < kthSmallest) {
            return searchKSmallest(partitionIndex + 1, right, kthSmallest, nums);
        } else {
            return searchKSmallest(left, partitionIndex - 1, kthSmallest, nums);
        }

    }

    int partition(int left, int right, int pivot, int[] nums) {
        int val = nums[pivot];

        swap(pivot, right, nums);
        int swapIndex = left;
        for (int i = left; i <= right; i++) {
            if (nums[i] < val) {
                swap(i, swapIndex++, nums);
            }
        }
        swap(swapIndex, right, nums);
        return swapIndex;
    }

    void swap(int a, int b, int[] nums) {
        int val = nums[a];
        nums[a] = nums[b];
        nums[b] = val;
    }
}
