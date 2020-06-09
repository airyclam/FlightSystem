/**
 * This class extends MyLinkedList to override and implement methods get, indexOf, lastIndexOf, and set.
 * @author lameric
 * 
 * @param <E> can be a linked list of any object
 */
public class EricsLinkedList<E> extends MyLinkedList<E> {
	/**
	 * Takes an integer index and returns element at index.
	 */
	public E get(int index) {
		Node<E> current = head;
	      for (int i = 0; i < index; i++) {
	        current=current.next;
	      }
		return current.element;
	  }
	/**
	 * Takes an object and returns true if list contains object, false otherwise.
	 */
	public boolean contains(Object e) {
		Node<E> current = head;
	      for (int i = 0; i < size; i++) {
	    	if (current.element.equals(e)) {
	    		return true;
	    	}
	        current=current.next;
	      }
	    return false;
	}
	/**
	 * Finds the first instance of object in list, returns -1 if object is not in list.
	 */
	  public int indexOf(Object e) {
		Node<E> current = head;
	      for (int i = 0; i < size; i++) {
	    	if (current.element.equals(e)) {
	    		return i;
	    	}
	        current=current.next;
	      }
	    return -1;
	  }
	  /**
	   * Finds the last instance of object in list, returns -1 if object is not in list.
	   */
	  public int lastIndexOf(E e) {
		 Node<E> current = head;
		 int index = -1;
		 int maxIndex = -1;
	      	for (int i = 0; i < size; i++) {
	      		if (current.element == e) {
	      			index = i;
	      		}
	      		if (index > maxIndex) {
	      			maxIndex = index;
	      		}
	      		current=current.next;
	      	}
	      	if(maxIndex > -1) {
	      		return maxIndex;
	      	}
	     return -1;
	  }
	  /**
	   * Changes the element at index to indicated element.
	   */
	  public E set(int index, E e) {
		  Node<E> current = head;
		   for (int i = 0; i < index; i++) {
		        current=current.next;
		   }
		   current.element = e;
		  return null;
	  }
}

