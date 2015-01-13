package dylan.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dylan.exceptions.CharacterNotExistsException;
import dylan.objects.Board;
import dylan.objects.BoggleDictionary;

/**
 * @author Dylan Lees
 * Utility class that will solve a board.
 * Currently just prints out all the words it finds.
 * Things to remember
 * - You cannot find the same word twice
 * - You cannot use the same letter twice in the same word.
 */
public class Solver {

    private static final Logger logger = Logger.getLogger(Solver.class.getName());
    private static final BoggleDictionary dictionary = new BoggleDictionary();
    private static Board theBoard;
    List<String> foundWords = new ArrayList<String>();
	/**
	 * Constructor of the Solver class
	 * Currently does nothing.
	 */
	public Solver()
	{
		
	}

	/**
	 * Solves the board.  Currently outputs any words found.
	 * @return foundWords - returns the list of words it has found.
	 */
	public List<String> solve(Board inputBoard) {
        logger.entering(getClass().getName(), "Solver's solve method");
        theBoard = inputBoard;
		int numberOfRows = theBoard.getNumberOfRows();
		int numberOfColumns = theBoard.getNumberOfColumns();
		
		for (int startRow = 0; startRow < numberOfRows; startRow++)
		{
			for (int startColumn = 0; startColumn < numberOfColumns; startColumn++)
			{
				String currentString = "";
				List<String> usedLetterPositions = new ArrayList<String>();
				
				//Let's get recursive and traverse that board
				traverseBoard(usedLetterPositions, startRow, startColumn, currentString);
			}
		}
		
		logger.exiting(getClass().getName(), "Solver's solve method");
		return foundWords;
	}
	
	private void traverseBoard(List<String> usedLetterPositions, int currentRow, int currentColumn, String currentString)
	{
		//Have we been here before?
		if (!usedLetterPositions.contains(currentRow + "," + currentColumn))
		{
			//No we haven't, add it to the current list!
			usedLetterPositions.add(currentRow + ","+currentColumn);
			//Try adding the current letter you are standing on.
			try
			{
				currentString += theBoard.getCharacterAt(currentRow, currentColumn);
			}
			catch (CharacterNotExistsException cne)
			{
				logger.log(Level.SEVERE, "Failed to get a letter at " + currentRow + ", " + currentColumn, cne);
			}
	
			//Then test it for a word, make sure it's not a repeat
			if (dictionary.isAWord(currentString) && !foundWords.contains(currentString))
			{
				foundWords.add(currentString);
			}
			
			if (dictionary.isAPrefix(currentString))
			{
				int totalColumns = theBoard.getNumberOfColumns();
				int totalRows = theBoard.getNumberOfRows();
				
				//We'll start by checking to the left, then top left, then top, then top right, and so on.
				if (currentColumn - 1 != -1)
				{
					traverseBoard(new ArrayList<String>(usedLetterPositions), currentRow, currentColumn - 1, currentString);
				}
				if (currentRow - 1 != -1 && currentColumn - 1 != -1)
				{
					traverseBoard(new ArrayList<String>(usedLetterPositions), currentRow - 1, currentColumn - 1, currentString);
				}
				if (currentRow - 1 != -1)
				{
					traverseBoard(new ArrayList<String>(usedLetterPositions), currentRow - 1, currentColumn, currentString);
				}
				if (currentRow - 1 != -1 && currentColumn + 1 != totalColumns)
				{
					traverseBoard(new ArrayList<String>(usedLetterPositions), currentRow - 1, currentColumn + 1, currentString);
				}
				if (currentColumn + 1 != totalColumns)
				{
					traverseBoard(new ArrayList<String>(usedLetterPositions), currentRow, currentColumn + 1, currentString);
				}
				if (currentRow + 1 != totalRows && currentColumn + 1 != totalColumns)
				{
					traverseBoard(new ArrayList<String>(usedLetterPositions), currentRow + 1, currentColumn + 1, currentString);
				}
				if (currentRow + 1 != totalRows)
				{
					traverseBoard(new ArrayList<String>(usedLetterPositions), currentRow + 1, currentColumn, currentString);
				}
				if (currentRow + 1 != totalRows && currentColumn - 1 != -1)
				{
					traverseBoard(new ArrayList<String>(usedLetterPositions), currentRow + 1, currentColumn - 1, currentString);
				}
			}
		}
	}
}
