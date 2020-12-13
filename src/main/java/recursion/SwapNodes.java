package recursion;

import utils.ListNode;

public class SwapNodes {


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
