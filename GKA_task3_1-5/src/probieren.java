
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import task_4.CustomVertex;
import task_4.Algorithmen.FleuryEulertour;
import task_4.GraphGenerator.EulerGraphGenerator;
import task_4.Service.GraphVizExporter;

public class probieren
{
		
	private static int i = 13; 
	
	public static void main(String[] args)
	{
		EulerGraphGenerator gen = new EulerGraphGenerator();
		gen.baueEulerGraph(i);
		Graph<CustomVertex, DefaultWeightedEdge> g = gen.gibEulerGraph();
		
		FleuryEulertour fleury = new FleuryEulertour(g);
		System.err.println(fleury.gibKantenfolge());
		
		new GraphVizExporter().exportGraphToDotFile(g, "EulerGraphGeneratorV" +i);
	}
}
