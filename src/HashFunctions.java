import java.lang.Math;
import java.util.Random;

public class HashFunctions {
    public int goldenRatioHashFunc(Utils helper, long code) { 
        double ratio = ((1 + Math.sqrt(5))/2.0);
        double invRatio = ratio - 1;
        int hash = (int)(Math.floor((2*helper.getNumDict()) * ((code * invRatio) - Math.floor(code * invRatio))));
        
        return hash;
    }
    public int multiplyAddDivideHashFunc(Utils helper, long code){
        int a = 1234;
        int b = 5230;
        int hash = (int)(((a*code + b) % 58693) % (2*helper.getNumDict()));
        return Math.abs(hash);
    }
    public int divisionHashFunc(Utils helper, long code){
        int hash = (int)(code % 12289);
        return hash;
    }
}
