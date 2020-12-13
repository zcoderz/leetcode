package design;

import utils.Directory;
import utils.File;
import utils.IFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Simple set of methods to implement in memory file system
 * the code assumes that the paths being passed are syntactically correct
 * in production code there should be piece that validates the paths for correctness
 *
 *
 */
public class FileSystem {

    public static void main(String [] args) {
        FileSystem fs = new FileSystem();
        fs.ls("/");
        fs.mkdir("/a/b/c");
        List<String> strL = fs.ls("/a/b");
        for (String str: strL) {
            System.out.println(str);
        }
        fs.addContentToFile("/a/b/c/d","hello");

        fs.readContentFromFile("/a/b/c/d");

        System.out.println("hello....");
    }

    Directory root;
    public FileSystem() {
        root = new Directory("Root" , null );
        root.setIsRoot(true);
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
        String [] dirs =  path.substring(1).split("/");
        IFile curr = root;
        for(String dir: dirs) {
            IFile child = curr.getChild(dir);
            if (child == null) {
                child = new Directory(dir, (Directory) curr);
                curr.addFile(child);
            }
            curr = child;
        }
    }

    public void addContentToFile(String filePath, String content) {
        String [] dirs =  filePath.substring(1).split("/");
        IFile curr = root;
        for(String dir: dirs) {
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
        String [] dirs =  filePath.substring(1).split("/");
        IFile curr = root;
        for(String dir: dirs) {
            curr = curr.getChild(dir);
        }
        return curr.getContent();
    }
}
