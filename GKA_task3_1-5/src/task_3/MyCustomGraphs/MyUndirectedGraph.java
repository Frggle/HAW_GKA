package task_3.MyCustomGraphs;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Pseudograph;


public class MyUndirectedGraph extends Pseudograph<String,DefaultWeightedEdge>
{
    /**
     * 
     */
    private static final long serialVersionUID = 7960711891527756198L;
    // ListenableGraph<String, DefaultEdge> graph;
    Pseudograph<String, DefaultWeightedEdge> graph;


    public MyUndirectedGraph()
    {
        super(DefaultWeightedEdge.class);
        graph = new Pseudograph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
    }
}
