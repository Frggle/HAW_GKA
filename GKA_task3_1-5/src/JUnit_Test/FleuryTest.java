package JUnit_Test;

import static org.junit.Assert.*;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Test;

import task_4.CustomVertex;
import task_4.Algorithmen.FleuryEulertour;
import task_4._main.StartUpMain;

public class FleuryTest
{
	// Kantenanzahl von G muss identisch sein mit Kantenanzahl von G'
	@SuppressWarnings({ "static-access", "unused" })
	@Test
	public void FleuryAnzahlKanten1()
	{
		String path = "./src/bspGraphen/eulerNikolaus.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");
		
		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "fleury");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();
		
		System.err.println("FleuryAnzahlKanten1, eulerMini1");
		FleuryEulertour fleury = new FleuryEulertour(g);
		
		assertEquals(fleury.gibKantenfolge().size(), g.edgeSet().size());
	}

	// Kantenanzahl von G muss identisch sein mit Kantenanzahl von G'
	@SuppressWarnings({ "static-access", "unused" })
	@Test
	public void FleuryAnzahlKanten2()
	{
		String path = "./src/bspGraphen/eulerMini1.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");
		
		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "fleury");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		System.err.println("FleuryAnzahlKanten2, eulerMini1");
		FleuryEulertour fleury = new FleuryEulertour(g);

		assertEquals(fleury.gibKantenfolge().size(), g.edgeSet().size());
	}

	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */

	@SuppressWarnings({ "static-access", "unused" })
	// Start- und Endknoten der Eulertour sind identisch
	@Test
	public void FleuryStartEndknoten1()
	{
		String path = "./src/bspGraphen/eulerMini1.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");
		
		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "fleury");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		System.err.println("FleuryStartEndknoten1, eulerMini1");
		FleuryEulertour fleury = new FleuryEulertour(g);
				
		assertEquals(fleury.gibStartknoten(), fleury.gibEndknoten());
	}

	@SuppressWarnings({ "static-access", "unused" })
	// Start- und Endknoten der Eulertour sind identisch
	@Test
	public void FleuryStartEndknoten2()
	{
		String path = "./src/bspGraphen/eulerNikolaus.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");
		
		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "fleury");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		System.err.println("FleuryStartEndknoten2, eulerNikolaus");
		FleuryEulertour fleury = new FleuryEulertour(g);

		assertEquals(fleury.gibStartknoten(), fleury.gibEndknoten());
	}

	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */

	@SuppressWarnings({ "static-access", "unused" })
	@Test
	public void Fleury100Zyklen()
	{
		String path = "./src/bspGraphen/eulerNikolaus.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");

		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "fleury");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		FleuryEulertour fleury;

		for (int i = 1; i <= 100; i++)
		{
			fleury = new FleuryEulertour(g);	
			assertEquals(fleury.gibStartknoten(), fleury.gibEndknoten());
		}
	}
	
	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */

	@SuppressWarnings({ "static-access", "unused" })
	@Test(expected=IllegalArgumentException.class)
	public void FleuryNegativTest()
	{
		String path = "./src/bspGraphen/eulerKeinNikolaus.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");

		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "fleury");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		FleuryEulertour fleury = new FleuryEulertour(g);
	}
}
