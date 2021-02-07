package linked_list;

import utils.ListNode;


/**
 * 138. Copy List with Random Pointer
 *
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 *
 * Example 1:
 *
 *
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * interesting problem. the code asks to create copies of a linked list which contains random pointers as well
 * could do it simply by using a map that points old nodes to their new counterparts and updating the random
 * once construction is done.
 *
 * however, the below code does the work without using extra space by interleaving node copies between original nodes
 * this creates a bit of work to adjust pointers appropriately for random pointers and then resetting original list
 * and new list to point to appropriate next pointers.
 *
 * IMP-1: A very clever idea to a common question
 */
public class CopyRandomList {

    public ListNode copyRandomList(ListNode head) {
        if (head == null) return null;
        ListNode origHead = head;
        while (head != null) {
            ListNode tempCopy = copyNode (head);
            head.setNext(tempCopy);
            head = tempCopy.getNext();
        }
        setRandomPointers(origHead);
        ListNode newHead = origHead.next;
        clearOrigNodes(origHead);
        return newHead;
    }

    void clearOrigNodes(ListNode node) {
        while (node != null && node.next != null) {
            ListNode copyNode = node.next;
            node.next = node.next.next;
            if(copyNode.next != null) {
                copyNode.next = copyNode.next.next;
            }
            node = node.next;
        }
    }

    void setRandomPointers(ListNode head) {
        while(head != null) {
            head.next.setRandom(head.getRandom().next);
            head = head.next.next;
        }
    }

    public ListNode copyNode(ListNode node) {
        ListNode nodeCopy = new ListNode();
        nodeCopy.setVal(node.val);
        nodeCopy.setNext(node.getNext());
        return nodeCopy;
    }
}
