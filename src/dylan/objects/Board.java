package dylan.objects;
import java.util.Random;


public class Board 
{
	private String[][] layout;
	private int rows;
	private int columns;
	
	public Board(int rows, int columns)
	{
		this.rows = rows;
		this.columns = columns;
		layout = new String[rows][columns];
		populateBoard();
	}
	
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
	
	private void populateBoard()
	{
		for (int rowNum = 0; rowNum < rows; rowNum++)
		{
			for (int colNum = 0; colNum < columns; colNum++)
			{
				Random generator = new Random();
				String randomLetter = String.valueOf((char)(generator.nextInt(26) + 'A'));
				layout[rowNum][colNum] = randomLetter;
			}
		}
	}
}
