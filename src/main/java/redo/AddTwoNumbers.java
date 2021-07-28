package redo;

import utils.ListNode;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode origHead=null;
        ListNode currNode=null;

        while (l1 != null || l2 != null) {
            int lVal = l1==null ? 0 : l1.val;
            int rVal = l2==null ? 0 : l2.val;

            int res = lVal + rVal + carry;
            carry = res / 10;
            res = res % 10;
            ListNode newNode = new ListNode(res);
            if (origHead == null) {
                origHead = newNode;
                currNode = newNode;
            } else {
                currNode.next = newNode;
                currNode = newNode;
            }
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (carry != 0) {
            currNode.next = new ListNode(carry);
        }
        return origHead;
    }
}
