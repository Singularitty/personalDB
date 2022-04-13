/**
 * Handles all search related operations for files and directories
 * @author Luís Ferreirinha
 * @email luispedroferreirinha@gmail.com
 * @date 10/04/2022
 */

package personalDB.Engine;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Finder {
    private DirectoryHandler searchDirectory;
    private String searchQuery;

    public Finder(DirectoryHandler searchDirectory, String searchQuery) {
        this.searchDirectory = searchDirectory;
        this.searchQuery = searchQuery;
    }

    public File findFirstFileStrict() {
        Queue<File> queue = new LinkedList<>();
        File[] rootDirectoryContent = this.searchDirectory.getDirectory().listFiles();

        if (rootDirectoryContent.length == 0) {
            System.out.println("This directory is empty!");
        } else {
            for (File f : rootDirectoryContent) {
                if (f.isFile()) {
                    if (f.getName().equals(this.searchQuery)) {
                        return f;
                    }
                } else {
                    queue.add(f);
                }
            }
            for (File f : queue) {
                if (f.isFile()) {
                    if (f.getName().equals(this.searchQuery)) {
                        return f;
                    }
                } else {
                    queue.add(f);
                }
            }
        }
        return null;
    }

}
