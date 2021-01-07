package linked_list;

import utils.ListNode;

/**
 * This problem is not as simple as it seems! Basically the list is circular and it has a turning point
 * where next value is less than prior. The question in leet code doesnt stress the notion of a turning point.
 * Best to ask interviewer of expected scenarios in test data and write out the test cases before solving a problem
 * such as this.
 *
 * Nice problem to practice!
 */
public class InsertIntoASortedCircularLinkedList {

    public static void main(String [] args) {
        ListNode node3 = new ListNode(3);
        ListNode node5 = new ListNode(5);
        ListNode node1 = new ListNode(1);

        node3.next = node5;
        node5.next = node1;
        node1.next = node3;

        InsertIntoASortedCircularLinkedList circList = new InsertIntoASortedCircularLinkedList();
        ListNode modList = circList.insert(node3, 0);
        circList.printList(modList);

        modList = circList.insert(node3, 15);
        circList.printList(modList);

        modList = circList.insert(node3, 7);
        circList.printList(modList);

        modList = circList.insert(node3, 2);
        circList.printList(modList);
    }

    public void printList(ListNode node) {
        ListNode start = node;
        while (node.next != start) {
            System.out.print(node.val + "," );
            node = node.next;
        }
        System.out.println(node.val);
    }

    /**
     * these conditions look real simple but thinking through them takes time.
     * best to write this out via test cases and expected behavior and then write conditions
     * in simplest possible form to cater to the tests
     * @param head
     * @param insertVal
     * @return
     */
    public ListNode insert(ListNode head, int insertVal) {
        if (head == null) {
            ListNode ListNode = new ListNode(insertVal);
            ListNode.next = ListNode;
            return ListNode;
        } else {
            ListNode prior = head;
            ListNode curr = head.next;
            while (curr != head &&
                    //this checks for a simple case where new val is greater than prior but less
                    //the current
                    (!(prior.val <= insertVal && curr.val >= insertVal))) {
                // this condition checks for whether this is the turning point in the list
                if (curr.val < prior.val
                // if turning point and insert value is greater than previous or less than curr
                // the data needs to be inserted here
                        && (insertVal >= prior.val || (insertVal <= curr.val))) {
                    break;
                }
                prior = curr;
                curr = curr.next;
            }
            insert(prior, curr, insertVal);
            return head;
        }
    }

    public void insert(ListNode endListNode, ListNode curr, int insertVal) {
        ListNode ListNode = new ListNode(insertVal);
        endListNode.next = ListNode;
        ListNode.next = curr;
    }

}
