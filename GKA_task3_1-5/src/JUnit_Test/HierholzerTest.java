package JUnit_Test;

import static org.junit.Assert.*;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Test;

import task_4.CustomVertex;
import task_4.Algorithmen.HierholzerEulertour;
import task_4._main.StartUpMain;

public class HierholzerTest
{
	// Kantenanzahl von G muss identisch sein mit Kantenanzahl von G'
	@SuppressWarnings({ "static-access", "unused" })
	@Test
	public void HierholzerAnzahlKanten1()
	{
		String path = "./src/bspGraphen/eulerNikolaus.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");
		
		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "hierholzer");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		HierholzerEulertour hierholzer = new HierholzerEulertour(g);

		assertEquals(hierholzer.gibKantenfolge().size(), g.edgeSet().size());
	}

	// Kantenanzahl von G muss identisch sein mit Kantenanzahl von G'
	@SuppressWarnings({ "static-access", "unused" })
	@Test
	public void HierholzerAnzahlKanten2()
	{
		String path = "./src/bspGraphen/eulerMini1.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");
		
		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "hierholzer");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		HierholzerEulertour hierholzer = new HierholzerEulertour(g);
		
		assertEquals(hierholzer.gibKantenfolge().size(), g.edgeSet().size());
		}

	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */

	@SuppressWarnings({ "static-access", "unused" })
	// Start- und Endknoten der Eulertour sind identisch
	@Test
	public void HierholzerStartEndknoten1()
	{
		String path = "./src/bspGraphen/eulerMini1.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");
		
		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "hierholzer");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		HierholzerEulertour hierholzer = new HierholzerEulertour(g);
		
		assertEquals(hierholzer.gibStartknoten(), hierholzer.gibEndknoten());
	}

	@SuppressWarnings({ "static-access", "unused" })
	// Start- und Endknoten der Eulertour sind identisch
	@Test
	public void HierholzerStartEndknoten2()
	{
		String path = "./src/bspGraphen/eulerNikolaus.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");
		
		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "hierholzer");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		HierholzerEulertour hierholzer = new HierholzerEulertour(g);

		assertEquals(hierholzer.gibStartknoten(), hierholzer.gibEndknoten());
	}

	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */

	@SuppressWarnings({ "static-access", "unused" })
	@Test
	public void Hierholzer100Zyklen()
	{
		String path = "./src/bspGraphen/eulerNikolaus.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");

		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "hierholzer");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		HierholzerEulertour hierholzer;

		for (int i = 1; i <= 100; i++)
		{
			hierholzer = new HierholzerEulertour(g);	
			assertEquals(hierholzer.gibStartknoten(), hierholzer.gibEndknoten());
		}
	}
	
	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */

	@SuppressWarnings({ "static-access", "unused" })
	@Test(expected=IllegalArgumentException.class)
	public void HierholzerNegativTest()
	{
		String path = "./src/bspGraphen/eulerKeinNikolaus.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");

		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "fleury");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		HierholzerEulertour hierholzer = new HierholzerEulertour(g);
	}
}
