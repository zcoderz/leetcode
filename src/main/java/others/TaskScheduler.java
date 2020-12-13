package others;

import java.util.Arrays;

public class TaskScheduler {
    public static void main (String [] args) {
        //char [] tasks = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        //char [] tasks = {'A','A','A','B','B','B'};
        char [] tasks = {'A','A','A','B','B','B', 'C','C','C', 'D', 'D', 'E'};
        TaskScheduler scheduler = new TaskScheduler();
        int t = scheduler.leastInterval(tasks, 2);
        System.out.println(t);
    }

    public int leastInterval(char[] tasks, int n) {
        int num = 'Z' - 'A' + 1;
        int [] taskFreq = new int[num];
        for (char task : tasks) {
            taskFreq[task-'A'] = taskFreq[task-'A'] + 1;
        }
        Arrays.sort(taskFreq);
        int maxFreq = taskFreq[num-1];
        int openSlots = (maxFreq-1) * n;

        for (int j = num-2; j>=0 && openSlots > 0 && taskFreq[j] > 0; j--) {
            openSlots -= Math.min(taskFreq[j], maxFreq-1);
        }

        return tasks.length + openSlots;
    }
}
