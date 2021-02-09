package recursion;

import utils.ListNode;

/**
 *
 * 21. Merge Two Sorted Lists
 *
 * Merge two sorted linked lists and return it as a sorted list.
 * The list should be made by splicing together the nodes of the first two lists.
 *
 * Example 1:
 *
 *
 * Input: l1 = [1,2,4], l2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 *
 * Simple question
 * don't skip the easy questions.
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
