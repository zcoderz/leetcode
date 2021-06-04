package face_book.medium;

import utils.Pair;
import utils.TreeNode;

import java.util.Stack;

public class ConstructBinaryTreeFromString {

    public static void main(String [] args) {
        ConstructBinaryTreeFromString construct = new ConstructBinaryTreeFromString();
        //TreeNode node = construct.str2tree("-4(2(3)(1))(6(5)(7))");
        TreeNode node = construct.str2tree("4");
        int j = 1;
    }

    public TreeNode str2tree(String s) {
        if (s.isEmpty()) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();

        int index = 0;

        while (index < s.length()) {
            if ((s.charAt(index) == '-') || (Character.isDigit(s.charAt(index)))) {
                Pair<Integer, Integer> p = parseInteger(s, index);
                index = p.second;
                TreeNode node = new TreeNode(p.first);
                stack.add(node);
            } else if (s.charAt(index) == '(') {
                index++;
            } else if (s.charAt(index) == ')') {
                TreeNode node = stack.pop();
                TreeNode prior = stack.peek();
                if (prior.left == null) {
                    prior.left = node;
                } else {
                    prior.right = node;
                }
                index++;
            }
        }
        return stack.pop();
    }

    Pair<Integer, Integer> parseInteger(String s, Integer index) {
        StringBuilder builder = new StringBuilder();
        while (index < s.length() && ((s.charAt(index) == '-') || (Character.isDigit(s.charAt(index))))) {
            builder.append(s.charAt(index++));
        }
        return new Pair<>(Integer.parseInt(builder.toString()), index);
    }
}
