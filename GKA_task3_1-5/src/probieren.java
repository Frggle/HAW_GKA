
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

import task_3.CustomVertex;
import task_3.Algorithmen.Kruskal;
import task_3.Service.GraphVizExporter;

public class probieren
{
		
	public static void main(String[] args)
	{
		Graph<CustomVertex, DefaultWeightedEdge> graph = new WeightedPseudograph<CustomVertex, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);

		graph.addVertex(new CustomVertex("eins", 0));
		graph.addVertex(new CustomVertex("zwei", 0));
		graph.addVertex(new CustomVertex("drei", 0));
		graph.addVertex(new CustomVertex("vier", 0));
		graph.addVertex(new CustomVertex("fünf", 0));

		DefaultWeightedEdge e1 = graph.addEdge(new CustomVertex("eins", 0), new CustomVertex("zwei", 0));
		DefaultWeightedEdge e2 = graph.addEdge(new CustomVertex("drei", 0), new CustomVertex("vier", 0));
		DefaultWeightedEdge e3 = graph.addEdge(new CustomVertex("eins", 0), new CustomVertex("fünf", 0));
		DefaultWeightedEdge e4 = graph.addEdge(new CustomVertex("zwei", 0), new CustomVertex("drei", 0));
		DefaultWeightedEdge e5 = graph.addEdge(new CustomVertex("eins", 0), new CustomVertex("vier", 0));
		
		((Pseudograph<CustomVertex, DefaultWeightedEdge>) graph).setEdgeWeight(e1, 1.0);
		((Pseudograph<CustomVertex, DefaultWeightedEdge>) graph).setEdgeWeight(e2, 2.0);
		((Pseudograph<CustomVertex, DefaultWeightedEdge>) graph).setEdgeWeight(e3, 4.0);
		((Pseudograph<CustomVertex, DefaultWeightedEdge>) graph).setEdgeWeight(e4, 3.0);
		((Pseudograph<CustomVertex, DefaultWeightedEdge>) graph).setEdgeWeight(e5, 2.0);
					
		System.err.println("ohne Kruskal:       " + graph);
		Kruskal kruskal = new Kruskal();
		kruskal.builtTree(graph);
		Graph<CustomVertex, DefaultWeightedEdge> g = kruskal.getGraph();
		System.err.println("Kruskal Spanningtree " + g);
		
		System.err.println("Gesamtkantengewichtung " + kruskal.gibGesamtgewichtung());
		System.err.println("Laufzeit: " + kruskal.getRunTimeInMiliSec());		
		
		new GraphVizExporter().exportGraphToDotFile(g, "miniTest");
	}
}
