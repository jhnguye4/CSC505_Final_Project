import java.util.*;
public class HashCollisions {
    
    public ArrayList<LinkedList<String>> separateChaining(ArrayList<LinkedList<String>> dictionary, Utils helper) {
        dictionary = new ArrayList<LinkedList<String>>(2*helper.getNumDict());
        for(int i = 0; i < (2*helper.getNumDict()); i++){
            LinkedList<String> list = new LinkedList<String>();
            dictionary.add(list);
        }
        return dictionary;
    }

    public ArrayList<Vector> coalescedChaining(ArrayList<Vector> dictionary, Utils helper){
        dictionary = new ArrayList<Vector>(helper.getNumDict());
        
        for(int i = 0; i < dictionary.size(); i++){
            LinkedList<String> list = new LinkedList<String>();
            
            Vector v = new Vector();
            
            v.add(null);
            v.add(null);
            
            dictionary.add(v);
        }
        
        return dictionary;
    }

    public void linearProbing(){
        //TODO
    }

    public void doubleHashing(){
        //TODO
    }
}
