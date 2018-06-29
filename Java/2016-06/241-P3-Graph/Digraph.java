/**
 * Rachel Chiang, CS 241-02
 * 
 * Project 3: Dijkstra's Algorithm
 */

/**
 * This class represents the graph and all the operations one can apply to the 
 * graph (in other words, the fun bulk of the project). There are four private
 * fields: {@link #cityList}, {@link #connections}, {@link #CITIES_AMNT},
 * and {@link #size}. The big methods include {@link #addCity(City)}, 
 * {@link #addEdge(int, Edge)}, {@link #edgeRemove(int, int)}, and 
 * {@link #Dijkstra(int, int)}. The remainder are either simple calculators, 
 * searchers, or getters: {@link #searchSmallest(double[], City[])}, 
 * {@link #calcPathWeight(int, int[])}, {@link #cityExist(String)}, 
 * {@link #getCity(int)}, and {@link #getDistance(int, int)}.
 */
class Digraph {
	
	/**
	 * The arrays cityList and connections contain the objects City and Edge
	 * respectively. The {@link #cityList} contains all the vertices, whereas
	 * the {@link #connections} contains all the edges with the index of the
	 * array of Edges corresponding with the index of the "from" city in 
	 * {@link #cityList}.
	 */
	private City[] cityList;
	private Edge[] connections;
	
	/**
	 * The {@link #CITIES_AMNT} just is the amount of cities in the city.dat
	 * file. There is little reason to have this except for quick access to
	 * change if new files with a different amount of cities in them were to
	 * be loaded. The {@link #size} is basically used for adding cities to
	 * the {@link #cityList}.
	 */
	private int CITIES_AMNT = 20;
	private int size;
	
	/**
	 * This is the constructor. It initializes {@link #cityList} and 
	 * {@link #connections} to null and the {@link #size} to zero.
	 */
	public Digraph() {
		cityList = new City[CITIES_AMNT];
		connections = new Edge[CITIES_AMNT];
		for (int i = 0; i < CITIES_AMNT; ++i)
		{
			cityList[i] = null;
			connections[i] = null;
		}
		size = 0;
	}
	
	/**
	 * This method simply adds a new city to {@link #cityList} and then
	 * increments the size by one. This method should really only be used
	 * at the beginning of the program, when the file city.dat is being
	 * read.
	 * @param newCity - The city to add to the list.
	 */
	public void addCity(City newCity)
	{
		cityList[size] = newCity;
		++size;
	}
	
	/**
	 * This method adds a new edge to {@link #connections} given the index the new
	 * edge starts at and the new edge to add itself.
	 * @param fromIndex - the index that corresponds with the city where the edge
	 * 			starts
	 * @param newEdge - the new edge to be added
	 * @return - If the addition was successful, it will return true. Otherwise,
	 * 			it will return false.
	 */
	public boolean addEdge(int fromIndex, Edge newEdge)
	{
		if (connections[fromIndex] == null)
		{
			// There are no roads leaving from the city given by fromIndex
			connections[fromIndex] = newEdge;
			return true;
		}
		else
		{
			// There are roads, so we must add it to the end of the list...
			Edge cur = connections[fromIndex];
			while (cur.getNext() != null)
			{
				if (cur.getTo() == newEdge.getTo())
				{
					// ... unless the road has already been defined. We cannot
					// redefine it and we cannot duplicate it, so return false
					return false;
				}
				cur = cur.getNext();
			}
			cur.setNext(newEdge);
			
			return true;
		}
	}
	
	/**
	 * This method removes the road between two cities.
	 * 
	 * @param fromCity - The index corresponding to the city that the road is going from
	 * @param toCity - The index corresponding to the city that the road is going to
	 * @return - True if the removal was successful, false if not.
	 */
	public boolean edgeRemove(int fromCity, int toCity)
	{
		Edge prev = connections[fromCity];
		if (prev == null)
		{
			// edge has no paths outward
			return false;
		}
		else
		{
			// edge has outward path
			
			// check head
			if (prev.getTo() == toCity)
			{
				connections[fromCity] = prev.getNext();
				return true;
			}
			
			// check rest of list
			Edge cur = prev.getNext();
			while (cur != null)
			{
				if (cur.getTo() == toCity)
				{
					prev.setNext(cur.getNext());
					cur.setNext(null);
					return true;
				}
				prev = prev.getNext();
				cur = cur.getNext();
			}
		}
		
		return false;
	}
	
	/**
	 * This method finds the shortest path between two cities. This method uses
	 * {@link #searchSmallest(double[], City[])} for assistance.
	 * @param source - The index of the "from" city
	 * @param target - The index of the "to" city
	 * @return - s, the set containing the path with the shortest distance
	 */
	public int[] Dijkstra(int source, int target)
	{
		// An array containing the distance from source to a given vertex
		double[] dist = new double[size];
		// An array containing the indices of the cities last visited by
		// the prev's indices
		int[] prev = new int[size];
		// An array which will be either empty or contain the indices of the cities
		// of the shortest path
		int s[] = new int[size];
		// An array holding all the cities that must be checked upon every iteration
		City[] q = new City[size];
		
		// initialization of the arrays to infinity, -1, or the corresponding cities
		// from the {@link #cityList}
		for (int i = 0; i < size; ++i)
		{
			dist[i] = Double.POSITIVE_INFINITY;
			prev[i] = -1;
			s[i] = -1;
			q[i] = cityList[i];
		}
		
		// The distance between the source to the source will be 0
		dist[source] = 0;
		// Keeping a counter because an array itself isn't going to be null
		// or checking the unanimous nullity of all the elements in the array might take
		// longer than just keeping a counter? A boolean with exit routes might work
		// in retrospect, might've been clearer to just implement q as a heap but oh well
		int counter = 0;
		
		while (counter < q.length)
		{
			++counter;
			int u = searchSmallest(dist, q);
			
			if (dist[u] == Double.POSITIVE_INFINITY)
			{
				break;
			}
			else
			{
				// remove the city index u from q
				q[u] = null;
				
				// if u is the target we found the shortest path
				if (u == target)
				{
					int it = s.length - 1;
					// I stored it in backwards here, which is somewhat
					// trivial I think, because if it wasn't backwards here
					// it'd be backwards somewhere else (in my case, in the
					// Project3 class)
					while (prev[u] != -1 && it >= 0)
					{
						s[it] = u;
						u = prev[u];
						--it;
					}
					return s;
				}
				else
				{
					for (int v = 0; v < q.length; ++v)
					{
						// checking all vertices v
						double alt = Double.POSITIVE_INFINITY;
						// initialize alt
						if (q[v] != null)
						{
							// If v is still in q, it still may be optimized
							if (getDistance(u, v) != -1)
							{
								// If v exists as a neighbor of u
								alt = dist[u] + getDistance(u, v);
								// attempt to minimize the distance stored for it
								if (alt < dist[v])
								{
									dist[v] = alt;
									prev[v] = u;
								}
							}
						}
					}
				}
			}
		}
		return s;
	}
	
	/**
	 * This method assists the {@link #Dijkstra(int, int)} algorithm. It simply finds
	 * the index of the city with the shortest distance and that still exists in q.
	 * @param dist - The array of distances to search through
	 * @param q - The array of cities to check for whether or not the city still needs
	 *			to be considered
	 * @return - the index at which the smallest distance is
	 */
	public int searchSmallest(double[] dist, City[] q)
	{
		// initialize the return value
		int smallIndex = 0;
		while (q[smallIndex] == null)
		{
			// make sure the initialized one itself still belongs to q
			// in case the initialization ends up needing to be returned
			++smallIndex;
		}
		
		// The actual searching
		for (int i = 0; i < dist.length; ++i)
		{
			if (dist[i] <= dist[smallIndex])
			{
				// check for smaller
				if (q[i] != null)
				{
					// check for existence 
					smallIndex = i;
				}
			}
		}
		
		return smallIndex;
	}
	
	/**
	 * This method calculates the weight of the path given by p from a city. It is 
	 * used for the 'D' command.
	 * @param fromCity - The "from" city
	 * @param p - The shortest path
	 * @return - The distance along this path p
	 */
	public double calcPathWeight(int fromCity, int[] p)
	{
		double weight = 0;
		for (int i = 0; i < p.length; ++i)
		{
			if (p[i] != -1)
			{
				// It must be a valid index
				weight += getDistance(fromCity, p[i]);
				fromCity = p[i];
			}
		}
		return weight;
	}
	
	/**
	 * This method determines whether a city exists in the cityList and tells at
	 * which index, or -1 if it doesn't.
	 * @param c
	 * @return
	 */
	public int cityExist(String c)
	{
		for (int i = 0; i < size; ++i)
		{
			if (cityList[i].getCode().equals(c))
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * This method simply retrieves the city in the graph from the {@link #cityList}
	 * @param index - given some index
	 * @return - the city at index in {@link #cityList}
	 */
	public City getCity(int index)
	{
		if (index < size)
		{
			return cityList[index];
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Another simple 'getter' that will find the distance between two cities.
	 * If it isn't found though, it will just return -1.
	 * @param fromCity
	 * @param toCity
	 * @return
	 */
	public double getDistance(int fromCity, int toCity)
	{
		Edge cur = connections[fromCity];
		
		if (fromCity == toCity)
		{
			return -1;
		}
		
		while (cur != null)
		{
			if (cur.getTo() == toCity)
			{
				return cur.getDistance();
			}
			cur = cur.getNext();
		}
		
		return -1;
	}
	
}
