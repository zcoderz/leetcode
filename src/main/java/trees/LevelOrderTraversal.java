package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {


    class Solution {
        class Pair<T, U> {
            T fst;
            U snd;
            Pair(T a, U b) {
                this.fst = a;
                this.snd = b;
            }

            public int hashCode() {
                return fst.hashCode() + 7 * snd.hashCode();
            }

            public boolean equals(Object pP) {
                Pair p = (Pair) pP;
                return (p.snd == this.snd) && (p.fst == fst);
            }
        }
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }


        List<List<Integer>> traversalOrder = new ArrayList<>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();

        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) { return traversalOrder; }
            queue.add(new Pair<>(root, 0));
            while (!queue.isEmpty()) {
                Pair<TreeNode, Integer> p = queue.poll();
                TreeNode node = p.fst;
                Integer l = p.snd;
                List<Integer> list = traversalOrder.get(l);
                if (null == list) {
                    list = new ArrayList<>();
                }
                list.add(node.val);
                if (node.left != null) {
                    queue.add(new Pair<TreeNode, Integer>(node.left, l+1));
                }
                if (node.right != null) {
                    queue.add(new Pair<TreeNode, Integer>(node.left, l+1));
                }
            }
            return traversalOrder;
        }
    }
}
