package redo;

import utils.Pair;
import utils.TreeNode;

import java.util.Stack;

public class ConstructBinaryTreeFromString {

    public static void main(String [] args) {
        //String str = "4(2(3)(1))(6(5))";
        String str = "4";
        ConstructBinaryTreeFromString con = new ConstructBinaryTreeFromString();
        TreeNode theNode = con.str2tree(str);
        System.out.println(1);
    }

    public TreeNode str2tree(String s) {
        int index = 0;
        int len = s.length();

        TreeNode head = null;
        Stack<TreeNode> stack = new Stack<> ();


        while (index < len) {
            if (Character.isDigit(s.charAt(index)) || s.charAt(index) == '-') {
                String sV = parseInteger(s, index);
                index += sV.length();
                int v = Integer.parseInt(sV);
                TreeNode node = new TreeNode(v);
                if (!stack.isEmpty()) {
                    TreeNode prior = stack.peek();
                    if (prior.left == null) {
                        prior.left = node;
                    } else {
                        prior.right = node;
                    }
                }
                if (head == null) {
                    head = node;
                }
                stack.push(node);
            } else if (s.charAt(index) == ')') {
                stack.pop(); index++;
            } else if (s.charAt(index) == '(') {
                index++;
            }

        }
        return head;
    }

    String parseInteger(String str, Integer indx) {
        StringBuilder builder = new StringBuilder();
        while(indx < str.length() && (Character.isDigit(str.charAt(indx)) || str.charAt(indx) == '-')) {
            builder.append(str.charAt(indx++));
        }
        return builder.toString();
    }

}
