package redo;



public class CopyListWithRandomPointer {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String [] args) {
        Node  n7 = new Node(7);
        Node  n13 = new Node(13);
        Node  n11 = new Node(11);
        Node  n10 = new Node(10);
        Node  n1 = new Node(1);
        n7.next = n13;
        n13.next = n11;
        n13.random = n7;
        n11.next = n10;
        n11.random = n1;
        n10.random = n11;
        n10.next = n1;

        n1.random=n7;
        CopyListWithRandomPointer copy = new CopyListWithRandomPointer();
        Node ret = copy.copyRandomList(n7);
        int j = 2;
    }

    public Node copyRandomList(Node head) {
        Node origHead = head;
        while (head != null) {
            Node newNode = new Node(head.val);
            newNode.next = head.next;
            head.next = newNode;
            head = newNode.next;
        }
        head = origHead;
        while (head != null) {
            if (head.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
        Node retHead = origHead.next;
        head = origHead;
        while (head != null) {
            Node next = head.next;
            if (next.next != null) {
                head.next = next.next;
                head = head.next;
            } else {
                head.next = null;
                head = null;
            }

            if (head != null) {
                next.next = head.next;
            }
        }

        return retHead;
    }
}
