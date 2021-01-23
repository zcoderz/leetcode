package misc;

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
