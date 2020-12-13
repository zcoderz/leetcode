package recursion;

import utils.ListNode;

/**
 * mastering recursion needs a lot of practice!!!
 * dont skip the easy questions.
 *
 */
public class MergeTwoSortedLists {

    public static void main (String [] args) {
        ListNode l1A = new ListNode(1), l1B = new ListNode(2), l1C = new ListNode(4) ;
        ListNode l2A = new ListNode(1), l2B = new ListNode(3), l2C = new ListNode(4) ;
        l1A.next=l1B; l1B.next=l1C;
        l2A.next=l2B; l2B.next =l2C;

        MergeTwoSortedLists msL = new MergeTwoSortedLists();
        ListNode ret = msL.mergeTwoLists(l1A, l2A);
        while (ret != null) {
            System.out.println(ret.val);
            ret = ret.next;
        }

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
//      below is an interesting case to skip over duplicate values.
//         else if (l2.val == l1.val) {
//            l1.next = mergeTwoLists(l1.next, l2.next);
//            return l1;
        } else if (l1.val > l2.val) {
            //important to recognize that equal can be treat as below.....this one is tricky!
            //i started to write a slightly more complicated case for equal but realized equal is same as below
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        } else {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
    }
}
