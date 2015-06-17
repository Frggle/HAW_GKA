package task_4.GraphGenerator;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Pseudograph;

import task_4.CustomVertex;

public class EulerGraphGenerator2
{
	private int knotenAnzahl;
	private Graph<CustomVertex, DefaultWeightedEdge> eulerGraph;
	
	public EulerGraphGenerator2()
	{
		eulerGraph = new Pseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	}
	
	public void baueGraph(int _knoten)
	{
		knotenAnzahl = _knoten;
		
		// Erstelle X Knoten
		for(int i = 1; i <= knotenAnzahl; i++)
		{
			eulerGraph.addVertex(new CustomVertex("" + i));
		}
	}
	
	/* Gib den random Eulergraph zurück */
	public Graph<CustomVertex, DefaultWeightedEdge> gibGraph()
	{
		return eulerGraph;
	}
}
