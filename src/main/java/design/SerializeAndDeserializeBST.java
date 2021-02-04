package design;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 449. Serialize and Deserialize BST
 * Serialization is converting a data structure or object into a sequence of bits so
 * that it can be stored in a file or memory buffer, or transmitted across a network
 * connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You need to ensure that a binary search tree can be serialized to a string,
 * and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [2,1,3]
 * Output: [2,1,3]
 *
 * IMP-1 : common question
 *
 */
public class SerializeAndDeserializeBST {
    private static String nullStr = new String("null");

    public static void main(String[] args) {
        SerializeAndDeserializeBST sBst = new SerializeAndDeserializeBST();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        String str = sBst.serialize(node1);
        System.out.println(str);
        TreeNode rNode = sBst.deserialize(str);
        int j = 1;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String str;
        if (root != null) {
            str = String.valueOf(root.val);
        } else {
            return nullStr;
        }

        String strLeft = serialize(root.left);
        String strRight = serialize(root.right);

        str = str + "," + strLeft + "," + strRight;
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arrData = data.split(",");
        List<String> lData = new LinkedList<>(Arrays.asList(arrData));
        TreeNode node = recurseDeserialize(lData);
        return node;
    }

    private TreeNode recurseDeserialize(List<String> lData) {
        String strVal = lData.get(0);
        lData.remove(0);
        if (strVal.compareTo(nullStr) == 0) {
            return null;
        } else {
            int ival = Integer.valueOf(strVal);
            TreeNode theNode = new TreeNode(ival);
            theNode.left = recurseDeserialize(lData);
            theNode.right = recurseDeserialize(lData);
            return theNode;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
