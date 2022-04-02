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

public class Main {

    public static void main(String[] args) {

        boolean exit = false;

        Interface screen = new Interface();
        Parser TokenParser = new Parser();

        screen.drawStartScreen();

        while (!exit) {

            screen.drawScreen();
            Operation action = TokenParser.parser(TokenParser.getInput());

            System.out.println(action);

            if (action == Operation.EXIT) {
                exit = true;
            } else {

            }


        }

    }
}
