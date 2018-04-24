/**
 * exception class for no lowercase alphabet
 * @author hyejeongkim
 *
 */
public class NoLowerAlphaException extends Exception {
	/**
	 * constructor with no parameter
	 */
	public NoLowerAlphaException()
	{
		super("The password must contain at least one "
				+ "lowercase alphabetic character");
	}
	/**
	 * constructor with String parameter
	 * @param String string input
	 */
	public NoLowerAlphaException(String string) {
		super("The password must contain at least one "
				+ "lowercase alphabetic character");
	}

}
