package linkedin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SortedTransformArray {

    public static void main(String [] args) {
        int [] nums = {-4,-2,2,4};
        int a = 1, b = 3, c = 5;

        SortedTransformArray sorted = new SortedTransformArray();
        int [] res = sorted.sortTransformedArray(nums, a, b, c);
        for (int v: res) {
            System.out.println(v);
        }
    }

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        LinkedList<Integer> list = new LinkedList<>();
        processTransform(nums, a, b, c, list);
        int [] res = new int[list.size()] ;
        Iterator<Integer> iterList = list.iterator();
        for (int i =0; iterList.hasNext(); i++) {
            res[i] = iterList.next();
        }
        return res;
    }


    int quadratic(int val, int a, int b, int c) {
        return a*val*val + b*val + c;
    }

    void processTransform(int[] nums, int a, int b, int c, LinkedList<Integer> list) {
        int left = 0;
        int right = nums.length-1;

        while (right >= left) {
            int lVal = quadratic(nums[left], a, b, c);
            int rVal = quadratic(nums[right], a, b, c);

            if (a > 0) {
                if (lVal > rVal) {
                    list.addFirst(lVal);
                    left++;
                } else {
                    list.addFirst(rVal);
                    right--;
                }
            } else {
                if (lVal < rVal) {
                    list.add(lVal);
                    left++;
                } else {
                    list.add(rVal);
                    right--;
                }
            }
        }
    }
}
