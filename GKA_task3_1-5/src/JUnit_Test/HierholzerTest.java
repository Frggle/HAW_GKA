package JUnit_Test;

import static org.junit.Assert.*;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Test;

import task_4.CustomVertex;
import task_4.Algorithmen.HierholzerEulertour;
import task_4.GraphGenerator.EulerGraphGenerator;
import task_4._main.StartUpMain;

public class HierholzerTest
{
	// Kantenanzahl von G (Graph) muss identisch sein mit Kantenanzahl von G' (EulerTour)
	@SuppressWarnings({ "static-access", "unused" })
	@Test
	public void HierholzerAnzahlKanten1()
	{
		String path = "./src/bspGraphen/eulerNikolaus.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");
		
		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "Hierholzer");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();
		
		System.err.println("HierholzerAnzahlKanten1, eulerMini1");
		HierholzerEulertour hierholzer = new HierholzerEulertour(g);
		
		assertEquals(hierholzer.gibKantenfolge().size(), g.edgeSet().size());
	}

	// Kantenanzahl von G (Graph) muss identisch sein mit Kantenanzahl von G' (EulerTour)
	@SuppressWarnings({ "static-access", "unused" })
	@Test
	public void HierholzerAnzahlKanten2()
	{
		String path = "./src/bspGraphen/eulerMini1.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");
		
		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "Hierholzer");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		System.err.println("HierholzerAnzahlKanten2, eulerMini1");
		HierholzerEulertour hierholzer = new HierholzerEulertour(g);

		assertEquals(hierholzer.gibKantenfolge().size(), g.edgeSet().size());
	}

	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */
	
	// Die Kantenfolge der Eulertour muss zusammenhängend sein und die Kanten müssen tatsächlich einen Zyklus bilden
	@SuppressWarnings({ "static-access", "unused" })
	@Test
	public void ZusammenHaengendAlleKantenBenutzt()
	{
		String path = "./src/bspGraphen/eulerNikolaus.graph";
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");
		
		StartUpMain main = new StartUpMain();

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "fleury");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		System.err.println("AlleKantenVerbunden, eulerNikolaus");
		HierholzerEulertour hierholzer = new HierholzerEulertour(g);
		
		List<DefaultWeightedEdge> edgesEulertour = hierholzer.gibKantenfolge();
		
		Graph<CustomVertex, DefaultWeightedEdge> eulerGraph = null;
		try
		{
			eulerGraph = hierholzer.gibEulerGraph();
		} catch (Exception e1)
		{
			System.err.println("Fehler beim Hierholzer Eulergraph - JUnit Tests");
		}
		
		ConnectivityInspector<CustomVertex, DefaultWeightedEdge> connect = 
				new ConnectivityInspector<CustomVertex, DefaultWeightedEdge>((UndirectedGraph<CustomVertex, DefaultWeightedEdge>) eulerGraph);
		assertTrue(connect.isGraphConnected());
		
		// Alle Kanten aus der Eulertour müssen identisch sein mit den Kanten aus dem Originalgraphen
		assert(edgesEulertour.containsAll(g.edgeSet()));
//		Boolean selbenKantenAusOrginalGraph = true;
//		for(DefaultWeightedEdge edge : edgesEulertour)
//		{
//			selbenKantenAusOrginalGraph = selbenKantenAusOrginalGraph && g.containsEdge(edge);
//		}
//		assertTrue(selbenKantenAusOrginalGraph);
		
		// Es wurden alle Kanten aus dem Originalgraph verwendet
		assertEquals(g.edgeSet().size(), edgesEulertour.size());
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

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "Hierholzer");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		System.err.println("HierholzerStartEndknoten1, eulerMini1");
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

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "Hierholzer");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		System.err.println("HierholzerStartEndknoten2, eulerNikolaus");
		HierholzerEulertour hierholzer = new HierholzerEulertour(g);

		assertEquals(hierholzer.gibStartknoten(), hierholzer.gibEndknoten());
	}

	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */

//	@SuppressWarnings({ "static-access", "unused" })
//	@Test
//	public void Hierholzer100Zyklen1()
//	{
//		String path = "./src/bspGraphen/eulerNikolaus.graph";
//		CustomVertex start = new CustomVertex("eins");
//		CustomVertex ende = new CustomVertex("eins");
//
//		StartUpMain main = new StartUpMain();
//
//		List<CustomVertex> temp = main.programmStarten(path, start, ende, "Hierholzer");
//		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();
//
//		HierholzerEulertour hierholzer;
//
//		for (int i = 1; i <= 100; i++)
//		{
//			hierholzer = new HierholzerEulertour(g);	
//			assertEquals(hierholzer.gibStartknoten(), hierholzer.gibEndknoten());
//		}
//	}
//	
//	@SuppressWarnings({ "static-access", "unused" })
//	@Test
//	public void Hierholzer100Zyklen2()
//	{
//		String path = "./src/bspGraphen/eulerMini1.graph";
//		CustomVertex start = new CustomVertex("eins");
//		CustomVertex ende = new CustomVertex("eins");
//
//		StartUpMain main = new StartUpMain();
//
//		List<CustomVertex> temp = main.programmStarten(path, start, ende, "Hierholzer");
//		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();
//
//		HierholzerEulertour hierholzer;
//
//		for (int i = 1; i <= 100; i++)
//		{
//			hierholzer = new HierholzerEulertour(g);	
//			assertEquals(hierholzer.gibStartknoten(), hierholzer.gibEndknoten());
//		}
//	}
		
	@Test
	public void RandomEulerGraph()
	{
		EulerGraphGenerator gen;
		
		for(int i = 3; i<=40; i++)
		{
			gen = new EulerGraphGenerator();
			gen.baueEulerGraph(i);
			Graph<CustomVertex, DefaultWeightedEdge> erzeugterGraph = gen.gibEulerGraph();
			
			HierholzerEulertour hierholzer = new HierholzerEulertour(erzeugterGraph);
			Graph<CustomVertex, DefaultWeightedEdge> hierholzerEulerGraph = null;
			try
			{
				hierholzerEulerGraph = hierholzer.gibEulerGraph();
			} catch (Exception e)
			{
				System.err.println("Error beim Hierholzer");
			}
			
			// Testet ob Eulertour zusammenhängend ist
			ConnectivityInspector<CustomVertex, DefaultWeightedEdge> connect = 
					new ConnectivityInspector<CustomVertex, DefaultWeightedEdge>((UndirectedGraph<CustomVertex, DefaultWeightedEdge>) hierholzerEulerGraph);
			assertTrue(connect.isGraphConnected());
			
			// Start und Endknoten sind identisch
			assertEquals(hierholzer.gibStartknoten(), hierholzer.gibEndknoten());
			
			// Selbe Anzahl an Kanten
			assertEquals(hierholzer.gibKantenfolge().size(), erzeugterGraph.edgeSet().size());
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

		List<CustomVertex> temp = main.programmStarten(path, start, ende, "Hierholzer");
		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();

		HierholzerEulertour hierholzer = new HierholzerEulertour(g);
	}
}
