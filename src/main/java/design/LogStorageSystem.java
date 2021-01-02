package design;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Converting to java local date time to manage the date comparisons.
 * In a production system managing date arithmetic in hand written code is very error prone , hence leveraging
 * an already tested LocalDateTime class that comes with jdk is the preferred approach.
 *
 * However, the con is that LocalDateTime is very slow.
 */
public class LogStorageSystem {

    public static void main(String [] args) {
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
        entries = logStorageSystem.retrieve("2019:01:01:23:59:59","2019:01:02:23:59:59","Minute");
        System.out.println(entries);
    }

    TreeMap<LocalDateTime, List<Integer>> idsToDateMap = new TreeMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd:HH:mm:ss");

    public LogStorageSystem() {
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
                endT = LocalDateTime.of(endT.getYear()+1, 1, 1, 0, 0, 0, 0);
            }
            case "Month" -> {
                startT = LocalDateTime.of(startT.getYear(), startT.getMonth(), 1, 0, 0, 0, 0);
                endT = LocalDateTime.of(endT.getYear(), endT.getMonth(), 1, 0, 0, 0, 0);
                endT = endT.plusMonths(1);
            }
            case "Day" ->   {
                startT = LocalDateTime.of(startT.getYear(), startT.getMonth(),
                        startT.getDayOfMonth(), 0, 0, 0, 0);
                endT = LocalDateTime.of(endT.getYear(), endT.getMonth(),
                        endT.getDayOfMonth(), 0, 0, 0, 0);
                endT = endT.plusDays(1);
            }
            case "Hour" ->  {
                startT = LocalDateTime.of(startT.getYear(), startT.getMonth(), startT.getDayOfMonth(),
                        startT.getHour(), 0, 0, 0);
                endT = LocalDateTime.of(endT.getYear(), endT.getMonth(),
                        endT.getDayOfMonth(), endT.getHour(), 0, 0, 0);
                endT = endT.plusHours(1);
            }
            case "Minute" ->  {
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
