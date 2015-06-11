package task_4.Algorithmen;

import java.util.ArrayList;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;

import task_4.CustomVertex;

public class Kruskal
{
	/**
	 * Instanzvariablen
	 */
	private Graph<CustomVertex, DefaultWeightedEdge> graphOriginal = null;
	private Graph<CustomVertex, DefaultWeightedEdge> graphKruskal = null;
	private Double treeWeight = 0.0;
	private Long runningTime = null;

	
	/**
	 * Konstruktor
	 */
	public Kruskal()
	{
	}
	
	/**
	 * Konstruiert den minimalen Spannbaum
	 * Laufzeit: O(n^2) - zwei ineinander verschachtelte Iterationen
	 * @param _graph, weighted + undirected Graph
	 */
	public void builtTree(Graph<CustomVertex, DefaultWeightedEdge> _graph)
	{
		long startTime = System.nanoTime();
		graphOriginal = _graph;
		graphKruskal = new WeightedPseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Set<DefaultWeightedEdge> edgeSet = graphOriginal.edgeSet();
		DepthFirstSearch_CycleDetector CycleDetector = new DepthFirstSearch_CycleDetector();
//		DepthFirstIterator<CustomVertex, DefaultWeightedEdge> dfsIter = null;
//		ArrayList<CustomVertex> nachbarKnoten = null;
		treeWeight = 0.0;
		
		// für indizierten Zugriff schreiben wir den Set in ein ArrayList
		ArrayList<DefaultWeightedEdge> edgeSetAsArray = new ArrayList<DefaultWeightedEdge>();
		for(DefaultWeightedEdge e : edgeSet)
		{
			edgeSetAsArray.add(e);
		}
	
		// wiederhole bis keine Kante mehr gelegt werden muss
		while(!edgeSetAsArray.isEmpty())
		{
			Double minEdgeWeight = Double.MAX_VALUE;
			DefaultWeightedEdge aktuelleKante = null;
			
			// Iteriere über alle Kantengewichtungen und nehme das Minimum
			for(DefaultWeightedEdge e : edgeSetAsArray)
			{
				if(graphOriginal.getEdgeWeight(e) <= minEdgeWeight)
				{
					minEdgeWeight = graphOriginal.getEdgeWeight(e);
					aktuelleKante = e;
				}
			}		
						
			// extrahiere aus der Kante die Knoten-Informationen
			CustomVertex v1 = graphOriginal.getEdgeSource(aktuelleKante);
			CustomVertex v2 = graphOriginal.getEdgeTarget(aktuelleKante);
			// Kontrolle ob extrahiere Knoten im Originalgraph vorhanden sind, wenn nicht werfe Exception
			if(!(graphOriginal.containsVertex(v1) && graphOriginal.containsVertex(v2)))
			{
				try
				{
					throw new Exception("Knoten " + v1.getVertexName() + " oder " + v2.getVertexName());
				} catch (Exception e1)
				{
					System.err.println("Error beim Exception werfen");
				}
			} 		

//			nachbarKnoten = new ArrayList<CustomVertex>();
//			dfsIter = new DepthFirstIterator<CustomVertex, DefaultWeightedEdge>(graphKruskal, v1);
//			Boolean cycleDetect = false;
//			while(dfsIter.hasNext() && !cycleDetect)
//			{
//				nachbarKnoten.add(dfsIter.next());
//				if(nachbarKnoten.contains(v2))
//				{
//					cycleDetect = true;
//				}
//			}
//			if(!cycleDetect)
//			{
//				addVandE(v1, v2, aktuelleKante);
//			}
			// hinzufügen der Knoten, Kante inkl. Kantengewichtung in den Kruskalbaum
			if(addVandE(v1, v2, aktuelleKante))
			{
				// entfernt die Kante wieder, wenn diese ein Zyklus verursacht
				// da der Kruskalbaum aus mehreren Komponenten bestehen kann, gucke nur in der Komponente wo die neue Kante hinzugefügt wurde
				if(CycleDetector.detectCycle(graphKruskal,v1))
				{
					graphKruskal.removeEdge(v1,v2);
//					System.err.println("Kante gelöscht " + aktuelleKante.toString() + " --> " + !graphKruskal.containsEdge(v1,v2));
				} else
				{
					// wenn Kante kein Zyklus verursacht, addiere die Gewichtung auf
					treeWeight+= graphOriginal.getEdgeWeight(aktuelleKante);
				}
			}
//			System.err.println("aktuelle Runtime: " + (System.nanoTime() - startTime) / 1000000 + "ms");
			edgeSetAsArray.remove(aktuelleKante);
		}
		runningTime = System.nanoTime() - startTime;
	}
	
	/**
	 * Hilfsfunktion - Bekommt 2 Knoten und eine Kante und fügt es dem Kruskalbaum hinzu
	 * @param v1, erster Knoten
	 * @param v2, zweiter Knoten
	 * @param e, Kante zwischen v1-v2
	 * @return boolean, ob die Kante/Knoten erfolgreich dem Kruskalbaum hinzugefügt wurden
	 */
	private boolean addVandE(CustomVertex v1, CustomVertex v2, DefaultWeightedEdge e)
	{
		Double weight = graphOriginal.getEdgeWeight(e);
		graphKruskal.addVertex(v1);
		graphKruskal.addVertex(v2);
		DefaultWeightedEdge edge = graphKruskal.addEdge(v1, v2);
		((AbstractBaseGraph<CustomVertex, DefaultWeightedEdge>) graphKruskal).setEdgeWeight(edge, weight);
		return graphKruskal.containsEdge(v1, v2) && graphKruskal.getEdgeWeight(e) == weight;
	}
	
	/**
	 * Getter für den Graph
	 * @return Spannbaum
	 */
	public Graph<CustomVertex, DefaultWeightedEdge> getGraph()
	{
		return graphKruskal;
	}
	
	/**
	 * Getter für die insgesamte Kantengewichtung im Kruskalbaum
	 * @return Double, Kantengewichtungssumme
	 */
	public Double gibGesamtgewichtung()
	{
		return treeWeight;
	}
	
	/**
	 * Gibt die tatsächliche Laufzeit in Milisec. zurück
	 * @return Long, Laufzeit
	 */
	public Long getRunTimeInMiliSec()
	{
		return runningTime / 1000000;
	}
}
