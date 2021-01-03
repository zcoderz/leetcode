package utils.graph;

public class Edge {
    int src, dest, cost;

    public Edge() {
    }

    public Edge(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }

    public Edge(int src, int dest, int cost) {
        this.src = src;
        this.dest = dest;
        this.cost = cost;
    }

    public int getSrc() {
        return src;
    }

    public int getDest() {
        return dest;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }
}
