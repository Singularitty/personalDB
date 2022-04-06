/**
 * Operation Engine
 * Executes program Operations
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 02/04/2022
 */

package personalDB.Engine;

import personalDB.Operation;
import personalDB.TerminalInterface.Help;
import personalDB.TerminalInterface.Interface;

import java.util.List;


public class OperationHandler {
     private final DirectoryHandler fileEngine;
     private final Interface screen;

    public OperationHandler(DirectoryHandler fileEngine, Interface screen) {
        this.fileEngine = fileEngine;
        this.screen = screen;
    }

    /**
     * Executes commands that might take arguments:
     * FIND, DELETE, CREATE, GOTO, OPEN, HELP, BACK
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
                fileEngine.backDir();
                break;
            case GOTO:
                if (args.size() == 2) {
                    fileEngine.goToDir(args.get(1));
                } else {
                  System.out.println("Too many arguments.");
                }
                break;
            case LS:
                screen.displayDirectoryContent();
                break;
            case RMDIR:
                if (args.size() == 2) {
                    fileEngine.deleteDir(args.get(1));
                } else {
                    System.out.println("Too many arguments");
                }
                break;
            case MKDIR:
                if (args.size() == 2) {
                    fileEngine.createDir(args.get(1));
                } else {
                    System.out.println("Too many arguments");
                }
                break;
            case CREATE:
                break;
            case DELETE:
                break;
            case OPEN:
                break;
            case FIND:
                break;
            case INVALID:
                System.out.println("Invalid command specified.");
                break;
        }
    }

}
