package task_3.Algorithmen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;
import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

import task_3.CustomVertex;

public class Prim_Heap
{
	private Graph<CustomVertex, DefaultWeightedEdge> spannbaum;
	private FibonacciHeap<CustomVertex> heap;
	private Set<DefaultWeightedEdge> edgeInT;
	private CustomVertex hinzugefuegterKnoten;
	private Set<CustomVertex> vertexInT = new HashSet<CustomVertex>();
	private double gesamtgewicht;
	private Long runTime;
	private Map<CustomVertex, FibonacciHeapNode<CustomVertex>> nodeMap;  

	public Prim_Heap(WeightedPseudograph<CustomVertex, DefaultWeightedEdge> graph)
	{	
		long start = System.currentTimeMillis();
		gesamtgewicht = 0;
		heap = new FibonacciHeap<CustomVertex>();
		edgeInT = new HashSet<DefaultWeightedEdge>();
		nodeMap = new HashMap<>();
		
		//------------------------------------------------------------------------------------------
		//alle Knoten in die Queue
		Set<CustomVertex> alleKnoten = graph.vertexSet();
		
		for(CustomVertex v : alleKnoten)
		{
			FibonacciHeapNode<CustomVertex> node = new FibonacciHeapNode<CustomVertex>(v);
			nodeMap.put(v, node);
			heap.insert(node, Integer.MAX_VALUE/2);
		}
		//-------------------------------------------------------------------------------------------
		
		
		
		//-------------------------------------------------------------------------------------------
		//StartKnoten auswählen
		hinzugefuegterKnoten = heap.min().getData();
		vertexInT.add(heap.removeMin().getData());
		//--------------------------------------------------------------------------------------------
		
		while(heap.size() > 0)
		{
			//----------------------------------------------------------------------------------------
			//für jeden hinzugefügten Knoten prüfen, ob es zu jedem Knoten in PriorityQueue nun eine kürzere Kante gibt
			Set<DefaultWeightedEdge> kantenVomHinzugefuegtenKnoten = graph.edgesOf(hinzugefuegterKnoten);
			
			for(DefaultWeightedEdge e : kantenVomHinzugefuegtenKnoten)
			{
				CustomVertex target = graph.getEdgeTarget(e);
				CustomVertex source = graph.getEdgeSource(e);
				CustomVertex nachbar = target.equals(hinzugefuegterKnoten) ? source : target; 
				if(!vertexInT.contains(nachbar) && nodeMap.get(nachbar).getKey() > graph.getEdgeWeight(e))
				{
					heap.delete(nodeMap.get(nachbar));
					nodeMap.remove(nachbar);
					FibonacciHeapNode<CustomVertex> node = new FibonacciHeapNode<CustomVertex>(nachbar);
					nodeMap.put(nachbar, node);
					nachbar.setKuerzesteKantezuT(e);
					heap.insert(node, graph.getEdgeWeight(e));
				}
			}
			//----------------------------------------------------------------------------------------

			
			
			//-----------------------------------------------------------------------------------------
			//Knoten mit kürzester Kante aus der Queue rausnehmen und in T hinzufügen
			hinzugefuegterKnoten = heap.min().getData();
			vertexInT.add(heap.removeMin().getData());
			edgeInT.add(hinzugefuegterKnoten.getKuerzesteKantezuT());
			gesamtgewicht += graph.getEdgeWeight(hinzugefuegterKnoten.getKuerzesteKantezuT());
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
