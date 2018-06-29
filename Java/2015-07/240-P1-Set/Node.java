/*
 * The Node class represents each individual entity to be created and linked together in a list.
 * It contains two parameters when initialized: the object (which is the element) and the next node
 * (which is how the list will be linked). It has two methods designed to retrieve the element and
 * the next node, and it has two methods to change the values of the element and next node.
 * 
 * Rachel Chiang
 */
class Node {
	/*
	 * The Node has two fields, which are the Object element and Node next. These can be changed after
	 * they are initialized in Node(Object, Node) through setElement(Object) and setNode(Node) and can be
	 * retrieved outside of the class via getElement() and getNext().
	 */
	private Object element;
	private Node next;
	
	/*
	 * This method initializes the element as e and next as n during the creation of a new Node.
	 */
	public Node(Object e, Node n) {
		element = e;
		next = n;
	}
	
	/*
	 * This method returns the Object element.
	 */
	public Object getElement() {
		return element;
	}
	
	/*
	 * This method returns the Node next.
	 */
	public Node getNext() {
		return next;
	}
	
	/*
	 * This method sets the element to a new object passed as an argument.
	 */
	public void setElement(Object newElement) {
		element = newElement;
	}
	
	/*
	 * This method sets the next node to newNext.
	 */
	public void setNext(Node newNext) {
		next = newNext;
	}

}
