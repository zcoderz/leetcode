package hash_maps;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateSubTrees {
    Map<String, Integer> map = new HashMap<>();
    List<TreeNode> duplicates = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        processTree(root);
        return duplicates;
    }

    String processTree(TreeNode node) {
        if (node == null) {
            return "";
        }

        String left = processTree(node.left);
        String right = processTree(node.right);

        //the unique way to identify a node is via below signature (current, L, R)
        //if you do (L, curr, R), then if left and right are symmetric they will return same value even though they are
        //different
        //for example with L, curr, R the below two trees will return same value
        //         0              0
        //       0                  0
        //
        String strVal = node.val +  left + "," + right;

        Integer count = map.getOrDefault(strVal, 0);
        count++;

        if (count == 2) {
            duplicates.add(node);
        }
        map.put(strVal, count);

        return strVal;
    }
}
