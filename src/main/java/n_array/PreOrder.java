package n_array;

import utils.Node;

import java.util.*;

/**
 * 589. N-ary Tree Preorder Traversal
 * Given an n-ary tree, return the preorder traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 *
 *
 *
 * Follow up:
 *
 * Recursive solution is trivial, could you do it iteratively?
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [1,3,5,6,2,4]
 *
 * IMP-3: Good to practice for tree traversal.
 */
public class PreOrder {

    public List<Integer> preorder(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            list.add(node.val);
            List<Node> nodes = new LinkedList<>(node.children);
            Collections.reverse(nodes); //reverse because you want the left most child to be on top of stack
            stack.addAll(nodes);
        }
        return list;
    }

}
