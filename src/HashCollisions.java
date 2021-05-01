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

     public ArrayList<Vector<Object>> coalescedChaining(ArrayList<Vector<Object>> dictionary, Utils helper){
         dictionary = new ArrayList<Vector<Object>>(2*helper.getNumDict());
        
         for(int i = 0; i < (2*helper.getNumDict()); i++){            
        	 Vector<Object> v = new Vector<Object>();
            
             v.add(null);
             v.add(null);
            
             dictionary.add(v);
         }
        
         return dictionary;
     }

    public ArrayList<String> linearProbing(ArrayList<String> dictionary, Utils helper){
        dictionary = new ArrayList<String>(2*helper.getNumDict());
        
        for(int i = 0; i < (2*helper.getNumDict()); i++){            
            dictionary.add(null);
        }
       
        return dictionary;
    }

    public void doubleHashing(){
        //TODO
    }
}
