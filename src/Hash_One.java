import java.util.*;
import java.io.*;

public class Hash_One {
    //Global variables that keep track of how many words were mispelled
    int mispelled = 0;

    //Used to take all words in dictionary text file and place it into this arraylist
    ArrayList<String> arr = new ArrayList<String>();
    //Used to take all words in text file and place it into this arraylist
    ArrayList<String> file = new ArrayList<String>();
    //Used for separate chaining method
    ArrayList<LinkedList<String>> dictionary;
    //Used for coalesced chaining method
    ArrayList<Vector<Object>> dictionary2;
    //Used for linear probing
    ArrayList<String> dictionary3;

    //Declare and Instantiate the objects to use different components and helper functions
    Utils helper = new Utils();
    HashCodes code = new HashCodes();
    HashFunctions function = new HashFunctions();
    HashCollisions collisions = new HashCollisions();

    //Used to keep track of users options in order to pass to if statements
    String codeType = "";
    String func = "";
    String collision = "";

    /**
     * This method is used to prompt the user for a dictionary, textfile, and specifications for 
     * the hash table that they want to generate. This method will then call methods from the other 
     * objects that were instantiated above to do the computation. 
     */
    public Hash_One() {
        //Prompt User for specifications
        Scanner console = new Scanner(System.in);
        System.out.print("Enter a Dictionary: ");
        String filename = console.next().toLowerCase();

        Scanner console2 = new Scanner(System.in);
        System.out.print("Enter a Text File: ");
        String filename2 = console2.next().toLowerCase();

        Scanner console3 = new Scanner(System.in);
        System.out.print("Enter a Hash Code(polynomial(p), additive(a), cyclic(c)): ");
        codeType  = console3.next().toLowerCase();

        Scanner console4 = new Scanner(System.in);
        System.out.print("Enter a Compression Function(multiplication(m), multiply add divide(mad), division(d)): ");
        func = console4.next().toLowerCase();

        Scanner console5 = new Scanner(System.in);
        System.out.print("Enter a Collision Resolution Method(separate(s), coalesced(c), linear(l)): ");
        collision = console5.next().toLowerCase();

        System.out.println();


        Scanner input = null;
        Scanner input2 = null;

        if (filename.endsWith(".txt")) 
        {
            input = helper.getInputScanner(filename);
            input2 = helper.getInputScanner(filename2);
            if (input != null) {
                if (input2 != null) { 
                    //Takes the dictionary and puts the words into an arraylist
                    arr = helper.processDictWords(input,arr);
                    //Takes the file and puts the words into an arraylist
                    file = helper.processText(input2,file);

                    //Initialize the collision methods
                    if(collision.equals("s")){
                        dictionary = collisions.separateChaining(dictionary, helper);
                    }
                    else if(collision.equals("c")){
                        dictionary2 = collisions.coalescedChaining(dictionary2, helper);
                    }
                    else if(collision.equals("l")){
                    	dictionary3 = collisions.linearProbing(dictionary3, helper);
                    }
                    

                    String word;
                    long hcode = 0;
                    int index = 0;

                    long start = System.nanoTime();

                    //Generate the Hash Table
                    for(int i = 0; i < helper.getNumDict(); i++){
                        word = arr.get(i);

                        //hash the dictionary word with the chosen hash code
                        if(codeType.equals("p")){
                            hcode = code.polynomialHashWord(word);
                        }
                        else if(codeType.equals("a")){
                            hcode = code.additiveHashWord(word);
                        }
                        else if(codeType.equals("c")){
                            hcode = code.cyclicShiftHashWord(word);
                        }
                        
                        //get index of the dictionary word with the chosen compression function after hash code is generated
                        if(func.equals("m")){
                            index = function.goldenRatioHashFunc(helper, hcode);        
                        }
                        else if(func.equals("mad")){
                            index = function.multiplyAddDivideHashFunc(helper, hcode);
                        }
                        else if(func.equals("d")){
                            index = function.divisionHashFunc(helper, hcode);
                        }
                        
                        //store the word at index that was generated, collision method will determine how collisions are handled
                        if(collision.equals("s")){
                        	dictionary.get(index).add(word);
                        }
                        else if(collision.equals("c")){
                            if(dictionary2.get(index).get(0) == null)
                            	dictionary2.get(index).set(0, word);
                            else {
                            	int next_index = findLastEmpty(dictionary2);
                            	updatePointer(dictionary2,index,next_index);
                            	dictionary2.get(next_index).set(0, word);
                            }
                        }
                        else if(collision.equals("l")){
                        	if(dictionary3.get(index) == null || word.equals(dictionary3.get(index)))
                            	dictionary3.set(index, word);
                            else {
                            	              
                            	int count = 1;
                            	int tempind = index;
                            	
                            	while(dictionary3.get(index) != null) {
                            		
                            		count++;
                            		index = (tempind + count) % dictionary3.size(); 

                            	}
                            	dictionary3.set(index, word);
                            }
                        }
                    }

                    //Go through all words of text file to see if its in hash table
                    for(int i = 0; i < file.size(); i++){
                        spellCheck(file.get(i));
                    }

                    long end = System.nanoTime();
                    //Calculating the runtime of this algorithm and the number of comparisons it would take.
                    long sortTimeInNano = end - start;
                    double sortTimeIn10thSeconds = (double) sortTimeInNano / Math.pow(10, 8);

                    System.out.println("(3) The number of misspelled words in the text: " + mispelled);
                    System.out.println("(4) The total number of probes during the checking phase: " + helper.getTotalProbe());
                    System.out.println("(5) The average number of probes per word checked: " + (double)helper.getTotalProbe()/helper.getNumText());
                    
                    if(collision.equals("s")){
                    	System.out.println("(6) Load Factor " + helper.countFilledSeparateChaining(dictionary));
                    }
                    else if(collision.equals("c")){
                    	System.out.println("(6) Load Factor " + helper.countFilledVect(dictionary2));
                    }
                    else if(collision.equals("l")){
                    	System.out.println("(6) Load Factor " + helper.countFilled(dictionary3));
                    }
                    
                    
                    System.out.println("(7) Run Time " + sortTimeIn10thSeconds);
                }
            }
        } else {
            System.out.println("Invalid filename");
        }
        input.close();
    }
    public static void main(String[] args) {
        new Hash_One();
    }

    /**
     * Method takes in a word from the text file, generates a hash code, passes hash code to a compression function to get an
     * index, then searches the hash table to see if it is in it.
     * @param word
     */
    public void spellCheck(String word)
    {
        long hcode = 0;
        int index = 0;
        boolean found = false;

        //hash the text file word with the chosen hash code
        if(codeType.equals("p")){
            hcode = code.polynomialHashWord(word);
        }
        else if(codeType.equals("a")){
            hcode = code.additiveHashWord(word);
        }
        else if(codeType.equals("c")){
            hcode = code.cyclicShiftHashWord(word);
        }

        //get index of the text file word with the chosen compression function after hash code is generated
        if(func.equals("m")){
            index = function.goldenRatioHashFunc(helper, hcode);        
        }
        else if(func.equals("mad")){
            index = function.multiplyAddDivideHashFunc(helper, hcode);
        }
        else if(func.equals("d")){
            index = function.divisionHashFunc(helper, hcode);
        }
        
        //Call helper function to go through hash table to see if the word is in the hash table
        if(collision.equals("s")){
        	found = helper.findWord(dictionary, index, word);
        }
        else if(collision.equals("c")){
        	found = helper.findWordCoalesced(dictionary2, index, word);
        }
        else if(collision.equals("l")){
        	found = helper.findWordLinear(dictionary3, index, word);
        }

        if(!found){
            mispelled++;
        }
    }
    
    
    public int findLastEmpty(ArrayList<Vector<Object>> dict) {
    	
    	for(int i = dict.size() - 1; i > 0; i--) {
    		if(dict.get(i).get(0) == null)
    			return i;
    	}
    	
    	return -1;
    	
    }
    
    public ArrayList<Vector<Object>> updatePointer(ArrayList<Vector<Object>> dict, int ind, int next){
    	
    	while(dict.get(ind).get(1) != null) {
    		ind = (int)dict.get(ind).get(1);
    	}
    	
    	dict.get(ind).set(1, next);
    	
    	return dict;
    }
}
