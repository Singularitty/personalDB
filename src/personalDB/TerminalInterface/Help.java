/**
 * Help screen
 * Displays program options
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 03/04/2022
 */

package personalDB.TerminalInterface;

import personalDB.Parser.Operation;
import personalDB.Parser.Parser;

import java.util.List;

public class Help {

    /**
     * Displays a help screen based on the arguments give
     * @param arguments Input split into tokens
     */
    public static void getHelp(List<String> arguments) {
        if (arguments.size() == 1) {
            displayCommands();
        } else if (arguments.size() == 2) {
            Operation queriedCommand = Parser.getCommand(arguments.get(1));
            usageInfo(queriedCommand);
        } else {
            System.out.println("Usage: HELP COMMAND");
        }
    }

    /**
     * Prints all available commands in the terminal screen
     */
    private static void displayCommands() {
        System.out.println("-- Available Commands --");
        System.out.println("EXIT: Exits the program");
        System.out.println("HELP: Displays this screen");
        System.out.println("FIND: Finds a file");
        System.out.println("DELETE: Deletes a file");
        System.out.println("CREATE: Creates a file");
        System.out.println("CD: Goes to the specified directory");
        System.out.println("OPEN: Opens specified file");
        System.out.println("BACK: Goes to previous directory");
        System.out.println("Type \"HELP COMMAND\" for usage information.");
    }

    /**
     * Prints usage instructions for the specified command
     * @param command Operation for which the instructions to will be displayed
     */
    private static void usageInfo(Operation command) {
        switch (command) {
            case HELP -> System.out.println("Displays all usable commands.\nUsage: HELP");
            case EXIT -> System.out.println("Exits the program.\nUsage: EXIT");
            case FIND -> System.out.println("Finds a file.\nUsage: FIND FILENAME\nE.g: FIND JpersonalDB.java");
            case DELETE -> System.out.println("Deletes a file in the current directory.\nUsage: DELETE FILENAME");
            case CREATE -> System.out.println("Creates a file in the current directory.\nUsage: CREATE FILENAME");
            case CD -> System.out.println("Go to the specified directory.\nUsage: CD DIRECTORY\nE.g: CD /home/documents/personalDB");
            case OPEN -> System.out.println("Opens the specified file in the current directory with the default program.\nUsage: OPEN FILENAME");
            case BACK -> System.out.println("Goes to the previous directory.\nUsage: BACK");
            default -> System.out.println("Invalid command specified.");
        }
    }

}
