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
    private FileHandlingEngine fileEngine;

    public OperationHandler(FileHandlingEngine fileEngine) {
        this.fileEngine = fileEngine;
    }

    /**
     * Executes commands that might take arguments:
     * FIND, DELETE, CREATE, GOTO, OPEN, HELP, BACK
     * @param command Operation that represents an command the user
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
                if (args.size() == 1) {
                    fileEngine.goToDir(args.get(0));
                } else {
                  System.out.println("Too many arguments.");
                }
                break;
        }
    }
}
