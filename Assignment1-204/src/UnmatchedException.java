/**
 * exception class for no match of first input and second input
 * @author hyejeongkim
 *
 */
public class UnmatchedException extends Exception {
	/**
	 * constructor with no parameter
	 */
	public UnmatchedException()
	{
		super("The Passwords do not match");
	}
	/**
	 * constructor with String parameter
	 * @param  string String input
	 */
	public UnmatchedException(String string) {
		super("The Passwords do not match");
	}

}