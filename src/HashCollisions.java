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

    public void coalescedChaining(){
        //TODO
    }

    public void linearProbing(){
        //TODO
    }

    public void doubleHashing(){
        //TODO
    }
}
