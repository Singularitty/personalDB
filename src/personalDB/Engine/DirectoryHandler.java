/**
 * Handles all files operations
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 03/04/2022
 */

package personalDB.Engine;

import personalDB.exceptions.InvalidDirectory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class stores information about the current directory and handles
 * all directory related operations
 */
public class DirectoryHandler {
    private File currentDir;

    /**
     * Creates an instance of this class
     * @param currentDir Current working directory
     * @requires currentDir is a directory
     */
    public DirectoryHandler(String currentDir) throws InvalidDirectory {
        this.currentDir = new File(currentDir);
        if (!this.currentDir.isDirectory()) {
            throw new InvalidDirectory("This is not a directory.");
        }
    }

    /**
     * Returns current directory
     * @return String representing the current directory
     */
    public String getCurrentDir () {
        return currentDir.getPath();
    }

    /**
     * Returns all the names files and directories in the current directory
     * @return List of files and directories in the current directory
     */
    public String[] listAll () {
        return this.currentDir.list();
    }

    /**
     * Goes to the previous directory in the path
     */
    public void backDir() {
        List<String> oldPath = pathParser(this.currentDir.getPath());
        StringBuilder newPath = new StringBuilder();

        if (oldPath.size() > 1) {
            for (int i = 0; i < oldPath.size() - 1; i++) {
                newPath.append(oldPath.get(i)).append(File.separator);
            }
            newPath.deleteCharAt(newPath.length()-1);
            this.assignDirectory(newPath.toString());
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
            this.assignDirectory(this.currentDir.getPath() + File.separator + newPath);
        } else {
            this.assignDirectory(newPath);
        }
    }

    /**
     * Creates a new diretory for the path given if it is not a directory already
     * @param dirPath Path to new directory
     * @requires dirPath != null
     */
    public void createDir(String dirPath) {
        File tempDir = this.finalPath(dirPath);
        if (!tempDir.isDirectory()) {
            tempDir.mkdir();
        }
    }

    /**
     * Deletes the specified directory if it exists
     * @param dirPath Directory to be deleted
     */
    public void deleteDir(String dirPath) {
        File tempDir = this.finalPath(dirPath);
        if (tempDir.isDirectory()) {
            tempDir.delete();
        }
    }

    /**
     * Takes a path and returns a File with the fullPath
     * @param path new path
     * @return File with the correct final path
     */
    private File finalPath(String path) {
        if (!isFullPath(path)) {
            return new File(this.currentDir.getPath() + File.separator + path);
        }
        return new File(path);
    }

    /**
     * Checks if the full path is a valid directory and sets it as new currentDir if so
     * @param fullPath Path to the directory
     */
    private void assignDirectory(String fullPath) {
        File tempDir = new File(fullPath);
        if (!tempDir.isDirectory()) {
            System.out.println("That directory does not exist.");
        } else {
            this.currentDir = tempDir;
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
        List<String> currentPath = pathParser(this.currentDir.getPath());

        for (int i = 0; i < Math.min(newPath.size(), currentPath.size()); i++) {
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

        List<String> pathTokens = new ArrayList<>();
        String separator = File.separator.replace("\\","\\\\");
        Collections.addAll(pathTokens, path.split(separator));
        return pathTokens;
    }
}
