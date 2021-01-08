package face_book;

import java.util.*;

/**
 * 636. Exclusive Time of Functions
 * On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.
 *
 * Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack,
 * and when a function call ends, its ID is popped off the stack. The function whose ID is at the top of the stack is
 * the current function being executed. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.
 *
 * You are given a list logs, where logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}".
 * For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3, and "1:end:2"
 * means a function call with function ID 1 ended at the end of timestamp 2. Note that a function can be called multiple times, possibly recursively.
 *
 * A function's exclusive time is the sum of execution times for all function calls in the program. For example, if a function is called twice,
 * one call executing for 2 time units and another call executing for 1 time unit, the exclusive time is 2 + 1 = 3.
 *
 * Return the exclusive time of each function in an array, where the value at the ith index represents the exclusive time for the function with ID i
 *
 */
public class ExclusiveTimeOfFunctions {

    public static void main(String [] args) {
        String [] loggArr = {"0:start:0","1:start:2","1:end:5","0:end:6"};
        ExclusiveTimeOfFunctions exclusive = new ExclusiveTimeOfFunctions();
        List<String> list = Arrays.asList(loggArr);
//        int [] out = exclusive.exclusiveTime(2, list);
//        System.out.println(Arrays.toString(out));
//
//        String [] loggArr1 = {"0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"};
//        list = Arrays.asList(loggArr1);
//        out = exclusive.exclusiveTime(2, list);
//        System.out.println(Arrays.toString(out));
//
//        String [] loggArr2 = {"0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"};
//        list = Arrays.asList(loggArr2);
//        out = exclusive.exclusiveTime(2, list);
//        System.out.println(Arrays.toString(out));
//
//        String [] loggArr3 = {"0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"};
//        list = Arrays.asList(loggArr3);
//        out = exclusive.exclusiveTime(2, list);
//        System.out.println(Arrays.toString(out));

        String [] loggArr4 = {"0:start:0","1:start:5","2:start:6","3:start:9","4:start:11","5:start:12","6:start:14","7:start:15","1:start:24","1:end:29","7:end:34","6:end:37","5:end:39","4:end:40","3:end:45","0:start:49","0:end:54","5:start:55","5:end:59","4:start:63","4:end:66","2:start:69","2:end:70","2:start:74","6:start:78","0:start:79","0:end:80","6:end:85","1:start:89","1:end:93","2:end:96","2:end:100","1:end:102","2:start:105","2:end:109","0:end:114"};
        list = Arrays.asList(loggArr4);
        int [] out = exclusive.exclusiveTime(2, list);
        System.out.println(Arrays.toString(out));

    }

    /**
     * the question indicates that the index of the slot represents the function id
     * hence we could just store the time in array indexed by the id
     * but the below code is more generic as it can handle functions with different order ids
     *
     * @param n
     * @param logs
     * @return
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        //use a map to count time for a function
        Map<Integer, Integer> idToTime = new HashMap<>();
        //use a list to indicate order in which functions are seen
        List<Integer> functionOrder = new ArrayList<>();
        //use a stack to track the current running functions
        Stack<Integer> workingFunctions = new Stack<>();
        int priorTime = 0;
        for (String info : logs) {
            String [] steps = info.split(":");
            int id = Integer.parseInt(steps[0]);
            int time = Integer.parseInt(steps[2]);
            String workType = steps[1];
            if (workType.equals("start")) {
                if (!workingFunctions.isEmpty()) {
                    //add the time for prior function when a new one starts
                    int priorIdTmp = workingFunctions.peek();
                    int timeWorked = time - priorTime;
                    timeWorked += idToTime.getOrDefault(priorIdTmp, 0);
                    idToTime.put(priorIdTmp, timeWorked);
                }
                if (!idToTime.containsKey(id)) {
                    //if its a new function, add its time as 0 in map
                    functionOrder.add(id);
                    idToTime.put(id, 0);
                }
                //add function on stack
                workingFunctions.push(id);
                priorTime = time;
            } else {
                //update the time in map when a function ends.
                //end is till end of the given time slow. hence add 1 to the diff of time - prior
                int timeOnCpu = time - priorTime + 1;
                timeOnCpu += idToTime.get(id);
                idToTime.put(id, timeOnCpu);
                workingFunctions.pop();
                priorTime = time;
                priorTime++; //move time ahead since end takes the current slot
            }
        }
        int [] retList = new int[functionOrder.size()];
        int i = 0;
        for (int id : functionOrder) {
            retList[i++] = idToTime.get(id);
        }
        return retList;
    }

}
