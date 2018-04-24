/**
 * exception class for no digit, number 
 * @author hyejeongkim
 *
 */
public class NoDigitException extends Exception {
    /**
     * constructor with no parameter
     */
	public NoDigitException()
	{
		super("The password must contain at least "
				+ "one digit");
	}
	/**
	 * constructor with String parameter
	 * @param String string input
	 */
	public NoDigitException(String string) {
		super("The password must contain at least "
				+ "one digit");
	}

}
