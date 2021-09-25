package face_book.medium;


import java.util.LinkedList;

/**
 * 71. Simplify Path
 * Medium
 *
 * 743
 *
 * 195
 *
 * Add to List
 *
 * Share
 * Given a string path, which is an absolute path (starting with a slash '/')
 * to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
 *
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..'
 * refers to the directory up a level, and any multiple consecutive slashes (i.e. '//')
 * are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
 *
 * The canonical path should have the following format:
 *
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory to the target file
 * or directory (i.e., no period '.' or double period '..')
 * Return the simplified canonical path.
 *
 *
 *
 * Example 1:
 *
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 */
public class SimplifyPath {

    public static void main(String [] args) {
        String test = "/a/./b////../../c////";
        SimplifyPath simplifyPath = new SimplifyPath();
        String out = simplifyPath.simplifyPath(test);
        System.out.println(out);
        out = simplifyPath.simplifyPath("/home/blahh////");
        System.out.println(out);
        out = simplifyPath.simplifyPath("/../");
        System.out.println(out);
    }

    public String simplifyPath(String path) {
        String [] split = path.split("/");
        LinkedList<String> stack = new LinkedList<>();
        for (String str : split) {
            if (str.equals(".") || str.isEmpty()) {
                continue;
            }
            if (str.equals("..")) {
                if (stack.size() > 0) {
                    stack.removeLast();
                }
                continue;
            }
            stack.add(str);
        }

        StringBuilder newPath = new StringBuilder("");
        for (String str: stack) {
            newPath.append("/");
            newPath.append(str);
        }
        return newPath.length() == 0? "/" : newPath.toString();

    }

    public String simplifyPathComplex(String path) {
        LinkedList<String> stack = new LinkedList<>();
        for (int i = 0; i < path.length(); ) {
            if (path.charAt(i) == '/' ||  path.startsWith("./", i)) {
                i++;
                continue;
            }
            if (path.startsWith("../", i) || (path.startsWith("..", i) && i+2 == path.length())) {
                stack.removeLast();
                i = i +2;
            } else {
                StringBuilder str = new StringBuilder();
                while (i != path.length() && path.charAt(i) != '/') {
                    str.append(path.charAt(i));
                    i++;
                }
                stack.add(str.toString());
            }
        }
        StringBuilder newPath = new StringBuilder("/");
        for (String str: stack) {
            newPath.append(str);
            newPath.append("/");
        }
        newPath.delete(newPath.length()-1, newPath.length());
        return newPath.toString();
    }
}
