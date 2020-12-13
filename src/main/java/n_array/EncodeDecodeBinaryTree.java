package n_array;

import utils.Node;
import utils.TreeNode;

import java.util.ArrayList;

/**
 * conceptually simple, flatten the children in an n-array tree
 * into a list of connected binary tree nodes
 */
public class EncodeDecodeBinaryTree {


    public TreeNode encode(Node root) {
        if (root == null) return null;
        TreeNode node = new TreeNode(root.val);
        if (!root.children.isEmpty()) {
            Node firstChild = root.children.get(0); //make the first child the left node
            if (firstChild != null) {
                node.left = encode(firstChild); //recurse
            }
            TreeNode prior = node.left; //make the other children right nodes of the first left node
            for (int i = 1; i < root.children.size(); i++) {
                prior.right = encode(root.children.get(i)); //recurse for each node
                prior = prior.right;
            }
        }
        return node;
    }

    //Decodes your binary tree to an n-ary tree.
    //undo the above. 
    public Node decode(TreeNode root) {
        if (root == null) return null;
        Node node = new Node(root.val, new ArrayList<>());
        TreeNode next =  root.left;
        while (next != null) {
            node.children.add(decode(next));
            next = next.right;
        }
        return node;
    }

}
