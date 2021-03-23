package face_book.medium;

import java.util.Arrays;

/**
 * 621. Task Scheduler
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task.
 * Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 *
 * problem asks to find max time it will take to finish the tasks given a cool down period between the slots.
 * IMP-1 : Very elegant approach to a common question.
 */
public class TaskScheduler {
    public static void main(String[] args) {
        //char [] tasks = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        //char [] tasks = {'A','A','A','B','B','B'};
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'};
        TaskScheduler scheduler = new TaskScheduler();
        int t = scheduler.leastInterval(tasks, 2);
        System.out.println(t);
    }

    /**
     * Below is a very elegant approach to this problem
     *
     * @param tasks array of tasks, each task is represented by a char
     * @param n     cool down period between tasks
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int num = 'Z' - 'A' + 1;
        int[] taskFreq = new int[num];
        for (char task : tasks) {
            taskFreq[task - 'A'] = taskFreq[task - 'A'] + 1;
        }
        //sort the tasks by their frequency
        Arrays.sort(taskFreq);
        int maxFreq = taskFreq[num - 1];
        //open slots is a very intelligent concept.
        //it indicates the max wait that is bound to occur if there were no
        //other tasks that can get done during the wait
        int openSlots = (maxFreq - 1) * n;
        //remove the open slots until all slots have been used
        for (int j = num - 2; j >= 0 && openSlots > 0 && taskFreq[j] > 0; j--) {
            //taking a min here so that if two tasks have same highest frequency , you can only reduce at max
            //(maxFrequency -1)
            openSlots -= Math.min(taskFreq[j], maxFreq - 1);
        }

        //number slots needed are total work unit + remaining open slots
        return tasks.length + openSlots;
    }
}
