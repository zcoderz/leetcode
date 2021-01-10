package utils;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int val;
    public String name;
    public List<Node> children;

    public Node() {}

    public Node(String name) {
        this.name = name;
        children = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        children = new ArrayList<>();
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}