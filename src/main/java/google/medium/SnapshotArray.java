package google.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1146. Snapshot Array
 * Medium
 *
 * 696
 *
 * 132
 *
 * Add to List
 *
 * Share
 * Implement a SnapshotArray that supports the following interface:
 *
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 *
 *
 * Example 1:
 *
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation:
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5);  // Set array[0] = 5
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 *
 * This is a nice question to practice as it has overlap with practical problems in coding.
 *
 * concept is simple : start with an array, per snap create an overlap map , sore new items in the overlay
 * when an item , snap is requested find the latest snap that contains the index. if none, return from original array
 *
 * IMP-2 : Good practice question
 *
 */
public class SnapshotArray {

    public static void main(String [] args) {
        SnapshotArray snapshotArray = new SnapshotArray(3);
        snapshotArray.set(0,5);
        snapshotArray.snap();
        snapshotArray.set(0,6);
        int v = snapshotArray.get(0,0);
        System.out.print(v);
    }

    int[] snapShotArray;
    int snapShotId = -1;
    Map<Integer, Map<Integer, Integer>> snapShotMap = new HashMap<>();

    public SnapshotArray(int length) {
        snapShotArray = new int[length];
    }

    public void set(int index, int val) {
        if (snapShotId == -1) {
            snapShotArray[index] = val;
        } else {
            Map<Integer, Integer> snapMap = snapShotMap.computeIfAbsent(snapShotId, (l) -> new HashMap<>());
            snapMap.put(index, val);
        }
    }

    public int snap() {
        snapShotId++;
        return snapShotId;
    }

    public int get(int index, int snap_id) {
        snap_id--;
        while (snap_id > -2) {
            if (snap_id == -1) {
                return snapShotArray[index];
            } else {
                Map<Integer, Integer> map = snapShotMap.get(snap_id);
                if (null != map) {
                    Integer val = map.get(index);
                    if (val != null) {
                        return val;
                    }
                }
            }
            snap_id--;
        }
        return -1;
    }

}
