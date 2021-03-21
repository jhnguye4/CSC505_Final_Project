/**
	 * private inner class that creates Nodes for the linked list
	 * 
	 * @author jhnguye4
	 *
	 */
	public class ListNode<E> {
		/** public field that holds the data of the linked list */
		public E data;
		/** public field for the next ListNode */
		public ListNode next;
		/** public field for the prev ListNode */
		public ListNode prev;

		/**
		 * Constructor method that takes in one parameter that creates a node with
		 * information but the previous and next links are null
		 * 
		 * @param data the data that the new node will hold
		 */
		public ListNode(E data) {
			this(data, null, null);
		}

		/**
		 * Constructor method that takes in three parameters to create a node and link
		 * it to the previous link and the next linke
		 * 
		 * @param data the data that the new node will hold
		 * @param prev points to the previous link
		 * @param next points to the next link
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}