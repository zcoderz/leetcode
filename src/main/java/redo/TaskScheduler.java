package redo;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TaskScheduler {

    public static void main(String [] args) {
        char [] nums = {'A','A','A','B','B','B'};
        int n = 2;
        TaskScheduler scheduler = new TaskScheduler();
        int res = scheduler.leastInterval(nums, n);
        System.out.println(res);
    }

    public int leastInterval(char[] tasks, int n) {
        int [] freq = new int[26];
        for (char ch : tasks) {
            freq[ch-'A']++;
        }
        Arrays.sort(freq);
        int max = freq[freq.length-1];
        int wastedCycles = n * (max-1);
        for (int i =0; i < freq.length-1; i++) {
            wastedCycles = wastedCycles - Math.min(freq[i], max-1);
        }
        return tasks.length + wastedCycles;
    }
}
