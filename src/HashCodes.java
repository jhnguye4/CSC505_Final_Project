import java.util.*;
public class HashCodes {

    public long polynomialHashWord(Utils helper,String word){
        long ascii;
        long code = 0;

        for(int j = 0; j < word.length(); j++){
            ascii = (long)word.charAt(j);
            code += ascii * Math.pow(4,j);
        }
        return code;
    }
    
    public long additiveHashWord(Utils helper, String word){
        int ascii;
        long code = 0;

        for(int j = 0; j < word.length(); j++){
            ascii = (int)word.charAt(j);
            code += ascii;
        }

        return code;
    }

    public long cyclicShiftHashWord(String word){
        int shift = 5;
		int code = 0;
		for(int i=0; i<word.length(); i++) { 
			code = (code<<(shift)) | (code>>>(32-shift));
			code += (int)(word.charAt(i));
		}
		return code;
    }
}