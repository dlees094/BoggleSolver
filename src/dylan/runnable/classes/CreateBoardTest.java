package dylan.runnable.classes;
import dylan.objects.Board;


public class CreateBoardTest 
{
	public static void main(String args[])
	{
		Board b = new Board(4,4);
		b.consolePrintBoard();
	}
}
