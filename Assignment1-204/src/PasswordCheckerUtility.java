import java.util.ArrayList;
/**
 * This class is Password checker utility
 * it can check the validation of each password or password list
 * @author hyejeongkim
 *
 */
public class PasswordCheckerUtility {
	/**
	 * constructor with no parameter
	 */
	public PasswordCheckerUtility()
	{
		
	}
	/**
	 * check a password is valid or not 
	 * 
	 * @param String s input password
	 * @return true if valid password according to the rules, return false if an invalid password 
	 * @throws LengthException thrown if length is less than 6 characters
	 * @throws NoDigitException thrown if no digit
	 * @throws NoUpperAlphaException thrown if no uppercase alphabetic
	 * @throws NoLowerAlphaException thrown if no lowercase alphabetic
	 * @throws InvalidSequenceException thrown if more than 2 of same character.
	 */
	public static boolean isValidPassword(String s) throws LengthException,
    NoDigitException,NoUpperAlphaException,NoLowerAlphaException,
    InvalidSequenceException
	{
    //length check
		if (s.length()<6 )
		{
			throw new LengthException("The password must be at least 6 "
					+ "characters long:");
		}
		else 
		{   
			// Digit upper case and lower case valid sequence check
			int upperCharCount=0;
			int lowerCharCount=0;
			int numCounter=0;
			int sameCha=0;
			for (int i = 0; i < s.length(); i++) 
			{
				
	            char ch = s.charAt(i);
	            if(Character.isDigit(ch))
	            {
	            	numCounter++;
	            }
	            else if(Character.isUpperCase(ch))
	            {
	            	upperCharCount++;
	            }
	            else if(Character.isLowerCase(ch))
	            {
	            	lowerCharCount++;
	            }
			}
			// 3 more character check
	        for(int j=0;j < s.length()-2; j++)
	        {
	            if (s.charAt(j)==s.charAt(j+1) && s.charAt(j+1)==s.charAt(j+2))
	            {
	            	sameCha++;
	            }
			}
	        
			if(numCounter<1)
			{
				throw new NoDigitException("The password must contain at least "
						+ "one digit");
			}
			if(upperCharCount<1)
			{
				throw new NoUpperAlphaException("The password must contain at least one "
						+ "uppercase alphabetic character");
			}
			if(lowerCharCount<1)
			{
				throw new NoLowerAlphaException("The password must contain at least one "
						+ "lowercase alphabetic character");
			}
			if(sameCha>0)
			{
				throw new InvalidSequenceException("The password cannot contain more "
            			+ "than two of the same character in sequence.");
				
			}
			return true;	
		}		
	}
	//weak password check	
	/**
	 * check whether the password is weak or not
	 * @param String s input password
	 * @return true if length of password is greater than 5 but less than 10
	 */
	public static boolean isWeakPassword(String s)
	{
		if (5<s.length()&& s.length()<11)
		{
			return true;
		}
		else 
			return false;
	}
	//ArrrayList
	/**
	 * check the password list  
	 * @param arrayList1 made by chosen file
	 * @return an arraylist of invalid passwords
	 */
	public static ArrayList<String> validPasswords(ArrayList<String> arrayList1)
	{
		ArrayList<String> invalidList= new ArrayList<String>();
		for(int i=0; i<arrayList1.size(); i++)
		{
			try {
				isValidPassword(arrayList1.get(i));
			} 
			catch (LengthException e1)
			{
				invalidList.add(arrayList1.get(i) + " "+e1.getMessage());
			}
			catch(NoDigitException e1)
			{
				invalidList.add(arrayList1.get(i)+ " " + e1.getMessage());
			}
			catch(NoUpperAlphaException e1)
			{
				invalidList.add(arrayList1.get(i) + " "+ e1.getMessage());
			}
			catch(NoLowerAlphaException e1)
			{
				invalidList.add(arrayList1.get(i)+ " " + e1.getMessage());
			}
			catch(InvalidSequenceException e1)
			{
				invalidList.add(arrayList1.get(i) + " "+ e1.getMessage());
			}
		}
			return invalidList ;
	}
}
