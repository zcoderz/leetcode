package misc;

import java.util.Arrays;

/**
 * problem asks to find max time it will take to finish the tasks given a cool down period between the slots.
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
            openSlots -= Math.min(taskFreq[j], maxFreq - 1);
        }

        //number slots needed are total work unit + remaining open slots
        return tasks.length + openSlots;
    }
}
