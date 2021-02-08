package frequent.medium;

/**
 * 11. Container With Most Water
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).
 * Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 *
 * Notice that you may not slant the container.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 *
 * IMP-2: Simple question thats common
 *
 */
public class ContainerWithMostWater {

    /**
     * start from both right and left edges calculating max area for each container. after each iteration move the
     * smaller side inwards keep repeating until right is ahead of left
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int maxArea = 0;
        int r = height.length - 1;
        int l = 0;
        while (r > l) {
            int dist = r - l;
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * dist);
            if (height[r] > height[l]) {
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1};
        int theArea = maxArea(arr);
    }

}
