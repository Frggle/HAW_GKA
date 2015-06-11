package task_4.MyCustomGraphs;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;


public class MyDirectedWeightedGraph extends DirectedWeightedPseudograph<String, DefaultWeightedEdge>
{
    /**
     * 
     */
    private static final long serialVersionUID = -2748184402642234327L;
    DirectedWeightedPseudograph<String, DefaultWeightedEdge> graph;
    
    public MyDirectedWeightedGraph()
    {
        super(DefaultWeightedEdge.class);
        graph = new DirectedWeightedPseudograph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
    }
}
