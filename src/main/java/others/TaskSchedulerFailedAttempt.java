package others;

import java.util.*;

public class TaskSchedulerFailedAttempt {

    public static void main (String [] args) {
        //char [] tasks = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        //char [] tasks = {'A','A','A','B','B','B'};
        char [] tasks = {'A','A','A','B','B','B', 'C','C','C', 'D', 'D', 'E'};
        TaskSchedulerFailedAttempt scheduler = new TaskSchedulerFailedAttempt();
        int t = scheduler.leastInterval(tasks, 2);
        System.out.println(t);
    }

    public int leastInterval(char[] tasks, int n) {
        Arrays.sort(tasks);
        char priorTask = '-';
        Map<Character, Queue<Character>> taskQueue = new HashMap<>();

        Queue<Character> tQueue = null;
        for (char task : tasks) {
            if (task != priorTask) {
                tQueue = new LinkedList<>();
                taskQueue.put(task, tQueue);
            }
            priorTask = task;
            tQueue.add(task);
        }

        int time = 0;
        Map<Character, Integer> chrTime = new HashMap<>();
        Map<Integer, Character> timeToCharMap = new TreeMap<>();

        while (!taskQueue.isEmpty()) {
            time++;

            //clear finished tasks state
            for(Iterator<Map.Entry<Character, Queue<Character>>> it = taskQueue.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Character, Queue<Character>> entry = it.next();
                if(entry.getValue().isEmpty()) {
                    it.remove();
                    Character c = entry.getKey();
                    Integer t = chrTime.get(c);
                    timeToCharMap.remove(t);
                    chrTime.remove(c);
                }
            }
            if (taskQueue.isEmpty()) {
                time--;
                break;
            }
            Iterator<Map.Entry<Integer, Character>> it = timeToCharMap.entrySet().iterator();
            Integer oldestTaskTime = Integer.MAX_VALUE;
            Map.Entry<Integer, Character> entry = null;
            if (it.hasNext()) {
                entry = it.next();
                oldestTaskTime = entry.getKey();
            }
            Character cToUse;
            if ((time-oldestTaskTime) > n) {
                cToUse = entry.getValue(); //if no wait then use oldest task
            } else {
                Set<Character> remainingTasks = new HashSet<>(taskQueue.keySet());
                Set<Character> tasksDone = chrTime.keySet();
                remainingTasks.removeAll(tasksDone);
                if (remainingTasks.isEmpty()) {
                    cToUse = entry.getValue(); //pick the oldest task if no new tasks left
                } else {
                   Iterator<Character> itS = remainingTasks.iterator(); //pick the first new task
                   cToUse = itS.next();
                }
            }

            Queue<Character> qTask = taskQueue.get(cToUse);
            qTask.poll(); //remove task

            //update times in maps
            Integer lastCharTime = chrTime.get(cToUse);
            if (null != lastCharTime) {
                timeToCharMap.remove(lastCharTime);
                int diff = time - lastCharTime;
                if ((diff)  <= n) {
                    time += n - diff + 1;
                }
            }
            timeToCharMap.put(time, cToUse);
            chrTime.put(cToUse, time);
            System.out.println("Task " + cToUse + " at time " + time );
        }
        return time;
    }
}
