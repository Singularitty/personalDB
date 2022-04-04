/**
 * Operation Engine
 * Executes program Operations
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 02/04/2022
 */

package personalDB;

import java.util.List;


public class OperationHandler {
     private final FileHandlingEngine fileEngine;

    public OperationHandler(FileHandlingEngine fileEngine) {
        this.fileEngine = fileEngine;
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
            case EXIT:
                break;
            case HELP:
                Help.getHelp(args);
                break;
            case BACK:
                fileEngine.backDir();
                break;
            case FIND:
                break;
            case DELETE:
                break;
            case CREATE:
                break;
            case GOTO:
                if (args.size() == 2) {
                    fileEngine.goToDir(args.get(1));
                } else {
                  System.out.println("Too many arguments.");
                }
                break;
            case OPEN:
                break;
            case INVALID:
                break;
            case NONE:
                break;
        }
    }
}
