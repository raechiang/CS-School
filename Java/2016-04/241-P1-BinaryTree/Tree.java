/**
 * Rachel Chiang, CS 241-02
 * 
 * Project 1: Binary Search Tree Implementation
 */

/**
 * This class contains all the Binary Search Tree functions and variables.
 * There is one variable, {@link #root}.
 * The tree's primary functions are {@link #add(int, BTNode)}, {@link #remove(int, BTNode)},
 * {@link #getPredecessor(int, BTNode)}, {@link #getSuccessor(int, BTNode)},
 * {@link #preOrderTrav(BTNode)}, {@link #inOrderTrav(BTNode)}, and 
 * {@link #postOrderTrav(BTNode)}, while the rest are used mostly to support these methods.
 */
class Tree
{
	/**
	 * This variable is a BTNode that is initialized in the constructor {@link #Tree()}
	 * as null and can be accessed by other classes via {@link #getRoot()}.
	 */
	private BTNode root;

	/**
	 * This is the constructor. It initializes {@link #root}.
	 */
	public Tree()
	{
		root = null;
	}
	
	/**
	 * This method gives access to the BTNode {@link #root} of the BST.
	 * @return root
	 */
	public BTNode getRoot()
	{
		return root;
	}
	
	/**
	 * This method allows insertion of a new integer to the BST. If the tree is
	 * empty, that is, {@link #root} is still null, then the new integer, element,
	 * will simply be added as the new root. Otherwise, the new integer will be
	 * added as a leaf node depending on the preexisting nodes in the tree.
	 * If the element already exists, it will not be added, as no duplicates are
	 * allowed. A 'cursor', named current, will traverse down the tree recursively,
	 * taking either the left or right path depending on whether the element is less
	 * than or greater than current (respectively). Once the correct position is
	 * found, the integer will be adopted as a new node, establishing the proper
	 * relationship connections with its new parent.
	 * @param element - The integer value to be added.
	 * @param current - A parameter meant for recursion. This is the 'cursor' that
	 * 			points to the node whose data is to be compared against the new element
	 * 			and, if allowed, to grow the leaf. 
	 */
	public void add(int element, BTNode current)
	{
		if (root == null)
		{
			// Make sure tree not empty
			root = new BTNode(element, null, null, null);
		}
		else
		{
			// Tree is not empty!
			if (element > current.getData())
			{
				// If the element > current's data, go right
				if (current.getRight() != null)
				{
					// If the right is not empty, recursively call,
					// setting new current as the right child of this current
					add(element, current.getRight());
				}
				else
				{
					// right is empty, so we can add the new node
					BTNode newLeaf = new BTNode(element, null, null, current);
					current.setRight(newLeaf);
				}
			}
			else if (element < current.getData())
			{
				// the element <= current's data, go left
				if (current.getLeft() != null)
				{
					// If the left is not empty, recursively call,
					// setting new current as the left child of this current
					add(element, current.getLeft());
				}
				else
				{
					// The left is empty, so we can simply add the new node
					BTNode newLeaf = new BTNode(element, null, null, current);
					current.setLeft(newLeaf);
				}
			}
			else
			{
				// the element already exists in the tree
				System.out.println(" " + element + " already exists.");
			}
		}
	}
	
	/**
	 * This method removes a node from the BST, depending on a provided integer target.
	 * First, the node to remove must be searched for, using the same rules as adding
	 * (less than means go left or greater than means go right). Once it is found,
	 * removal may begin.
	 * The remove must deal with several cases, since it needs help repairing paths
	 * between nodes. Aside from the element to delete not existing in the tree, there
	 * are these cases:
	 * - Case 1: No LST, is a root
	 *    The wanted node does not have a left subtree (LST) and is a root
	 * - Case 2: No LST, is a LC
	 *    The wanted node does not have a LST and is, itself, a left child.
	 *    In other words, the rightmost (RM) of its LST cannot replace it, so its
	 *    right subtree (RST) must replace it as its parent's left child.
	 * - Case 3: No LST, is a RC
	 *    The wanted node does not have a LST and is, itself, a right child.
	 *    In other words, its RST must replace it as its parent's right child.
	 * - Case 4: LST without a RM
	 *    The wanted node has an LST but its LST does not have a RM node. The
	 *    wanted node is simply replaced with its LST.
	 * - Case 5: LST with a RM
	 *    The wanted node has an LST and its LST has a RM node. The wanted node
	 *    is thus replaced with the RM node's data and the RM is essentially
	 *    dropped.
	 * Cases 4 and 5 both use another method for support, {@link #getRM_LST(BTNode)},
	 * which retrieves the rightmost node of the left subtree.
	 * @param element - The integer to remove.
	 * @param current - A 'cursor' for recursion, similar to the current of 
	 * 			{@link #add(int, BTNode)}, except it is used for removal.
	 * @return If the removal was successful, this method returns true. Otherwise, it
	 * 			returns false (meaning the integer was not found in the tree).
	 */
	public boolean remove(int element, BTNode current)
	{
		if (current == null)
		{
			// current does not exist
			return false;
		}
		
		// target found
		if (current.getData() == element)
		{
			// No LST
			if (current.getLeft() == null)
			{
				// is root and has no LST
				if (current == root)
				{
					root = root.getRight();
					if (current.getRight() != null)
					{
						current.getRight().setParent(null);
					}
					return true;
				}
				
				// is a left child itself and has no LST
				// (ie cannot be replaced by RM of LST)
				if (current == current.getParent().getLeft())
				{
					current.getParent().setLeft(current.getRight());
				}
				// is a right child itself and has no LST
				else
				{
					current.getParent().setRight(current.getRight());
				}
				
				// if target has a right, set its right's parent to current's parent
				if (current.getRight() != null)
				{
					current.getRight().setParent(current.getParent());
				}
				
				return true;
			}
			// Has a LST
			else
			{
				BTNode replacer = getRM_LST(current.getLeft());
				// replace data with RM of LST
				if (replacer == current.getLeft())
				{
					// no RM node
					// so just replace current's data,
					//  current's left child -- all with replacer's information
					current.setData(replacer.getData());
					current.setLeft(replacer.getLeft());
					if (replacer.getLeft() != null)
					{
						replacer.getLeft().setParent(current);
					}
				}
				else
				{
					// RM node exists
					// set current's LST's right to be replacer's LST
					// set current's data to replacer's data
					// remove RM node
					replacer.getParent().setRight(replacer.getLeft());
					if (replacer.getLeft() != null)
					{
						replacer.getLeft().setParent(replacer.getParent());
					}
					current.setData(replacer.getData());
				}
				return true;
			}
		}
		// target not found
		else if (element < current.getData())
		{
			// element is less than current data
			// go left
			return remove(element, current.getLeft());
		}
		else
		{
			// element is greater than current data
			// go right
			return remove(element, current.getRight());
		}
	}
	
	/**
	 * This method finds the rightmost node of a given node (start). It is used to
	 * support {@link #remove(int, BTNode)} and {@link #getPredecessor(int, BTNode)}.
	 * @param start - The node to start at the search for the rightmost node. Then, it is
	 * 			used recursively until the rightmost is found. Or if it isn't, then it
	 * 			simply returns itself.
	 * @return This will return the rightmost node of the given node, start, or it will
	 * 			return start itself since it is the rightmost node.
	 */
	public BTNode getRM_LST(BTNode start)
	{
		if (start.getRight() != null)
		{
			return getRM_LST(start.getRight());
		}
		else
		{
			return start;
		}
	}
	
	/**
	 * Similar to {@link #getRM_LST(BTNode)}, this method just finds the leftmost node of the
	 * given node (start). It is used to support {@link #getSuccessor(int, BTNode)}.
	 * @param start - The node to start the search at. Then it is used recursively.
	 * @return This will return the leftmost node of the given node, start, or it will
	 * 			return start itself.
	 */
	public BTNode getLM_RST(BTNode start)
	{
		if (start.getLeft() != null)
		{
			return getLM_RST(start.getLeft());
		}
		else
		{
			return start;
		}
	}
	
	/**
	 * This method finds the predecessor of a given target integer (the integer node 
	 * that would come before it if arranged in order). If the predecessor does not 
	 * exist, then it will return null. It uses {@link #getRM_LST(BTNode)} and
	 * {@link #findLeftParent(BTNode)} for support. If the target does exist, some
	 * cases must be considered.
	 * - Case 1: Has LST
	 *    The target has a LST, so its predecessor will be contained there, either
	 *    as the LST's root itself or as the RM node of the LST.
	 * - Case 2: No LST, is a RC
	 *    The target does not have a LST and is the right child of its parent. The
	 *    parent is thus the predecessor.
	 * - Case 3: No LST, is a LC
	 *    The target does not have a LST and is the left child of its parent. A search
	 *    must be conducted to check if any of its ancestors is a right child of their
	 *    parents.
	 *    (For instance, given 2, 5, 4, 3, find the predecessor of 3. Its predecessor
	 *    would be 2, but it must search the parent path, the grandparent path, and the
	 *    great-grandparent path before it finds a right child relationship.)
	 * @param target - The integer value to find the predecessor for.
	 * @param current - The 'cursor' which is used for recursion and eventually will be
	 * 			the targeted node.
	 * @return The node that contains the predecessor will be returned, or it will be
	 * 			null if the target does not have a predecessor.
	 */
	public BTNode getPredecessor(int target, BTNode current)
	{
		if (current == null)
		{
			return null;
		}
		
		if (target == current.getData())
		{
			// look for predecessor
			
			// current has LST
			if (current.getLeft() != null)
			{
				BTNode pred = getRM_LST(current.getLeft());
				return pred;
			}
			
			// current does not have LST
			return findLeftParent(current);
		}
		else if (target < current.getData())
		{
			// go left
			return getPredecessor(target, current.getLeft());
		}
		else
		{
			// go right
			return getPredecessor(target, current.getRight());
		}
	}
	
	/**
	 * It's essentially the same thing as {@link #getPredecessor(int, BTNode)} except
	 * in the opposite direction...
	 * If the successor does not exist, it will return null. It uses
	 * {@link #getLM_RST(BTNode)} and {@link #findRightParent(BTNode)} for support.
	 * If the target exists, some cases must be considered.
	 * - Case 1: Has RST
	 *    The target has a RST, so its successor will be there, either as the RST's
	 *    root or as the LM node of the RST.
	 * - Case 2: No RST, is a LC
	 *    The target does not have a RST and is a left child of its parent. The parent
	 *    is thus the successor.
	 * - Case 3: No RST, is a RC
	 *    The target does not have a RST and is the right child of its parent. A search
	 *    must be conducted to check if any of its ancestors is the left child of their
	 *    parents.
	 * @param target - The integer value to find the successor for.
	 * @param current - The 'cursor' which is used for recursion and eventually will be
	 * 			the targeted node.
	 * @return The BTNode that contains the successor will be returned, or it will be
	 * 			null if the target does not have a successor.
	 */
	public BTNode getSuccessor(int target, BTNode current)
	{
		if (current == null)
		{
			return null;
		}
		
		if (target == current.getData())
		{
			// look for successor
			
			// current has RST
			if (current.getRight() != null)
			{
				BTNode suc = getLM_RST(current.getRight());
				return suc;
			}
			
			// current does not have RST
			return findRightParent(current);
		}
		else if (target < current.getData())
		{
			// go left
			return getSuccessor(target, current.getLeft());
		}
		else
		{
			// go right
			return getSuccessor(target, current.getRight());
		}
	}
	
	/**
	 * This method recursively searches for the first left parent that it finds. It
	 * is used as support for {@link #getPredecessor(int, BTNode)}.
	 * @param current - Needed for the recursive search.
	 * @return - Returns the parent, or null if it none of the ancestors are correct.
	 */
	public BTNode findLeftParent(BTNode current)
	{
		if (current.getParent() == null)
		{
			return null;
		}
		
		if (current.getParent().getRight() == current)
		{
			return current.getParent();
		}
		else
		{
			return findLeftParent(current.getParent());
		}
	}
	
	/**
	 * This method is used to support {@link #getSuccessor(int, BTNode)}. Similar to the
	 * {@link #findLeftParent(BTNode)}, it recursively searches the ancestor paths until
	 * it finds the right link.
	 * @param current - The 'cursor' for the recursive search.
	 * @return - Returns the correct parent, or null if it can't be found.
	 */
	public BTNode findRightParent(BTNode current)
	{
		if (current.getParent() == null)
		{
			return null;
		}
		
		if (current.getParent().getLeft() == current)
		{
			return current.getParent();
		}
		else
		{
			return findRightParent(current.getParent());
		}
	}
	
	/**
	 * This method goes through the tree recursively, printing out the right nodes as
	 * it travels, in pre-order. It processes the root first, then the left subtree,
	 * then the right subtree.
	 * @param current - The cursor pointing to the current node being processed.
	 */
	public void preOrderTrav(BTNode current)
	{
		if (root == null)
		{
			System.out.print("The tree is empty.");
		}
		else
		{
			// process root
			System.out.print(current.getData() + " ");

			// process left subtree w/ recursion
			if (current.getLeft() != null)
			{
				preOrderTrav(current.getLeft());
			}

			// process right subtree w/ recursion
			if (current.getRight() != null)
			{
				preOrderTrav(current.getRight());
			}
		}
	}
	
	/**
	 * This method goes through the tree recursively, printing out the right nodes
	 * as it travels, in in-order. It processes the left subtree, root, and right
	 * subtree in that order.
	 * @param current - The cursor pointing to the current node being processed.
	 */
	public void inOrderTrav(BTNode current)
	{
		if (root != null)
		{
			// left subtree w/ recursion
			if (current.getLeft() != null)
			{
				inOrderTrav(current.getLeft());
			}

			// root
			System.out.print(current.getData() + " ");

			// right subtree w/ recursion
			if (current.getRight() != null)
			{
				inOrderTrav(current.getRight());
			}
		}
		else
		{
			System.out.print("The tree is empty.");
		}
	}
	
	/**
	 * This method goes through the tree recursively, printing out the right nodes
	 * as it travels, in post-order. It processes the left subtree, right subtree,
	 * and root, in that order.
	 * @param current - The cursor pointing to the current node being processed.
	 */
	public void postOrderTrav(BTNode current)
	{
		if (root == null)
		{
			System.out.print("The tree is empty.");
		}
		else
		{
			// left subtree w/ recursion
		
			if (current.getLeft() != null)
			{
				postOrderTrav(current.getLeft());
			}
		
			// right subtree w/ recursion
			if (current.getRight() != null)
			{
				postOrderTrav(current.getRight());
			}
		
			// root
			System.out.print(current.getData() + " ");
		}
	}
	
	/**
	 * This method is used before the {@link #getPredecessor(int, BTNode)} and the
	 * {@link #getSuccessor(int, BTNode)} methods in the main method in Project1.java,
	 * just to help keep the printed response more easy to organize. It tells whether
	 * the element exists in the tree or not.
	 * @param value - The integer value to search for in the tree.
	 * @param current - The 'cursor' for recursion.
	 * @return - Returns true if the value exists in the tree or false if not.
	 */
	public boolean doesExist(int value, BTNode current)
	{
		if (current == null)
		{
			return false;
		}
		
		if (value == current.getData())
		{
			return true;
		}
		else if (value < current.getData())
		{
			return doesExist(value, current.getLeft());
		}
		else
		{
			return doesExist(value, current.getRight());
		}
	}
	
}
