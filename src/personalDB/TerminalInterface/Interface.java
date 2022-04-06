/**
 * Terminal interface
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 02/04/2022
 */

package personalDB.TerminalInterface;

import personalDB.Engine.DirectoryHandler;

public class Interface {
    private final DirectoryHandler fileEngine;

    public Interface(DirectoryHandler fileEngine) {
        this.fileEngine = fileEngine;
    }

    public void drawStartScreen() {
        programInfo();
    }

    public void drawScreen() {
        sessionInfo();
        inputSection();
    }

    public void displayDirectoryContent() {
        String[] content = this.fileEngine.listAll();
        System.out.println("Content of " + this.fileEngine.getCurrentDir() + ":");
        for (String s : content) {
            System.out.println(s);
        }
    }

    private void programInfo() {
        drawLine();
        System.out.println("\t\t\t\t\t\tpersonalDB\n");
        String author = "Luís Ferreirinha";
        String versionDate = "03/04/2022";
        System.out.println("\tMade by: " + author + " \t\t " + versionDate);
    }


    private void sessionInfo() {
        drawLine();
        System.out.println("Current Directory: " + fileEngine.getCurrentDir());
    }

    private void inputSection() {
        drawLine();
        System.out.print(':');
    }

    private void drawLine() {
        System.out.println("---------------------------------------------------------------");
    }

}
