import java.util.*;
public class HashCodes {
    public ArrayList<LinkedList<String>> polynomialHashTable(Utils helper, HashFunctions func, ArrayList<LinkedList<String>> dictionary,ArrayList<String> arr){
        String word;
        int ascii;
        int index;
        long code = 0;
        for(int i = 0; i < helper.getNumDict(); i++){
            word = arr.get(i);
            for(int j = 0; j < word.length(); j++){
                ascii = (int)word.charAt(j);
                code += ascii * Math.pow(4,j);
            }
            index = func.goldenRatioHashFunc(helper, code);
            dictionary.get(index).add(word);
            code = 0;
        }
        return dictionary;
    }

    public int polynomialHashWord(Utils helper, HashFunctions func,String word){
        int ascii;
        int index;
        long code = 0;

        for(int j = 0; j < word.length(); j++){
            ascii = (int)word.charAt(j);
            code += ascii * Math.pow(4,j);
        }
        index = func.goldenRatioHashFunc(helper, code);
        return index;
    }
    
    public ArrayList<LinkedList<String>> additiveHashTable(Utils helper, HashFunctions func, ArrayList<LinkedList<String>> dictionary,ArrayList<String> arr){
        String word;
        int ascii;
        int index;
        long code = 0;
        for(int i = 0; i < helper.getNumDict(); i++){
            word = arr.get(i);
            for(int j = 0; j < word.length(); j++){
                ascii = (int)word.charAt(j);
                code += ascii;
            }
            index = func.goldenRatioHashFunc(helper, code);
            dictionary.get(index).add(word);
            code = 0;
        }
        return dictionary;
    }
    public int additiveHashWord(Utils helper, HashFunctions func,String word){
        int ascii;
        int index;
        long code = 0;

        for(int j = 0; j < word.length(); j++){
            ascii = (int)word.charAt(j);
            code += ascii;
        }
        index = func.goldenRatioHashFunc(helper, code);
        return index;
    }

    public ArrayList<LinkedList<String>> cyclicHashTable(Utils helper, HashFunctions func, ArrayList<LinkedList<String>> dictionary,ArrayList<String> arr){
        String word;
        int shift = 5;
        int ascii;
        int index;
        long code = 0;
        for(int i = 0; i < helper.getNumDict(); i++){
            word = arr.get(i);
            for(int j = 0; j < word.length(); j++){
                ascii = (int)word.charAt(j);
                code = (code<<(shift)) | (code>>>(32-shift));
			    code += ascii;
            }
            index = func.goldenRatioHashFunc(helper, code);
            dictionary.get(index).add(word);
            code = 0;
        }
        return dictionary;
    }

    public int cyclicShiftHashWord(Utils helper, HashFunctions func,String word){
        int shift = 5;
        int index;
		int code = 0;
		for(int i=0; i<word.length(); i++) { 
			code = (code<<(shift)) | (code>>>(32-shift));
			code += (int)(word.charAt(i));
		}
        index = func.goldenRatioHashFunc(helper,code);
		return index;
    }
}