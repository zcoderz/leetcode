package trees;
public class LowestCommonAncestor {
    public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val) {
            boolean bFound =  ((findNode(root.left, q)) || (findNode(root.right, q)));
            if (bFound) {
                return root;
            } else {
                return null;
            }
        } else if (root.val == q.val) {
            boolean bFound =  findNode(root.left, p) || findNode(root.right, p);
            if (bFound) {
                return root;
            } else {
                return null;
            }
        }

        boolean pFoundLeft = findNode(root.left, p);
        boolean qFound;
        if (pFoundLeft) {
            qFound = findNode(root.right, q);
        } else {
            qFound = findNode(root.left, q);
        }
        if (qFound) {
            return root;
        }
        if (pFoundLeft) {
            return lowestCommonAncestor(root.left, p, q);
        }   else {
            return lowestCommonAncestor(root.right, p, q);
        }
    }


    boolean findNode(TreeNode node, TreeNode a) {
        if (node == null) return false;
        if (node.val == a.val) {
            return true;
        }
        if (node.left != null) {
            boolean found = findNode(node.left, a);
            if (found) {return true;}
        }
        if (node.right != null) {
            boolean found = findNode(node.right, a);
            if (found) {return true;}
        }
        return false;
    }
}
