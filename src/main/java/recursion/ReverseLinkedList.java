package recursion;

import utils.ListNode;

public class ReverseLinkedList {

    /**
     * writing the code simply in below lines is a bit tricky!
     * pay special attention to line 20 below...head.next = null.
     * head.next.next = head; , this is a simple swap
     * @param head
     * @return
     */

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head; //terminating condition
        ListNode fh = reverseList(head.next); //recurse to end
        head.next.next = head; //swap pointers
        //this is done so that the first node ends up pointing to null.
        //for other items , their next will be corrected by the above line in recursion stack
        head.next = null;
        return fh;
    }

}
