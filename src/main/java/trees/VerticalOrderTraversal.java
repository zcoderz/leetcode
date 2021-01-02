package trees;

import utils.TreeNode;

import java.util.List;
import java.util.TreeMap;

public class VerticalOrderTraversal {
    TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
    public List<List<Integer>> verticalOrder(TreeNode root) {
        processTree(root, 0);
    }

}
