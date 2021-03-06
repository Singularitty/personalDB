/**
 * Operation Engine
 * Executes program Operations
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 02/04/2022
 */

package personalDB.Engine;

import personalDB.Parser.Operation;
import personalDB.TerminalInterface.Help;
import personalDB.TerminalInterface.Interface;

import java.util.List;


public class OperationHandler {
     private final DirectoryHandler directoryHandler;
     private final FileHandler fileHandler;
     private final Interface screen;

    public OperationHandler(DirectoryHandler directoryHandler, FileHandler fileHandler, Interface screen) {
        this.directoryHandler = directoryHandler;
        this.fileHandler = fileHandler;
        this.screen = screen;
    }

    /**
     * Executes commands that might take arguments:
     * FIND, DELETE, CREATE, CD, OPEN, HELP, BACK
     * @param command Operation that represents a command the user
     *               would like to perform
     * @param args Arguments for said command
     * @requires args cannot be empty
     */
    public void execute(Operation command, List<String> args) {
        switch(command) {
            case HELP:
                Help.getHelp(args);
                break;
            case BACK:
                directoryHandler.goBack();
                break;
            case CD:
                if (args.size() == 1) {
                    directoryHandler.cdDir(args.get(0));
                } else {
                  System.out.println("Too many arguments.");
                }
                break;
            case LS:
                screen.displayDirectoryContent();
                break;
            case RMDIR:
                if (args.size() == 1) {
                    directoryHandler.deleteDir(args.get(0));
                } else {
                    System.out.println("Too many arguments.");
                }
                break;
            case MKDIR:
                if (args.size() == 1) {
                    directoryHandler.mkDir(args.get(0));
                } else {
                    System.out.println("Too many arguments.");
                }
                break;
            case CREATE:
                if (args.size() == 1) {
                    fileHandler.createFile(args.get(0));
                } else {
                    System.out.println("Too many arguments.");
                }
                break;
            case DELETE:
                if (args.size() == 1) {
                    fileHandler.deleteFile(args.get(0));
                } else {
                    System.out.println("Too many arguments.");
                }
                break;
            case OPEN:
                if (args.size() == 1) {
                    fileHandler.openFile(args.get(0));
                } else {
                    System.out.println("Too many arguments.");
                }
                break;
            case FIND:
                break;
            case INVALID:
                System.out.println("Invalid command specified.");
                break;
        }
    }

}
