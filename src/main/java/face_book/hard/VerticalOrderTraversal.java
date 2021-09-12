package face_book.hard;

import utils.TreeNode;

import java.util.*;

public class VerticalOrderTraversal {

    public static void main(String [] args) {
        TreeNode node = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node0 = new TreeNode(0);
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2b = new TreeNode(0);
        node.left = node1;
        node1.left = node0;
        node1.right = node2;
        node.right = node4;
        node4.left = node2b;

        VerticalOrderTraversal vert = new VerticalOrderTraversal();
        List<List<Integer>> theList = vert.verticalTraversal(node);
        int j =1;
    }

    Map<Integer, List<Long>> map = new TreeMap<>();
    long  mask = (1L << 32) -1;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        process(root, 0, 0);
        List<List<Integer>> retVal = new ArrayList<>();
        for (List<Long> list : map.values()) {
            list.sort( (Long a, Long b) ->   {
              long rowA = a >> 32;
              long rowB = b >> 32;
              if (rowA > rowB) {
                  return 1;
              } else if (rowB > rowA) {
                  return -1;
              } else {
                  long valA = mask & a;
                  long valB = mask & b;
                  return Long.compare(valA, valB);
              }
            });
            List<Integer> retList = new ArrayList<>();
            for (long v: list) {
                retList.add((int)(mask & v));
            }
            retVal.add(retList);
        }
        return retVal;
    }


    void process(TreeNode node, int colIndex, int rowIndex) {
        if (node == null) return;
        process(node.left, colIndex-1, rowIndex + 1);
        List<Long> list = map.getOrDefault(colIndex, new ArrayList<>());
        long val = (long) rowIndex << 32;
        val += node.val;
        list.add(val);
        map.put(colIndex, list);
        process(node.right, colIndex+1, rowIndex + 1);
    }
}
