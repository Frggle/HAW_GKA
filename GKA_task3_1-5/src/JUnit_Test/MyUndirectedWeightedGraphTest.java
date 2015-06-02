package JUnit_Test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedPseudograph;
import org.junit.Test;

import task_3.MyCustomGraphs.MyUndirectedWeightedGraph;

public class MyUndirectedWeightedGraphTest
{
    @SuppressWarnings("rawtypes")
    @Test
    public void testMyUndirectedWeightedGraph()
    {
        Graph graph = new MyUndirectedWeightedGraph();
        assertTrue(graph instanceof WeightedPseudograph);
    }
}
