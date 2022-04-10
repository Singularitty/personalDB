/**
 * This class handles input parsing
 * It splits the given command by spaces and returns
 * each lexeme detected, it always assumes the first
 * lexeme to be an operation and the rest to be arguments
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 02/04/2022
 */

package personalDB.Parser;

import personalDB.Exceptions.InvalidInputException;
import java.util.ArrayList;
import java.util.List;

/**
 * Objects of this class represent an input that has been parsed and
 * divided into lexemes, according to the programs' defined syntax
 */
public class Parser {
    private final String source;
    private List<String> lexemes = new ArrayList<>();
    private int current = 0;

    /**
     * Constructs an object of this class by automatically parsing
     * the given input
     * @param input String representing user input, the strings' format should
     *              be according to the programs' defined syntax.
     */
    public Parser(String input) {
        this.source = input;
        this.parseInput();
    }

    /**
     * Takes the first lexeme from an input and returns the corresponding command
     * if said lexeme is a string matching a valid operation
     * @return Command matching the input
     */
    @SuppressWarnings("DuplicatedCode")
    public Operation getCommand() {
        return switch (this.lexemes.get(0).toUpperCase()) {
            case "EXIT" -> Operation.EXIT;
            case "HELP" -> Operation.HELP;
            case "FIND" -> Operation.FIND;
            case "DELETE" -> Operation.DELETE;
            case "CREATE" -> Operation.CREATE;
            case "CD" -> Operation.CD;
            case "OPEN" -> Operation.OPEN;
            case "BACK" -> Operation.BACK;
            case "MKDIR" -> Operation.MKDIR;
            case "RMDIR" -> Operation.RMDIR;
            case "LS" -> Operation.LS;
            default -> Operation.INVALID;
        };
    }

    /**
     * Takes a string matching a valid operation and returns the
     * corresponding Operation command
     * @param command String representing a valid operation
     * @return Command corresponding to the given string
     */
    @SuppressWarnings("DuplicatedCode")
    public static Operation getCommand(String command) {
        return switch (command.toUpperCase()) {
            case "EXIT" -> Operation.EXIT;
            case "HELP" -> Operation.HELP;
            case "FIND" -> Operation.FIND;
            case "DELETE" -> Operation.DELETE;
            case "CREATE" -> Operation.CREATE;
            case "CD" -> Operation.CD;
            case "OPEN" -> Operation.OPEN;
            case "BACK" -> Operation.BACK;
            case "MKDIR" -> Operation.MKDIR;
            case "RMDIR" -> Operation.RMDIR;
            case "LS" -> Operation.LS;
            default -> Operation.INVALID;
        };
    }

    /**
     * Returns the arguments (if given) contained in the input,
     * in a List of Strings.
     * If only a command was given in the input then it returns
     * an empty List.
     * @return List<String> containing the arguments.
     */
    public List<String> getArguments() {
        if (lexemes.size() > 1) {
            return lexemes.subList(1, lexemes.size());
        } else {
            return new ArrayList<>();
        }
    }


    /**
     * Parses the input given by the user.
     * It uses spaces (char ' ') has a separator for
     * splitting the string into lexemes. If a given string
     * contains a quotes inside a lexeme or a lexeme inside quotes
     * with spaces inside those quotes (e.g. cd \Users\"Awesome Directory"\Test)
     * it ignores the space character inside quotes and returns the correct
     * lexemes for that string.
     * Each time a lexeme is constructed it is added to a private attribute of the
     * current object.
     */
    private void parseInput() {
        try {
            StringBuilder lexeme = new StringBuilder();
            while (!isAtEnd()) {
                if (isQuote(peek())) {
                    lexeme.append(getQuotedString());
                } else if (isSpace(peek())) {
                    lexemes.add(lexeme.toString());
                    lexeme.setLength(0);
                    advance();
                } else if (peekNext() == '\0') {
                    lexeme.append(advance());
                    lexemes.add(lexeme.toString());
                    lexeme.setLength(0);
                } else {
                    lexeme.append(advance());
                }
            }
            if (lexeme.length() > 0) {
                lexemes.add(lexeme.toString());
            }
        } catch (InvalidInputException e) {
            System.out.println(e);
            lexemes = List.of("Invalid");
        }
    }

    /**
     * Parses a string with quotes surrounding it returning the content
     * inside it.
     * @return String inside of quotes
     * @throws InvalidInputException If quotes are not closed before the
     *         loop reaches the end of the string.
     */
    private String getQuotedString() throws InvalidInputException {
        StringBuilder tempStr = new StringBuilder();
        advance();
        while (!isQuote(peek()) && peek() != '\0') {
            tempStr.append(advance());
        }
        if (peek() == '\0') {
            throw new InvalidInputException("Quotation marks not closed!");
        } else {
            advance();
            return tempStr.toString();
        }
    }

    /**
     * Checks if the current character is a quote '"'
     * (Kinda redundant but makes code more readable)
     * @param c Char to be checked
     * @return True if it is a quote
     */
    private boolean isQuote(char c) {
        return c == '"';
    }

    /**
     * Checks if the character is a space ' '
     * (Kinda redundant but makes code more readable)
     * @param c Char to be checked
     * @return True if it is a space
     */
    private boolean isSpace(char c) {
        return c == ' ';
    }

    /**
     * Returns the current char without advancing the cursor
     * in the string. If it reaches the end of the string
     * it returns '\0'
     * @return Current char || '\0' if at end of string
     */
    private char peek() {
        if (isAtEnd()) {
            return '\0';
        }
        return source.charAt(current);
    }

    /**
     * Returns the next character (current + 1) without advancing the cursor
     * in the string. If it reaches the end of the string it returns '\0'.
     * @return Next character || '\0' if at end of string
     */
    private char peekNext() {
        if (current + 1 >= source.length()) {
            return '\0';
        }
        return source.charAt(current + 1);
    }

    /**
     * Checks if the current cursor is at the end of the string
     * @return True if the cursor reached or surpassed the total length
     *         of the string
     */
    private boolean isAtEnd() {
        return current >= source.length();
    }

    /**
     * Returns the character at the current position of the cursor and
     * advances the cursor one position ahead.
     * @return Char at current position
     */
    private char advance() {
        return source.charAt(current++);
    }

}
