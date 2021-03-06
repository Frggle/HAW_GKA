package JUnit_Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import task_4.CustomVertex;
import task_4._main.StartUpMain;


public class DijkstraSearchTest
{
    // +++++++++++++++++++
    // +++++ 1. Graph+++++ 
    // +++++++++++++++++++
    String path1 = "./src/bspGraphen/bsp1.graph";
    CustomVertex start1 = new CustomVertex("a");
    CustomVertex ende1 = new CustomVertex("f");
         
    @SuppressWarnings("static-access")
    @Test
    public void testDijkstraSearch1()
    {
        ArrayList<CustomVertex> erwarteteLoesung = new ArrayList<CustomVertex>();
        erwarteteLoesung.add(new CustomVertex("a"));
        erwarteteLoesung.add(new CustomVertex("k"));
        erwarteteLoesung.add(new CustomVertex("g"));
        erwarteteLoesung.add(new CustomVertex("e"));
        erwarteteLoesung.add(new CustomVertex("f"));
        StartUpMain startMain = new StartUpMain();
        List<CustomVertex> dijkstraLoesung = startMain.programmStarten(path1,start1,ende1,"bfs");
        assertEquals(erwarteteLoesung, dijkstraLoesung);
    }

    // +++++++++++++++++++
    // +++++ 2. Graph+++++ 
    // +++++++++++++++++++
    String path2 = "./src/bspGraphen/bsp2.graph";
    CustomVertex start2 = new CustomVertex("j");
    CustomVertex ende2 = new CustomVertex("e");
         
    @SuppressWarnings("static-access")
    @Test
    public void testDijkstraSearch2()
    {
        ArrayList<CustomVertex> erwarteteLoesung = new ArrayList<CustomVertex>();
        erwarteteLoesung.add(new CustomVertex("j"));
        erwarteteLoesung.add(new CustomVertex("c"));
        erwarteteLoesung.add(new CustomVertex("e"));
        StartUpMain startMain = new StartUpMain();
        List<CustomVertex> dijkstraLoesung = startMain.programmStarten(path2,start2,ende2,"bfs");
        assertEquals(erwarteteLoesung, dijkstraLoesung);
    }
    
    String path2neg = "./src/bspGraphen/bsp2.graph";
    CustomVertex start2neg = new CustomVertex("a");
    CustomVertex ende2neg = new CustomVertex("i");
    //-------------------------------------------------- Endlosschleife, wenn keine Verbindung gefunden wurde----------------!!!!!!!!
    @SuppressWarnings("static-access")
    @Test
    public void testDijkstraSearch2Negativ()
    {
    	StartUpMain startMain = new StartUpMain();
        List<CustomVertex> dijkstraLoesung = startMain.programmStarten(path2neg,start2neg,ende2neg,"bfs");
        assertEquals(null, dijkstraLoesung);
    }
    
    // +++++++++++++++++++
    // +++++ 3. Graph+++++ 
    // +++++++++++++++++++
    String path3 = "./src/bspGraphen/bsp3.graph";
    CustomVertex start3 = new CustomVertex("Detmold",195);
    CustomVertex ende3 = new CustomVertex("Hannover",132);
    @SuppressWarnings("static-access")
    @Test
    public void testDijkstraSearch3()
    {
        ArrayList<CustomVertex> erwarteteLoesung = new ArrayList<CustomVertex>();
        erwarteteLoesung.add(new CustomVertex("Detmold",195));
        erwarteteLoesung.add(new CustomVertex("Hameln",166));
        erwarteteLoesung.add(new CustomVertex("Walsrode",81));
        erwarteteLoesung.add(new CustomVertex("Minden",157));
        erwarteteLoesung.add(new CustomVertex("Hannover",132));
        StartUpMain startMain = new StartUpMain();
        List<CustomVertex> dijkstraLoesung = startMain.programmStarten(path3,start3,ende3,"bfs");
        assertEquals(erwarteteLoesung, dijkstraLoesung);
    }

    // +++++++++++++++++++
    // +++++ 4. Graph+++++ 
    // +++++++++++++++++++
    String path4 = "./src/bspGraphen/bsp4.graph";
    CustomVertex start4 = new CustomVertex("h");
    CustomVertex ende4 = new CustomVertex("e");
    @SuppressWarnings("static-access")
    @Test
    public void testDijkstraSearch4()
    {
        ArrayList<CustomVertex> erwarteteLoesung = new ArrayList<CustomVertex>();
        erwarteteLoesung.add(new CustomVertex("h"));
        erwarteteLoesung.add(new CustomVertex("c"));
        erwarteteLoesung.add(new CustomVertex("d"));
        erwarteteLoesung.add(new CustomVertex("e"));
        StartUpMain startMain = new StartUpMain();
        List<CustomVertex> dijkstraLoesung = startMain.programmStarten(path4,start4,ende4,"bfs");
        assertEquals(erwarteteLoesung, dijkstraLoesung);
    }

    // +++++++++++++++++++
    // +++++ 5. Graph+++++ 
    // +++++++++++++++++++
    String path5 = "./src/bspGraphen/bsp5.graph";
    CustomVertex start5 = new CustomVertex("b");
    CustomVertex ende5 = new CustomVertex("f");
    @SuppressWarnings("static-access")
    @Test
    public void testDijkstraSearch5()
    {
        ArrayList<CustomVertex> erwarteteLoesung = new ArrayList<CustomVertex>();
        erwarteteLoesung.add(new CustomVertex("b"));
        erwarteteLoesung.add(new CustomVertex("k"));
        erwarteteLoesung.add(new CustomVertex("c"));
        erwarteteLoesung.add(new CustomVertex("d"));
        erwarteteLoesung.add(new CustomVertex("e"));
        erwarteteLoesung.add(new CustomVertex("f"));
        StartUpMain startMain = new StartUpMain();
        List<CustomVertex> dijkstraLoesung = startMain.programmStarten(path5,start5,ende5,"bfs");
        assertEquals(erwarteteLoesung, dijkstraLoesung);
    }

    // +++++++++++++++++++
    // +++++ 6. Graph+++++ 
    // +++++++++++++++++++
    String path6 = "./src/bspGraphen/bsp6.graph";
    CustomVertex start6 = new CustomVertex("1");
    CustomVertex ende6 = new CustomVertex("5");
    @SuppressWarnings("static-access")
    @Test
    public void testDijkstraSearch6()
    {
        ArrayList<CustomVertex> erwarteteLoesung = new ArrayList<CustomVertex>();
        erwarteteLoesung.add(new CustomVertex("1"));
        erwarteteLoesung.add(new CustomVertex("8"));
        erwarteteLoesung.add(new CustomVertex("5"));
        StartUpMain startMain = new StartUpMain();
        List<CustomVertex> dijkstraLoesung = startMain.programmStarten(path6,start6,ende6,"bfs");
        assertEquals(erwarteteLoesung, dijkstraLoesung);
    }
    
 // ****************************************************
 	// ************* teste gegen andere Algorithmen *******
 	// ****************************************************
 	String path7 = "./src/bspGraphen/bsp3.graph";
 	CustomVertex start7 = new CustomVertex("Hameln",166);
 	CustomVertex ende7 = new CustomVertex("Hamburg",0);
 	@SuppressWarnings("static-access")
 	@Test
 	public void testDijkstraSearch7()
 	{
 		StartUpMain startMain = new StartUpMain();
 		List<CustomVertex> astarLoesung = startMain.programmStarten(path7, start7, ende7, "astar");
 		List<CustomVertex> dijkstraLoesung = startMain.programmStarten(path7, start7, ende7, "dijkstra");
 		assertEquals(dijkstraLoesung, astarLoesung);		
 	}
}
