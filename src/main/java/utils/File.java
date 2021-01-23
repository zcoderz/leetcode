package utils;

import java.util.List;

public class File implements IFile {
    private String strName;
    private Directory parentDir;
    private StringBuilder builder = new StringBuilder();


    public File(String strName, Directory parentDir) {
        this.strName = strName;
        this.parentDir = parentDir;
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public IFile getParentDirectory() {
        return parentDir;
    }

    @Override
    public String getName() {
        return strName;
    }

    @Override
    public void addContentToFile(String strVal) {
        builder.append(strVal);
    }

    @Override
    public String getContent() {
        return builder.toString();
    }

    @Override
    public void addFile(IFile file) {

    }

    @Override
    public void setIsRoot(boolean isRoot) {

    }

    @Override
    public boolean isRoot() {
        return false;
    }

    @Override
    public List<IFile> getChildren() {
        return null;
    }

    @Override
    public IFile getChild(String name) {
        return null;
    }
}
