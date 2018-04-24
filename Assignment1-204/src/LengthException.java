/**
 * exception class for too short length
 */

public class LengthException extends Exception 
{
	/**
	 * constructor with no parameter
	 */
	public LengthException()
	{
		super("The password must be at least 6 "
				+ "characters long:");
	}
	/**
	 * constructor with String parameter
	 * @param String s input
	 */
	public LengthException(String s)
	{
		super("The password must be at least 6 "
				+ "characters long:");
	}
	
}