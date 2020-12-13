package recursion;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class IsSameTree {

    /**
     * recursive solution for same tree problem
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTreeRecursive(TreeNode p, TreeNode q) {
        if (!checkSame(p, q)) {
            return false;
        }
        if (p==null && q== null) {
            return true;
        }
        boolean same = isSameTreeRecursive(p.left, q.left);
        if (!same) {
            return false;
        }
        same = isSameTreeRecursive(p.right, q.right);
        if (!same) {
            return false;
        }
        return true;
    }

    boolean checkSame(TreeNode p, TreeNode q) {
        if (p==null && q==null) {
            return true;
        }
        if (p==null || q == null) {
            return false;
        }
        if(p.val != q.val) {
            return false;
        }
        return true;
    }


    /**
     * non recursive solution, mimic a recursion using physical stack.
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<TreeNode> aStack = new Stack<>();
        Stack<TreeNode> bStack = new Stack<>();

        aStack.push(p);
        bStack.push(q);

        while (!aStack.isEmpty()) {
            TreeNode aNode = aStack.pop();
            TreeNode bNode = bStack.pop();
            boolean same = checkSame(aNode, bNode);
            if (!same) {
                return false;
            }
            if (aNode == null && bNode == null) {
                continue;
            }
            aStack.push(aNode.right);
            bStack.push(bNode.right);

            aStack.push(aNode.left);
            bStack.push(bNode.left);
        }

        return true;
    }
}
