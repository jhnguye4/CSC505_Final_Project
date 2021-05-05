import java.lang.Math;

/**
 * This class is used in the Hash_One file and is used to generate three different types of 
 * compression functions. The compression functions are multiplication, division, and multiply add divide.
 */
public class HashFunctions {
    /**
     * index(k) = floor(m(kA - floor(kA))
     * This is the multiplication function that the user selects, we called it golden ratio as we used this ratio for A as it 
     * was recommended by Knuth from the book Introduction to Algorithms. To modify this algorithm:
     * 
     * 0 < A < 1
     * m > size of hash table
     * @param helper 
     * @param code 
     * @return index of the hash table
     */
    public int goldenRatioHashFunc(Utils helper, long code) { 
        double ratio = ((1 + Math.sqrt(5))/2.0);
        double invRatio = ratio - 1;
        int hash = (int)(Math.floor((2*helper.getNumDict()) * ((code * invRatio) - Math.floor(code * invRatio))));
        return hash;
    }

    /**
     * index(k) = ((ak+b)mod p) mod m
     * This is the multiply add divide function that user selects. Parts of algorithm that may be modified:
     * 
     * m = size of hash table
     * p > m
     * a must be a value from [0,p-1] and a > 0
     * b any value from [0, p-1]
     * @param helper
     * @param code
     * @return index of the hash table
     */
    public int multiplyAddDivideHashFunc(Utils helper, long code){
        int a = 1234;
        int b = 5230;
        int hash = (int)(((a*code + b) % 58693) % (2*helper.getNumDict()));
        return Math.abs(hash);
    }

    /**
     * index(k) = k % m
     * This is the division function that user selects. Parts of algorithm that may be modified:
     * 
     * m is a prime number
     * @param helper
     * @param code
     * @return index of the hash table
     */
    public int divisionHashFunc(Utils helper, long code){
        int hash = (int)(code % 48673);
        return Math.abs(hash);
    }
}
