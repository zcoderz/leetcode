package recursion;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
