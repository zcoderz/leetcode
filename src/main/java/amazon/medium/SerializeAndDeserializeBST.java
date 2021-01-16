package amazon.medium;

import utils.Pair;
import utils.TreeNode;

public class SerializeAndDeserializeBST {

    public static void main(String [] args) {
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


    private static String nullDelim = "null,";
    private static int nullDelimLen = nullDelim.length();

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
        if(Character.isDigit(string.charAt(index))) {
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
            return Pair.of(null, index+ nullDelimLen);
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
