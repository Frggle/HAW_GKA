package task_3.GraphGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;

import task_3.CustomVertex;
import task_3.MyCustomGraphs.MyUndirectedWeightedGraph;

public class GraphGen_mit_Gewichtung_Heuristic
{
	@SuppressWarnings("rawtypes")
	private Graph graph = new MyUndirectedWeightedGraph();//<CustomVertex, DefaultWeightedEdge>(
			//DefaultWeightedEdge.class);

	@SuppressWarnings("unchecked")
	public GraphGen_mit_Gewichtung_Heuristic(int aNumOfVertexes, int aNumOfEdges)
	{
		GraphGen_ohne_Gewichtung_Heuristic graphGenSimple = new GraphGen_ohne_Gewichtung_Heuristic(aNumOfVertexes,
				aNumOfEdges);
		graph = graphGenSimple.gibGraph();

		WeightedPseudograph<CustomVertex, DefaultWeightedEdge> graphtemp = new WeightedPseudograph<CustomVertex, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);
	
		Set<CustomVertex> vertexSet = graph.vertexSet();
		
		//holt sich den höchten Knoten, damit dieser als Endknoten markiert werden kann
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(CustomVertex v : vertexSet)
		{
			array.add(Integer.parseInt(v.getVertexName()));
		}
		Collections.sort(array);
				
		
		for (CustomVertex v1 : vertexSet)
		{
			CustomVertex vtemp = null;
			//Random random = new Random();
			//int maxHeuristic = vertexSet.size() * 3;
			//int minHeuristic = 1;
			//int heuristic = random.nextInt(maxHeuristic - minHeuristic + 1) + minHeuristic;
			if (!v1.getVertexName().equals(""+array.get(array.size()-1)))
			{
				int heuristic = Integer.parseInt(v1.getVertexName());
				vtemp = new CustomVertex(v1.getVertexName(), heuristic);
				graphtemp.addVertex(vtemp);
			} else
			{
//				System.out.println(array.get(array.size()-1));
				vtemp = new CustomVertex("Endknoten", 0);
				graphtemp.addVertex(vtemp);
			}
		}
		// Der alte Graph mit Gewichtung OHNE Heuristic als Array
		ArrayList<CustomVertex> arrayOriginal = new ArrayList<CustomVertex>();
		// Der neue Graph mit Gewichtung und Heuristic als Array
		ArrayList<CustomVertex> arrayTemp = new ArrayList<CustomVertex>();

		// indizierter Zugriff auf die CustomVertex beider Graphen
		for (CustomVertex v : vertexSet)
		{
			arrayOriginal.add(v);
		}
		Set<CustomVertex> vertexSetTemp = graphtemp.vertexSet();
		for (CustomVertex v : vertexSetTemp)
		{
			arrayTemp.add(v);
		}

		// setze Gewichtung
		for (CustomVertex v1 : vertexSet)
		{
			for (CustomVertex v2 : vertexSet)
			{
				if (graph.containsEdge(v1, v2))
				{
					int posv1 = arrayOriginal.indexOf(v1);
					int posv2 = arrayOriginal.indexOf(v2);
															
					Random random = new Random();
					// maximal doppelt so lang wie die Heuristic
					int v1Weight = arrayTemp.get(posv1).getVertexAttribute();
					// mindestens größer als die Heuristic
					int v2Weight = arrayTemp.get(posv2).getVertexAttribute();
					
					int minWeight = (v1Weight > v2Weight) ? v1Weight : v2Weight;
					int maxWeight = 2*(minWeight+1);
					
					
					double weight = (double) random.nextInt(maxWeight - minWeight + 1) + minWeight;
					// sorgt dafür das es keine 2 Kanten zwischen 2 Knoten gibt
					if (!graphtemp.containsEdge(arrayTemp.get(posv1), arrayTemp.get(posv2)))
					{
						DefaultWeightedEdge e = graphtemp.addEdge(arrayTemp.get(posv1), arrayTemp.get(posv2));
						graphtemp.setEdgeWeight(e, weight);
					}
					// löscht Schlingen
					if (graphtemp.containsEdge(arrayTemp.get(posv1), arrayTemp.get(posv2)) && arrayTemp.get(posv1).equals(arrayTemp.get(posv2)))
					{
						graphtemp.removeEdge(arrayTemp.get(posv1), arrayTemp.get(posv2));
					}
				}
			}
		}
		graph = graphtemp;

		//new GraphVizExporter().exportGraphToDotFile(graph);
	}

	@SuppressWarnings("unchecked")
	public Graph<CustomVertex, DefaultWeightedEdge> gibGraph()
	{
		return graph;
	}

}
