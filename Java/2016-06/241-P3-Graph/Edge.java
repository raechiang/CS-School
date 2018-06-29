/**
 * Rachel Chiang, CS 241-02
 * 
 * Project 3: Dijkstra's Algorithm
 */

/**
 * This class represents the roads between two vertices (cities). I will not
 * be too detailed because it is a pretty simple class.
 */
class Edge {
	/**
	 * Self-explanatory. I chose an int and the index of the city because
	 * it'd be smaller than a whole City and integers are easy to work with
	 */
	private int toIndex;
	private double distance;
	private Edge next;
	
	/**
	 * Constructor
	 * @param to
	 * @param dist
	 */
	public Edge(int to, double dist)
	{
		toIndex = to;
		distance = dist;
		next = null;
	}
	
	/**
	 * Simple setters.
	 * @param n
	 */
	public void setNext(Edge n)
	{
		next = n;
	}
	public void setDistance(double d)
	{
		distance = d;
	}

	/**
	 * Simple getters.
	 * @return
	 */
	public Edge getNext()
	{
		return next;
	}
	public int getTo()
	{
		return toIndex;
	}
	public double getDistance()
	{
		return distance;
	}
}
