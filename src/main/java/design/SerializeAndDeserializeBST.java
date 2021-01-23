package design;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
