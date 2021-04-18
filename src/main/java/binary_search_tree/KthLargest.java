package binary_search_tree;

import utils.TreeNode;

/**
 * 215. Kth Largest Element in an Array
 *
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 * this is an interesting problem.
 * takes some thinking to get the concept of counts in the head
 * but its simple once you understand the logic of when to search left or right based on current count
 * and that of its left and right children :)
 *
 * There are simpler ways to solve the problem. For example put the data in a treeset whose size is ensured to be at
 * max k. move left to right in array adding and removing elements from the treeset. at end return the last element
 * in the treeset.
 */
public class KthLargest {

    public static void main(String [] args) {

        int [] nums = {4,5,8,2,1,9,12,13};
        KthLargest kthLargest = new KthLargest(3, nums);
        int val = kthLargest.findKthLargest(kthLargest.root, 3);
        System.out.println(val);

        val = kthLargest.add(5);
        System.out.println(val);

        val = kthLargest.add(10);
        System.out.println(val);

        val = kthLargest.add(9);
        System.out.println(val);
    }


    TreeNode root ;
    int kth ;


    public KthLargest(int k, int[] nums) {
        kth = k;
        root = null;
        for (int n : nums) {
            root = addToTree(root, n);
        }
    }


    public int add(int val) {
        root = addToTree(root, val);
        return findKthLargest(root, kth);
    }

    /**
     * add to tree and update the counts
     * @param node
     * @param n
     * @return
     */
    private TreeNode addToTree(TreeNode node, int n) {
        if (node == null) {
            return new TreeNode(n, 1);
        }
        if (n > node.val ) {
            node.right = addToTree(node.right, n);
        } else if (n < node.val) {
            node.left = addToTree(node.left, n);
        }

        //count is the number of children a node has.
        //so when a new child is added under a node we increment its count
        node.count = node.count+1;
        return node;
    }

    /**
     * the kth arithmetic is simple if you draw a tree out on a white board and work the edge cases out based off of
     * test case
     * @param node
     * @param k
     * @return
     */
    private int findKthLargest(TreeNode node, int k) {
        //check how many numbers are greater than the current number
        int right = node.right == null ? 0 : node.right.count;
        //check where the current number falls based on
        //its count - numbers less than it
        //nodeNum is the kth largest index of this node based off of its children
        int nodeNum = node.count - (node.left== null? 0: node.left.count);

        if (k <= right) {
            //if kth largest is on right, go to right
            assert node.right != null;
            return findKthLargest(node.right, k);
        } else if (k > nodeNum) {
            assert node.left != null;
            //if we are searching for a number smaller than the current node, we go left
            //while also decrementing from k the 'nodeNum' which states the kth rank of this node based off of its
            //children
            return findKthLargest(node.left, k-nodeNum);
        } else {
            //this must be the number you are looking for
            return node.val;
        }
    }

}
