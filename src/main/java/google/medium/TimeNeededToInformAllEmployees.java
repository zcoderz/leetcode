package google.medium;

import utils.Node;


/**
 * 1376. Time Needed to Inform All Employees
 * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company has is
 * the one with headID.
 *
 * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of
 * the i-th employee, manager[headID] = -1. Also it's guaranteed that the subordination relationships have a tree structure.
 *
 * The head of the company wants to inform all the employees of the company of an urgent piece of news. He will
 * inform his direct subordinates and they will inform their subordinates and so on until
 * all employees know about the urgent news.
 *
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e After informTime[i]
 * minutes, all his direct subordinates can start spreading the news).
 *
 * Return the number of minutes needed to inform all the employees about the urgent news.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, headID = 0, manager = [-1], informTime = [0]
 * Output: 0
 * Explanation: The head of the company is the only employee in the company.
 *
 * IMP-3: Simple and fun question.
 *
 */
public class TimeNeededToInformAllEmployees {

    public static void main(String [] args) {
        TimeNeededToInformAllEmployees time = new TimeNeededToInformAllEmployees();
        int n = 7, headID = 6;
        int [] manager = {1,2,3,4,5,6,-1};
        int [] informTime = {0,6,5,4,3,2,1};
        int theTime = time.numOfMinutes(n, headID, manager, informTime);
        System.out.println(theTime);
    }

    int maxTime = 0;

    /**
     * easy problem. transform to a graph with nodes containing inform time.
     * traverse the graph while tracking time. total time is time to the deepest node.
     * @param n
     * @param headID
     * @param manager
     * @param informTime
     * @return
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {

        Node root = null;
        Node [] nodes = new Node[manager.length];
        for (int i =0; i < nodes.length; i++) {
            Node node = nodes[i];
            if (node == null) {
                node = new Node(i);
                node.var = informTime[i];
                nodes[i] = node;
            }
            int managerId = manager[i];
            Node managerNode;
            if (managerId != -1) {
                managerNode = nodes[managerId];
                if (managerNode == null) {
                    managerNode = new Node(managerId);
                    managerNode.var = informTime[managerId];
                    nodes[managerId] = managerNode;
                }
                managerNode.children.add(node);
            } else {
                root = node;
            }
        }

        if (root != null) {
            findTime(root, 0);
        }
        return maxTime;
    }

    /**
     * simple graph traversal
     * @param node
     * @param timeSoFar
     */
    void findTime(Node node, int timeSoFar) {
        timeSoFar += node.var;
        maxTime = Math.max(timeSoFar, maxTime);
        for(Node child : node.children) {
            findTime(child, timeSoFar);
        }
    }
}
