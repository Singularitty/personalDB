/**
 * Parser
 * Parses user input
 * Can return commands and arguments
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 02/04/2022
 */

package personalDB;


import personalDB.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class handles user input and parsing. It also returns tokens identifying
 * operations in the program
 */
public class Parser {
    private final String userInput;
    private int cursor = 0;

    /**
     * Constructor
     * @requires userInput != null
     * @param userInput input from the user
     */
    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Reads from stdin and returns the value read has a String
     * @return String containing the input from stdin
     */
    public static String getInput() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    /**
     * Parses a string in order to identify tokens and returns then in a string
     * @param input String to be parsed
     * @return List of tokens
     */
    public static Operation getCommand(String input) {

        List<String> Tokens = new ArrayList<>(Arrays.asList(input.split(" ")));

        return switch (Tokens.get(0).toUpperCase()) {
            case "EXIT" -> Operation.EXIT;
            case "HELP" -> Operation.HELP;
            case "FIND" -> Operation.FIND;
            case "DELETE" -> Operation.DELETE;
            case "CREATE" -> Operation.CREATE;
            case "GOTO" -> Operation.GOTO;
            case "OPEN" -> Operation.OPEN;
            case "BACK" -> Operation.BACK;
            case "MKDIR" -> Operation.MKDIR;
            case "RMDIR" -> Operation.RMDIR;
            case "LS" -> Operation.LS;
            default -> Operation.INVALID;
        };
    }

    /**
     * Uses whitespace to split a string into tokens, removes the first
     * token (assumed to be the command token) and returns the rest of the tokens
     * which are assumed to be the arguments in a List.
     * @param input String to be parsed
     * @return List of strings assumed to be the arguments in the input string
     */
    public static List<String> getArgs(String input) throws InvalidInputException {

        Parser inputParser = new Parser(input);

        List<String> args = new ArrayList<>();

        StringBuilder token = new StringBuilder();

        while (!inputParser.endOfLine()) {
            if (inputParser.peekCurrentChar() != ' ') {
                if (inputParser.peekCurrentChar() != '\"') {
                    token.append(inputParser.getCurrentChar());
                } else {
                    String result = inputParser.getStringInQuotes();
                    if (result.equals("\"")) {
                        throw new InvalidInputException("Invalid command/path given: Quotation marks not closed!");
                    } else args.add(result);
                }
            } else {
                if (token.length() == 0) {
                    inputParser.advanceCursor();
                } else {
                    args.add(token.toString());
                    token.setLength(0);
                    inputParser.advanceCursor();
                }
            }
        }

        if (token.length() > 0) {
            args.add(token.toString());
        }

        return args;
    }

    /**
     * Gets the string that was inside quotation marks
     * @return String
     */
    private String getStringInQuotes() {
        int n = 1;
        while (!this.nextEOL(n)) {
            if (this.peekNextN(n) == '\"') {
                int oldCursor = this.cursor;
                this.cursor += n+1;
                return this.userInput.substring(oldCursor+1, this.cursor-1);
            } else {
                n++;
            }
        }
        return "\"";
    }

    /**
     * Checks if cursor is at the end of the string
     * @return True if cursor is at the end of string
     */
    private boolean endOfLine() {
        return this.cursor == this.userInput.length();
    }

    /**
     * Checks if the next nth character is the end of the string
     * @return True if so
     */
    private boolean nextEOL(int n) {
        return this.cursor + n == this.userInput.length();
    }

    /**
     * Advances cursor
     */
    private void advanceCursor() {
        this.cursor++;
    }

    /**
     * Gets the current char at the cursor position and moves the cursor
     * @requires endOfLine == false
     * @return Char at current cursor position
     */
    private char getCurrentChar() {
        char result = this.userInput.charAt(this.cursor);
        this.cursor++;
        return result;
    }

    /**
     * Peeks at the char in the current cursor pos
     * @return char at this position
     */
    private char peekCurrentChar() {
        return this.userInput.charAt(cursor);
    }

    /**
     * Peeks at the next char in the string
     * @requires endOfLine == false
     * @return the next char in the string
     */
    private char peekNextN(int n) {
        return userInput.charAt(cursor+n);
    }

}
