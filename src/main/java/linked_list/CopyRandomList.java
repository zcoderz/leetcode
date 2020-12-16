package linked_list;

import utils.ListNode;


/**
 * interesting problem. the code asks to create copies of a linked list which contains random pointers as well
 * could do it simply by using a map that points old nodes to their new counterparts and updating the random
 * once construction is done.
 *
 * however, the below code does the work without using extra space by interleaving node copies between original nodes
 * this creates a bit of work to adjust pointers appropriately for random pointers and then resetting original list
 * and new list to point to appropriate next pointers.
 *
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
