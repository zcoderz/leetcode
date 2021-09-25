package face_book.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 1570. Dot Product of Two Sparse Vectors
 * Medium
 *
 * 402
 *
 * 55
 *
 * Add to List
 *
 * Share
 * Given two sparse vectors, compute their dot product.
 *
 * Implement class SparseVector:
 *
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.
 *
 * Follow up: What if only one of the vectors is sparse?
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 * Example 2:
 *
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 *
 */
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
