package recursion;

import utils.ListNode;

/**
 * 24. Swap Nodes in Pairs
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 *
 * Input: head = []
 * Output: []
 *
 */
public class SwapNodes {

    /**
     * recursively swap in pairs
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        } else if (head.next == null) {
            return head;
        }
        ListNode n = swapPairs(head.next.next);
        ListNode b = head.next;
        b.next = head;
        head.next = n;
        return b;
    }
}
