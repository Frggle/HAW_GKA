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
import org.jgrapht.graph.Pseudograph;

import task_4.CustomVertex;
import task_4.Service.GraphVizExporter;

public class FleuryEulertour
{
	private List<DefaultWeightedEdge> kantenfolge;
	private Graph<CustomVertex, DefaultWeightedEdge> orgGraph;
	private Graph<CustomVertex, DefaultWeightedEdge> clonedGraph;
	private List<DefaultWeightedEdge> unusedEdges; 
	private CustomVertex startKnoten;
	private CustomVertex endKnoten;

	@SuppressWarnings("unchecked")
	public FleuryEulertour(Graph<CustomVertex, DefaultWeightedEdge> _g) throws IllegalArgumentException
	{
		orgGraph = _g;

		clonedGraph = (Graph<CustomVertex, DefaultWeightedEdge>) ((AbstractBaseGraph<CustomVertex, DefaultWeightedEdge>) orgGraph).clone();

		kantenfolge = new ArrayList<DefaultWeightedEdge>();

		if(preconditions())
		{
			Set<CustomVertex> vertexMenge = orgGraph.vertexSet();
			Set<DefaultWeightedEdge> edgeMenge = orgGraph.edgeSet();

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
				CustomVertex nextV = nextVertex(aktKnoten);
				aktKnoten = nextV;
				endKnoten = nextV;
			}	
		} else
		{
			throw new IllegalArgumentException("Vorbedingung verletzt!!! -> keine Eulertour möglich");
		}	
	}

	@SuppressWarnings("unchecked")
	private CustomVertex nextVertex(CustomVertex vertex)
	{
		CustomVertex nextVertex = null;

//		Set<DefaultWeightedEdge> nachbarKanten = orgGraph.edgesOf(vertex);
//		List<DefaultWeightedEdge> inzidenteUnbenutzteKanten = new ArrayList<DefaultWeightedEdge>();
		// Zwischenschritt da edgesOf(vertex) eine ConcurrentModificationException wirft
//		for (DefaultWeightedEdge e : nachbarKanten)
//		{
//			inzidenteUnbenutzteKanten.add(e);
//		}
		// Schnittmenge -> alle unbenutzten Kanten geschnitten mit den inzidenten Kanten 
//		inzidenteUnbenutzteKanten.retainAll(unusedEdges);

		Set<DefaultWeightedEdge> inzidenteUnbenutzteKanten = clonedGraph.edgesOf(vertex);
		
		List<DefaultWeightedEdge> bridgeEdges = new ArrayList<DefaultWeightedEdge>();
		List<DefaultWeightedEdge> nonBridges = new ArrayList<DefaultWeightedEdge>();
				
		// Suche zuerst Kanten die keine Brücke sind (=> 2 Komponenten im Graph verursachen)
		
//		System.err.println("Knoten " + vertex.getVertexName() + ": " + inzidenteUnbenutzteKanten);
//		System.err.println("       " + kantenfolge);
		
		for (DefaultWeightedEdge e : inzidenteUnbenutzteKanten)
		{																			
//			CustomVertex source = clonedGraph.getEdgeSource(e);
//			CustomVertex target = clonedGraph.getEdgeTarget(e);
//			CustomVertex other = vertex.equals(source) ? target : source;
			
//			Boolean isBridge = clonedGraph.edgesOf(other).size() > 1 ? false : true;
			
			Graph<CustomVertex, DefaultWeightedEdge> tmp = (Graph<CustomVertex, DefaultWeightedEdge>) ((AbstractBaseGraph<CustomVertex, DefaultWeightedEdge>) clonedGraph).clone();
			tmp.removeEdge(e);
			
			ConnectivityInspector<CustomVertex, DefaultWeightedEdge> connect = 
					new ConnectivityInspector<CustomVertex, DefaultWeightedEdge>((UndirectedGraph<CustomVertex, DefaultWeightedEdge>) tmp);
			
//			if(isBridge)
			if(!connect.isGraphConnected())
			{
				bridgeEdges.add(e);
//				System.err.println("	Bridge " + e);
			} else
			{
				nonBridges.add(e);
//				System.err.println("	Keine Bridge " + e);
			}
		}
						
		DefaultWeightedEdge edge = null;
		if(nonBridges.isEmpty())
		{
			edge = bridgeEdges.get(0);
		} else
		{
			edge = nonBridges.get(0);
		}
		
//		System.err.println("       " + edge);
		
		unusedEdges.remove(edge);
		kantenfolge.add(edge);
		clonedGraph.removeEdge(edge);
		
		CustomVertex source = orgGraph.getEdgeSource(edge);
		CustomVertex target = orgGraph.getEdgeTarget(edge);
				
		nextVertex = vertex.equals(source) ? target : source;
		
		return nextVertex;
	}
	
	private Boolean preconditions()
	{
		Boolean erfuellt = true;
		
		// Ist der Graph zusammenhängend?
		ConnectivityInspector<CustomVertex, DefaultWeightedEdge> connect = 
				new ConnectivityInspector<CustomVertex, DefaultWeightedEdge>((UndirectedGraph<CustomVertex, DefaultWeightedEdge>) orgGraph);
		
		for(CustomVertex v : orgGraph.vertexSet())
		{
			if(orgGraph.edgesOf(v).size() % 2 == 0)
			{
				erfuellt = erfuellt && true;
			} else
			{
				erfuellt = erfuellt && false;
			}
		}
		return erfuellt && connect.isGraphConnected();
	}
	
	/* Gib die Eulertour als Graph zurück */
	public Graph<CustomVertex, DefaultWeightedEdge> gibEulerGraph() throws Exception
	{
		Graph<CustomVertex, DefaultWeightedEdge> eulerGraph = new Pseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		for(CustomVertex v : orgGraph.vertexSet())
		{
			eulerGraph.addVertex(v);
		}
		
		for(DefaultWeightedEdge e : kantenfolge)
		{
			CustomVertex source = orgGraph.getEdgeSource(e);
			CustomVertex target = orgGraph.getEdgeTarget(e);
			
			eulerGraph.addEdge(source, target);
		}
		
		if(eulerGraph.edgeSet().size() != orgGraph.edgeSet().size())
		{
			throw new Exception("Fehler beim Erstellen des Eulergraphs - Fleury");
		}
		new GraphVizExporter().exportGraphToDotFile(eulerGraph, "gespeicherterEulerGraphFleury");
		return eulerGraph;
	}
	
	/* Gibt Kantenfolge für Eulertour zurück */
	public List<DefaultWeightedEdge> gibKantenfolge()
	{
		return kantenfolge;
	}
	
	/* Gib original Graph */
	public Graph<CustomVertex, DefaultWeightedEdge> gibOrgGraph()
	{
		return orgGraph;
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