package redo;

import utils.TreeNode;

import java.util.Stack;

public class BSTIterator {
    TreeNode curr;
    Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        curr = root;
    }

    public int next() {
        int ret;

        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        curr = stack.pop();
        ret = curr.val;
        curr = curr.right;
        return ret;
    }

    public boolean hasNext() {
        if ((curr != null) || !stack.isEmpty()) {
            return true;
        }
        return false;
    }
}
