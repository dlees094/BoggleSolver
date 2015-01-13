package dylan.objects;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoggleDictionary {
	

    private static final Logger logger = Logger.getLogger(BoggleDictionary.class.getName());
	private Trie prefixTree;
	
	/**
	 * Constructor of the Dictionary
	 * Calls a method to generate a prefix tree based on words.txt that will be used
	 * by the solver class to traverse the board and find words. 
	 */
	public BoggleDictionary()
	{
        logger.entering(getClass().getName(), "BoggleDictionaryConstructor");
		
		prefixTree = new Trie();
		try
		{
			generateTree();
		}
		catch (IOException ioe)
		{
			logger.log(Level.SEVERE, "Error reading the words.txt file", ioe);
		}
		
		logger.exiting(getClass().getName(), "BoggleDictionaryConstructor");
	}
	
	/**
	 * Getter method for the dictionary trie
	 * @return Trie - the dictionary
	 */
	public Trie getTree()
	{
		return prefixTree;
	}

	/**
	 * Generates a prefix tree based on words.txt.
	 * @throws IOException when file cannot be read
	 */
	private void generateTree() throws IOException
	{
		File file = new File("words.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
		   prefixTree.add(line);
		}
		br.close();
	}	
}
