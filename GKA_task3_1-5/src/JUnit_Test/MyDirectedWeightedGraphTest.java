package JUnit_Test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.junit.Test;

import task_4.MyCustomGraphs.MyDirectedWeightedGraph;

public class MyDirectedWeightedGraphTest
{
    @SuppressWarnings("rawtypes")
    @Test
    public void testMyDirectedGraph()
    {
        Graph graph = new MyDirectedWeightedGraph();
        assertTrue(graph instanceof DirectedWeightedPseudograph);
    }
}
