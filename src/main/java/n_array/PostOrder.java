package n_array;

import utils.Node;

import java.util.*;

/**
 * 590. N-ary Tree Postorder Traversal
 * Given an n-ary tree, return the postorder traversal of its nodes' values.
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
 * Output: [5,6,3,2,4,1]
 *
 * IMP-3: Simple question, good to practice for tree traversal.
 */
public class PostOrder {

    /**
     * this is a beautiful approach. mentally think of adding to the beginning of output list instead of the end
     * similarly add last child on top of stack so that is the next to be processed as it will be added to the beginning
     * of the list....
     *
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            //stack.addAll will make the right most child on top while is what we want
            stack.addAll(n.children);
            //add the newly processed val into the beginning of the list
            list.addFirst(n.val);
        }
        return list;
    }

    /**
     * regular approach, will visit each item twice. need to pop it only when its already been seen (i,e worked on its
     * children)
     *
     * @param root
     * @return
     */
    public List<Integer> postorderUsingHash(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Set<Node> seen = new HashSet<>();
        while (!stack.isEmpty()) {
            Node n = stack.peek();
            if (seen.contains(n)) {
                stack.pop();
                list.add(n.val);
            } else {
                //doing an inplace reversal, better to copy before changing order so that you
                //dont modify the shape of the tree
                Collections.reverse(n.children);
                stack.addAll(n.children);
            }
            seen.add(n);
        }
        return list;
    }
}
