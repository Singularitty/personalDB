/**
 * Terminal interface
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 02/04/2022
 */

package personalDB;

public class Interface {

    public void drawStartScreen() {
        programInfo();
    }

    public void drawScreen() {
        sessionInfo();
        inputSection();
    }

    private void programInfo() {
        drawLine();
        System.out.println("\t\t\t\t\t\tpersonalDB\n");
        System.out.println("\tMade by: Luís Ferreirinha \t\t Version Date: 02/04/2022");
    }

    private void sessionInfo() {
        drawLine();
        System.out.println("Current Directory: " + System.getProperty("user.dir"));
    }

    private void inputSection() {
        drawLine();
        System.out.print(':');
    }

    private void drawLine() {
        System.out.println("---------------------------------------------------------------");
    }

}
