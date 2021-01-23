package queue.bfs;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D
 * coordinate on this grid.
 * <p>
 * Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair
 * with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple
 * (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if
 * there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until
 * there are no available workers.
 */
public class WorkerBikes {

    public static void main(String[] args) {
        //int [][] workers = {{0,0},{2,1}};
        //int [][] bikes = {{1,2},{3,3}};
        int[][] workers = {{0, 0}, {1, 1}, {2, 0}};
        int[][] bikes = {{1, 0}, {2, 2}, {2, 1}};

        WorkerBikes workerBikes = new WorkerBikes();
        int[] assigns = workerBikes.assignBikes(workers, bikes);
        System.out.println(Arrays.toString(assigns));
    }


    /**
     * Sort by distance , worker and bike using PQ then start assigning workers bikes based on the sort condition
     * <p>
     * problem is that the problem executes M*N * (logN) time.
     * <p>
     * can not choose a BFS solution here as that would not cater to min distance , A bike which is closest to a person
     * (person A)  maybe closer to another person (person B).
     *
     * @param workers
     * @param bikes
     * @return
     */
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((int[] arr1, int[] arr2) -> {
            if (arr1[0] == arr2[0]) {
                if (arr1[1] == arr2[1]) {
                    return Integer.compare(arr1[2], arr2[2]);
                } else {
                    return Integer.compare(arr1[1], arr2[1]);
                }
            } else {
                return Integer.compare(arr1[0], arr2[0]);
            }
        });

        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                int[] arr = new int[3];
                arr[0] = dist;
                arr[1] = i;
                arr[2] = j;
                priorityQueue.add(arr);
            }
        }
        int[] assignments = new int[workers.length];
        Arrays.fill(assignments, -1);
        int[] bAssigns = new int[bikes.length];
        Arrays.fill(bAssigns, -1);
        int numAssigned = 0;
        while (numAssigned < workers.length) {
            int[] arr = priorityQueue.poll();
            int worker = arr[1];
            int bike = arr[2];
            if (assignments[worker] == -1 && bAssigns[bike] == -1) {
                assignments[worker] = bike;
                bAssigns[bike] = worker;
                numAssigned++;
            }
        }
        return assignments;
    }
}
