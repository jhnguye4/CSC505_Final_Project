/**
 * This class is used in the Hash_One file and is used to generate three different types of 
 * hash codes. The compression functions are polynomial, additive, and cyclic.
 */
public class HashCodes {

    /**
     * h(s) = summation_i(c*r^i), where i is 0 to string size - 1
     * Takes the word that is passed through and goes through each character of that string.
     * Multiplying it by a constant and raising it to its ith position to ensure unique hash codes by considering letter order.
     * 
     * Parts that may be modified:
     * r > 1, we have chosen r = 4 below
     * @param word
     * @return generated hash code
     */
    public long polynomialHashWord(String word){
        long ascii;
        long code = 0;

        for(int j = 0; j < word.length(); j++){
            ascii = (long)word.charAt(j);
            code += ascii * Math.pow(4,j);
        }
        return code;
    }
    
    /**
     * h(s) = summation_i(c), where i is 0 to string size - 1
     * Takes the word that is passed through and goes through each character of that string.
     * It adds all the ascii values of the string together. Considered poor encoder. Nothing can
     * be modified from this hash code.
     * @param word
     * @return generated hash code
     */
    public long additiveHashWord(String word){
        int ascii;
        long code = 0;

        for(int j = 0; j < word.length(); j++){
            ascii = (int)word.charAt(j);
            code += ascii;
        }
        return code;
    }

    /**
     * Takes the word that is passed through and goes through each character of that string.
     * Before each character it will shift the current state of the hash code, shift it left and right 
     * and then take OR operation. Then it will add the ascii value of the character.
     * 
     * Parts of hash code that can be modified:
     * shift may be modified, common shift values are 5, 6, 7 ,9 and 13
     * @param word
     * @return generated hash code
     */
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