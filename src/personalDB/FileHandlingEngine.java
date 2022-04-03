/**
 * Handles all files operations
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 03/04/2022
 */

package personalDB;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class should only have one object created at a time
 * It stores information about the current directory and handles
 * all the file operations
 */
public class FileHandlingEngine {
    private String currentDir;

    /**
     * Creates an instance of this class
     * @param currentDir Current working directory
     */
    public FileHandlingEngine(String currentDir) {
        this.currentDir = currentDir;
    }

    /**
     * Returns current directory
     * @return String representing the current directory
     */
    public String getCurrentDir () {
        return this.currentDir;
    }


    /**
     * Goes to the previous directory in the path
     */
    public void backDir() {
        List<String> oldPath = pathParser(this.currentDir);
        StringBuilder newPath = new StringBuilder();

        if (oldPath.size() > 1) {
            for (int i = 0; i < oldPath.size() - 1; i++) {
                newPath.append(oldPath.get(i) + File.separator);
            }
            newPath.deleteCharAt(newPath.length()-1);
            this.currentDir = newPath.toString();
        } else {
            System.out.println("This is the root directory.");
        }
    }

    /**
     * Sets the current directory to the specified directory
     * If the new path is a fullPath the current directory is set to that
     * If it's not a full path the specified path is added to the current path
     * @param newPath New path
     */
    public void goToDir(String newPath) {

        if (!isFullPath(newPath)) {
            StringBuilder tempPath = new StringBuilder();
            tempPath.append(this.currentDir + File.separator + newPath);
            this.currentDir = tempPath.toString();
        } else {
            this.currentDir = newPath;
        }
    }

    /**
     * Checks if the given path contains the old path
     * That is if the specified path is a full path to the directory
     * or if it's just a directory inside the current directory
     * @param path Path to be checked
     * @return True if the given path is a full path
     */
    private boolean isFullPath(String path) {
        List<String> newPath = pathParser(path);
        List<String> currentPath = pathParser(this.currentDir);

        int smallestLength;
        if (newPath.size() < currentPath.size()) {
            smallestLength = newPath.size();
        } else {
            smallestLength = currentPath.size();
        }

        for (int i = 0; i < smallestLength; i++) {
            if (!newPath.get(i).equals(currentPath.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Takes the current path and returns all the directories composing that path in a list
     * @return List containing all the directories that make up that path
     */
    private List<String> pathParser(String path) {

        List<String> pathTokens = new ArrayList<String>();

        String separator = File.separator.replace("\\","\\\\");

        for (String s : path.split(separator)) {
            pathTokens.add(s);
        }

        return pathTokens;
    }



}
