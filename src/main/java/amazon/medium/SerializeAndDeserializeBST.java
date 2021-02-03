package amazon.medium;

import utils.Pair;
import utils.TreeNode;

/**
 * 449. Serialize and Deserialize BST
 * Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a
 * file or memory buffer, or transmitted across a network connection link to
 * be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You need to ensure that a binary search tree can be serialized to a string, and this string
 * can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 * IMP-2 -- nice to practice serializing and deserializing data structures
 *
 * Example 1:
 *
 * Input: root = [2,1,3]
 * Output: [2,1,3]
 *
 */
public class SerializeAndDeserializeBST {

    private static String nullDelim = "null,";
    private static int nullDelimLen = nullDelim.length();

    public static void main(String[] args) {
        TreeNode nodeA = new TreeNode(1);
        TreeNode nodeB = new TreeNode(2);
        TreeNode nodeC = new TreeNode(3);
        TreeNode nodeD = new TreeNode(4);
        nodeA.left = nodeB;
        nodeA.right = nodeC;
        nodeC.right = nodeD;

        SerializeAndDeserializeBST serial = new SerializeAndDeserializeBST();
        String str = serial.serialize(nodeA);
        System.out.println(str);
        TreeNode node = serial.deserialize(str);
        int j = 0;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder string = new StringBuilder();
        traverseAndWrite(root, string);
        return string.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Pair<TreeNode, Integer> node = traverseAndRead(data, 0);
        return node.first;
    }

    private Pair<TreeNode, Integer> traverseAndRead(String string, int index) {
        if (index >= string.length()) {
            return Pair.of(null, index);
        }
        if (Character.isDigit(string.charAt(index))) {
            StringBuilder number = new StringBuilder();
            while (Character.isDigit(string.charAt(index))) {
                number.append(string.charAt(index++));
            }
            index++;
            int num = Integer.parseInt(number.toString());
            TreeNode node = new TreeNode(num);
            Pair<TreeNode, Integer> left = traverseAndRead(string, index);
            Pair<TreeNode, Integer> right;
            index = left.second;
            right = traverseAndRead(string, index);
            node.left = left.first;
            node.right = right.first;
            index = right.second;
            return Pair.of(node, index);
        } else {
            return Pair.of(null, index + nullDelimLen);
        }

    }

    private void traverseAndWrite(TreeNode node, StringBuilder builder) {
        if (node == null) {
            builder.append(nullDelim);
        } else {
            builder.append(node.val).append(",");
            traverseAndWrite(node.left, builder);
            traverseAndWrite(node.right, builder);
        }
    }
}
