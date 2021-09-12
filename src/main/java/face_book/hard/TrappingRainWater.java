package face_book.hard;

/**
 * 42. Trapping Rain Water
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 *
 * IMP-1: Good practice.
 * ToDO : add to website.
 */
public class TrappingRainWater {

    public static void main(String [] args) {
        int [] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater trapping = new TrappingRainWater();
        int trap = trapping.trap(heights);
        System.out.println(trap);
    }

    public int trap(int[] height) {
        int leftIndex = 0;
        int rightIndex = height.length-1;
        int leftMax = height[0];
        int rightMax = height[height.length-1];

        int waterUnits = 0;

        while (rightIndex > leftIndex) {
            int theH = 0;
            if (leftMax < rightMax) {
                theH = Math.max(0, leftMax - height[leftIndex++]);
                leftMax = Math.max(leftMax, height[leftIndex]);
            } else {
                theH = Math.max(0, rightMax - height[rightIndex--]);
                rightMax = Math.max(rightMax, height[rightIndex]);
            }
            waterUnits += theH;
        }
        return waterUnits;
    }

    public int trapDP(int[] height) {
        int [] leftHts = new int[height.length];
        int [] rightHts = new int[height.length];

        leftHts[0] = height[0];
        for (int i =1; i < height.length; i++) {
            leftHts[i] = Math.max(height[i], leftHts[i-1]);
        }

        rightHts[height.length-1] = height[height.length-1];
        for (int i =height.length-2; i >=0; i--) {
            rightHts[i] = Math.max(height[i], rightHts[i+1]);
        }

        int traps = 0;

        for (int i =0; i < height.length; i++) {
            traps += Math.max(0, Math.min(leftHts[i], rightHts[i]) - height[i]);
        }

        return traps;
    }

}
