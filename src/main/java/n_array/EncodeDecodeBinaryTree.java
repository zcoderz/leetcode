package n_array;

import utils.Node;
import utils.TreeNode;

import java.util.ArrayList;

/**
 * 431. Encode N-ary Tree to Binary Tree
 * Design an algorithm to encode an N-ary tree into a binary tree and decode the binary tree to get the original N-ary tree.
 * An N-ary tree is a rooted tree in which each node has no more than N children. Similarly,
 * a binary tree is a rooted tree in which each node has no more than 2 children.
 * There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that an N-ary tree can be encoded to a binary tree and this binary tree
 * can be decoded to the original N-nary tree structure.
 *
 * Nary-Tree input serialization is represented in their level order traversal,
 * each group of children is separated by the null value (See following example).
 *
 * For example, you may encode the following 3-ary tree to a binary tree in this way:
 *
 *
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Note that the above is just an example which might or might not work.
 * You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 *
 * conceptually simple, flatten the children in an n-array tree into a list of connected binary tree nodes
 *
 * Leet code has excellent visual explanation of the below code. Read the explanation from leet code.
 *
 * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/solution/
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
        TreeNode next = root.left;
        while (next != null) {
            node.children.add(decode(next));
            next = next.right;
        }
        return node;
    }

}
