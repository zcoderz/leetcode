package utils;

import java.util.ArrayList;
import java.util.List;

public class Directory implements IFile {
    private boolean isRoot;
    private String name;
    private Directory parent;
    //could change this to hash map for faster lookups.
    private List<IFile> children = new ArrayList<>();

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }


    @Override
    public void addFile(IFile file) {
        children.add(file);
    }

    @Override
    public void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    @Override
    public boolean isRoot() {
        return isRoot;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public IFile getParentDirectory() {
        return parent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<IFile> getChildren() {
        return children;
    }

    @Override
    public IFile getChild(String name) {
        for (IFile child : children) {
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }

    @Override
    public void addContentToFile(String strVal) {
    }

    @Override
    public String getContent() {
        return null;
    }
}
