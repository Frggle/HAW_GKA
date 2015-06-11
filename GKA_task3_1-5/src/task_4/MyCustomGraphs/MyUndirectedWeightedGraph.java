package task_4.MyCustomGraphs;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;


public class MyUndirectedWeightedGraph extends WeightedPseudograph<String, DefaultWeightedEdge>
{
    /**
     * 
     */
    private static final long serialVersionUID = 8640393625309671616L;
    WeightedPseudograph<String, DefaultWeightedEdge> graph;
    
    public MyUndirectedWeightedGraph()
    {
        super(DefaultWeightedEdge.class);
        graph = new WeightedPseudograph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
    }
}
