package amazon.medium;

import utils.Pair;
import utils.Tuple;

import java.util.*;

/**
 * 1152. Analyze User Website Visit Pattern
 * We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].
 *
 * A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.
 * (The websites in a 3-sequence are not necessarily distinct.)
 *
 * Find the 3-sequence visited by the largest number of users. If there is more than one solution,
 * return the lexicographically smallest such 3-sequence.
 *
 *
 *
 * Example 1:
 *
 * Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"],
 * timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
 * Output: ["home","about","career"]
 * Explanation:
 * The tuples in this example are:
 * ["joe", 1, "home"]
 * ["joe", 2, "about"]
 * ["joe", 3, "career"]
 * ["james", 4, "home"]
 * ["james", 5, "cart"]
 * ["james", 6, "maps"]
 * ["james", 7, "home"]
 * ["mary", 8, "home"]
 * ["mary", 9, "about"]
 * ["mary", 10, "career"]
 * The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
 * The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
 * The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
 * The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
 * The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
 *
 *
 * This question has lots of detail to it. While conceptually not hard the wording of the question in leet code is very
 * unclear. This question is asked a whole lot by Amazon. I guess they are trying to check if candidate can ask
 * questions to clear out the ambiguity in a spec.
 * <p>
 * if asked this question, id ask clear questions to the interviewer to clarify the question.
 * <p>
 * see question wording from leet code below We are given some website visits: the user with name username[i] visited
 * the website website[i] at time timestamp[i].
 * <p>
 * A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits. (The websites
 * in a 3-sequence are not necessarily distinct.)
 * <p>
 * Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the
 * lexicographically smallest such 3-sequence.
 *
 * IMP-2 : The wording of this question is tricky, good to practice.
 */
public class UserWebsitePattern {

    public static void main(String[] args) {
//        String [] users = {"joe","joe","joe","james","james","james","james","mary","mary","mary"};
//        int []    times = {1,2,3,4,5,6,7,8,9,10};
//        String [] sites = {"home","about","career","home","cart","maps","home","home","about","career"};

        //String [] users = {"zkiikgv","zkiikgv","zkiikgv","zkiikgv"};
        //int []    times = {436363475,710406388,386655081,797150921};
        //String [] sites = {"wnaaxbfhxp","mryxsjc","oz","wlarkzzqht"};

        String[] users = {"h", "eiy", "cq", "h", "cq", "txldsscx", "cq", "txldsscx", "h", "cq", "cq"};
        int[] times = {527896567, 334462937, 517687281, 134127993, 859112386, 159548699, 51100299, 444082139, 926837079, 317455832, 411747930};
        String[] sites = {"hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "yljmntrclw", "hibympufi", "yljmntrclw"};

        UserWebsitePattern uWeb = new UserWebsitePattern();
        List<String> tuple = uWeb.mostVisitedPattern(users, times, sites);
        System.out.println(tuple);
    }


    /**
     * main caller method for the question. the code for each user finds combination of sites (3 sites) that the person
     * visited the combinations are permuted based on the sort of the times stamp that the site was visited.
     * <p>
     * then the list of sites across each users , where list consists of 3 sites is aggregated. when a site combination
     * repeats , the count for that combination is increased.
     * <p>
     * the site combination with highest frequency is returned. if two combs have same frequency one with lower lexical
     * sort order is returned
     *
     * @param username
     * @param timestamp
     * @param website
     * @return
     */
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Pair<Integer, String>>> userToSites = new HashMap<>();
        //create a map from user to list of sites by time stamp
        for (int i = 0; i < website.length; i++) {
            List<Pair<Integer, String>> webSites = userToSites.computeIfAbsent(username[i], (l) -> new ArrayList<>());
            webSites.add(new Pair<>(timestamp[i], website[i]));
        }

        Map<Tuple<String, String, String>, Integer> tupleToCount = new HashMap<>();
        for (Map.Entry<String, List<Pair<Integer, String>>> mapEntry : userToSites.entrySet()) {
            //iterate through the sites a user visited
            Set<Tuple<String, String, String>> tuples = new HashSet<>();
            List<Pair<Integer, String>> sitesByTime = mapEntry.getValue();
            //sort sites by time so the priority per spec is correct
            Collections.sort(sitesByTime, Comparator.comparingInt(a -> a.first));
            //convert the sites to a set of tuples. use set so repeated site combinations are not double counted
            convertSitesToTuples(tuples, sitesByTime, new LinkedList<>(), 0, 3);
            //process new tuples and aggregate total into the count of tuples across users
            for (Tuple<String, String, String> tuple : tuples) {
                Integer count = tupleToCount.getOrDefault(tuple, 0);
                tupleToCount.put(tuple, count + 1);
            }
        }

        //add to PQ that's sorted by count and then the lexical order.
        PriorityQueue<Pair<Tuple<String, String, String>, Integer>> pq = new PriorityQueue<>(
                (l, r) -> {
                    if (l.second.equals(r.second)) {
                        String a = l.first.toString();
                        String b = r.first.toString();
                        return a.compareTo(b);
                    } else {
                        return r.second.compareTo(l.second);
                    }
                });
        for (Map.Entry<Tuple<String, String, String>, Integer> entry : tupleToCount.entrySet()) {
            pq.add(new Pair<>(entry.getKey(), entry.getValue()));
        }

        //get the first element from PQ which the question is asking for.
        Tuple<String, String, String> tp = pq.poll().first;
        List<String> data = new ArrayList<>();
        data.add(tp.first);
        data.add(tp.second);
        data.add(tp.third);
        return data;
    }

    /**
     * permute through the sizes to find list of combinations equal to that specified by target
     * <p>
     * write only the signature of this method in interview and come back to completing it if you get time.
     *
     * @param tuples   this is a set so that we dont store duplicate tuples
     * @param sites
     * @param siteComb
     * @param index
     * @param target
     */
    void convertSitesToTuples(Set<Tuple<String, String, String>> tuples, List<Pair<Integer, String>> sites,
                              LinkedList<String> siteComb, int index, int target) {
        if (siteComb.size() == target) {
            Tuple<String, String, String> tuple = Tuple.of(siteComb.get(0), siteComb.get(1), siteComb.get(2));
            tuples.add(tuple);
            return;
        }

        int finalIndex = target - siteComb.size();

        for (int i = index; i <= (sites.size() - finalIndex); i++) {
            siteComb.add(sites.get(i).second);
            convertSitesToTuples(tuples, sites, siteComb, i + 1, target);
            siteComb.removeLast();
        }
    }
}
