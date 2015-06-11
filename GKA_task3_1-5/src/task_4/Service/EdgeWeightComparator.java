package task_4.Service;

import java.util.Comparator;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import task_4.CustomVertex;

@SuppressWarnings("hiding")
public class EdgeWeightComparator<DefaultWeightedEdge> implements Comparator<DefaultWeightedEdge>
{
	private Graph<CustomVertex, DefaultWeightedEdge> graph = null;
	
	public EdgeWeightComparator(Graph<CustomVertex, DefaultWeightedEdge> _graph)
	{
		graph = _graph;
	}

	@Override
	public int compare(DefaultWeightedEdge o1, DefaultWeightedEdge o2)
	{
		return Double.compare(graph.getEdgeWeight(o1), graph.getEdgeWeight(o2));
	}
}
