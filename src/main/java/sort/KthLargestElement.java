package sort;

import java.util.Random;

/**
 * 215. Kth Largest Element in an Array
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 * IMP-3: Common interview questions are on this theme.
 * The same question is also solved in the class : KthLargest via binary trees.
 * Most efficient to find the slot via quick select
 *
 */
public class KthLargestElement {

    Random random = new Random();

    public static void main(String[] args) {
        KthLargestElement kthLargestElement = new KthLargestElement();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int val = kthLargestElement.findKthLargest(nums, k);
    }

    /**
     * find the (length of nums - k) smallest element, thats the same as the kth largest element
     * @param nums
     * @param k
     * @return
     */
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
