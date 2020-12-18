package frequent.general.medium;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;


/**
 * This is a good problem in that we face similar problems in practical coding at work
 *
 * using a combination of a hash map and tree map makes perfect sense in this case
 *
 */
public class TimeBasedKeyValueStore {

    public static void main(String [] args) {
        TimeBasedKeyValueStore keyValueStore = new TimeBasedKeyValueStore();
        keyValueStore.set("mango" , "star", 1);
        keyValueStore.set("mango" , "juice", 10);
        keyValueStore.set("apple" , "sweet", 20);

        String val = keyValueStore.get("mango", 0);
        System.out.println(val);

        val = keyValueStore.get("mango", 5);
        System.out.println(val);

        val = keyValueStore.get("mango", 20);
        System.out.println(val);

        val = keyValueStore.get("banana", 20);
        System.out.println(val);
    }

    /**
     * storing value and time in a separate class to keep to keep these variables coherent
     * otherwise, could have stored them in a map which had a key as integer (time)
     */
    class TimeValue implements Comparable{
        int time;
        String value;

        /**
         * implement compare to so the data can be sorted
         * @param obj
         * @return
         */
        public int compareTo(Object obj) {
            TimeValue tVal = (TimeValue) obj;
            return Integer.compare(time, tVal.time);
        }

        /**
         * equals implementation
         * @param obj
         * @return
         */
        public boolean equals(Object obj) {
            TimeValue tVal = (TimeValue) obj;
            return tVal.time == this.time;
        }
    }

    private Map<String, TreeSet<TimeValue>> keyValMap = new HashMap<>();


    public void set(String key, String value, int timestamp) {
        TimeValue val = new TimeValue();
        val.value = value;
        val.time = timestamp;

        TreeSet<TimeValue> set = keyValMap.computeIfAbsent(key, (l) -> new TreeSet<>());
        set.add(val);
    }

    public String get(String key, int timestamp) {
        TreeSet<TimeValue> set = keyValMap.get(key);
        if (null == set) return "";

        TimeValue val = new TimeValue();
        val.time = timestamp;

        //get the latest value before or at the specified time.
        TimeValue valInSet = set.floor(val);
        if (valInSet != null) {
            return valInSet.value;
        }
        return "";
    }

    public TimeBasedKeyValueStore() {

    }
}
