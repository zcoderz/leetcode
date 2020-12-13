package trees;

import java.util.*;

public class ZigZagLevelOrderTraversal {

    public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    };

    public static enum Direction {
        GO_LEFT, GO_RIGHT
    };

    public static class TreeNodeDirection {
        TreeNode node;
        Direction direction;
        int level;

        public TreeNodeDirection(TreeNode node, Direction direction, int level) {
            this.node = node;
            this.direction = direction;
            this.level = level;
        }
    }

    public static class ListTreeNodeDir {
        Direction direction;
        LinkedList<TreeNodeDirection> directions = new LinkedList<>();
    }

    public static void main(String [] args) {
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode (7);
        TreeNode n20 = new TreeNode(20);
        TreeNode n9 = new TreeNode(9);
        TreeNode n3 = new TreeNode(3);

        n3.left = n9;
        n3.right = n20;
        n20.left = n15;
        n20.right = n7;

        ZigZagLevelOrderTraversal lO = new ZigZagLevelOrderTraversal();
        lO.zigzagLevelOrder(n3);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            Map<Integer, ListTreeNodeDir> mapNodes = new HashMap<>();
            int prevLevel = 1;
            List<List<Integer>> nodeList = new ArrayList<>();
            ListTreeNodeDir n = new ListTreeNodeDir(); n.direction=Direction.GO_LEFT;
            n.directions.add(new TreeNodeDirection(root, Direction.GO_LEFT, prevLevel));
            mapNodes.put(prevLevel, n);
            while (!mapNodes.isEmpty()) {
                ListTreeNodeDir listTreeNodeDirToProcess = mapNodes.get(prevLevel);
                ListTreeNodeDir listTreeNodeDir = new ListTreeNodeDir();
                if (listTreeNodeDirToProcess.direction == Direction.GO_LEFT) {
                    listTreeNodeDir.direction = Direction.GO_RIGHT;
                } else {
                    listTreeNodeDir.direction = Direction.GO_LEFT;
                }
                addToNodeList(nodeList , listTreeNodeDirToProcess.directions, prevLevel);

                LinkedList<TreeNodeDirection> revDirections = new LinkedList<>();
                while (!listTreeNodeDirToProcess.directions.isEmpty()) {
                    revDirections.add(listTreeNodeDirToProcess.directions.removeLast());
                }

                while(!revDirections.isEmpty()) {
                    TreeNodeDirection nodeDirection = revDirections.removeFirst();

                    TreeNode node = nodeDirection.node;
                    if(nodeDirection.direction == Direction.GO_LEFT) {
                        if (node.right != null) {
                            TreeNodeDirection nodeD = new TreeNodeDirection(node.right, Direction.GO_RIGHT, prevLevel+1);
                            listTreeNodeDir.directions.add(nodeD);
                        }
                        if (node.left != null) {
                            TreeNodeDirection nodeD = new TreeNodeDirection(node.left, Direction.GO_RIGHT, prevLevel+1);
                            listTreeNodeDir.directions.add(nodeD);
                        }

                    } else {
                        if (node.left != null) {
                            TreeNodeDirection nodeD = new TreeNodeDirection(node.left, Direction.GO_LEFT, prevLevel+1);
                            listTreeNodeDir.directions.add(nodeD);
                        }
                        if (node.right != null) {
                            TreeNodeDirection nodeD = new TreeNodeDirection(node.right, Direction.GO_LEFT, prevLevel+1);
                            listTreeNodeDir.directions.add(nodeD);
                        }
                    }
                }
                mapNodes.remove(prevLevel);
                prevLevel++;
                if (!listTreeNodeDir.directions.isEmpty()) {
                    mapNodes.put(prevLevel, listTreeNodeDir);
                }
            }
            return nodeList;
    }

    void addToNodeList(List<List<Integer>> nodeList , LinkedList<TreeNodeDirection> directions, int level ) {

        if (nodeList.size() < level) {
            nodeList.add(new ArrayList<>());
        }
        List<Integer> nodeInfo = nodeList.get(level-1);
        for (TreeNodeDirection dir : directions) {
            nodeInfo.add(dir.node.val);
        }

    }

}
