package dylan.objects;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Dylan Lees
 * Utility class that will generate a 
 * dictionary that the Solver will use
 * to test if a string is a word or not.
 */
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
	 * Method that will help the solver find more words.
	 * @return a boolean dictating if there are
	 * 		any words in the dictionary that
	 * 		start with this prefix.
	 */
	public boolean isAPrefix(String prefix)
	{
		return (prefixTree.suggest(prefix).length > 1);
	}
	
	/**
	 * Method that will help the solver discover a word.
	 * @return a boolean dictating if the word is a word
	 * 		in the dictionary.
	 */
	public boolean isAWord(String word)
	{
		return (prefixTree.isEntry(word));
	}

	/**
	 * Generates a prefix tree based on words.txt.
	 * @throws IOException when file cannot be read
	 */
	private void generateTree() throws IOException
	{
		URL url = getClass().getResource("words.txt");
		File file = new File(url.getPath());
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) 
		{
			prefixTree.add(line.toUpperCase());
		}
		br.close();
	}	
}
