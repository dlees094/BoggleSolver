package dylan.runnable;
import java.util.List;

import dylan.objects.Board;
import dylan.solver.Solver;


public class CreateBoardTest 
{
	public static void main(String args[])
	{
		Board myBoard = new Board(4,4);
		myBoard.consolePrintBoard();
		
		Solver mySolver = new Solver();
		List<String> results = mySolver.solve(myBoard);
		
		for (String word : results)
		{
			System.out.println("Found the word: " + word);
		}
	}
}
