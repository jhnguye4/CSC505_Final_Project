import java.util.*;

/**
 * This class is used in the Hash_One file and is used to generate three different types of 
 * collision resolution methods. The hash codes are separate chaining, coalesced chaining, and linear probing.
 */
public class HashCollisions {
    /**
     * Separate Chaining collision method that user selects which is an array of linked list. Where collisions occur
     * where there alredy exist a word at a specific index
     * 
     * Parts of algorithm that may be modified:
     * hash table size which we have chosen to be 2*helper.getNumDict()
     * @param dictionary
     * @param helper
     * @return dictionary hash table
     */
    public ArrayList<LinkedList<String>> separateChaining(ArrayList<LinkedList<String>> dictionary, Utils helper) {
        //initialize array of size
        dictionary = new ArrayList<LinkedList<String>>(2*helper.getNumDict());
        //Go through the arraylist and add a linked list to each index
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
        dictionary = new ArrayList<String>();
        
        for(int i = 0; i < (2*helper.getNumDict()); i++){            
            dictionary.add(null);
        }
       
        return dictionary;
    }
}
