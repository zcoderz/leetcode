package logic;

/**
 * 346. Moving Average from Data Stream
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Implement the MovingAverage class:
 *
 * MovingAverage(int size) Initializes the object with the size of the window size.
 * double next(int val) Returns the moving average of the last size values of the stream.
 *
 *
 * Example 1:
 *
 * Input
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * Output
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * Explanation
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // return 1.0 = 1 / 1
 * movingAverage.next(10); // return 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 *
 */
public class MovingAverage {

    int[] arr;
    int len;
    int count = 0;
    double sum = 0;
    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        arr = new int[size];
        len = size;
    }

    public static void main(String[] args) {
        MovingAverage mv = new MovingAverage(3);
        double d = mv.next(1);
        System.out.println(d);
        d = mv.next(2);
        System.out.println(d);
        d = mv.next(3);
        System.out.println(d);
        d = mv.next(4);
        System.out.println(d);
    }

    //[a, b , c]
    public double next(int val) {
        sum += val; //add current to sum
        int head = (count + 1) % len; //index where the new element will get inserted
        //index where the new element will get inserted i same as the one that will be removed
        //if the index wasnt taken previously, the value will be 0 and hence can be ignored
        sum -= arr[head];
        arr[head] = val;    //update value in index
        count++;
        return sum / Math.min(len, count); //return the correct average
    }

}
