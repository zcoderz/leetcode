package amazon.medium;


import java.util.Arrays;

/**
 * 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
 * Medium
 *
 * 716
 *
 * 180
 *
 * Add to List
 *
 * Share
 * You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:
 *
 * horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
 * verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the
 * arrays horizontalCuts and verticalCuts. Since the answer can be a large number, return this modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 * Output: 4
 * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts.
 * After you cut the cake, the green piece of cake has the maximum area.
 */
public class MaxArea {

    public static void main(String [] args) {
        int [] horizontals = {3,1};
        int [] veriticals = {1};
        MaxArea max = new MaxArea();
        int a = max.maxArea(5, 4, horizontals, veriticals);
        System.out.println(a);
    }

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {

        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);


        int numHorizontalCuts = horizontalCuts.length-1;
        int numVerticalCuts = verticalCuts.length-1;

        long maxHeight = Math.max(horizontalCuts[0], h-horizontalCuts[numHorizontalCuts]);

        for (int i = 1; i <= numHorizontalCuts; i++) {
            maxHeight = Math.max(maxHeight, horizontalCuts[i]- horizontalCuts[i-1]);
        }

        long maxWidth = Math.max(verticalCuts[0], w-verticalCuts[numVerticalCuts]);
        for (int i = 1; i <= numVerticalCuts; i++) {
            maxWidth = Math.max(maxWidth, verticalCuts[i]- verticalCuts[i-1]);
        }

        return (int) ((maxHeight * maxWidth) % 1000000007);

    }
}

