import java.util.*;
import java.io.*;

public class Utils {
	/**
	 * private field for size of the list and will be updated when a link is added
	 * or removed
	 */
	private int size = 0;
	private int n;
	private int totalProbe = 0;
	private int text;

	// Returns Scanner for an input file
	// Returns null if the file does not exist
	/**
	 * getInputScanner method is used to create a scanner of the input file the user
	 * entered. It will return null and a FileNotFoundException if the user enters a
	 * file that does not exist.
	 *
	 *
	 * @param filename The string is the filename that you prompted the user to
	 *                 enter
	 * @return returns Scanner of the filename or null if there is no such file.
	 */
	public Scanner getInputScanner(String filename) {
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return fileScanner;
	}

	// Returns PrintStream for an output file
	// If the output file already exists, asks the user if it is OK to overwrite the
	// file
	// If it is not OK to overwrite the file or a FileNotFoundException occurs, null
	// is
	// returned instead of a PrintStream
	/**
	 * getOutputPrintStream method is used to create a output files with the
	 * opposite extentions. If the file already exists then it will prompt the user
	 * if they want to overwrite the file If they respond no then the output will
	 * return null. If file is unable to be written then it will prompt the user of
	 * the error
	 *
	 * @param console  scanner that is used to prompt user to answer if they want to
	 *                 overwrite file
	 * @param filename file that the user inputted and depending extention, it is
	 *                 used to strip and put on the opposite extention for the
	 *                 output file.
	 * @return returns PrintStream of the filename with the opposite extention or
	 *         null if file could not be written
	 */
	public PrintStream getOutputPrintStream(Scanner console, String filename) {
		PrintStream output = null;
		if (filename.endsWith(".txt")) {
			filename = filename.substring(0, filename.length() - 3);
			filename = filename + "-output.txt";

		}
		File file = new File(filename);
		try {
			if (!file.exists()) {
				output = new PrintStream(file);
			} else {
				System.out.print(filename + " exists - OK to overwrite(y,n)?: ");
				String reply = console.next().toLowerCase();
				if (reply.startsWith("y")) {
					output = new PrintStream(file);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File unable to be written " + e);
		}
		return output;
	}

	public ArrayList<String> processDictWords(Scanner input, ArrayList<String> arr) {
		n = 0;
		while (input.hasNext()) {
			String word = input.next();
			arr.add(word);
			n += 1;
		}
		System.out.println("(1) The number of words in the dictionary: " + n);
		return arr;
	}

	public ArrayList<String> processText(Scanner input, ArrayList<String> file) {
		text = 0;
		String noChar = "";
		String temp = "";
		String word = "";
		while (input.hasNext()) {
			word = input.next();
			noChar = word.replaceAll("[^a-zA-Z0-9']", "");
			if (Character.isUpperCase(word.charAt(0))) {
				noChar = noChar.toLowerCase();
			}
			temp = noChar;

			if (word.endsWith("'s")) {
				temp = noChar.substring(0, noChar.length() - 2);
			}
			if (word.endsWith("es")) {
				temp = noChar.substring(0, noChar.length() - 2);
				file.add(temp);
				text += 1;
				if (word.endsWith("s")) {
					temp = noChar.substring(0, noChar.length() - 1);
				}
			}
			if (word.endsWith("ed")) {
				temp = noChar.substring(0, noChar.length() - 2);
				file.add(temp);
				text += 1;
				if (word.endsWith("d")) {
					temp = noChar.substring(0, noChar.length() - 1);
				}
			}
			if (word.endsWith("er")) {
				temp = noChar.substring(0, noChar.length() - 2);
				if (word.endsWith("r")) {
					temp = noChar.substring(0, noChar.length() - 1);
				}
			}
			if (word.endsWith("ing")) {
				temp = noChar.substring(0, noChar.length() - 3);
				file.add(temp);
				text += 1;
				if (word.endsWith("ing")) {
					temp = temp + "e";
				}

			}
			if (word.endsWith("ly")) {
				temp = noChar.substring(0, noChar.length() - 2);
			}
			if (word.endsWith("ies")) {
				temp = noChar.substring(0, noChar.length() - 3);
				temp = temp + "y";
			}

			file.add(temp);
			text += 1;
		}

		System.out.println("(2) The number of words in the text to be spell-checked: " + text);
		return file;
	}

	public boolean findWord(ArrayList<LinkedList<String>> dictionary, int index, String word) {
		boolean found = false;
		LinkedList<String> current = dictionary.get(index);
		for (int i = 0; i < current.size(); i++) {
			if (current.get(i).equals(word)) {
				totalProbe++;
				found = true;
				break;
			}
			totalProbe++;
		}
		return found;
	}

	public boolean findWordCoalesced(ArrayList<Vector<Object>> dictionary, int index, String word) {
		boolean found = false;
		Vector<Object> current = dictionary.get(index);
		
		while (!found && current.get(0) != null) {
			totalProbe++;
			if (current.get(0).equals(word))
				found = true;

			if (current.get(1) != null)
				index = (int) current.get(1);
			else
				break;
			
			current = dictionary.get(index);
		}

		return found;
	}
	
	public boolean findWordLinear(ArrayList<String> dictionary, int index, String word) {
		boolean found = false;
		
		int tempind = index;
		int count = 1;
		
		while(!found && count < dictionary.size() * 2) {
			totalProbe++;
			count++;
			if(word.equals(dictionary.get(index))) {
				found = true;
				break;
			}else {
				index = (tempind + count) % dictionary.size();
			}
		}
		
		return found;
	}

	public int getNumDict() {
		return n;
	}

	public int getNumText() {
		return text;
	}

	public int getTotalProbe() {
		return totalProbe;
	}

	public double countFilled(ArrayList<String> dict) {

		int count = 0;

		for (Object o : dict) {
			if (o != null)
				count++;
		}

		return (double) count / dict.size();

	}

	public double countFilledSeparateChaining(ArrayList<LinkedList<String>> dict) {

		int count = 0;

		for (int i = 0; i < dict.size(); i++) {
			if (dict.get(i).size() > 0) {
				count++;
			}
		}

		return (double) count / dict.size();

	}

	public double countFilledVect(ArrayList<Vector<Object>> dict) {

		int count = 0;

		for (Vector<Object> v : dict) {
			if (v.get(0) != null)
				count++;
		}

		return (double) count / dict.size();
	}

}
