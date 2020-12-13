package trees;

import java.util.HashMap;
import java.util.Map;

public class CreateTreeFromPreAndInOrder {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main (String [] args) {
        int [] preOrder = {3,9,20,15,7};
        int [] inOrder = {9,3,15,20,7};

        CreateTreeFromPreAndInOrder createTreeFromPreAndInOrder = new CreateTreeFromPreAndInOrder();

        TreeNode node =  createTreeFromPreAndInOrder.buildTree(preOrder, inOrder);
        int j;
    }
    Map<Integer, Integer> inOrderIndexMap = new HashMap<>();

    int rootIndex = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inOrderIndexMap.put(inorder[i], i);
        }
        return recurseTree(0, preorder.length, preorder, inorder);
    }

    TreeNode recurseTree(int left, int right, int[] preorder, int[] inorder) {
        if (left > right || rootIndex > preorder.length) {
            return null;
        }

        int val = preorder[rootIndex++];
        TreeNode root = new TreeNode (val);
        int index = inOrderIndexMap.get(val);
        root.left = recurseTree(left, index-1, preorder, inorder);
        root.right = recurseTree(index+1, right, preorder, inorder);

        return root;
    }
}
