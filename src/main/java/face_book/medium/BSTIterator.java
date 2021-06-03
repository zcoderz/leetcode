package face_book.medium;

import utils.TreeNode;

import java.util.Stack;

public class BSTIterator {

    TreeNode curr;
    Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        curr = root;
    }

    public int next() {
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        curr = stack.pop();
        TreeNode ret = curr;
        curr = curr.right;
        return ret.val;
    }

    public boolean hasNext() {
        if (!stack.isEmpty() || curr != null) {
            return true;
        } else {
            return false;
        }
    }

}
