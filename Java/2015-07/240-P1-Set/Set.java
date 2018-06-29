/*
 * This class represents the singly linked list, implementing Sets, including their operations
 * subsetOf, isEqual, union, intersection, and complement. It also allows amendments to the list:
 * add new nodes with addLast(Object) or addElement(Object), or remove the node with a given element
 * with remove(Object). You can also check the status of the list: its size through size() and
 * whether a specific element is contained with contain(Object).
 */
class Set {
	/*
	 * The head and size are initialized and will be used throughout the methods.
	 */
	protected Node head;
	protected long size;
	
	/*
	 * This method initializes the head and the size. Since the head is a dummy head, the size
	 * is set to zero.
	 */
	public Set() {
		head = new Node(null, null);
		size = 0;
	}
	
	/*
	 * This method adds a new node using the passed object as the element.
	 */
	public void addLast (Object object) {
		// The cur is set to the head's next node because the head must remain as the dummy head.
		Node cur = head.getNext();
		// A new node is created with the new object.
		Node newNode = new Node(object, null);
		
		// First checks to see if the head is indeed the last node.
		if (cur == null) {
			head.setNext(newNode);
		}
		else {
			// If the current is not the last node (if it's not empty), then it enters a loop to
			// verify whether the next node is empty or not. It it isn't, cur traverses the list
			// until the loop is broken
			while (cur.getNext() != null) {
				cur = cur.getNext();
			}
			// After the loop is broken, the current sets its next to the newNode,
			cur.setNext(newNode);
		}
		// and the size is incremented.
		size++;
	}
	
	/*
	 * This method accepts an object as the argument and checks if the list already contains the object.
	 * If the object exists in the list, it returns true; otherwise, it returns false.
	 */
	public boolean contain(Object object) {
		Node cur = head.getNext();
		// If the node after the head is empty, it skips the loop and returns false, as the object cannot
		// exist in an empty list.
		while (cur != null) {
			// If cur isn't null, it checks to see if the current element is the same as the object first
			if (cur.getElement().equals(object)) {
				// If they are the same, the method ends, returning true.
				return true;
			}
			// After checking and seeing that they aren't equal, cur traverses the list.
			cur = cur.getNext();
		}
		return false;
	}
	
	/*
	 * This method removes the node that contains the passed object. If it successfully removes it,
	 * it returns true; otherwise, it returns false.
	 */
	public boolean remove(Object object) {
		// This first checks if the list is empty, and if it is an exception is thrown.
		if (size==0) {
			throw new IllegalStateException("The list is empty.");
		}
		
		Node cur = head;
		Node temp = cur.getNext(); // saving the node after cur.
		
		while (cur.getNext() != null) {
			if (temp.getElement().equals(object)) {
				// If the node after cur has the object we're trying to remove, the current node
				// sets its next node to the one after temp
				cur.setNext(temp.getNext());
				// and subsequently deletes the temp
				temp.setElement(null);
				temp.setNext(null);
				// then decrements the size
				size--;
				// Successful removal returns true
				return true;
			}
			// if it hasn't been found, the temp and cur continue to traverse the list.
			temp = temp.getNext();
			cur = cur.getNext();
		}
		// If after traversing the entire list the element hasn't been found, the method returns false.
		return false;
	}
	
	/*
	 * This method simply adds the element to the end of the list. It asks for an object to add and calls
	 * the addLast(Object) method to add it to the end after checking to see if the object already
	 * exists in the list, thereby avoiding duplicate elements.
	 */
	public boolean addElement(Object object) {
		if (contain(object)) {
			return false;
		}
		else {
			addLast(object);
			return true;
		}
	}
	
	/*
	 * This method simply returns the size of the list.
	 */
	public long size() {
		return size;
	}
	
	/*
	 * This method asks for a set to test whether it is the subset of the current list. If it is, it will
	 * return true. Otherwise, it returns false.
	 */
	public boolean subsetOf(Set test) {
		Node cur = test.head.getNext();
		// integer used to count how many of the elements are the same
		int sCounter = 0;
		while (cur != null) {
			if (contain(cur.getElement())) {
				sCounter++;
			}
			cur = cur.getNext();
		}

		// if the number of same elements between the current set and the test set match the
		// size of the test set, it returns true
		if (sCounter == test.size()) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * This method checks if two sets are equal to each other, meaning they have equal sizes
	 * and contain the same elements regardless of the order.
	 */
	public boolean isEqual(Set test) {
		if (size == test.size() && size >= 1) {
			Node cur1 = head.getNext();
			Node cur2 = test.head.getNext();
			int sameCounter = 0;
			for (int i=0; i<size; i++) {
				for (int j=0; j<test.size(); j++) {
					if (cur1.getElement().equals(cur2.getElement())) {
						sameCounter++;
					}
					cur2 = cur2.getNext();
				}
				cur1 = cur1.getNext();
				cur2 = test.head.getNext();
			}
			
			if (sameCounter == size) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (size == 0 && test.size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * This method accepts a Set test as the set to test. It combines the current set with the
	 * test and adds all of their elements into a new set C, without duplicates. It then returns
	 * the new set C.
	 */
	public Set union(Set test) {
		Set C = new Set();
		Node cur1 = head.getNext();
		Node cur2 = test.head.getNext();
		while (cur1 != null) {
			C.addElement(cur1.getElement());
			cur1 = cur1.getNext();
		}
		while (cur2 != null) {
			if (!( contain(cur2.getElement()) )) {
				C.addElement(cur2.getElement());
			}
			cur2 = cur2.getNext();
		}
		
		return C;
	}
	
	/*
	 * This method creates a new set full of elements from both the passed set (test) and the current
	 * set.
	 */
	public Set intersection(Set test) {
		Set C = new Set();
		Node cur2 = test.head.getNext();
		
		while (cur2 != null) {
			if (contain(cur2.getElement())) {
				C.addElement(cur2.getElement());
			}
			cur2 = cur2.getNext();
		}
		
		return C;
	}
	
	/*
	 * This method returns a new set comprised of elements from only one of the two sets (the current
	 * set and the set passed as the argument, test).
	 */
	public Set complement(Set test) {
		Set C = new Set();
		Node cur1 = head.getNext();
		
		while (cur1 != null) {
			if (!( test.contain(cur1.getElement()) )) {
				C.addElement(cur1.getElement());
			}
			cur1 = cur1.getNext();
		}
		
		return C;
	}

	/*
	 * This method overrides the object toString() method to print the elements in a set in the
	 * following format: {element1, element2, ...}
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		Node cur = head.getNext();
		int index = 0;
		String firstBracket = "{";
		String allStrings = firstBracket;
		
		while (cur != null) {
			String theSet = cur.getElement() + "";
			cur = cur.getNext();
			index++;
			allStrings += theSet;
			if (index != size) {
				allStrings += ", ";
			}
		}
		if (cur == null) {
			allStrings += "}";
		}
		
		return allStrings;
	}
	
}
