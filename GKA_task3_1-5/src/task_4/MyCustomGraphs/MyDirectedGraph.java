package task_4.MyCustomGraphs;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedPseudograph;

import task_4.CustomVertex;


public class MyDirectedGraph extends DirectedPseudograph<CustomVertex, DefaultWeightedEdge>
{
    /**
     * 
     */
    private static final long serialVersionUID = -6814455970166853023L;
    DirectedPseudograph<CustomVertex, DefaultWeightedEdge> graph;

    public MyDirectedGraph()
    {
        super(DefaultWeightedEdge.class);
        graph = new DirectedPseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
    }
}
