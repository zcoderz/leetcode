package utils.graph;

public class Vertex {
    int parent;
    int rank;
    int id;

    public Vertex(int id, int parent, int rank) {
        this.parent = parent;
        this.rank = rank;
        this.id = id;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getParent() {
        return parent;
    }

    public int getRank() {
        return rank;
    }

    public int getId() {
        return id;
    }
}