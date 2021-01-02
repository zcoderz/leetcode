package trees;

import utils.Pair;
import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    List<List<Integer>> traversalOrder = new ArrayList<>();
    Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return traversalOrder;
        }
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> p = queue.poll();
            TreeNode node = p.first;
            Integer l = p.second;
            List<Integer> list = traversalOrder.get(l);
            if (null == list) {
                list = new ArrayList<>();
            }
            list.add(node.val);
            if (node.left != null) {
                queue.add(new Pair<TreeNode, Integer>(node.left, l + 1));
            }
            if (node.right != null) {
                queue.add(new Pair<TreeNode, Integer>(node.left, l + 1));
            }
        }
        return traversalOrder;
    }

}
