package task_4.Algorithmen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import task_4.CustomVertex;

public class FleuryEulertour
{
	private List<DefaultWeightedEdge> kantenfolge;
	private Graph<CustomVertex, DefaultWeightedEdge> g;
	private List<DefaultWeightedEdge> unusedEdges; 
	private CustomVertex startKnoten;
	private CustomVertex endKnoten;

	public FleuryEulertour(Graph<CustomVertex, DefaultWeightedEdge> _g) throws IllegalArgumentException
	{
		g = _g;
		kantenfolge = new ArrayList<DefaultWeightedEdge>();

		if(preconditions())
		{
			Set<CustomVertex> vertexMenge = g.vertexSet();
			Set<DefaultWeightedEdge> edgeMenge = g.edgeSet();

			Iterator<CustomVertex> iterV = vertexMenge.iterator();
			CustomVertex aktKnoten = iterV.next();

			unusedEdges = new ArrayList<DefaultWeightedEdge>();
			for (DefaultWeightedEdge e : edgeMenge)
			{
				unusedEdges.add(e);
			}
		
			startKnoten = aktKnoten;
			
			while (!unusedEdges.isEmpty())
			{
				System.err.println("aktKnoten " + aktKnoten.getVertexName());
				CustomVertex nextV = nextVertex(unusedEdges, aktKnoten);
				aktKnoten = nextV;
				endKnoten = nextV;
				System.err.println();
			}	
		} else
		{
			throw new IllegalArgumentException("Vorbedingung verletzt!!! -> keine Eulertour möglich");
		}	
	}

	@SuppressWarnings("unchecked")
	private CustomVertex nextVertex(List<DefaultWeightedEdge> unusedE, CustomVertex vertex)
	{
		CustomVertex nextVertex = null;

		Set<DefaultWeightedEdge> nachbarKanten = g.edgesOf(vertex);
		List<DefaultWeightedEdge> inzidenteUnbenutzteKanten = new ArrayList<DefaultWeightedEdge>();

		// Zwischenschritt da edgesOf(vertex) eine ConcurrentModificationException wirft
		for (DefaultWeightedEdge e : nachbarKanten)
		{
			inzidenteUnbenutzteKanten.add(e);
		}
		inzidenteUnbenutzteKanten.retainAll(unusedE);

		List<DefaultWeightedEdge> bridgeEdges = new ArrayList<DefaultWeightedEdge>();
		List<DefaultWeightedEdge> nonBridges = new ArrayList<DefaultWeightedEdge>();

		ConnectivityInspector<CustomVertex, DefaultWeightedEdge> connectInspector;
		Graph<CustomVertex, DefaultWeightedEdge> clonedGraph;
				
		// Suche zuerst Kanten die keine Brücke sind (=> 2 Komponenten im Graph verursachen)
		System.err.println("Knoten " + vertex.getVertexName() + ": " + inzidenteUnbenutzteKanten);
		for (DefaultWeightedEdge e : inzidenteUnbenutzteKanten)
		{			
			clonedGraph = (Graph<CustomVertex, DefaultWeightedEdge>) ((AbstractBaseGraph<CustomVertex, DefaultWeightedEdge>) g).clone();
					
//			clonedGraph.removeEdge(e);
											
			CustomVertex source = clonedGraph.getEdgeSource(e);
			CustomVertex target = clonedGraph.getEdgeTarget(e);
			CustomVertex other = vertex.equals(source) ? target : source;
			
			Boolean isBridge = clonedGraph.edgesOf(other).size() > 1 ? false : true;
			
			System.err.println("Kante " + e + " ist Bridge: " + isBridge);
			
			if(isBridge)
			{
				bridgeEdges.add(e);
			} else
			{
				nonBridges.add(e);
			}
			
			connectInspector = new ConnectivityInspector<CustomVertex, DefaultWeightedEdge>((UndirectedGraph<CustomVertex, DefaultWeightedEdge>) clonedGraph);
						
			// Verursacht ein Error, ConnectivityInspector wird nicht bei jedem Durchlauf neu erzeugt, sondern übernimmt die Instanz von vorherigen
//			if (!connectInspector.isGraphConnected())
//			{
//				bridgeEdges.add(e);
//			} else
//			{
//				nonBridges.add(e);
//			}
		}
		System.err.println("NonBridge " + nonBridges);
		System.err.println("Bridge " + bridgeEdges);
						
		DefaultWeightedEdge edge = null;
		if(nonBridges.isEmpty())
		{
			edge = bridgeEdges.get(0);
		} else if(bridgeEdges.isEmpty())
		{
			edge = nonBridges.get(0);
		}
		
		unusedEdges.remove(edge);
		kantenfolge.add(edge);
		g.removeEdge(edge);
		
		System.err.println("entferne Kante " + edge);

		CustomVertex source = g.getEdgeSource(edge);
		CustomVertex target = g.getEdgeTarget(edge);
				
		nextVertex = vertex.equals(source) ? target : source;
		
		return nextVertex;
	}
	
	private Boolean preconditions()
	{
		Boolean erfuellt = true;
		
		// Ist der Graph zusammenhängend?
		ConnectivityInspector<CustomVertex, DefaultWeightedEdge> connect = 
				new ConnectivityInspector<CustomVertex, DefaultWeightedEdge>((UndirectedGraph<CustomVertex, DefaultWeightedEdge>) g);
		
		for(CustomVertex v : g.vertexSet())
		{
			if(g.edgesOf(v).size() % 2 == 0)
			{
				erfuellt = erfuellt && true;
			} else
			{
				erfuellt = erfuellt && false;
			}
		}
		
		return erfuellt && connect.isGraphConnected();
	}
	
	/* Gibt Kantenfolge für Eulertour zurück */
	public List<DefaultWeightedEdge> gibKantenfolge()
	{
		return kantenfolge;
	}
	
	/* Gibt den Startknoten von EulerTour */
	public CustomVertex gibStartknoten()
	{
		return startKnoten;
	}
	
	/* Gibt den Endknoten von EulerTour */
	public CustomVertex gibEndknoten()
	{
		return endKnoten;
	}
}