package box;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WorkUnit {
    LinkedList<LinkedList<ModificationInterface>> workUnit = new LinkedList<>();

    void createTransaction() {
        workUnit.add(new LinkedList<>());
    }

    boolean rollbackTransaction() {
        if (workUnit.isEmpty())  {
            return false;
        }
        workUnit.removeLast();
        return true;
    }

    List<ModificationInterface> getAndDeleteNextWork() {
        if (workUnit.isEmpty()) return null;
        return workUnit.removeFirst();
    }

    ModificationInterface checkForKey(String key) {
        Iterator<LinkedList<ModificationInterface>> topIter = workUnit.descendingIterator();
        while (topIter.hasNext()) {
            LinkedList<ModificationInterface> nextList = topIter.next();
            Iterator<ModificationInterface> nextIter = nextList.descendingIterator();
            while (nextIter.hasNext()) {
                ModificationInterface mod = nextIter.next();
                String fKey = mod.getKey();
                if (fKey.equals(key)) {
                    return mod;
                }
            }
        }
        return null;
    }

    LinkedList<LinkedList<ModificationInterface>> getWorkUnits() {
        return workUnit;
    }

    boolean transInProgress() {
        return workUnit.size() > 0;
    }

    void addWork(ModificationInterface newWork) {
        if (workUnit.isEmpty()) return;
        workUnit.getLast().add(newWork);
    }
}
