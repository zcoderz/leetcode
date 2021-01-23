package utils;


public class SegmentTreeNodeRectangleArea {
    public int start;
    public int end;
    public int min;
    public SegmentTreeNodeRectangleArea left;
    public SegmentTreeNodeRectangleArea right;

    SegmentTreeNodeRectangleArea(int start, int end) {
        this.start = start;
        this.end = end;
    }


    /**
     * you are building here a segment tree where each node is storing index of the minimum element under it
     *
     * @param heights
     * @param start
     * @param end
     * @return
     */
    public static SegmentTreeNodeRectangleArea buildSegmentTree(int[] heights, int start, int end) {
        if (start > end) return null;

        SegmentTreeNodeRectangleArea root = new SegmentTreeNodeRectangleArea(start, end);
        if (start == end) {
            root.min = start; //this is a leaf node , its min value is itself
            return root;
        } else {
            int mid = (start + end) / 2;
            root.left = buildSegmentTree(heights, start, mid);
            root.right = buildSegmentTree(heights, mid + 1, end);
            //when merging left and right trees, find the min height and set that as the min height on this index
            root.min = heights[root.left.min] < heights[root.right.min] ? root.left.min : root.right.min;
        }
        return root;
    }


    /**
     * the code return the index of the min element in the range
     *
     * @param root
     * @param heights
     * @param start
     * @param end
     * @return
     */
    public int query(SegmentTreeNodeRectangleArea root, int[] heights, int start, int end) {
        //if segment is outside the range, skip the check -- return sentinel
        if (root == null || end < root.start || start > root.end) return -1;
        //recuse down to the segments that are entirely within the range
        if (start <= root.start && end >= root.end) {
            return root.min;
        }

        int leftMin = query(root.left, heights, start, end);
        int rightMin = query(root.right, heights, start, end);
        if (leftMin == -1) return rightMin; // if left is off range then return min on right and vice versa
        if (rightMin == -1) return leftMin;
        //return the min of left and right if they both fall within range
        return heights[leftMin] < heights[rightMin] ? leftMin : rightMin;
    }

}
