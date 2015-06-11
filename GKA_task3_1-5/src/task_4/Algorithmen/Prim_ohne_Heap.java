package task_4.Algorithmen;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;

import task_4.CustomPriorityQueue;
import task_4.CustomVertex;
import task_4.VertexAttribute;

public class Prim_ohne_Heap
{
	private Graph<CustomVertex, DefaultWeightedEdge> spannbaum;
	private CustomPriorityQueue priorityQueue;
	private Set<DefaultWeightedEdge> edgeInT;
	private CustomVertex hinzugefuegterKnoten;
	private Set<CustomVertex> vertexInT = new HashSet<CustomVertex>();
	private double gesamtgewicht;
	private Long runTime;

	public Prim_ohne_Heap(WeightedPseudograph<CustomVertex, DefaultWeightedEdge> graph)
	{
		long start = System.currentTimeMillis();
		gesamtgewicht = 0;
		priorityQueue = new CustomPriorityQueue();
		edgeInT = new HashSet<DefaultWeightedEdge>();
		
		//------------------------------------------------------------------------------------------
		//alle Knoten in die Queue
		Set<CustomVertex> alleKnoten = graph.vertexSet();
		
		for(CustomVertex v : alleKnoten)
		{
			VertexAttribute node = new VertexAttribute(null, Integer.MAX_VALUE/2);
			priorityQueue.insert(v, node);
		}
		//-------------------------------------------------------------------------------------------
		
		
		
		//-------------------------------------------------------------------------------------------
		//StartKnoten auswählen
		hinzugefuegterKnoten = priorityQueue.min();
		vertexInT.add(priorityQueue.removeMin());
		//--------------------------------------------------------------------------------------------
		
		while(priorityQueue.size() > 0)
		{
			//----------------------------------------------------------------------------------------
			//für jeden hinzugefügten Knoten prüfen, ob es zu jedem Knoten in PriorityQueue nun eine kürzere Kante gibt
			Set<DefaultWeightedEdge> kantenVomHinzugefuegtenKnoten = graph.edgesOf(hinzugefuegterKnoten);
			
			for(DefaultWeightedEdge e : kantenVomHinzugefuegtenKnoten)
			{
				CustomVertex target = graph.getEdgeTarget(e);
				CustomVertex source = graph.getEdgeSource(e);
				CustomVertex nachbar = target.equals(hinzugefuegterKnoten) ? source : target; 
				
				
				if(priorityQueue.contains(nachbar) && priorityQueue.getAttribute(nachbar).getWeight() > graph.getEdgeWeight(e))
				{
					priorityQueue.aendereAttribut(nachbar, new VertexAttribute(e, graph.getEdgeWeight(e)));
				}	
			}
			//----------------------------------------------------------------------------------------

			
			//-----------------------------------------------------------------------------------------
			//Knoten mit kürzester Kante aus der Queue rausnehmen und in T hinzufügen
			hinzugefuegterKnoten = priorityQueue.min();
			edgeInT.add(priorityQueue.getAttribute(hinzugefuegterKnoten).getEdge());
			gesamtgewicht += priorityQueue.getAttribute(hinzugefuegterKnoten).getWeight();
			vertexInT.add(priorityQueue.removeMin());
			
			//-----------------------------------------------------------------------------------------
		}
		
		//Spannbaum erstellen
		spannbaum = new WeightedPseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		for(CustomVertex v : vertexInT)
		{
			spannbaum.addVertex(v);
		}
		
		for(DefaultWeightedEdge e : edgeInT)
		{
			graph.setEdgeWeight((DefaultWeightedEdge) spannbaum.addEdge(graph.getEdgeSource(e), graph.getEdgeTarget(e)), graph.getEdgeWeight(e));
		}
		
		runTime = System.currentTimeMillis() - start;
	}
	
	public Graph<CustomVertex, DefaultWeightedEdge> gibGraph()
	{
		return spannbaum;
	}
	
	public Long gibLaufzeit()
	{
		return runTime;
	}
	
	public Double gibGesamtgewicht()
	{
		return gesamtgewicht;
	}
}
