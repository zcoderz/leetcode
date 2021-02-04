package design;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 635. Design Log Storage System
 * You are given several logs, where each log contains a unique ID and timestamp.
 * Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second,
 * for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.
 *
 * Implement the LogSystem class:
 *
 * LogSystem() Initializes the LogSystem object.
 * void put(int id, string timestamp) Stores the given log (id, timestamp) in your storage system.
 * int[] retrieve(string start, string end, string granularity)
 * Returns the IDs of the logs whose timestamps are within the range from start to end inclusive.
 * start and end all have the same format as timestamp,
 * and granularity means how precise the range should be (i.e. to the exact Day, Minute, etc.).
 * For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", and granularity = "Day" means that
 * we need to find the logs within the inclusive range from Jan. 1st 2017 to Jan. 2nd 2017, and the Hour, Minute,
 * and Second for each log entry can be ignored.
 *
 *
 * Example 1:
 *
 * Input
 * ["LogSystem", "put", "put", "put", "retrieve", "retrieve"]
 * [[], [1, "2017:01:01:23:59:59"], [2, "2017:01:01:22:59:59"], [3, "2016:01:01:00:00:00"],
 * ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year"], ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"]]
 * Output
 * [null, null, null, null, [3, 2, 1], [2, 1]]
 *
 * Explanation
 * LogSystem logSystem = new LogSystem();
 * logSystem.put(1, "2017:01:01:23:59:59");
 * logSystem.put(2, "2017:01:01:22:59:59");
 * logSystem.put(3, "2016:01:01:00:00:00");
 *
 * // return [3,2,1], because you need to return all logs between 2016 and 2017.
 * logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year");
 *
 * // return [2,1], because you need to return all logs between Jan. 1, 2016 01:XX:XX and Jan. 1, 2017 23:XX:XX.
 * // Log 3 is not returned because Jan. 1, 2016 00:00:00 comes before the start of the range.
 * logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour");
 *
 * Converting to java local date time to manage the date comparisons. In a production system managing date arithmetic in
 * hand written code is very error prone , hence leveraging an already tested LocalDateTime class that comes with jdk is
 * the preferred approach.
 * <p>
 * However, the con is that LocalDateTime is very slow.
 */
public class LogStorageSystem {

    TreeMap<LocalDateTime, List<Integer>> idsToDateMap = new TreeMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd:HH:mm:ss");
    public LogStorageSystem() {
    }

    public static void main(String[] args) {
        LogStorageSystem logStorageSystem = new LogStorageSystem();
        logStorageSystem.put(1, "2017:01:01:23:59:59");
        logStorageSystem.put(2, "2017:01:01:22:59:59");
        logStorageSystem.put(3, "2016:01:01:00:00:00");

        logStorageSystem.put(4, "2019:01:01:23:59:59");
        logStorageSystem.put(5, "2019:01:02:23:59:59");


        List<Integer> entries = logStorageSystem.retrieve("2016:01:01:01:01:01",
                "2017:01:01:23:00:00", "Year");
        System.out.println(entries);
        entries = logStorageSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour");
        System.out.println(entries);
        entries = logStorageSystem.retrieve("2019:01:01:23:59:59", "2019:01:02:23:59:59", "Minute");
        System.out.println(entries);
    }

    public void put(int id, String timestamp) {
        LocalDateTime dateTime = LocalDateTime.parse(timestamp, formatter);
        List<Integer> ids = idsToDateMap.getOrDefault(dateTime, new ArrayList<>());
        ids.add(id);
        idsToDateMap.put(dateTime, ids);
    }

    /**
     * Adjust the start and end dates based on granularity and then compare against the desired range in treemap
     *
     * @param start
     * @param end
     * @param granularity
     * @return
     */
    public List<Integer> retrieve(String start, String end, String granularity) {
        LocalDateTime startT = LocalDateTime.parse(start, formatter);
        LocalDateTime endT = LocalDateTime.parse(end, formatter);
        switch (granularity) {
            case "Year" -> {
                startT = LocalDateTime.of(startT.getYear(), 1, 1, 0, 0, 0, 0);
                endT = LocalDateTime.of(endT.getYear() + 1, 1, 1, 0, 0, 0, 0);
            }
            case "Month" -> {
                startT = LocalDateTime.of(startT.getYear(), startT.getMonth(), 1, 0, 0, 0, 0);
                endT = LocalDateTime.of(endT.getYear(), endT.getMonth(), 1, 0, 0, 0, 0);
                endT = endT.plusMonths(1);
            }
            case "Day" -> {
                startT = LocalDateTime.of(startT.getYear(), startT.getMonth(),
                        startT.getDayOfMonth(), 0, 0, 0, 0);
                endT = LocalDateTime.of(endT.getYear(), endT.getMonth(),
                        endT.getDayOfMonth(), 0, 0, 0, 0);
                endT = endT.plusDays(1);
            }
            case "Hour" -> {
                startT = LocalDateTime.of(startT.getYear(), startT.getMonth(), startT.getDayOfMonth(),
                        startT.getHour(), 0, 0, 0);
                endT = LocalDateTime.of(endT.getYear(), endT.getMonth(),
                        endT.getDayOfMonth(), endT.getHour(), 0, 0, 0);
                endT = endT.plusHours(1);
            }
            case "Minute" -> {
                startT = LocalDateTime.of(startT.getYear(), startT.getMonth(), startT.getDayOfMonth(),
                        startT.getHour(), startT.getMinute(), 0, 0);
                endT = LocalDateTime.of(endT.getYear(), endT.getMonth(),
                        endT.getDayOfMonth(), endT.getHour(), endT.getMinute(), 0, 0);
                endT = endT.plusMinutes(1);
            }
            default -> endT = endT.plusSeconds(1);
        }
        //keeping the data in a tree map helps to do range comparisons such as via subMap
        SortedMap<LocalDateTime, List<Integer>> rangeMap = idsToDateMap.subMap(startT, endT);
        List<Integer> retList = new ArrayList<>();
        for (Map.Entry<LocalDateTime, List<Integer>> idsEntry : rangeMap.entrySet()) {
            retList.addAll(idsEntry.getValue());
        }
        return retList;
    }
}
