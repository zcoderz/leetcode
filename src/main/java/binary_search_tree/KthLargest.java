package binary_search_tree;

import utils.TreeNode;

/**
 * this is an interesting problem.
 * takes some thinking to get the concept of counts in the head
 * but its simple once you understand the logic of when to search left or right based on current count
 * and that of its left and right children :)
 */
public class KthLargest {

    public static void main(String [] args) {

        int [] nums = {4,5,8,2};
        KthLargest kthLargest = new KthLargest(3, nums);
        int val = kthLargest.add(3);
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

        //if the same number is re inserted we adjust its count rather than creating a new node.
        //we could have created a new node and it would make the math a little simpler in search
        //but thats just wasting memory
        node.count = node.count+1;
        return node;
    }

    private int findKthLargest(TreeNode node, int k) {
        //check how many numbers are greater than the current number
        int right = node.right == null ? 0 : node.right.count;
        //check where the current number falls based on
        //its count - numbers less than it
        int nodeNum = node.count - (node.left== null? 0: node.left.count);

        //if kth largest is on right, go to right
        if (k <= right) {
            assert node.right != null;
            return findKthLargest(node.right, k);
        } else if (k > nodeNum) {
            //if k largest is greater than current number's k vale , go to left
            assert node.left != null;
            return findKthLargest(node.left, k-nodeNum);
        } else {
            //this must be the number you are looking for
            return node.val;
        }
    }

}
