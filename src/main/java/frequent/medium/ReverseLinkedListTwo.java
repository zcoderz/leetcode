package frequent.medium;

import utils.ListNode;

import java.util.Stack;

/**
 * 92. Reverse Linked List II
 * Reverse a linked list from position m to n. Do it in one-pass.
 *
 * Note: 1 ≤ m ≤ n ≤ length of list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 *
 * IMP-3: Common question but simple
 */
public class ReverseLinkedListTwo {

    public static void main(String [] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2); node1.next = node2;
//        ListNode node3 = new ListNode(3); node2.next = node3;
//        ListNode node4 = new ListNode(4); node3.next = node4;
//        ListNode node5 = new ListNode(5); node4.next = node5;

        ReverseLinkedListTwo rv = new ReverseLinkedListTwo();
        //ListNode ret = rv.reverseBetween(node1, 2, 4);
        ListNode ret = rv.reverseBetween(node1, 1, 2);
        while (ret != null) {
            System.out.println(ret.val); ret = ret.next;
        }
    }

    /**
     * some pointer arithmetic but relatively a simple problem solved via leveraging stack for reversal
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head.next == null) return head;
        m--; //the index of first element is 1 , hence decrement as we are starting first from 0
        int i =0;

        //progress the pointers forward
        ListNode origHead = head;
        ListNode priorEnd = null;
        for (; i < m && head != null; i++) {
            priorEnd = head;
            head = head.next;
        }
        int noToReverse = n-m;

        //add to stack the number of elements needed to be reversed
        Stack<ListNode> stack = new Stack<>();
        for (i=0; i < noToReverse && head != null; i++) {
            stack.add(head);
            head = head.next;
        }
        //keep track of what was next
        ListNode next = head;

        //adjust pointers by popping last from stack
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            if (priorEnd == null) {
                origHead = node; //if you starting from the first element in the list priorEnd is null
            } else {
                priorEnd.next = node;
            }
            priorEnd = node;
        }
        //adjust prior end to next
        if (priorEnd != null) {
            priorEnd.next = next;
        }
        return origHead;
    }
}
