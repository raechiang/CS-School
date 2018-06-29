/**
 * Rachel Chiang, CS 241-02
 * 
 * Project 3: Dijkstra's Algorithm
 */

/**
 * This class represents a vertex or city. The city contains the data
 * read from city.dat, including the {@link #num}, {@link #code},
 * {@link #name}, {@link #population}, and {@link #elevation}.
 */
class City {
	private int num;
	private String code;
	private String name;
	private int population;
	private int elevation;
	
	/**
	 * The constructor
	 * @param n
	 * @param c
	 * @param cName
	 * @param p
	 * @param e
	 */
	public City(int n, String c, String cName, int p, int e) {
		num = n;
		code = c;
		name = cName;
		population = p;
		elevation = e;
	}
	
	/**
	 * A method which will save all the data into a neat little string
	 * that can eventually be displayed to the user.
	 */
	public String toString()
	{
		String info = num + " " + code + " " + name
				+ " " + population + " " + elevation;
		
		return info;
	}
	
	/**
	 * Getters.
	 */
	public String getCode()
	{
		return code;
	}
	public String getCityName()
	{
		return name;
	}
	public int getNum()
	{
		return num;
	}
}
