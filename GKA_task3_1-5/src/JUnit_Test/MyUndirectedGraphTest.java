package JUnit_Test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;
import org.junit.Test;

import task_4.MyCustomGraphs.MyUndirectedGraph;

public class MyUndirectedGraphTest
{
    @SuppressWarnings("rawtypes")
    @Test
    public void testMyUndirectedGraph()
    {
        Graph graph = new MyUndirectedGraph();
        assertTrue(graph instanceof Pseudograph);
    }
}
