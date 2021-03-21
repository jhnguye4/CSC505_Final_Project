import java.util.*;
import java.io.*;
public class Utils{
    /**
	 * private field for size of the list and will be updated when a link is added
	 * or removed
	 */
	private int size = 0;
	/** private field that is a ListNode that is for the head */
	private ListNode head = null;
    private ListNode tail = null;

    
    public ListNode getHead(){
        if(head == null){
            throw new IllegalArgumentException("List is empty");
        }
        return head;
    }
    public ListNode getTail(){
        if(tail == null){
            throw new IllegalArgumentException("List is empty");
        }
        return tail;
    }
    public int getSize(){
        return size;
    }

    // Adds a number to the front of the list
    public void addFront(int num) {
        ListNode newNode = new ListNode<Integer>(num);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        size++;
    }
    // Adds a number to the front of the list
    public void addEnd(int num) {
        ListNode newNode = new ListNode<Integer>(num);
        ListNode currentNode = head;
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else{
            while(currentNode.next != null){
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
            newNode.prev = currentNode;
            tail = newNode;
        }  
        size++;
    }

    public void remove(ListNode node){
        if(node == head && head.next != null){
            head = head.next;
        }
        if(node == tail && tail.prev != null){
            tail = tail.prev;
        }
        if(node.prev != null && node.next != null){
            ListNode previous = node.prev;
            ListNode next = node.next;
            previous.next = next;
            next.prev = previous;
        }
        size--;      
    }

    // Helper function that would print out the list
    public void printList(ListNode begin) {
        ListNode currentNode = begin;

        while (currentNode != null) {
            // Print the data at current node
            System.out.print(currentNode.data + " ");
            // Go to next node
            currentNode = currentNode.next;
        }
        System.out.println();

    }
    
    
}
