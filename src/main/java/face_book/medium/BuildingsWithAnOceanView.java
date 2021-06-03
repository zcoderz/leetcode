package face_book.medium;

import utils.Pair;

import java.util.*;

public class BuildingsWithAnOceanView {

    public static void main(String [] args) {
        int [] arr = {4,2,3,1};
        BuildingsWithAnOceanView builds = new BuildingsWithAnOceanView();
        int [] smaller = builds.findBuildings(arr);
        Arrays.stream(smaller).forEach(System.out::println);
    }

    //a very simple approach : compare current building against the tallest building on the rights
    public int[] findBuildingsSimple(int[] heights) {
        int maxHeight = Integer.MIN_VALUE;
        LinkedList<Integer> list = new LinkedList<>();
        for (int j = heights.length-1; j >=0; j--) {
            if (heights[j] > maxHeight) {
                maxHeight = heights[j];
                list.addFirst(j);
            }
        }
        int [] res = new int[list.size()];
        Iterator<Integer> iter = list.iterator();
        int i = 0;
        while (iter.hasNext()) {
            res[i++] = iter.next();
        }
        return res;
    }
    //the below approach uses merge sort to find number of buildings smaller than the current index on right
    int [] smallerHeights;

    public int[] findBuildings(int[] heights) {
        smallerHeights = new int[heights.length];
        Pair<Integer, Integer>[] heightPairs = new Pair[heights.length];
        for (int i =0; i < heights.length; i++) {
            heightPairs[i] = new Pair<>(i, heights[i]);
        }
        sort(heightPairs);
        List<Integer> list = new ArrayList<>();
        for (int i =0; i < smallerHeights.length; i++) {
            smallerHeights[i] = smallerHeights.length-i-smallerHeights[i]-1;
            if (smallerHeights[i] ==0) {
                list.add(i);
            }
        }
        int [] arr = new int[list.size()];
        for (int i =0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public void sort( Pair<Integer, Integer>[] dest) {
        Pair<Integer, Integer>[] heightPairsSrc = new Pair[dest.length];
        for (int i =0; i < dest.length; i++) {
            heightPairsSrc[i] = new Pair<>(dest[i].first, dest[i].second);
        }
        sort(heightPairsSrc, dest, 0, dest.length - 1);
    }

    private void sort(Pair<Integer, Integer>[]  src, Pair<Integer, Integer>[] dest, int lo, int hi) {
        if (lo == hi) return;
        int mid = lo + (hi - lo) / 2;
        //the trick to swap src and destination is super nice
        //this allows you to work without having to copy data
        sort(dest, src, lo, mid);
        sort(dest, src, mid + 1, hi);
        //to do check and system copy if already sorted
        merge(src, dest, lo, hi, mid);
    }

    private void merge(Pair<Integer, Integer>[] src, Pair<Integer, Integer>[] dest, int lo, int hi, int mid) {
        int right = mid + 1;
        int i = lo;
        int curr = i;
        int smallOnRight = 0;
        while (curr <= hi) {
            if (right > hi) {
                this.smallerHeights[src[i].first] += smallOnRight;
                dest[curr++] = src[i++];
            } else if (i > mid) {
                dest[curr++] = src[right++];
            } else if (src[i].second > src[right].second) {
                smallOnRight++;
                dest[curr++] = src[right++];
            } else {
                this.smallerHeights[src[i].first] += smallOnRight;
                dest[curr++] = src[i++];
            }
        }
    }
}
