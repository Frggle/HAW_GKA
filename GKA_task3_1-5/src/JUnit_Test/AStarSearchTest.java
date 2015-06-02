package JUnit_Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Test;

import task_3.CustomVertex;
import task_3.Algorithmen.AStarSearch;
import task_3.Algorithmen.DijkstraSearch;
import task_3._main.StartUpMain;

public class AStarSearchTest
{
	// +++++++++++++++++++
	// +++++ 1. Graph+++++
	// +++++++++++++++++++
	String path1 = "./src/bspGraphen/bsp1.graph";
	CustomVertex start1 = new CustomVertex("a");
	CustomVertex ende1 = new CustomVertex("f");

	@SuppressWarnings("static-access")
	@Test
	public void testAStarSearch1()
	{
		ArrayList<CustomVertex> erwarteteLoesung = new ArrayList<CustomVertex>();
		erwarteteLoesung.add(new CustomVertex("a"));
		erwarteteLoesung.add(new CustomVertex("k"));
		erwarteteLoesung.add(new CustomVertex("g"));
		erwarteteLoesung.add(new CustomVertex("e"));
		erwarteteLoesung.add(new CustomVertex("f"));
		StartUpMain startMain = new StartUpMain();
		List<CustomVertex> astarLoesung = startMain.programmStarten(path1, start1, ende1, "astar");
		assertEquals(erwarteteLoesung, astarLoesung);
	}

	// +++++++++++++++++++
	// +++++ 2. Graph+++++
	// +++++++++++++++++++
	String path2 = "./src/bspGraphen/bsp2.graph";
	CustomVertex start2 = new CustomVertex("j");
	CustomVertex ende2 = new CustomVertex("e");

	@SuppressWarnings("static-access")
	@Test
	public void testAStarSearch2()
	{
		ArrayList<CustomVertex> erwarteteLoesung = new ArrayList<CustomVertex>();
		erwarteteLoesung.add(new CustomVertex("j"));
		erwarteteLoesung.add(new CustomVertex("c"));
		erwarteteLoesung.add(new CustomVertex("e"));
		StartUpMain startMain = new StartUpMain();
		List<CustomVertex> astarLoesung = startMain.programmStarten(path2, start2, ende2, "astar");
		assertEquals(erwarteteLoesung, astarLoesung);
	}

	String path2neg = "./src/bspGraphen/bsp2.graph";
	CustomVertex start2neg = new CustomVertex("a");
	CustomVertex ende2neg = new CustomVertex("i");

	@SuppressWarnings("static-access")
	@Test
	public void testAStarSearch2Negativ()
	{
		ArrayList<CustomVertex> erwarteteLoesung = new ArrayList<CustomVertex>();
		erwarteteLoesung.add(new CustomVertex("Keine Loesung"));
		StartUpMain startMain = new StartUpMain();
		List<CustomVertex> astarLoesung = startMain.programmStarten(path2neg, start2neg, ende2neg, "astar");
		assertEquals(erwarteteLoesung, astarLoesung);
	}

	// +++++++++++++++++++
	// +++++ 3. Graph+++++
	// +++++++++++++++++++
	String path3 = "./src/bspGraphen/bsp3.graph";
	CustomVertex start3 = new CustomVertex("Detmold", 195);
	CustomVertex ende3 = new CustomVertex("Hannover", 132);

	@SuppressWarnings("static-access")
	@Test
	public void testAStarSearch3()
	{
		ArrayList<CustomVertex> erwarteteLoesung = new ArrayList<CustomVertex>();
		erwarteteLoesung.add(new CustomVertex("Detmold", 195));
		erwarteteLoesung.add(new CustomVertex("Hameln", 166));
		erwarteteLoesung.add(new CustomVertex("Soltau", 63));
		erwarteteLoesung.add(new CustomVertex("Celle", 103));
		erwarteteLoesung.add(new CustomVertex("Hannover", 132));
		StartUpMain startMain = new StartUpMain();
		List<CustomVertex> astarLoesung = startMain.programmStarten(path3, start3, ende3, "astar");
		assertEquals(erwarteteLoesung, astarLoesung);
	}

	// +++++++++++++++++++
	// +++++ 4. Graph+++++
	// +++++++++++++++++++
	String path4 = "./src/bspGraphen/bsp4.graph";
	CustomVertex start4 = new CustomVertex("h");
	CustomVertex ende4 = new CustomVertex("e");

	@SuppressWarnings("static-access")
	@Test
	public void testAStarSearch4()
	{
		ArrayList<CustomVertex> erwarteteLoesung = new ArrayList<CustomVertex>();
		erwarteteLoesung.add(new CustomVertex("h"));
		erwarteteLoesung.add(new CustomVertex("b"));
		erwarteteLoesung.add(new CustomVertex("k"));
		erwarteteLoesung.add(new CustomVertex("g"));
		erwarteteLoesung.add(new CustomVertex("e"));
		StartUpMain startMain = new StartUpMain();
		List<CustomVertex> astarLoesung = startMain.programmStarten(path4, start4, ende4, "astar");
		assertEquals(erwarteteLoesung, astarLoesung);
	}

	// +++++++++++++++++++
	// +++++ 5. Graph+++++
	// +++++++++++++++++++
	String path5 = "./src/bspGraphen/bsp5.graph";
	CustomVertex start5 = new CustomVertex("b");
	CustomVertex ende5 = new CustomVertex("f");

	@SuppressWarnings({ "static-access", "unused" })
	@Test
	public void testAStarSearch5()
	{
		boolean thrown = false;
		try 
		{
		ArrayList<CustomVertex> erwarteteLoesung = new ArrayList<CustomVertex>();
		erwarteteLoesung.add(new CustomVertex("Keine Loesung"));
		StartUpMain startMain = new StartUpMain();
		List<CustomVertex> astarLoesung = startMain.programmStarten(path5, start5, ende5, "astar");
//		assertEquals(erwarteteLoesung, astarLoesung);
		}
		catch (IllegalArgumentException e)
		{
			thrown = true; 
		}
		assertTrue(thrown);
	}

	// +++++++++++++++++++
	// +++++ 6. Graph+++++
	// +++++++++++++++++++
	String path6 = "./src/bspGraphen/bsp6.graph";
	CustomVertex start6 = new CustomVertex("1");
	CustomVertex ende6 = new CustomVertex("5");

	@SuppressWarnings("static-access")
	@Test
	public void testAStarSearch6()
	{
		ArrayList<CustomVertex> erwarteteLoesung = new ArrayList<CustomVertex>();
		erwarteteLoesung.add(new CustomVertex("1"));
		erwarteteLoesung.add(new CustomVertex("8"));
		erwarteteLoesung.add(new CustomVertex("5"));
		StartUpMain startMain = new StartUpMain();
		List<CustomVertex> astarLoesung = startMain.programmStarten(path6, start6, ende6, "astar");
		assertEquals(erwarteteLoesung, astarLoesung);
	}
	
	// ****************************************************
	// ************* teste gegen andere Algorithmen *******
	// ****************************************************
	String path7 = "./src/bspGraphen/bsp3.graph";
	CustomVertex start7 = new CustomVertex("Hameln",166);
	CustomVertex ende7 = new CustomVertex("Hamburg",0);
	@SuppressWarnings("static-access")
	@Test
	public void testAStarSearch7()
	{
		StartUpMain startMain = new StartUpMain();
		List<CustomVertex> astarLoesung = startMain.programmStarten(path7, start7, ende7, "astar");
		List<CustomVertex> dijkstraLoesung = startMain.programmStarten(path7, start7, ende7, "dijkstra");
		assertEquals(dijkstraLoesung, astarLoesung);		
	}
	
	String path8 = "./src/bspGraphen/BIG6000.graph";
	CustomVertex start8 = new CustomVertex("15",15);
	CustomVertex ende8 = new CustomVertex("Endknoten",0);
	@SuppressWarnings("static-access")
	@Test
	public void testAStarSearch8()
	{
		StartUpMain startMain = new StartUpMain();
		@SuppressWarnings("unused")
		List<CustomVertex> temp = startMain.programmStarten(path8, start8, ende8, "");
		double dijkstraLaenge = 0.0;
		double astarLaenge = 0.0;
		Graph<CustomVertex,DefaultWeightedEdge> g = startMain.gibGraph();
		try
		{
			DijkstraSearch dijkstra = new DijkstraSearch(g, start8, ende8);
			dijkstraLaenge = dijkstra.gibWegLaenge();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			AStarSearch astar = new AStarSearch(g, start8, ende8);
			astarLaenge = astar.gibWegLaenge();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		double delta = 1.0;
		assertEquals(dijkstraLaenge,astarLaenge, delta);
	}
}
