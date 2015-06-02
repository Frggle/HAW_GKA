package task_3.GraphGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.VertexFactory;
import org.jgrapht.generate.RandomGraphGenerator;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;

import task_3.CustomVertex;

public class GraphGen_ohne_Gewichtung_Heuristic
{
	private Graph<CustomVertex, DefaultWeightedEdge> graph = new WeightedPseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);

	public GraphGen_ohne_Gewichtung_Heuristic(int aNumOfVertexes, int aNumOfEdges)
	{
		final int knotenAnzahl = aNumOfVertexes;
		VertexFactory<CustomVertex> vertexFactory = new VertexFactory<CustomVertex>()
		{
			ArrayList<Integer> knotenSet = randomInt(knotenAnzahl);
			int i = 0;

			@Override
			public CustomVertex createVertex()
			{
				int n = knotenSet.get(i);
				CustomVertex v = new CustomVertex(String.valueOf(n));
				i++;
				return v;
			}
		};

		Map<String, CustomVertex> map = new HashMap<String, CustomVertex>();

		RandomGraphGenerator<CustomVertex, DefaultWeightedEdge> graphGenerator = new RandomGraphGenerator<CustomVertex, DefaultWeightedEdge>(
				aNumOfVertexes, aNumOfEdges);

		//targetgraph, vertexFactory, resultMap
		graphGenerator.generateGraph(graph, vertexFactory, map);
				
		//new GraphVizExporter().exportGraphToDotFile(graph);
	}
	
	private ArrayList<Integer> randomInt(int aNumOfVertex)
	{
		ArrayList<Integer> alleKnoten = new ArrayList<Integer>();
		Random random = new Random();
		int max = aNumOfVertex * 2;
		int min = 2;
		int i = 0;
		while(alleKnoten.size() < aNumOfVertex)
		{
			i = random.nextInt(max - min +1) + min;
			if(!alleKnoten.contains(i))
			{
				alleKnoten.add(i);				
			}
		}
		return alleKnoten;
	}

	public Graph<CustomVertex, DefaultWeightedEdge> gibGraph()
	{
		return graph;
	}

}
