/**
 * personalDB
 * Program to help you manage files in your personal computer and in between servers/cloud storage
 * This was made because I was tired of managing files between my laptop, desktop, server and cloud
 * Right now it's just a simple file explorer, but I hope to add database like features, such as
 * storing files in an efficient way and retrieving then using hashes and some algorithms
 * This is a personal project designed to expand my knowledge in these areas of computing and not
 * meant to be anything serious or even useful
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 02/04/2022
 */

package personalDB;

import personalDB.Engine.DirectoryHandler;
import personalDB.Engine.OperationHandler;
import personalDB.TerminalInterface.Interface;
import personalDB.exceptions.InvalidDirectory;
import personalDB.exceptions.InvalidInputException;

import java.util.List;

public class JpersonalDB {

    public static void main(String[] args) {

        try {
            boolean exit = false;

            DirectoryHandler fileEngine = new DirectoryHandler(System.getProperty("user.dir"));
            Interface screen = new Interface(fileEngine);
            OperationHandler opHandler = new OperationHandler(fileEngine, screen);
            screen.drawStartScreen();

            while (!exit) {

                screen.drawScreen();
                String userInput = Parser.getInput();

                try {
                    Operation command = Parser.getCommand(userInput);
                    if (command == Operation.EXIT) {
                        exit = true;
                    } else {
                        List<String> arguments = Parser.getArgs(userInput);
                        opHandler.execute(command, arguments);
                    }
                } catch (InvalidInputException e) {
                    e.printStackTrace();
                }
            }
        } catch (InvalidDirectory e) {
            System.out.println("Could not read user directory.");
        }
    }
}
