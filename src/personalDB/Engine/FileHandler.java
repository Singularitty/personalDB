/**
 * Handles all file related operations
 * @author Luís Ferreirinha
 * @email luispedroferreirinha@gmail.com
 * @date 07/04/2022
 */

package personalDB.Engine;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Objects of this class handle operations related to files
 */
public class FileHandler {
    private final DirectoryHandler directory;

    /**
     * Constructor
     * @param directory Object identifying current directory
     * @requires directory != null
     */
    public FileHandler(DirectoryHandler directory) {
        this.directory = directory;
    }

    /**
     * Creates a new file in the current directory
     * @param filename Name of the new file
     */
    public void createFile(String filename) {
        try {
            File newFile = new File(directory.getCurrentDir() + File.separator + filename);
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("IOError could not write file.\n" + e);
        } catch (SecurityException e) {
            System.out.println("Access denied could not write file.\n" + e);
        }
    }

    /**
     * Deletes the specified file in the current directory
     * @param filename Name of the file to be deleted
     */
    public void deleteFile(String filename) {
        try {
            File newFile = new File(directory.getCurrentDir() + File.separator + filename);
            newFile.delete();
        } catch (SecurityException e) {
            System.out.println("Access denied could not delete file.\n" + e);
        }
    }

    /**
     * Opens the file with the given filename with its default application if said file exists
     * @param filename name of the file
     */
    public void openFile(String filename) {
        try {
            if (Desktop.isDesktopSupported()) {
                File tempFile = new File(directory.getCurrentDir() + File.separator + filename);
                Desktop.getDesktop().open(tempFile);
            } else {
                System.out.println("This is operation is not support on the current platform.");
            }
        } catch (IOException e) {
            System.out.println("the specified file has no associated application or the associated application fails to be launched.\n" + e);
        } catch (IllegalArgumentException e) {
            System.out.println("The specified file does not exist.");
        }
    }

}
