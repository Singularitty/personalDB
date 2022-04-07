/**
 * Expection to handle invalid input from the user
 * @author: Luís Ferreirinha
 * @email: luispedroferreirinha@gmail.com
 * @date: 02/04/2022
 */

package personalDB.Exceptions;

public class InvalidInputException extends Exception {

    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }

}
