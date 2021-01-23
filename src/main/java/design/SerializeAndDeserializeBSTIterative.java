package design;

import java.util.*;

public class SerializeAndDeserializeBSTIterative {

    private static String nullStr = new String("null");

    public static void main(String[] args) {
        SerializeAndDeserializeBSTIterative sBst = new SerializeAndDeserializeBSTIterative();
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
        List<String> stringList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node != null) {
                queue.add(node.left);
                queue.add(node.right);
            }
            if (node == null) {
                stringList.add(nullStr);
            } else {
                stringList.add(Integer.toString(node.val));
            }
        }
        return stringList.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        data = data.substring(1, data.length() - 1);

        StringTokenizer tokenizer = new StringTokenizer(data, ",");
        ArrayList<String> elements = new ArrayList<>();
        while (tokenizer.hasMoreElements()) {
            String strVal = tokenizer.nextToken();
            elements.add(strVal);
        }
        int index = 0;
        TreeNode root = null;
        int childIndex = 1;
        Map<Integer, TreeNode> tMap = new HashMap<>();


        while (index < elements.size()) {

            String val = elements.get(index++);
            val = val.replaceAll("\\s", "");


            if (val.compareTo(nullStr) != 0) {
                TreeNode curr = tMap.get(index - 1);

                int iVal = Integer.parseInt(val);
                if (curr == null) {
                    curr = new TreeNode(iVal);
                }

                if (root == null) {
                    root = curr;
                }

                String lChild = elements.get(childIndex++);
                lChild = lChild.replaceAll("\\s", "");
                if (lChild.compareTo(nullStr) != 0) {
                    iVal = Integer.parseInt(lChild);
                    TreeNode l = new TreeNode(iVal);
                    curr.left = l;
                    tMap.put(childIndex - 1, l);
                }

                String rChild = elements.get(childIndex++);
                rChild = rChild.replaceAll("\\s", "");
                if (rChild.compareTo(nullStr) != 0) {
                    iVal = Integer.parseInt(rChild);
                    TreeNode r = new TreeNode(iVal);
                    curr.right = r;
                    tMap.put(childIndex - 1, r);
                }

            }
        }
        return root;
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
