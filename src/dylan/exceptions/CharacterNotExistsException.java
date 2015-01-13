package dylan.exceptions;

/**
 * @author Dylan
 * Custom Exception -- will modify as required.
 */
public class CharacterNotExistsException extends Exception
{
	private static final long serialVersionUID = 1L;

	//Parameterless Constructor
    public CharacterNotExistsException() {}

    //Constructor that accepts a message
    public CharacterNotExistsException(String message)
    {
       super(message);
    }
}