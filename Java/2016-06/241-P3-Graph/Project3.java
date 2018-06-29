/**
 * Rachel Chiang, CS 241-02
 * 
 * Project 3: Dijkstra's Algorithm
 * 
 * This project implements a graph structure and may calculate the optimal path
 * between two vertexes using Dijkstra's algorithm. Here, each vertex represents 
 * a city, and an 'optimal' path is considered to be the shortest chain of roads 
 * between separate cities. Other abilities include finding the information of
 * a city given its code (two capital letters), adding a new edge (inserting a 
 * road) between two cities given two codes and a double, and removing an edge
 * between two cities given two codes. In all cases, if any code is incorrect
 * (for instance if the city does not exist), the command will be ignored. If
 * a provided double is less than or equal to 0, the command will be ignored.
 * Furthermore, more than one path between two cities is not allowed.
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * This class contains the {@link #main(String[])} method.
 */
class Project3 {

	public Project3() {
	}

	/**
	 * This method reads and saves data from the files city.dat and road.dat. It
	 * creates instances from the classes UserInterface, Digraph, Edge, and City.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// Creation of Digraph and UserInterface class
		Digraph graph = new Digraph();
		UserInterface ui = new UserInterface();
		
		// City data saved into Digraph
		Scanner cityIn = new Scanner(new FileReader("city.dat"));
		while (cityIn.hasNext())
		{
			int cityNum = cityIn.nextInt();
			String cityCode = cityIn.next();
			String cityName = cityIn.next();
			while (!(cityIn.hasNextInt()))
			{
				cityName += " " + cityIn.next();
			}
			int population = cityIn.nextInt();
			int elevation = cityIn.nextInt();
			
			City newCity = new City(cityNum, cityCode, cityName, 
					population, elevation);
			graph.addCity(newCity);
		}
		
		// Road data saved into Digraph
		Scanner roadIn = new Scanner(new FileReader("road.dat"));
		while (roadIn.hasNext())
		{
			int cityNumFrom = roadIn.nextInt();
			int cityNumTo = roadIn.nextInt();
			double roadWeight = roadIn.nextDouble();
			Edge newEdge = new Edge(cityNumTo - 1, roadWeight);
			graph.addEdge(cityNumFrom - 1, newEdge);
		}
		
		cityIn.close();
		roadIn.close();
		
		// User interaction and graph operations
		ui.welcome();
		boolean wantContinue = true;
		while (wantContinue)
		{
			char input = ui.requestCmd();
			wantContinue = ui.response(input);
			
			switch(input)
			{
				case 'Q': case 'q':
					// Q: User gives CC, program returns city info
					String cc = ui.requestCC();
					int cityIndex = graph.cityExist(cc);
					if (cityIndex != -1)
					{
						ui.printCityInfo(graph.getCity(cityIndex).toString());
					}
					else
					{
						// CC not found
						ui.doesNotExist(cc);
					}
					break;
				case 'D': case 'd':
				case 'I': case 'i':
				case 'R': case 'r':
					String cc1 = ui.requestCC();
					String cc2 = ui.requestCC();
					
					int cityIndex1 = graph.cityExist(cc1);
					int cityIndex2 = graph.cityExist(cc2);
					if (cityIndex1 != -1 && cityIndex2 != -1 && !(cc1.equals(cc2)))
					{
						// Two CCs were both found and are not identical
						switch(input)
						{
							case 'D': case 'd':
								// D: Given two CCs, program returns shortest distance
								int[] path = graph.Dijkstra(cityIndex1, cityIndex2);
								if (path[path.length - 1] == -1)
								{
									// empty, no path exists
									ui.doesNotExist("The path between " 
											+ graph.getCity(cityIndex1).getCityName() 
											+ " and " 
											+ graph.getCity(cityIndex2).getCityName());
								}
								else
								{
									// a path exists
									String minDistStr = "";
									
									for (int i = 0; i < path.length; ++i)
									{
										if (path[i] != -1)
										{
											minDistStr += graph.getCity(path[i]).getCityName() 
													+ ", ";
										}
									}
									ui.distReport(graph.getCity(cityIndex1).getCityName(), 
											graph.getCity(cityIndex2).getCityName(),
											minDistStr, graph.calcPathWeight(cityIndex1, path));
								}
								break;
							case 'I': case 'i':
								// I: Given two CCs and edge weight, add the edge
								double dist = ui.requestDub();
								if (dist > 0)
								{
									Edge newEdge = new Edge(cityIndex2, dist);
									boolean addSuccess = graph.addEdge(cityIndex1, newEdge);
									
									if (addSuccess)
									{
										ui.printInsertion(graph.getCity(cityIndex1),
												graph.getCity(cityIndex2),
												graph.getDistance(cityIndex1, cityIndex2));
									}
									else
									{
										ui.unsucAdd(graph.getCity(cityIndex1).getCityName(), 
												graph.getCity(cityIndex2).getCityName());
									}
								}
								else
								{
									// Dij doesn't work with neg edge weights
									ui.badDub();
								}
								break;
							case 'R': case 'r':
								// R: Given two CCs, remove their edge
								if (graph.edgeRemove(cityIndex1, cityIndex2))
								{
									// success
									ui.removalSuccessful(graph.getCity(cityIndex1).getCityName(), 
											graph.getCity(cityIndex2).getCityName());
								}
								else
								{
									// failed, the edge between the two dne
									ui.doesNotExist("The road between " + cc1 
											+ " and " + cc2);
								}
								break;
						}
					}
					else
					{
						// user input went wrong somewhere
						if (cityIndex1 == -1)
						{
							// first cc dne
							ui.doesNotExist(cc1);
						}
						if (cityIndex2 == -1)
						{
							// second cc dne
							ui.doesNotExist(cc2);
						}
						if (cc1.equals(cc2))
						{
							// user gave the same two cities
							ui.badCitiesIn();
						}
					}
					break;
			}
		}
	}

}
