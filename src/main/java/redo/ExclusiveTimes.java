package redo;

import java.util.*;

public class ExclusiveTimes {

    public static void main(String [] args) {
        ExclusiveTimes et = new ExclusiveTimes();
        int [] v = et.exclusiveTime(2, Arrays.asList("0:start:0","0:end:0","1:start:1","1:end:1","2:start:2","2:end:2","2:start:3","2:end:3"));
        System.out.println(Arrays.toString(v));

    }

    public int[] exclusiveTime(int n, List<String> logs) {
        Map<String, Integer> functionTimes = new HashMap<>();
        LinkedHashSet<String> functionOrder = new LinkedHashSet<>();
        Stack<String> functionStack = new Stack<>();

        int priorTime = -1;
        for (String log: logs) {
            String [] items = log.split(":");
            String function = items[0];
            String action = items[1];
            int time = Integer.parseInt(items[2]);

            if (action.equals("end")) {
                functionStack.pop();
                int timeDelta = time - priorTime+1;
                timeDelta += functionTimes.getOrDefault(function, 0);
                functionTimes.put(function, timeDelta);
                priorTime = time+1;
            } else {
                functionOrder.add(function);
                int timeDelta = 0;
                if (priorTime != -1) {
                    timeDelta += time - priorTime;
                    if (!functionStack.isEmpty()) {
                        String priorFunc = functionStack.peek();
                        timeDelta += functionTimes.getOrDefault(priorFunc, 0);
                        functionTimes.put(priorFunc, timeDelta);
                    }
                }
                functionStack.push(function);
                priorTime = time;
            }

        }

        int [] times = new int[functionOrder.size()];
        int i = 0;
        for (String function: functionOrder) {
            times[i++] = functionTimes.get(function);
        }
        return times;
    }
}
