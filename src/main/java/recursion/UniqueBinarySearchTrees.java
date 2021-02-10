package recursion;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 95. Unique Binary Search Trees II
 * Given an integer n, return all the structurally unique BST's (binary search trees),
 * which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * Example 2:
 *
 * Input: n = 1
 * Output: [[1]]
 *
 */
public class UniqueBinarySearchTrees {

    public static void main(String []args) {
        UniqueBinarySearchTrees ubst = new UniqueBinarySearchTrees();
        List<TreeNode> nodes = ubst.generateTrees(2);
        System.out.println(nodes.size());
    }


    public List<TreeNode> generateTrees(int n) {
        if (n==0) {
            return new ArrayList<>();
        }
        return generateTreesRec(1, n);
    }

    /**
     * clever idea - number of tree combinations is a cross product of left and right children
     * so recuse for left and right partitions, do a double for loop to process each combination
     * @param start
     * @param end
     * @return
     */
    public List<TreeNode>  generateTreesRec(int start, int end) {
        if (start > end) {
            List<TreeNode> treeNodes = new ArrayList<>();
            treeNodes.add(null);
            return treeNodes;
        }

        List<TreeNode> nodes = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            //partition around i
            //number of tres is cross product of left and right options
            //the below code just enumerates them
            List<TreeNode> leftNodes = generateTreesRec(start, i-1);
            List<TreeNode> rightNodes = generateTreesRec(i+1, end);
            for(TreeNode left : leftNodes) {
                for (TreeNode right : rightNodes) {
                    TreeNode theNode = new TreeNode(i);
                    theNode.left = left;
                    theNode.right = right;
                    nodes.add(theNode);
                }
            }
        }
        return nodes;
    }

}
