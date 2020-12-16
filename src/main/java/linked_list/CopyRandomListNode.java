package linked_list;

import java.util.*;

public class CopyRandomListNode {

    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    };

    Map<RandomListNode, RandomListNode> oldToNew = new HashMap<RandomListNode, RandomListNode>();
    Set<RandomListNode> visitedNodes = new HashSet<RandomListNode>();


    public RandomListNode copyRandomList(RandomListNode node) {
        if (null == node) {
            return null;
        }
        if(visitedNodes.contains(node)) {
            return oldToNew.get(node);
        }
        visitedNodes.add(node);
        RandomListNode theNode = oldToNew.get(node);
        if (null == theNode) {
            theNode  = copyNode(node);
            oldToNew.put(node, theNode);
        }
        theNode.next = copyRandomList(node.next);
        theNode.random = copyRandomList(node.random);
        return theNode;
    }

    private RandomListNode copyNode(RandomListNode node) {
        RandomListNode theNode = new RandomListNode(node.label);
        return theNode;
    }
}
