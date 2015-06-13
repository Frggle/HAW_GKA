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


public class HierholzerEulertour
{
	private Graph<CustomVertex, DefaultWeightedEdge> g;
	private Graph<CustomVertex, DefaultWeightedEdge> clonedGraph;
	private List<DefaultWeightedEdge> kantenfolge;
	private CustomVertex startKnoten;
	private CustomVertex endKnoten;
	private List<List<DefaultWeightedEdge>> unterkreise;
	private List<DefaultWeightedEdge> edgeList;
	
	/* Konstruktor */
	@SuppressWarnings("unchecked")
	public HierholzerEulertour(Graph<CustomVertex, DefaultWeightedEdge> _g) throws IllegalArgumentException
	{
		g = _g;
		clonedGraph = (Graph<CustomVertex, DefaultWeightedEdge>) ((AbstractBaseGraph<CustomVertex, DefaultWeightedEdge>) g).clone();
		unterkreise = new ArrayList<List<DefaultWeightedEdge>>();
		edgeList = new ArrayList<DefaultWeightedEdge>();

		Set<CustomVertex> vertexMenge = g.vertexSet();

		Iterator<CustomVertex> iterV = vertexMenge.iterator();
		CustomVertex aktKnoten = iterV.next();
		
		for(DefaultWeightedEdge e : g.edgeSet())
		{
			edgeList.add(e);
		}
		
		if(preconditions())
		{
			while(!edgeList.isEmpty())
			{
				konstruiereUnterkreise(aktKnoten);
				System.err.println("--------------------------");
				Iterator<CustomVertex> iterVtemp = g.vertexSet().iterator();
				
				Boolean notFound = true;
				while(iterVtemp.hasNext() && notFound)
				{
					CustomVertex v = iterVtemp.next();
					if(clonedGraph.edgesOf(v).size() > 0)
					{
						aktKnoten = v;
						notFound = false;
					}
				}
			}			
		} else
		{
			throw new IllegalArgumentException("Vorbedingung verletzt!!! -> keine Eulertour möglich");
		}	
	}
	
	/* erstellt zufällige Unterkreise und löscht die benutzten Kanten aus dem clonedGraph */
	private void konstruiereUnterkreise(CustomVertex v)
	{
		// TODO
		CustomVertex aktKnoten = v;
		CustomVertex endKnoten = null;
		List<DefaultWeightedEdge> subListEdges = new ArrayList<DefaultWeightedEdge>();
				
		// Wiederhole solange bis zufällig der neue Knoten der Startknoten v ist
		while(!v.equals(endKnoten))
		{
			Set<DefaultWeightedEdge> nachbarKanten = clonedGraph.edgesOf(aktKnoten);
			Iterator<DefaultWeightedEdge> iterE = nachbarKanten.iterator();
			DefaultWeightedEdge e = iterE.next();
			CustomVertex source = clonedGraph.getEdgeSource(e);
			CustomVertex target = clonedGraph.getEdgeTarget(e);
			aktKnoten = aktKnoten.equals(source) ? target : source;
			endKnoten = aktKnoten;
			subListEdges.add(e);
			edgeList.remove(e);
			System.err.println("subListEdges " + subListEdges);
		}
		
		unterkreise.add(subListEdges);
	}
	
	/* Testet ob der Graph zusammenhängend ist und ob alle Knotengrade gerade sind */
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