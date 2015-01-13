package dylan.objects;
import java.util.Random;

import dylan.exceptions.CharacterNotExistsException;

/**
 * @author Dylan Lees
 * This object is the physical board of boggle
 * It should contain an x by y representation
 * of a boggle board populated with letters.
 */
public class Board 
{
	private char[][] layout;
	private int rows;
	private int columns;
	
	/**
	 * Constructor of Board
	 * This will set the number of rows and columns in
	 * the board and populate the board with letters.
	 */	
	public Board(int rows, int columns)
	{
		this.rows = rows;
		this.columns = columns;
		layout = new char[rows][columns];
		populateBoard();
	}

	/**
	 * Just a debug method that will show me the board in a readable way.
	 */	
	public void consolePrintBoard()
	{
		for (int rowNum = 0; rowNum < rows; rowNum++)
		{
			for (int colNum = 0; colNum < columns; colNum++)
			{
				System.out.print(layout[rowNum][colNum] + " ");
			}
			System.out.println("");
		}
	}

	/**
	 * Returns the rows variable.
	 * @return the number of rowss the board contains.
	 */
	public int getNumberOfRows()
	{
		return rows;
	}
	
	/**
	 * Returns the columns variable.
	 * @return the number of columns the board contains.
	 */
	public int getNumberOfColumns()
	{
		return columns;
	}
	
	/**
	 * This method will return a letter at the specified x, y from the board.
	 * @param x - the row which to check
	 * @param y - the column which to check
	 * @return the character at the position x, y
	 * @throws CharacterNotExistsException - This exception will be thrown 
	 * 		if you attempt to get a character beyond the size of the board.
	 */
	public char getCharacterAt(int x, int y) throws CharacterNotExistsException
	{
		if (x >= rows)
		{
			throw new CharacterNotExistsException("There are not " + x + " rows in this particular board.  Please fix the solver algorithm.");
		}
		else if (y >= columns)
		{
			throw new CharacterNotExistsException("There are not " + y + " columns in this particular board.  Please fix the solver algorithm.");
		}
		return layout[x][y];
	}
	
	/**
	 * Puts random letters into the spaces
	 * of the board.  Called by the constructor.
	 */	
	private void populateBoard()
	{
		for (int rowNum = 0; rowNum < rows; rowNum++)
		{
			for (int colNum = 0; colNum < columns; colNum++)
			{
				Random generator = new Random();
				char randomLetter = ((char)(generator.nextInt(26) + 'A'));
				layout[rowNum][colNum] = randomLetter;
			}
		}
	}
}
