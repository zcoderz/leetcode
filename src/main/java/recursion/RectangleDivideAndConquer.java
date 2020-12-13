package recursion;

public class RectangleDivideAndConquer {

    public static void main(String[] args) {
        //int[] heights = {2, 1, 5, 6, 4, 11, 5};
        //int [] heights = {6, 7, 5, 2, 4, 5, 9, 3};
        int [] heights = {2,1,5,6,2,3};
        RectangleDivideAndConquer lg = new RectangleDivideAndConquer();
        int area = lg.largestRectangleArea(heights);
        System.out.println(area);
    }

//    public int calculateArea(int[] heights, int start, int end) {
//        if (start > end)
//            return 0;
//        int minindex = start;
//        for (int i = start; i <= end; i++)
//            if (heights[minindex] > heights[i])
//                minindex = i;
//        return Math.max(heights[minindex] * (end - start + 1), Math.max(calculateArea(heights, start, minindex - 1), calculateArea(heights, minindex + 1, end)));
//    }

    public int calculateArea(int[] heights, int start, int end) {
        if (start > end) return 0;
        int minIndex = start;
        for (int i = start; i < end; i++) {
            if (heights[i] < heights[minIndex]) {
                minIndex = i;
            }
        }
        int minA = heights[minIndex] * (end-start+1);
        int lA = calculateArea(heights, start, minIndex-1);
        int rA = calculateArea(heights, minIndex+1, end);
        return Math.max(minA, Math.max(lA, rA));
    }

    public int largestRectangleArea(int[] heights) {
        return calculateArea(heights, 0, heights.length - 1);
    }
}
