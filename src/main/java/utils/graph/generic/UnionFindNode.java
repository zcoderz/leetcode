package utils.graph.generic;


/**
 * Got the base code from github and modified a bit code is similar to utils.graph.UnionFind however is generic and thus
 * can be used for data type such as strings
 *
 * @param <T>
 */
public class UnionFindNode<T> {

    private T value;
    /* This structure can contain heterogeneous template types
     *  since no operation is performed between the nodes values themselves.
     */
    private UnionFindNode<T> parent;
    private int rank;

    public UnionFindNode(T value) {
        this.value = value;
        this.parent = this; // Make it its own set
        this.rank = 0;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        // Two nodes a and b are equal iif a.value.equals(b.value)
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnionFindNode<T> that = (UnionFindNode<T>) o;
        return value.equals(that.value);
    }

    @Override
    public String toString() {
        if (parent != this) return "[" + value + "]->" + parent;
        return "[" + value + "](root)";
    }

    /***
     * Finds the equivalence class of this node.
     * For all nodes a and b of the same tree, find(a) = find(b)
     *
     * collapse parents, so next find call is faster
     * @return the root node which uniquely identify this tree.
     */
    public UnionFindNode<T> find() {
        UnionFindNode<T> node = this;
        if (node != parent) {
            node.parent = node.parent.find();
        }
        return parent;
    }

    /***
     * Joins two DisjointSetNodes together assuming
     * those are the heads of each DisjointSet you want.
     * Example: if you join other=B and this=3 in structures
     * A->B->C->D and 1->2->3->4, then you'll have
     * A->B->3->4 and 1->2->3->4 but C->D
     * So now find(A) = find(B) = 4 ≠ find(C) = D
     * @param other the subtree you want to join to this tree.
     */
    public void join(UnionFindNode<T> other) {
        if (other == null) throw new NullPointerException();
        other.parent = this;
    }

    /***
     * Joins two DisjointSetNodes together such that
     * for all node a and b in this and in other,
     * find(a) = find(b).
     * This is not the same as join, c.f. join documentation.
     * @param other the tree you want to join to this tree.
     */
    public void union(UnionFindNode<T> other) {
        UnionFindNode<T> p = this.find();
        UnionFindNode<T> q = other.find();
        if (p.equals(q)) return;
        if (p.rank < q.rank) {
            // Swaps like p,q = q,p would
            UnionFindNode<T> temp = p;
            p = q;
            q = temp;
        }
        // Always join a small rank (q) to a big rank (p)
        // since rank(p.join(q)) = | p.rank     if p.rank > q.rank
        //                         | p.rank+1   else
        p.join(q);
        if (p.rank == q.rank) p.rank++;
    }

}
