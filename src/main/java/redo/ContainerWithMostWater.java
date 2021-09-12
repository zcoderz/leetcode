package redo;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left =0;
        int right = height.length-1;
        while (right > left) {
            int width = right - left;
            int theH = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, theH*width);
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }
}
