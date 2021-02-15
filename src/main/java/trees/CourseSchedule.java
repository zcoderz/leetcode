package trees;

import java.util.*;

/**
 * 210. Course Schedule II
 * There are a total of n courses you have to take labelled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must
 * take the course bi before the course ai.
 *
 * Given the total number of courses numCourses and a list of the prerequisite pairs,
 * return the ordering of courses you should take to finish all courses.
 *
 * If there are many valid answers, return any of them. If it is impossible to finish all courses,
 * return an empty array.
 *
 * IMP-1: Very common question 210. Easy topological sort based solution.
 *
 */
public class CourseSchedule {

    public static void main(String [] args) {
        CourseSchedule courseSchedule = new CourseSchedule();
        int [][] prereqs =  {{0,1},{0,2},{1,2}};
        int [] order = courseSchedule.findOrder(3, prereqs);
        for (int i : order) {
            System.out.println(i);
        }
    }

    Map<Integer, List<Integer>> courseDependencies = new HashMap<>();
    LinkedList<Integer> courseList = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    Set<Integer> visiting = new HashSet<>();
    private boolean hasCycle = false;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int dep = prerequisites[i][1];
            List<Integer> depCourses = courseDependencies.getOrDefault(course, new ArrayList<>());
            depCourses.add(dep);
            courseDependencies.put(course, depCourses);
        }
        for (int i =0; i < numCourses; i++) {
            dfs(i);
        }

        if (hasCycle) {
            return  new int[0];
        }
        int[] intCourseList = new int[courseList.size()];
        int i = 0;
        for (Integer course : courseList) {
            intCourseList[i] = course;
            i++;
        }
        return intCourseList;
    }

    /**
     * recursively traverse each dependency if one exists
     * @param course
     */
    void dfs(Integer course) {
        if (hasCycle || visited.contains(course)) {
            return;
        }
        List<Integer> depCourses = courseDependencies.get(course);
        if (null == depCourses) {
            if (!visited.contains(course)) {
                cleanUp(course);
            }
            return;
        }
        for (Integer depCourse : depCourses) {
            if (visited.contains(depCourse)) {
                continue; //already processed
            }
            if (visiting.contains(depCourse)) {
                hasCycle = true;
            }
            visiting.add(depCourse);
            dfs(depCourse);
            visiting.remove(depCourse);
        }
        cleanUp(course);
    }

    void cleanUp(int course) {
        visited.add(course);
        courseList.add(course);
    }

    /**
     * here is a non recursive solution from leetcode using indegree of each vertex
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrderNonRecursive(int numCourses, int[][] prerequisites) {

        boolean isPossible = true;
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        // Create the adjacency list representation of the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
            lst.add(dest);
            adjList.put(src, lst);

            // Record in-degree of each vertex
            indegree[dest] += 1;
        }

        // Add all vertices with 0 in-degree to the queue
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int i = 0;
        // Process until the Q becomes empty
        while (!q.isEmpty()) {
            int node = q.remove();
            topologicalOrder[i++] = node;

            // Reduce the in-degree of each neighbor by 1
            if (adjList.containsKey(node)) {
                for (Integer neighbor : adjList.get(node)) {
                    indegree[neighbor]--;

                    // If in-degree of a neighbor becomes 0, add it to the Q
                    if (indegree[neighbor] == 0) {
                        q.add(neighbor);
                    }
                }
            }
        }

        // Check to see if topological sort is possible or not.
        if (i == numCourses) {
            return topologicalOrder;
        }

        return new int[0];
    }
}
