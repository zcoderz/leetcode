package trees;

import java.util.*;

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
