package queue;

public class MovingAverage {

    public static void main(String [] args) {
        MovingAverage mv = new MovingAverage(3);
        double d = mv.next(1);
        System.out.println(d);
        d = mv.next (2);
        System.out.println(d);
        d = mv.next(3);
        System.out.println(d);
        d = mv.next(4);
        System.out.println(d);
    }

    int [] arr ;
    int len ;
    int count =0;
    double sum = 0;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        arr = new int[size];
        len = size;
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
