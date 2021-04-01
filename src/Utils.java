import java.util.*;
import java.io.*;
public class Utils{
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
        while (input.hasNext()){
            String word = input.next();
            arr.add(word);
            n += 1;
        }
        System.out.println("(1) The number of words in the dictionary: " + n);
        return arr;
    }
    
    public ArrayList<String> processText(Scanner input, ArrayList<String> file) {
        text = 0; 
        while (input.hasNext()){
            String word = input.next();
            
            file.add(word);
            text += 1;
        }
        System.out.println("(2) The number of words in the text to be spell-checked: " + text);
        return file;
    }

    public boolean findWord(ArrayList<LinkedList<String>> dictionary,int index, String word){
        boolean found = false;
        LinkedList<String> current = dictionary.get(index);
        for(int i = 0; i < current.size(); i++){
            if(current.get(i).equals(word)){
                totalProbe++;
                found = true;
                break;
            }
            totalProbe++;
        }
        return found;
    }

    public int getNumDict(){
        return n;
    }

    public int getNumText(){
        return text;
    }

    public int getTotalProbe(){
        return totalProbe;
    }
}
