package face_book.medium;

import java.util.ArrayList;
import java.util.List;

public class SparseVector {

    List<int []> pairs = new ArrayList<>();
    SparseVector(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                pairs.add(new int[]{i, nums[i]});
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int res = 0;
        int left=0, right = 0;
        while (left < pairs.size() && right <  vec.pairs.size()) {
            if (this.pairs.get(left) [0] == vec.pairs.get(right)[0]) {
                res += this.pairs.get(left) [1] * vec.pairs.get(right)[1];
                left++; right++;
            } else if (this.pairs.get(left) [0] < vec.pairs.get(right)[0]) {
                left++;
            } else {
                right++;
            }
        }
        return res;
    }
}
