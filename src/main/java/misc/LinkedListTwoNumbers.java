package misc;

public class LinkedListTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode firstNode = null;
        ListNode theNode = null;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int a = l1.val + l2.val + carry;
            carry = a / 10;
            int leftO = a % 10;
            ListNode tempNode = new ListNode(leftO);
            if (theNode == null) {
                theNode = tempNode;
                firstNode = tempNode;
            } else {
                theNode.next = tempNode;
                theNode = tempNode;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        if (l1 != null) {
            theNode.next = l1;
            theNode = l1;
        } else {
            theNode.next = l2;
            theNode = l2;
        }

        while (carry != 0) {
            int a = theNode.val + carry;
            carry = a / 10;
            int leftO = a % 10;
            theNode.val = leftO;
            if (theNode.next == null) {
                ListNode tmpNode = new ListNode(0);
                theNode.next = tmpNode;
                theNode = tmpNode;
            } else {
                theNode = theNode.next;
            }
        }

        return firstNode;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
