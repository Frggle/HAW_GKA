package JUnit_Test;

import static org.junit.Assert.*;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Test;

import task_4.CustomVertex;
import task_4.Algorithmen.FleuryEulertour;
import task_4.GraphGenerator.EulerGraphGenerator;
import task_4._main.StartUpMain;

public class FleuryTest
{
	// Kantenanzahl von G (Graph) muss identisch sein mit Kantenanzahl von G' (EulerTour)
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

	// Kantenanzahl von G (Graph) muss identisch sein mit Kantenanzahl von G' (EulerTour)
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
		FleuryEulertour fleury = new FleuryEulertour(g);
		
		List<DefaultWeightedEdge> edgesEulertour = fleury.gibKantenfolge();
		
		Graph<CustomVertex, DefaultWeightedEdge> eulerGraph = null;
		try
		{
			eulerGraph = fleury.gibEulerGraph();
		} catch (Exception e1)
		{
			System.err.println("Fehler beim Fleury Eulergraph - JUnit Tests");
		}
		
		ConnectivityInspector<CustomVertex, DefaultWeightedEdge> connect = 
				new ConnectivityInspector<CustomVertex, DefaultWeightedEdge>((UndirectedGraph<CustomVertex, DefaultWeightedEdge>) eulerGraph);
		assertTrue(connect.isGraphConnected());
		
		// Alle Kanten aus der Eulertour müssen identisch sein mit den Kanten aus dem Originalgraphen
		Boolean selbenKantenAusOrginalGraph = true;
		for(DefaultWeightedEdge edge : edgesEulertour)
		{
			selbenKantenAusOrginalGraph = selbenKantenAusOrginalGraph && g.containsEdge(edge);
		}
		assertTrue(selbenKantenAusOrginalGraph);
		
		// Es wurden alle Kanten aus dem Originalgraph verwendet
		assertEquals(g.edgeSet().size(), edgesEulertour.size());
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

//	@SuppressWarnings({ "static-access", "unused" })
//	@Test
//	public void Fleury100Zyklen1()
//	{
//		String path = "./src/bspGraphen/eulerNikolaus.graph";
//		CustomVertex start = new CustomVertex("eins");
//		CustomVertex ende = new CustomVertex("eins");
//
//		StartUpMain main = new StartUpMain();
//
//		List<CustomVertex> temp = main.programmStarten(path, start, ende, "fleury");
//		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();
//
//		FleuryEulertour fleury;
//
//		for (int i = 1; i <= 100; i++)
//		{
//			fleury = new FleuryEulertour(g);	
//			assertEquals(fleury.gibStartknoten(), fleury.gibEndknoten());
//		}
//	}
//	
//	@SuppressWarnings({ "static-access", "unused" })
//	@Test
//	public void Fleury100Zyklen2()
//	{
//		String path = "./src/bspGraphen/eulerMini1.graph";
//		CustomVertex start = new CustomVertex("eins");
//		CustomVertex ende = new CustomVertex("eins");
//
//		StartUpMain main = new StartUpMain();
//
//		List<CustomVertex> temp = main.programmStarten(path, start, ende, "fleury");
//		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();
//
//		FleuryEulertour fleury;
//
//		for (int i = 1; i <= 100; i++)
//		{
//			fleury = new FleuryEulertour(g);	
//			assertEquals(fleury.gibStartknoten(), fleury.gibEndknoten());
//		}
//	}
//	
//	@SuppressWarnings({ "static-access", "unused" })
//	@Test
//	public void Fleury100Zyklen3()
//	{
//		String path = "./src/bspGraphen/eulerGroß.graph";
//		CustomVertex start = new CustomVertex("Endknoten", 0);
//		CustomVertex ende = new CustomVertex("Endknoten", 0);
//
//		StartUpMain main = new StartUpMain();
//
//		List<CustomVertex> temp = main.programmStarten(path, start, ende, "fleury");
//		Graph<CustomVertex, DefaultWeightedEdge> g = main.gibGraph();
//
//		FleuryEulertour fleury;
//
//		for (int i = 1; i <= 100; i++)
//		{
//			fleury = new FleuryEulertour(g);	
//			assertEquals(fleury.gibStartknoten(), fleury.gibEndknoten());
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
			
			FleuryEulertour fleury = new FleuryEulertour(erzeugterGraph);
			Graph<CustomVertex, DefaultWeightedEdge> fleuryEulerGraph = null;
			try
			{
				fleuryEulerGraph = fleury.gibEulerGraph();
			} catch (Exception e)
			{
				System.err.println("Error beim Fleury");
			}
			
			// Testet ob Eulertour zusammenhängend ist
			ConnectivityInspector<CustomVertex, DefaultWeightedEdge> connect = 
					new ConnectivityInspector<CustomVertex, DefaultWeightedEdge>((UndirectedGraph<CustomVertex, DefaultWeightedEdge>) fleuryEulerGraph);
			assertTrue(connect.isGraphConnected());
			
			// Start und Endknoten sind identisch
			assertEquals(fleury.gibStartknoten(), fleury.gibEndknoten());
			
			// Selbe Anzahl an Kanten
			assertEquals(fleury.gibKantenfolge().size(), erzeugterGraph.edgeSet().size());
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
