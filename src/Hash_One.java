import java.util.*;
import java.io.*;

public class Hash_One {
    int lookUp = 0;
    int mispelled = 0;

    ArrayList<String> arr = new ArrayList<String>();
    ArrayList<String> file = new ArrayList<String>();
    ArrayList<LinkedList<String>> dictionary;
    ArrayList<Vector<Object>> dictionary2;
    ArrayList<String> dictionary3;
    Utils helper = new Utils();
    HashCodes code = new HashCodes();
    HashFunctions function = new HashFunctions();
    HashCollisions collisions = new HashCollisions();

    String codeType = "";
    String func = "";
    String collision = "";

    public Hash_One() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter a dictionary: ");
        String filename = console.next().toLowerCase();

        Scanner console2 = new Scanner(System.in);
        System.out.print("Enter a Text File: ");
        String filename2 = console2.next().toLowerCase();

        Scanner console3 = new Scanner(System.in);
        System.out.print("Enter a hash code(polynomial, additive, cyclic): ");
        codeType  = console3.next().toLowerCase();

        Scanner console4 = new Scanner(System.in);
        System.out.print("Enter a compression function(multiplication, MAD, division): ");
        func = console4.next().toLowerCase();

        Scanner console5 = new Scanner(System.in);
        System.out.print("Enter a collision method(separate, coalesced, linear): ");
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
                    arr = helper.processDictWords(input,arr);
                    file = helper.processText(input2,file);
                    if(collision.equals("separate")){
                        dictionary = collisions.separateChaining(dictionary, helper);
                    }
                    else if(collision.equals("coalesced")){
                        dictionary2 = collisions.coalescedChaining(dictionary2, helper);
                    }
                    else if(collision.equals("linear")){
                    	dictionary3 = collisions.linearProbing(dictionary3, helper);
                    }
                    

                    String word;
                    long hcode = 0;
                    int index = 0;

                    long start = System.nanoTime();
                    for(int i = 0; i < helper.getNumDict(); i++){
                        word = arr.get(i);
                        if(codeType.equals("polynomial")){
                            hcode = code.polynomialHashWord(word);
                        }
                        else if(codeType.equals("additive")){
                            hcode = code.additiveHashWord(word);
                        }
                        else if(codeType.equals("cyclic")){
                            hcode = code.cyclicShiftHashWord(word);
                        }
                
                        if(func.equals("multiplication")){
                            index = function.goldenRatioHashFunc(helper, hcode);        
                        }
                        else if(func.equals("mad")){
                            index = function.multiplyAddDivideHashFunc(helper, hcode);
                        }
                        else if(func.equals("division")){
                            index = function.divisionHashFunc(helper, hcode);
                        }
                        
                        if(collision.equals("separate")){
                        	dictionary.get(index).add(word);
                        }
                        else if(collision.equals("coalesced")){
                            if(dictionary2.get(index).get(0) == null)
                            	dictionary2.get(index).set(0, word);
                            else {
                            	int next_index = findLastEmpty(dictionary2);
                            	updatePointer(dictionary2,index,next_index);
                            	dictionary2.get(next_index).set(0, word);
                            }
                        }
                        else if(collision.equals("linear")){
                        	if(dictionary3.get(index) == null || word.equals(dictionary3.get(index)))
                            	dictionary3.set(index, word);
                            else {
                            	
                            	int tempind = index;
                            	int count = 1;
                            	
                            	while(dictionary3.get(index) != null) {
                            		
                            		index = (tempind + count) % dictionary3.size();
                            		count++;
                            		if(index > dictionary3.size()) {
                            			dictionary3 = extendDict(dictionary3);
                            		}
                            		
                            	}
                            	
                            	dictionary3.set(index, word);
                            }
                        }
                        
                    }

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
                    
                    if(collision.equals("separate")){
                    	System.out.println("(6) Load Factor " + helper.countFilledSeparateChaining(dictionary));
                    }
                    else if(collision.equals("coalesced")){
                    	System.out.println("(6) Load Factor " + helper.countFilledVect(dictionary2));
                    }
                    else if(collision.equals("linear")){
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

    public void spellCheck(String word)
    {
        long hcode = 0;
        int index = 0;
        boolean found = false;
        if(codeType.equals("polynomial")){
            hcode = code.polynomialHashWord(word);
        }
        else if(codeType.equals("additive")){
            hcode = code.additiveHashWord(word);
        }
        else if(codeType.equals("cyclic")){
            hcode = code.cyclicShiftHashWord(word);
        }

        if(func.equals("multiplication")){
            index = function.goldenRatioHashFunc(helper, hcode);        
        }
        else if(func.equals("mad")){
            index = function.multiplyAddDivideHashFunc(helper, hcode);
        }
        else if(func.equals("division")){
            index = function.divisionHashFunc(helper, hcode);
        }
        
        if(collision.equals("separate")){
        	found = helper.findWord(dictionary, index, word);
        }
        else if(collision.equals("coalesced")){
        	found = helper.findWordCoalesced(dictionary2, index, word);
        }
        else if(collision.equals("linear")){
        	found = helper.findWordLinear(dictionary3, index, word);
        }
        
        
        
        lookUp++;

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
    
    public ArrayList<String> extendDict(ArrayList<String> dict) {
    	
    	ArrayList<String> re = new ArrayList<>(dict.size() * 2);
    	
    	for(int i = 0; i < dict.size() * 2; i++) {
    		
    		if(i < dict.size()) {
    			re.set(i, dict.get(i));
    		}else {
    			re.add(null);
    		}
    		
    	}
    	
    	return re;
    	
    }
}
