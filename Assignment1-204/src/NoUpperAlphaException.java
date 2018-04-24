/**
 * exception class for no Uppercase alphabet
 * @author hyejeongkim
 *
 */
public class NoUpperAlphaException extends Exception {
    /**
     * constructor with no parameter
     */
	public NoUpperAlphaException()
	{
		super("The password must contain at least one "
						+ "uppercase alphabetic character");
	}
	/**
	 * constructor with String parameter
	 * @param String string input
	 */
	public NoUpperAlphaException(String string) {
		super("The password must contain at least one "
				+ "uppercase alphabetic character");
	}

}
