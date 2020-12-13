package search.binary_search;

public class NumZerosInSortedArray {

    public static void main(String []args) {
        NumZerosInSortedArray numZero = new NumZerosInSortedArray();
        int [] nums = {-2,-1,0,0,3,4,5};
        int theNum = numZero.numZerosInSorted(nums);
        System.out.println(theNum);

    }

    int numZerosInSorted(int [] array) {
        int leftIndex  = findNum(array, 0, true);
        if (leftIndex == -1) {
            return -1;
        }
        int rightIndex  = findNum(array, 0, false);
        return rightIndex-leftIndex+1;
    }

    int findNum(int [] array, int num, boolean checkLeft) {
        int left = 0; int right = array.length;

        while (right > left) {
            int mid = left + (right-left) /2;

            if (array[mid] < num) {
                left = mid+1;
            } else if (array[mid] > num) {
                right = mid-1;
            } else {
                if (checkLeft) {
                    if (mid==0) {
                        return 0;
                    } else if (array[mid-1] != num) {
                        return mid;
                    } else {
                        right = mid-1;
                    }
                }   else {
                    if (mid== array.length-1) {
                        return mid;
                    } else if (array[mid+1] != num) {
                        return mid;
                    } else {
                        left = mid + 1;
                    }
                }
            }
        }

        if (array[left] == 0) {
            return left;
        }  else {
            return -1;
        }
    }
}
