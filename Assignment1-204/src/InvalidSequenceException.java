/**
 * exception class for more than two sequence
 * @author hyejeongkim
 *
 */
public class InvalidSequenceException extends Exception {
	/**
	 * constructor with no parameter
	 */
	public InvalidSequenceException()
	{
		super("The password cannot contain more "
    			+ "than two of the same character in sequence.");
	}
	/**
	 * constructor with String parameter
	 * @param String string input
	 */
	public InvalidSequenceException(String string) {
		super("The password cannot contain more "
    			+ "than two of the same character in sequence.");
	}

}
