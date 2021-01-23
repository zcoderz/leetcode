package trees;

import java.util.*;

public class CourseSchedule {
    Map<Integer, List<Integer>> courseDependencies = new HashMap<>();
    List<Integer> courseList = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();
    Set<Integer> visiting = new HashSet<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        courseList.add(1, 2);
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int dep = prerequisites[i][1];
            List<Integer> depCourses = courseDependencies.getOrDefault(course, new ArrayList<>());
            depCourses.add(dep);
            courseDependencies.put(course, depCourses);
        }

        for (Integer course : courseDependencies.keySet()) {
            dfs(course);
        }
        int[] intCourseList = new int[courseList.size()];
        int i = 0;
        for (Integer course : courseList) {
            intCourseList[i] = course;
            i++;
        }
        return intCourseList;
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
                System.out.println("circular dep on course " + depCourse);
            }
            visiting.add(depCourse);
            dfs(depCourse);
            visiting.remove(depCourse);
        }
        visited.add(course);
        courseList.add(course);
    }

}
