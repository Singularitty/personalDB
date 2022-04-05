/**
 * This expection is thrown everytime something goes wrong when using directories
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 02/04/2022
 */


package personalDB.exceptions;

public class InvalidDirectory extends Exception {

    public InvalidDirectory (String errorMessage) {
        super(errorMessage);
    }

}
