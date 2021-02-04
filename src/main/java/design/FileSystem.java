package design;

import utils.Directory;
import utils.File;
import utils.IFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 588. Design In-Memory File System
 * Design an in-memory file system to simulate the following functions:
 *
 * ls: Given a path in string format. If it is a file path, return a list that only contains this file's name.
 * If it is a directory path, return the list of file and directory names in this directory.
 * Your output (file and directory names together) should in lexicographic order.
 *
 * mkdir: Given a directory path that does not exist, you should make a new directory according to the path.
 * If the middle directories in the path don't exist either, you should create them as well.
 * This function has void return type.
 *
 * addContentToFile: Given a file path and file content in string format.
 * If the file doesn't exist, you need to create that file containing given content.
 * If the file already exists, you need to append given content to original content. This function has void return type.
 *
 * readContentFromFile: Given a file path, return its content in string format.
 *
 *
 *
 * Example:
 *
 * Input:
 * ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
 * [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
 *
 * Output:
 * [null,[],null,null,["a"],"hello"]
 *
 * Explanation:
 *
 *
 * Simple set of methods to implement in memory file system the code assumes that the paths being passed are
 * syntactically correct in production code there should be piece that validates the paths for correctness
 *
 * IMP-1 : nice design question to practice
 */
public class FileSystem {

    Directory root;

    public FileSystem() {
        root = new Directory("Root", null);
        root.setIsRoot(true);
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        fs.ls("/");
        fs.mkdir("/a/b/c");
        List<String> strL = fs.ls("/a/b");
        for (String str : strL) {
            System.out.println(str);
        }
        fs.addContentToFile("/a/b/c/d", "hello");

        fs.readContentFromFile("/a/b/c/d");

        System.out.println("hello....");
    }

    public List<String> ls(String path) {
        IFile curr = root;
        //when the path being requested is just "/" it breaks the dirs into an array containing an empty string
        //which the below code cant iterate on. hence skip as we dont need to iterate on sub directories when
        //root is requested
        if (!path.equals("/")) {
            String[] dirs = path.substring(1).split("/");
            for (String dir : dirs) {
                curr = curr.getChild(dir);
            }
        }
        List<String> children = new ArrayList<>();
        if (curr.isDirectory()) {
            for (IFile child : curr.getChildren()) {
                children.add(child.getName());
            }
            Collections.sort(children); //return in ascending order
        } else {
            children.add(curr.getName());
        }

        return children;
    }

    public void mkdir(String path) {
        String[] dirs = path.substring(1).split("/");
        IFile curr = root;
        for (String dir : dirs) {
            IFile child = curr.getChild(dir);
            if (child == null) {
                child = new Directory(dir, (Directory) curr);
                curr.addFile(child);
            }
            curr = child;
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] dirs = filePath.substring(1).split("/");
        IFile curr = root;
        for (String dir : dirs) {
            IFile prior = curr;
            curr = curr.getChild(dir);
            if (curr == null) {
                curr = new File(dir, (Directory) prior);
                prior.addFile(curr);
            }
        }
        curr.addContentToFile(content);
    }

    public String readContentFromFile(String filePath) {
        String[] dirs = filePath.substring(1).split("/");
        IFile curr = root;
        for (String dir : dirs) {
            curr = curr.getChild(dir);
        }
        return curr.getContent();
    }
}
