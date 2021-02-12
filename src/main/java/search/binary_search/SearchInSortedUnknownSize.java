package search.binary_search;

/**
 * 702. Search in a Sorted Array of Unknown Size
 *
 * Given an integer array sorted in ascending order, write a function to search target in nums.
 * If target exists, then return its index, otherwise return -1.
 * However, the array size is unknown to you. You may only access the array using an ArrayReader interface,
 * where ArrayReader.get(k) returns the element of the array at index k (0-indexed).
 *
 * You may assume all integers in the array are less than 10000,
 * and if you access the array out of bounds, ArrayReader.get will return 2147483647.
 *
 *
 * Example 1:
 *
 * Input: array = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 *
 */
public class SearchInSortedUnknownSize {

    public static void main(String[] args) {
        SearchInSortedUnknownSize searchInSorted = new SearchInSortedUnknownSize();
        ArrayReader ar = new ArrayReader();
        int index = searchInSorted.search(ar, 9);
        System.out.println(index);
    }

    /**
     * In this solution we find the left and right indexes where the target will exist Once we find the right and left ,
     * we narrow towards the desired index.
     *
     * @param reader
     * @param target
     * @return
     */
    public int search(ArrayReader reader, int target) {
        if (reader.get(0) == target) {
            return 0;
        }
        int left = 0, right = 1;
        while (reader.get(right) < target) {
            left = right;
            right *= 2;
        }
        while (right >= left) {
            //System.out.println(left + " " + right);
            int pivot = left + (right - left) / 2;
            if (reader.get(pivot) == target) {
                return pivot;
            } else if (reader.get(pivot) > target) {
                right = pivot - 1;
            } else if (reader.get(pivot) < target) {
                left = pivot + 1;
            }
        }
        return -1;
    }

    /**
     * the solution is coded to dynamically find the right and left boundaries and then converge but thinking of this
     * solution is more complex as it doesnt find the typical binary search paradigm where you converge towards mid
     *
     * @param reader
     * @param target
     * @return
     */
    public int searchComplexThought(ArrayReader reader, int target) {
        if (reader.get(0) == target) {
            return 0;
        }

        int prior = 0;
        int nIndex = 1;

        do {
            if (reader.get(nIndex) == target) {
                return nIndex;
            } else if (reader.get(nIndex) > target) {
                int tmp = nIndex;
                nIndex = nIndex - (nIndex - prior) / 2;
                if (tmp == nIndex) nIndex--; //if delta between left and right is one, manually adjust
            } else if (reader.get(nIndex) < target) {
                int tmp = nIndex;
                nIndex = nIndex + (nIndex + prior) / 2;
                if (nIndex == tmp) nIndex++; //if delta between left and right is one, manually adjust
                prior = tmp;
            }
        } while (prior != nIndex);

        return -1;
    }

    public static class ArrayReader {
        int[] nums = {-1, 0, 3, 5, 9, 12};

        public int get(int index) {
            if (index >= nums.length) {
                return 2147483647;
            } else {
                return nums[index];
            }
        }
    }

}
