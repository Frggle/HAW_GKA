package JUnit_Test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedPseudograph;
import org.junit.Test;

import task_3.MyCustomGraphs.MyDirectedGraph;

public class MyDirectedGraphTest
{
    @SuppressWarnings("rawtypes")
    @Test
    public void testMyDirectedGraph()
    {
        Graph graph = new MyDirectedGraph();
        assertTrue(graph instanceof DirectedPseudograph);
    }
}
