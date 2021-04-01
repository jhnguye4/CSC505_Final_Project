import java.lang.Math;

public class HashFunctions {
    public int goldenRatioHashFunc(Utils helper, long code) { 
        double ratio = ((1 + Math.sqrt(5))/2.0);
        double invRatio = ratio - 1;
        int hash = (int)(Math.floor((2*helper.getNumDict()) * ((code * invRatio) - Math.floor(code * invRatio))));
        return hash;
    }
}
