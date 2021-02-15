package trees;

import java.util.*;

/**
 * 207. Course Schedule
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course
 * bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible
 *
 * IMP-1 : Easy topological sort based solution.
 */
public class CourseScheduleCircular {
    Map<Integer, List<Integer>> courseDependencies = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
    Set<Integer> visiting = new HashSet<>();
    boolean isCycle = Boolean.FALSE;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int dep = prerequisites[i][1];
            List<Integer> depCourses = courseDependencies.getOrDefault(course, new ArrayList<>());
            depCourses.add(dep);
            courseDependencies.put(course, depCourses);
        }

        for (Integer course : courseDependencies.keySet()) {
            if (isCycle) {
                return false;
            }
            dfs(course);
        }

        if (isCycle) {
            return false;
        } else {
            return true;
        }
    }

    void dfs(Integer course) {
        List<Integer> depCourses = courseDependencies.get(course);
        if (null == depCourses) {
            return;
        }
        for (Integer depCourse : depCourses) {
            if (visited.contains(depCourse)) {
                continue; //already processed
            }
            if (visiting.contains(depCourse)) {
                isCycle = true;
                return;
            }
            visiting.add(depCourse);
            dfs(depCourse);
            visiting.remove(depCourse);
            if (isCycle) {
                return;
            }
        }
        visited.add(course);
    }

}
