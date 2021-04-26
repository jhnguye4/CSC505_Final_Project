import java.util.*;
import java.io.*;

public class Hash_Four {
    int lookUp = 0;
    int mispelled = 0;

    ArrayList<String> arr = new ArrayList<String>();
    ArrayList<String> file = new ArrayList<String>();
    ArrayList<LinkedList<String>> dictionary;
    Utils helper = new Utils();
    HashCodes code = new HashCodes();
    HashFunctions function = new HashFunctions();
    HashCollisions collisions = new HashCollisions();
    

    public Hash_Four() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter a dictionary: ");
        String filename = console.next().toLowerCase();

        Scanner console2 = new Scanner(System.in);
        System.out.print("Enter a Text File: ");
        String filename2 = console2.next().toLowerCase();


        Scanner input = null;
        Scanner input2 = null;
        PrintStream output = null;
        

        if (filename.endsWith(".txt")) 
        {
            input = helper.getInputScanner(filename);
            input2 = helper.getInputScanner(filename2);
            if (input != null) {
                if (input2 != null) { 
                    arr = helper.processDictWords(input,arr);
                    file = helper.processText(input2,file);
                    dictionary = collisions.separateChaining(dictionary, helper);

                    String word;
                    long hcode = 0;
                    int index = 0;

                    long start = System.nanoTime();

                    for(int i = 0; i < helper.getNumDict(); i++){
                        word = arr.get(i);
                        hcode = code.polynomialHashWord(helper, word);
                        index = function.goldenRatioHashFunc(helper, hcode);
                        dictionary.get(index).add(word);
                    }

                    for(int i = 0; i < file.size(); i++){
                        spellCheck(helper, function, file.get(i));
                    }
                    long end = System.nanoTime();
                    //Calculating the runtime of this algorithm and the number of comparisons it would take.
                    long sortTimeInNano = end - start;
                    double sortTimeIn10thSeconds = (double) sortTimeInNano / Math.pow(10, 8);

                    System.out.println("(3) The number of misspelled words in the text: " + mispelled);
                    System.out.println("(4) The total number of probes during the checking phase: " + helper.getTotalProbe());
                    System.out.println("(5) The average number of probes per word checked: " + (double)helper.getTotalProbe()/helper.getNumText());
                    System.out.println("(6) Load Factor " + helper.countFilledSeparateChaining(dictionary));
                    System.out.println("(7) Run Time " + sortTimeIn10thSeconds);
                }
            }
        } else {
            System.out.println("Invalid filename");
        }
        input.close();
    }
    
    // public int findLastEmpty(ArrayList<Vector> dict) {
    	
    // 	for(int i = dict.size(); i > 0; i--) {
    // 		if(dict.get(i).get(0) == null)
    // 			return i;
    // 	}
    	
    // 	return -1;
    	
    // }
    
    // public ArrayList<Vector> updatePointer(ArrayList<Vector> dict, int ind, int next){
    	
    // 	while(dict.get(ind).get(1) != null) {
    // 		ind = (int)dict.get(ind).get(1);
    // 	}
    	
    // 	dict.get(ind).set(1, next);
    	
    // 	return dict;
    // }
    
    public static void main(String[] args) {
        new Hash_Four();
    }

    public void spellCheck(Utils helper, HashFunctions func, String word)
    {
        String noChar = word.replaceAll("[^a-zA-Z0-9']", "");
        String temp;
        long hcode = code.polynomialHashWord(helper, noChar);
        int index = func.goldenRatioHashFunc(helper, hcode);
        boolean found;
        found = helper.findWord(dictionary, index, noChar);
        lookUp++;
        if(!found){
            if(Character.isUpperCase(word.charAt(0))){
                lookUp++;
                temp = noChar.toLowerCase();
                hcode = code.polynomialHashWord(helper, temp);
                index = func.goldenRatioHashFunc(helper, hcode);
                found = helper.findWord(dictionary, index, temp);
            }
            if(!found){
                if(word.endsWith("'s")){
                    lookUp++;
                    temp = noChar.substring(0, noChar.length() - 2);
                    hcode = code.polynomialHashWord(helper, temp);
                    index = func.goldenRatioHashFunc(helper, hcode);
                    found = helper.findWord(dictionary, index, temp);
                }
                if(word.endsWith("s")){
                    if(!found){
                        lookUp++;
                        temp = noChar.substring(0, noChar.length() - 1);
                        hcode = code.polynomialHashWord(helper, temp);
                        index = func.goldenRatioHashFunc(helper, hcode);
                        found = helper.findWord(dictionary, index, temp);
                        if(!found && word.endsWith("es")){
                            lookUp++;
                            temp = noChar.substring(0, noChar.length() - 2);
                            hcode = code.polynomialHashWord(helper, temp);
                            index = func.goldenRatioHashFunc(helper, hcode);
                            found = helper.findWord(dictionary, index, temp);
                        }
                    }
                }
                if(word.endsWith("ed")){
                    lookUp++;
                    temp = noChar.substring(0, noChar.length() - 2);
                    hcode = code.polynomialHashWord(helper, temp);
                    index = func.goldenRatioHashFunc(helper, hcode);
                    found = helper.findWord(dictionary, index, temp);;
                    if(!found && word.endsWith("d")){
                        lookUp++;
                        temp = noChar.substring(0, noChar.length() - 1);
                        hcode = code.polynomialHashWord(helper, temp);
                        index = func.goldenRatioHashFunc(helper, hcode);
                        found = helper.findWord(dictionary, index, temp);
                    }
                }
                if(word.endsWith("er")){
                    lookUp++;
                    temp = noChar.substring(0, noChar.length() - 2);
                    hcode = code.polynomialHashWord(helper, temp);
                    index = func.goldenRatioHashFunc(helper, hcode);
                    found = helper.findWord(dictionary, index, temp);
                    if(!found && word.endsWith("r")){
                        lookUp++;
                        temp = noChar.substring(0, noChar.length() - 1);
                        hcode = code.polynomialHashWord(helper, temp);
                        index = func.goldenRatioHashFunc(helper, hcode);
                        found = helper.findWord(dictionary, index, temp);
                    }
                }
                if(word.endsWith("ing")){
                    lookUp++;
                    temp = noChar.substring(0, noChar.length() - 3);
                    hcode = code.polynomialHashWord(helper, temp);
                    index = func.goldenRatioHashFunc(helper, hcode);
                    found = helper.findWord(dictionary, index, temp);
                    if(!found){
                        lookUp++;
                        temp = noChar.substring(0, noChar.length() - 3);
                        temp = temp + "e";
                        hcode = code.polynomialHashWord(helper, temp);
                        index = func.goldenRatioHashFunc(helper, hcode);
                        found = helper.findWord(dictionary, index, temp);
                    }
                }
                if(word.endsWith("ly")){
                    lookUp++;
                    temp = noChar.substring(0, noChar.length() - 2);
                    hcode = code.polynomialHashWord(helper, temp);
                    index = func.goldenRatioHashFunc(helper, hcode);
                    found = helper.findWord(dictionary, index, temp);
                }
            }
        }
        if(!found){
            mispelled++;
        }
    }
}
