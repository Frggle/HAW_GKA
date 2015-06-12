package task_4.Algorithmen;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import task_4.CustomVertex;

public class HierholzerEulertour
{

	private Graph<CustomVertex, DefaultWeightedEdge> eulerGraph;
	private List<CustomVertex> knotenFolge;
	
	public HierholzerEulertour(Graph<CustomVertex, DefaultWeightedEdge> g)
	{
		
	}
	
	public List<CustomVertex> gibKantenfolge()
	{
		return knotenFolge;
	}
}
