package box;

public class DeleteAction implements  ModificationInterface{
    String key;

    public DeleteAction(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return null;
    }
}
