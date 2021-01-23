package n_array;

import utils.Node;

import java.util.*;

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
