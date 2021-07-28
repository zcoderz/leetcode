package box;

public class Driver {

    public static void main(String [] args) {
        DataTable table = new DataTable();
        SetAction setAction = new SetAction("a", "100");
        StringBuilder results = new StringBuilder();
        table.applyWork(setAction, results);
        GetAction getAction = new GetAction("a");
        table.applyWork(getAction, results);
        System.out.println(results);
        results.setLength(0);//reset
        getAction = new GetAction("b");
        table.applyWork(getAction, results);
        System.out.println(results);

        String res = table.rollBackTransaction();
        System.out.println(res);


        table.beginTransaction();
        results.setLength(0);

        getAction = new GetAction("c");
        table.applyWork(getAction, results);
        System.out.println(results); results.setLength(0);

        setAction = new SetAction("c", "300");
        table.applyWork(setAction, results);
        table.applyWork(getAction, results);
        System.out.println(results); results.setLength(0);

        table.beginTransaction();
        setAction = new SetAction("d", "400");
        table.applyWork(setAction, results);
        table.rollBackTransaction();

        getAction = new GetAction("d");
        table.applyWork(getAction, results);
        System.out.println(results); results.setLength(0);

        setAction = new SetAction("d", "100");
        table.applyWork(setAction, results);

        CountAction countAction = new CountAction("100");
        table.applyWork(countAction, results);
        System.out.println(results); results.setLength(0);
        res = table.commitTransaction();

        System.out.println(res);
    }
}
