package face_book.medium;

import utils.ListNode;

/**
 * 708. Insert into a Sorted Circular Linked List
 *
 * Given a node from a Circular Linked List which is sorted in ascending order,
 * write a function to insert a value insertVal into the list such that it remains a sorted circular list.
 * The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the circular list.
 *
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value.
 * After the insertion, the circular list should remain sorted.
 *
 * If the list is empty (i.e., given node is null), you should create a new single circular list and
 * return the reference to that single node. Otherwise, you should return the original given node.
 *
 * This problem is not as simple as it seems! Basically the list is circular and it has a turning point
 * where next value is less than prior. The question in leet code doesnt stress the notion of a turning point.
 * Best to ask interviewer of expected scenarios in test data and write out the test cases before solving a problem
 * such as this.
 *
 * IMP-2: Nice problem to practice!
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
            while (curr != head ) {
                if (prior.val <= insertVal && curr.val >= insertVal) {
                    //this checks for a simple case where new val is greater than prior but less
                    //the current
                    break;
                }
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
