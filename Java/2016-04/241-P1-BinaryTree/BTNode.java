/**
 * Rachel Chiang, CS 241-02
 * 
 * Project 1: Binary Search Tree Implementation
 */

/**
 * This class represents the nodes of the BST. It contains the {@link #data}, {@link #left},
 * {@link #right}, and {@link #parent} variables and some simple methods for getting and setting
 * these variables.
 */
class BTNode
{
	/**
	 * This field contains the data (an integer) of the node.
	 */
	private int data;
	
	/**
	 * These fields are relational and keeps the tree linked.
	 */
	private BTNode left;
	private BTNode right;
	private BTNode parent;
	
	/**
	 * This is the constructor for the node.
	 * @param initialData - Used to initialize {@link #data}.
	 * @param initialLeft - Used to initialize {@link #left}.
	 * @param initialRight - Used to initialize {@link #right}.
	 * @param initialParent - Used to initialize {@link #parent}.
	 */
	public BTNode(int initialData, BTNode initialLeft, BTNode initialRight, BTNode initialParent)
	{
		data = initialData;
		left = initialLeft;
		right = initialRight;
		parent = initialParent;
	}
	
	/**
	 * These are simply getters for the fields.
	 */
	public int getData()
	{
		return data;
	}
	public BTNode getLeft()
	{
		return left;
	}
	public BTNode getRight()
	{
		return right;
	}
	public BTNode getParent()
	{
		return parent;
	}
	
	/**
	 * These are simply setters for the fields.
	 */
	public void setData(int newData)
	{
		data = newData;
	}
	public void setLeft(BTNode newLeft)
	{
		left = newLeft;
	}
	public void setRight(BTNode newRight)
	{
		right = newRight;
	}
	public void setParent(BTNode newParent)
	{
		parent = newParent;
	}

}
