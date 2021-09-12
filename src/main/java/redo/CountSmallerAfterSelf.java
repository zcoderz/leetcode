package redo;

import utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class CountSmallerAfterSelf {

    public static void main (String [] args) {
        int [] arr = {-1,-1};
        CountSmallerAfterSelf count = new CountSmallerAfterSelf();
        List<Integer> res = count.countSmaller(arr);
        System.out.println(res);
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> retList = new ArrayList<>(nums.length);
        for (int i =0; i < nums.length; i++) {
            retList.add(0);
        }
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        List<Pair<Integer, Integer>> pairsAux = new ArrayList<>();
        for (int i =0; i < nums.length; i++) {
            pairs.add(Pair.of(nums[i], i));
            pairsAux.add(Pair.of(nums[i], i));
        }
        calculateSmaller(pairs, pairsAux, retList, 0, pairs.size()-1);
        return retList;
    }

    void calculateSmaller(List<Pair<Integer, Integer>> source, List<Pair<Integer, Integer>> dest,
                          List<Integer> retList, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right-left)/2;
        calculateSmaller(dest, source, retList, left, mid);
        calculateSmaller(dest, source, retList, mid+1, right);
        merge(source, dest, retList, left, mid+1, right);
    }

    void merge (List<Pair<Integer, Integer>> source, List<Pair<Integer, Integer>> dest,
                List<Integer> retList, int left, int mid, int right) {
        int currIndex= left;
        int origMid = mid;
        while (currIndex <= right) {
            if (mid > right || left < origMid && source.get(left).first <= source.get(mid).first) {
               retList.set(source.get(left).second, retList.get(source.get(left).second) + mid - origMid);
               dest.set(currIndex, source.get(left++));
            } else {
                //retList.set(source.get(left).second, retList.get(source.get(left).second) + 1);
                dest.set(currIndex, source.get(mid++));
            }
            currIndex++;
        }
    }
}
