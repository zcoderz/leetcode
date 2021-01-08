package face_book;

import java.util.*;

public class ExclusiveTimeOfFunctions {

    public static void main(String [] args) {
        String [] loggArr = {"0:start:0","1:start:2","1:end:5","0:end:6"};
        ExclusiveTimeOfFunctions exclusive = new ExclusiveTimeOfFunctions();
        List<String> list = Arrays.asList(loggArr);
        int [] out = exclusive.exclusiveTime(2, list);
        System.out.println(Arrays.toString(out));

        String [] loggArr1 = {"0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"};
        list = Arrays.asList(loggArr1);
        out = exclusive.exclusiveTime(2, list);
        System.out.println(Arrays.toString(out));

        String [] loggArr2 = {"0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"};
        list = Arrays.asList(loggArr2);
        out = exclusive.exclusiveTime(2, list);
        System.out.println(Arrays.toString(out));

        String [] loggArr3 = {"0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"};
        list = Arrays.asList(loggArr3);
        out = exclusive.exclusiveTime(2, list);
        System.out.println(Arrays.toString(out));

    }

    public int[] exclusiveTime(int n, List<String> logs) {
        Map<Integer, Integer> idToTime = new HashMap<>();
        List<Integer> functionOrder = new ArrayList<>();
        Stack<Integer> workingFunctions = new Stack<>();

        int priorTime = 0;
        int priorId = -1;
        String previousTye = "";
        for (String info : logs) {
            String [] steps = info.split(":");
            int id = Integer.parseInt(steps[0]);
            int time = Integer.parseInt(steps[2]);
            String workType = steps[1];
            if (!workingFunctions.isEmpty() && workType.equals("start")) {
                //handle case when new work is started while previous was in progress
                int priorIdTmp = workingFunctions.peek();
                int timeWorked = time - priorTime;
                if (previousTye.equals("end")) {
                    timeWorked--;
                }
                timeWorked += idToTime.getOrDefault(priorIdTmp, 0);
                idToTime.put(priorId, timeWorked);
            }
            if (workType.equals("start")) {
                if (!idToTime.containsKey(id)) {
                    functionOrder.add(id);
                    idToTime.put(id, 0);
                }
                workingFunctions.push(id);
            } else {
                int timeOnCpu = time - priorTime;
                if (previousTye.equals("start")) {
                    timeOnCpu++;
                }
                timeOnCpu += idToTime.get(id);
                idToTime.put(id, timeOnCpu);
                workingFunctions.pop();
            }
            priorTime = time;
            priorId = id;
            previousTye = workType;
        }
        int [] retList = new int[functionOrder.size()];
        int i = 0;
        for (int id : functionOrder) {
            retList[i++] = idToTime.get(id);
        }
        return retList;
    }

}
