package box;

import java.util.*;

public class DataTable {

    Map<String, String> dataTable = new HashMap<>();
    Map<String, Set<String>> valueCount = new HashMap<>();

    WorkUnit workUnit = new WorkUnit();

    public void beginTransaction() {
        workUnit.createTransaction();
    }

    public String rollBackTransaction() {
        if (!workUnit.rollbackTransaction()) {
            return "NO TRANSACTION";
        }
        return "";
    }

    public String commitTransaction() {
        if (!transactionInProgress()) {
            return "";
        }
        return applyWork();
    }

    public boolean transactionInProgress() {
        return workUnit.transInProgress();
    }

    public void applyWork(ModificationInterface work, StringBuilder results) {
        if (work instanceof GetAction) {
            processGet(work, results);
            return;
        }
        if (work instanceof CountAction) {
            processCount(work, results);
               return;
        }

        if (transactionInProgress()) {
            queueWork(work);
            return;
        }

        if (work instanceof SetAction) {
            processSet(work);
            return;
        }

        if (work instanceof DeleteAction) {
            processDelete(work);
        }
    }

    void processCount(ModificationInterface work, StringBuilder res) {
        String val = work.getValue();
        Set<String> keys = valueCount.get(val);
        if (keys == null) {
            keys = new HashSet<>();
        }
        LinkedList<LinkedList<ModificationInterface>> works = workUnit.getWorkUnits();
        for (LinkedList<ModificationInterface> list: works) {
            for (ModificationInterface mod: list) {
                if (mod instanceof DeleteAction) {
                    String k = mod.getKey();
                    keys.remove(k);
                } else if (mod instanceof SetAction) {
                    keys.remove(mod.getKey());
                    if (mod.getValue().equals(val)) {
                        keys.add(mod.getKey());
                    }
                }

            }
        }
        res.append(keys.size()).append("\n");
    }

    void processGet(ModificationInterface work, StringBuilder res) {
        String key = work.getKey();
        ModificationInterface exists = workUnit.checkForKey(key);

        if (exists instanceof DeleteAction) {
            res.append("NULL").append("\n");
            return;
        }

        if (exists instanceof SetAction) {
            res.append(exists.getValue()).append("\n");
            return;
        }

        if (dataTable.containsKey(key)) {
            res.append(dataTable.get(key)).append("\n");
        } else {
            res.append("NULL").append("\n");
        }
    }

    void processDelete(ModificationInterface work) {
        String key = work.getKey();
        String oldVal = dataTable.get(key);
        dataTable.remove(key);
        if (oldVal != null) {
            valueCount.get(oldVal).remove(key);
        }
    }

    void processSet(ModificationInterface work) {
        String key = work.getKey();
        String value = work.getValue();
        String oldVal = dataTable.get(key);
        dataTable.put(key, value);
        Set<String> keys = valueCount.get(oldVal);
        if (keys != null) {
            keys.remove(key);
        }
        keys = valueCount.getOrDefault(value, new HashSet<>());
        keys.add(key);
    }

    void queueWork(ModificationInterface work) {
        workUnit.addWork(work);
    }

    String applyWork() {
        StringBuilder results = new StringBuilder();
        List<ModificationInterface> work =  workUnit.getAndDeleteNextWork();

        while (work != null) {
            applyWork(work, results);
            work =  workUnit.getAndDeleteNextWork();
        }

        return results.toString();
    }

    void applyWork(List<ModificationInterface> work, StringBuilder results) {
        for (ModificationInterface theWork : work) {
            applyWork(theWork, results);
        }
    }

}
