package face_book.hard;

import utils.ListNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 23. Merge k Sorted Lists
 * Hard
 *
 * 8543
 *
 * 382
 *
 * Add to List
 *
 * Share
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 *
 * IMP-1
 * TODO: Add to website. The interval advancement for divide and conquer is very interesting!
 */
public class MergeKSortedLists {

    public static void main(String [] args) {
        MergeKSortedLists merge = new MergeKSortedLists();
        ListNode nodeOne = new ListNode(1);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        nodeOne.next= node4; node4.next = node5;

        ListNode nodeTwo = new ListNode(2);
        ListNode nodeSix = new ListNode(6);
        nodeTwo.next = nodeSix;

        ListNode nodeOneA = new ListNode(1);
        ListNode node4B = new ListNode(4);
        ListNode nodeSeven = new ListNode(7);
        nodeOneA.next = node4B;
        node4B.next = nodeSeven;


        ListNode [] lists = {nodeOne, nodeTwo, nodeOneA, nodeSeven, nodeSix};

        ListNode res = merge.mergeKLists(lists);
        int j = 1;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode h = new ListNode(0);
        ListNode ans=h;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                h.next = l1;
                h = h.next;
                l1 = l1.next;
            } else {
                h.next = l2;
                h = h.next;
                l2 = l2.next;
            }
        }
        if(l1==null){
            h.next=l2;
        }
        if(l2==null){
            h.next=l1;
        }
        return ans.next;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        int interval = 1;
        while(interval<lists.length){
            System.out.println(lists.length);
            int i = 0;
            for (; i + interval< lists.length; i=i+interval*2) {
                lists[i]=mergeTwoLists(lists[i],lists[i+interval]);
            }
            interval*=2;
        }

        return lists[0];
    }

    public ListNode mergeKListsUQ(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        while (lists.length != 1) {
            int sz = lists.length % 2 ==0? lists.length/2 : lists.length/2 + 1;
            ListNode [] scaledDown = new ListNode[sz];
            for (int i =0, index=0; i < lists.length; i += 2, index++) {
                ListNode head = null;
                ListNode curr = null;
                while (lists[i] != null || (i+1 < lists.length && lists[i+1] != null)) {
                    Integer nextIndex = i == lists.length-1 ? null: i+1;
                    int minIndex =0;
                    if (lists[i] != null && (nextIndex != null && lists[nextIndex] != null)) {
                        if (lists[i].val < lists[nextIndex].val) {
                            minIndex = i;
                        } else {
                            minIndex = nextIndex;
                        }
                    } else if (lists[i] != null) {
                        minIndex = i;
                    } else if (nextIndex != null) {
                        minIndex = nextIndex;
                    }
                    if (curr == null) {
                        curr = head =  lists[minIndex];
                    } else {
                        curr.next = lists[minIndex];
                        curr = curr.next;
                    }
                    lists[minIndex] = lists[minIndex].next;
                }
                scaledDown[index] = head;
            }
            lists = scaledDown;
        }
        return lists[0];
    }

    public ListNode mergeKListsSlow(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode head = null;
        ListNode curr = null;
        Set<Integer> possibles = new HashSet<>();
        for (int i =0 ; i < lists.length; i++) {
            possibles.add(i);
        }
        boolean end = false;
        while (!end) {
            int minVal = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int i : possibles) {
                if (lists[i] != null && lists[i].val <  minVal) {
                    minIndex = i;
                    minVal = lists[i].val;
                }
            }
            if (minIndex != -1) {
                ListNode newNode = new ListNode(lists[minIndex].val);
                if (curr != null) {
                    curr.next = newNode;
                } else {
                    head =  newNode;
                }
                curr = newNode;
                ListNode next = lists[minIndex].next;
                lists[minIndex] = next;
                if (next == null) {
                    possibles.remove(minIndex);
                }
            } else {
                end = true;
            }
        }
        return head;
    }
}
