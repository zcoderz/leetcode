package stack;

import java.util.List;
import java.util.Stack;

/**
 * Real easy code, use stack to DFS to rooms. iterate over visited, if any isnt visited, return.
 */
public class RoomAndKeys {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms.isEmpty()) return true;

        boolean[] visited = new boolean[rooms.size()];
        Stack<Integer> stack = new Stack<>();

        stack.push(0);
        visited[0] = true;
        
        while (!stack.isEmpty()) {
            int index = stack.pop();
            List<Integer> keys = rooms.get(index);
            for (Integer room : keys) {
                if (!visited[room]) {
                    visited[room] = true;
                    stack.push(room);
                }
            }
        }

        for (boolean v: visited) {
            if (!v) return false;
        }

        return true;
    }
}
