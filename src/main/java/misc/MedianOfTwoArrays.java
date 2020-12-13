package misc;


/**
 * https://www.geeksforgeeks.org/median-two-sorted-arrays-different-sizes-ologminn-m/
 * The pseudo code is to figure out how many elements to pick from the first array.
 * Once we know it, the rest is easy.
 * i = number of elements from first array
 * j = number of elements from second array
 * min and max variables are used to converge towards the correct i
 */
class MedianOfTwoArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int leftSize = nums1.length;
        int rightSize = nums2.length;
        int minIndex = 0;
        int maxIndex = leftSize;
        int i =0;
        int j = 0;
        int median = 0;
        if (leftSize == 0) {
            int mid = rightSize / 2;
            if (rightSize % 2 == 0) {
                return (nums2[mid-1] + nums2[mid]) / 2;
            } else {
                return nums2[mid];
            }
        } else if (rightSize == 0) {
            int mid = leftSize / 2;
            if (leftSize % 2 == 0) {
                return (nums1[mid-1] + nums1[mid]) / 2;
            } else {
                return nums1[mid];
            }
        }
        while (maxIndex >= minIndex) {
            i = (minIndex + maxIndex) / 2;
            j = (leftSize + rightSize + 1) /2  - i;

            if (j > 0 && i < leftSize && nums2[j-1] > nums1[i]) {
                minIndex = i + 1;
            }
            else if (i > 0 && j < rightSize && nums1[i-1] > nums2[j]) {
                maxIndex = i - 1;
            }
            else {
                if (i==0) {
                    median = nums2[j-1];
                    break;
                }

                if (j == 0) {
                    median = nums1[i-1];
                    break;
                }

                median = Math.max(nums1[i-1] , nums2[j-1]);
                break;
            }

        }
        if ((leftSize + rightSize) % 2 == 1) {
            return (double) median;
        }
        if (i == leftSize) {
            return (median + nums2[j] ) / 2;
        }
        if (j == rightSize) {
            return (median + nums1[i]) / 2;
        }

        return (median + Math.min(nums1[i], nums2[j])) / 2.0;
    }
    public static void main(String [] args) {
        int [] nums1 = {1,2};
        int [] nums2 = {3,4};
        double med = findMedianSortedArrays(nums1, nums2);
    }
}
