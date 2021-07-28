package redo;

import java.util.ArrayList;
import java.util.List;

public class SparseVector {


    List<int []> l = new ArrayList<>();
    SparseVector(int[] nums) {
        for (int i =0; i < nums.length; i++) {
            if (nums[i] != 0) {
                l.add(new int[] {i, nums[i]});
            }
        }
    }

    public int dotProduct(SparseVector vec) {
        int left = 0; int right = 0;
        int val = 0;
        while(left < l.size() && right < vec.l.size()) {
            if (this.l.get(left)[0] == vec.l.get(right)[0]) {
                val += this.l.get(left++)[1] * this.l.get(right++)[1];
            } else if (this.l.get(left)[0] < vec.l.get(right)[0]) {
                left++;
            } else {
                right++;
            }

        }
        return val;
    }
}
