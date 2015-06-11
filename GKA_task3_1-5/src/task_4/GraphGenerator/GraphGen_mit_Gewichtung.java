package task_4.GraphGenerator;

import java.util.Random;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;

import task_4.CustomVertex;

public class GraphGen_mit_Gewichtung
{
	private Graph<CustomVertex, DefaultWeightedEdge> graph = new WeightedPseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);

	public GraphGen_mit_Gewichtung(int aNumOfVertexes, int aNumOfEdges)
	{
	
		GraphGen_ohne_Gewichtung_Heuristic graphGenSimple = new GraphGen_ohne_Gewichtung_Heuristic(aNumOfVertexes, aNumOfEdges);
		graph = graphGenSimple.gibGraph();
					
		// zufällige, positive (1..X) Kantengewichtung
		WeightedPseudograph<CustomVertex, DefaultWeightedEdge> graphtemp = new WeightedPseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Set<CustomVertex> vertexSet = graph.vertexSet();
		for (CustomVertex v1 : vertexSet)
		{
			for (CustomVertex v2 : vertexSet)
			{
				if (graph.containsEdge(v1, v2))
				{
					Random random = new Random();
					int max = graph.vertexSet().size() * 3;
					int min = 1;
					graphtemp.addVertex(v1);
					graphtemp.addVertex(v2);
					// lässt nur eine Kante zwischen 2 Knoten zu
					if (!graphtemp.containsEdge(v1, v2))
					{
						DefaultWeightedEdge e = graphtemp.addEdge(v1, v2);
						graphtemp.setEdgeWeight(e, random.nextInt(max - min +1) + min);
					}
					// Löscht Schleifen auf sich selbst
					if (graphtemp.containsEdge(v1, v2) && v1.equals(v2))
					{
						graphtemp.removeEdge(v1, v2);
					}
				}
			}
		}
		
		graph = graphtemp;

		//new GraphVizExporter().exportGraphToDotFile(graph);
	}

	public Graph<CustomVertex, DefaultWeightedEdge> gibGraph()
	{
		return graph;
	}

}
