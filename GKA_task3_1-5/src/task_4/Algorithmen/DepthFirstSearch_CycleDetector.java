package task_4.Algorithmen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.NeighborIndex;
import org.jgrapht.graph.DefaultWeightedEdge;

import task_4.CustomVertex;

public class DepthFirstSearch_CycleDetector
{
	private Set<CustomVertex> weisseKnoten = new HashSet<CustomVertex>();
	private Set<CustomVertex> graueKnoten = new HashSet<CustomVertex>();
	private Set<CustomVertex> schwarzeKnoten = new HashSet<CustomVertex>();
	private ArrayList<CustomVertex> weisseKnotenArray = null;// new ArrayList<CustomVertex>();
	
	private CustomVertex startV = null;
	
	private Graph<CustomVertex, DefaultWeightedEdge> graph;
	
	public DepthFirstSearch_CycleDetector()//Graph<CustomVertex, DefaultWeightedEdge> _graph, CustomVertex _startV)
	{
	}
	
	public boolean detectCycle(Graph<CustomVertex, DefaultWeightedEdge> _graph, CustomVertex _startV)
	{
		// init.
		graph = _graph;
		startV = _startV;
		weisseKnoten = graph.vertexSet();
		weisseKnotenArray = new ArrayList<CustomVertex>();
		graueKnoten = new HashSet<CustomVertex>();
		schwarzeKnoten = new HashSet<CustomVertex>();

		weisseKnotenArray.add(startV);
		for(CustomVertex v : weisseKnoten)
		{
			if(!v.equals(startV))
			{
				weisseKnotenArray.add(v);	
			}
		}
				
		boolean cycledetected = false;
		for(int i = 0; i < weisseKnotenArray.size(); i++)
//		for(CustomVertex v : weisseKnotenArray)
		{
//			if(weisseKnotenArray.contains(v) && !cycledetected)
			if(weisseKnotenArray.contains(weisseKnotenArray.get(i)) && !cycledetected)
			{
//				cycledetected = dfsSearchVisit(v, graph);
				cycledetected = dfsSearchVisit(weisseKnotenArray.get(i), graph);
			}
		}
		return cycledetected;
	}
	
	private boolean dfsSearchVisit(CustomVertex vertex, Graph<CustomVertex, DefaultWeightedEdge> graph)
	{
		boolean result = false;
		NeighborIndex<CustomVertex, DefaultWeightedEdge> neighborIndex = new NeighborIndex<CustomVertex, DefaultWeightedEdge>(graph);
		graueKnoten.add(vertex);
		int index = weisseKnotenArray.indexOf(vertex);
				
		weisseKnotenArray.remove(index);
		Set<CustomVertex> nachbarKnoten = neighborIndex.neighborsOf(vertex);
		for(CustomVertex v : nachbarKnoten)
		{
			// Cycle detected
			if(schwarzeKnoten.contains(v))
			{
				result = true;
			} else if(weisseKnotenArray.contains(v))
			{
				dfsSearchVisit(v, graph);
			}
		}
		schwarzeKnoten.add(vertex);
		return result;
	}
}
