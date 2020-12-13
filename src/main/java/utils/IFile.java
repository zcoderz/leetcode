package utils;

import java.util.List;

/**
 * The interface includes union of methods in File and Directory classes.
 * We could add here a subset of methods that are valid for both files and directories
 * which is in interface designer a good philosophy but that will require client code
 * to do casts to access respective directory and file methods
 *
 */
public interface IFile {
    boolean isFile();
    boolean isDirectory();
    IFile getParentDirectory();
    String getName();
    IFile getChild(String name);
    void addContentToFile(String strVal);
    String getContent();
    public void addFile(IFile file);
    public void setIsRoot(boolean isRoot);
    boolean isRoot();
    List<IFile> getChildren();
}
