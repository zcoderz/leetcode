package linked_list;

import utils.ListNode;

/**
 * 160. Intersection of Two Linked Lists
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * For example, the following two linked lists:
 *
 *
 * begin to intersect at node c1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * Output: Reference of the node with value = 8
 * Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5].
 * There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 *
 */
public class IntersectionTwoLists {

    public static void main(String [] args) {
        ListNode node4 = new ListNode(4);
        ListNode node1 = new ListNode(1);
        ListNode node8 = new ListNode(8);
        ListNode node4b = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node5b = new ListNode(45);
        ListNode node6 = new ListNode(6);

        node4.next = node1; node1.next = node8; node8.next = node4b; node4b.next=node5;
        node5b.next = node6; node6.next = node1;

        ListNode headA = node4;
        ListNode headB = node5b;

        IntersectionTwoLists i = new IntersectionTwoLists();
        ListNode a = i.getIntersectionNode(headA, headB);
        if (null != a) {
            System.out.println(a.val);
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tailA=null;
        ListNode tailB=null;

        ListNode pointer1 = headA;
        ListNode pointer2 = headB;

        boolean pointer1IsA = true;
        boolean pointer2isB = true;

        while ((tailA==null) || (tailB==null) || (tailA == tailB) ) {

            if(pointer1 == pointer2) {
                return pointer1;
            }

            if (pointer1.next != null) {
                pointer1 = pointer1.next;
            } else if (pointer1IsA) {
                tailA = pointer1;
                pointer1 = headB;
                pointer1IsA = false;
            } else {
                pointer1 = headA;
                pointer1IsA = true;
            }

            if (pointer2.next != null) {
                pointer2 = pointer2.next;
            } else if (pointer2isB) {
                tailB = pointer2;
                pointer2 = headA;
                pointer2isB = false;
            } else {
                pointer2 = headB;
                pointer2isB = true;
            }

        }

        return null;
    }

}
