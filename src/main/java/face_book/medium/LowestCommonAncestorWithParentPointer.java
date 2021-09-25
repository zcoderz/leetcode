package face_book.medium;


import java.util.HashSet;
import java.util.Set;

/**
 * 1650. Lowest Common Ancestor of a Binary Tree III
 * Medium
 *
 * 397
 *
 * 14
 *
 * Add to List
 *
 * Share
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 *
 * Each node will have a reference to its parent node. The definition for Node is below:
 *
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 * According to the definition of LCA on Wikipedia:
 * "The lowest common ancestor of two nodes p and q in a tree T is the lowest
 * node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 */
public class LowestCommonAncestorWithParentPointer {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };

    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = p.parent;
        }

        while (q != null) {
            if (set.contains(q)) {
                return q;
            }
            q = q.parent;
        }
        return null;
    }
}
