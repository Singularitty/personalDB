/**
 * Parser
 * Parses user input
 * Can return commands and arguments
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 02/04/2022
 */

package personalDB;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class handles user input and parsing. It also returns tokens identifying
 * operations in the program
 */
public class Parser {

    /**
     * Reads from stdin and returns the value read has a String
     * @return String containing the input from stdin
     */
    public static String getInput() {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        return input;
    }

    /**
     * Parses a string in order to identify tokens and returns then in a string
     * @param input String to be parsed
     * @return List of tokens
     */
    public static Operation getCommand(String input) {

        List<String> Tokens = new ArrayList<String>();

        for (String token : input.split(" ")) {
            Tokens.add(token);
        }

        Operation action = Operation.NONE;

        switch (Tokens.get(0).toUpperCase()) {
            case "EXIT":
                action = Operation.EXIT;
                break;
            case "HELP":
                action = Operation.HELP;
                break;
            case "FIND":
                action = Operation.FIND;
                break;
            case "DELETE":
                action = Operation.DELETE;
                break;
            case "CREATE":
                action = Operation.CREATE;
                break;
            case "GOTO":
                action = Operation.GOTO;
                break;
            case "OPEN":
                action = Operation.OPEN;
                break;
            case "BACK":
                action = Operation.BACK;
                break;
            default:
                action = Operation.INVALID;
                break;
        }

        return action;
    }

    /**
     * Uses whitespace to split a string into tokens, removes the first
     * token (assumed to be the command token) and returns the rest of the tokens
     * which are assumed to be the arguments in a List.
     * @param input String to be parsed
     * @return List of strings assumed to be the arguments in the input string
     */
    public static List<String> getArgs(String input) {

        List<String> args = new ArrayList<String>();
        String[] Tokens = input.split(" ");

        for (int i = 1; i < Tokens.length; i++) {
            args.add(Tokens[i]);
        }

        return args;
    }

}
