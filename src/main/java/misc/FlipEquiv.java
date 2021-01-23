package misc;

public class FlipEquiv {
    public ChildNullStatus getChildNullStatus(final TreeNode node) {
        if ((node.left != null) && (node.right != null)) {
            return ChildNullStatus.BOTH_NOT_NULL;
        } else if ((node.left == null) && (node.right == null)) {
            return ChildNullStatus.BOTH_NULL;
        } else if ((node.left == null)) {
            return ChildNullStatus.LEFT_NULL;
        } else {
            return ChildNullStatus.RIGHT_NULL;
        }
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        //root doesnt have same value
        if (root1.val != root2.val) {
            return false;
        }
        ChildNullStatus aStatus = getChildNullStatus(root1);
        ChildNullStatus bStatus = getChildNullStatus(root2);
        //equal null children
        if (aStatus == ChildNullStatus.BOTH_NULL && bStatus == ChildNullStatus.BOTH_NULL) {
            return true;
        }
        //unequal children
        if (((aStatus == ChildNullStatus.RIGHT_NULL || aStatus == ChildNullStatus.LEFT_NULL)
                && ((bStatus == ChildNullStatus.BOTH_NULL) || (bStatus == ChildNullStatus.BOTH_NOT_NULL))) ||
                ((bStatus == ChildNullStatus.RIGHT_NULL || bStatus == ChildNullStatus.LEFT_NULL)
                        && ((aStatus == ChildNullStatus.BOTH_NULL) || (aStatus == ChildNullStatus.BOTH_NOT_NULL)))) {
            return false;
        }

        if (aStatus == ChildNullStatus.BOTH_NOT_NULL && bStatus == ChildNullStatus.BOTH_NOT_NULL) {
            boolean retA = flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
            boolean retB = flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
            return (retA || retB);
        }

        if (aStatus == ChildNullStatus.RIGHT_NULL && bStatus == ChildNullStatus.RIGHT_NULL) {
            return flipEquiv(root1.left, root2.left);
        }

        if (aStatus == ChildNullStatus.RIGHT_NULL && bStatus == ChildNullStatus.LEFT_NULL) {
            return flipEquiv(root1.left, root2.right);
        }

        if (aStatus == ChildNullStatus.LEFT_NULL && bStatus == ChildNullStatus.LEFT_NULL) {
            return flipEquiv(root1.right, root2.right);
        }

        if (aStatus == ChildNullStatus.LEFT_NULL && bStatus == ChildNullStatus.RIGHT_NULL) {
            return flipEquiv(root1.right, root2.left);
        }

        return true;
    }

    ;

    public enum ChildNullStatus {
        BOTH_NULL,
        BOTH_NOT_NULL,
        LEFT_NULL,
        RIGHT_NULL
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
