package utils;

public class ListNode {
    public int key;
    public int val;
    public ListNode next;
    public ListNode prior;
    public ListNode random;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public ListNode getRandom() {
        return random;
    }

    public void setRandom(ListNode random) {
        this.random = random;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public ListNode getPrior() {
        return prior;
    }

    public void setPrior(ListNode prior) {
        this.prior = prior;
    }
}