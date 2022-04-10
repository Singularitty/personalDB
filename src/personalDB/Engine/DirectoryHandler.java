/**
 * Handles all directory operations
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 03/04/2022
 */

package personalDB.Engine;

import org.jetbrains.annotations.NotNull;
import personalDB.Exceptions.InvalidDirectory;

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
     * Creates an instance of this class with a directory that might
     * not exist
     * @param directory File specifying a directory
     */
    private DirectoryHandler(File directory) {
        this.currentDir = directory;
    }

    /**
     * Returns current directory
     * @return String representing the current directory
     */
    public String getCurrentDir () {
        return currentDir.getPath();
    }

    /**
     * Goes to the parent directory of the current directory
     */
    public void goBack() {
        File parentDirectory = this.currentDir.getParentFile();
        if (parentDirectory == null) {
            System.out.println("This is the root directory.");
        } else {
            this.currentDir = parentDirectory;
        }
    }

    /**
     * Changes the current directory to the specified directory if it exists
     * @param pathname String representing the pathname of the new directory
     */
    public void cdDir(@NotNull String pathname) {
        File newDirectory;
        if (pathname.startsWith("..")) {
            List<String> lexemes = this.pathParser(pathname);
            if (lexemes.size() == 1) {
                this.goBack();
            } else {
                lexemes.remove(0);
                this.goBack();
                StringBuilder newPath = new StringBuilder();
                for (String lexeme : lexemes) {
                    newPath.append(lexeme).append(File.separator);
                }
                newPath.deleteCharAt(newPath.length()-1);
                this.cdDir(newPath.toString());
            }
        } else {
            if (isAbsolute(pathname)) {
                newDirectory = new File(pathname);
            } else {
                String absolutePathname = this.currentDir.getAbsolutePath() + File.separator + pathname;
                newDirectory = new File(absolutePathname);
            }
            if (newDirectory.isDirectory()) {
                this.currentDir = newDirectory;
            } else {
                System.out.println("That directory does not exist.");
            }
        }
    }

    /**
     * Deletes the directory with the specified pathname if it is empty and the program
     * has delete access.
     * @param pathname Path of the directory to be deleted
     */
    public void deleteDir(@NotNull String pathname) {
        File tempDir = getCorrectPathFile(pathname);
        if (tempDir.isDirectory()) {
            try {
                if (!tempDir.delete()) {
                    System.out.println("Directory is not empty.");
                }
            } catch (SecurityException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("This is not a directory.");
        }
    }

    /**
     * Creates a directory with the given pathname
     * @param pathname Path of the new directory
     */
    public void mkDir(@NotNull String pathname) {
        File tempDir = getCorrectPathFile(pathname);
        if (tempDir.isDirectory()) {
            System.out.println("This directory already exists.");
        } else {
            try {
                if (!tempDir.mkdir()) {
                    System.out.println("Could not create directory.");
                }
            } catch (SecurityException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Calculates the correct pathname for a given pathname
     * and returns a File object with that pathname.
     * @param pathname String representing that pathname
     * @return File with the correct pathname (Never returns null the compiler is dumb
     *         and doesn't know that the method is recursive).
     */
    private File getCorrectPathFile(@NotNull String pathname) {
        DirectoryHandler tempHandler = new DirectoryHandler(this.currentDir);
        File correctDirectory = null;
        if (pathname.startsWith("..")) {
            List<String> lexemes = tempHandler.pathParser(pathname);
            if (lexemes.size() == 1) {
                tempHandler.goBack();
            } else {
                lexemes.remove(0);
                tempHandler.goBack();
                StringBuilder newPath = new StringBuilder();
                for (String lexeme : lexemes) {
                    newPath.append(lexeme).append(File.separator);
                }
                newPath.deleteCharAt(newPath.length() - 1);
                tempHandler.cdDir(newPath.toString());
            }
        } else {
            if (isAbsolute(pathname)) {
                correctDirectory = new File(pathname);
            } else {
                String absolutePathname = tempHandler.currentDir.getAbsolutePath() + File.separator + pathname;
                correctDirectory = new File(absolutePathname);
            }
        }
        return correctDirectory;
    }


    /**
     * Returns all the content of a directory in an array of strings
     * @return String[] with all the content of that directory
     */
    public String[] listAll() {
        return this.currentDir.list();
    }

    /**
     * Takes a pathname and splits it into the respective directories that make it up
     * utilizing the operating systems' separator char as a regular expression.
     * @param pathname Pathname of the directory/file
     * @return List containing all the directories names that make that pathname
     */
    private List<String> pathParser(@NotNull String pathname) {
        List<String> result = new ArrayList<>();
        Collections.addAll(result, pathname.split(File.separator.replace("\\","\\\\")));
        return result;
    }

    /**
     * Checks if a given pathname is absolute or relative
     * @param pathname String representing the pathname
     * @return True if it is absolute
     */
    private static boolean isAbsolute(@NotNull String pathname) {
        if (pathname.matches("^[\\,\\\\,/].*$")) {
            return true;
        } else return pathname.matches("^[A-Z]:[\\,\\\\,/].*$");
    }

}