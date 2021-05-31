package linkedin;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {

    public static void main(String [] args) {
        FactorCombinations fc = new FactorCombinations();
        fc.getFactors(12);
        for (List<Integer> list : fc.res) {
            System.out.println(list);
        }
    }

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> getFactors(int n) {
        List<Integer> list = new ArrayList<>();
        process(2, list, n);
        return res;
    }

    void process(int start, List<Integer> list, int rem) {
        if (!list.isEmpty()) {
            List<Integer> copy = new ArrayList<>(list);
            copy.add(rem);
            res.add(copy);
        }

        for (int i = start; i*i <= rem; i++) {
            if ((rem % i) == 0) {
                list.add(i);
                process(i, list, rem/i);
                list.remove(list.size()-1);
            }
        }
    }

}
