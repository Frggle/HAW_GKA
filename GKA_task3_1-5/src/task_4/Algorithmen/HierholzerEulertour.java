package task_4.Algorithmen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Pseudograph;

import task_4.CustomVertex;
import task_4.Service.GraphVizExporter;


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
			throw new IllegalArgumentException("Vorbedingung verletzt!!! -> keine Eulertour mÃ¶glich");
		}
		
		kantenfolge = new ArrayList<DefaultWeightedEdge>();
		Stack<List<DefaultWeightedEdge>> circleStack = new Stack<List<DefaultWeightedEdge>>();
		circleStack.push(unterkreise.get(0));
		
		while(!circleStack.isEmpty())
		{
			List<DefaultWeightedEdge>currentCircle = circleStack.peek();
			if(unterkreise.contains(currentCircle))
			{
				unterkreise.remove(currentCircle);
			}
				unvisitedLoop:
				for(List<DefaultWeightedEdge> l : unterkreise)
				{
					for(DefaultWeightedEdge e : l)
					{						
						boolean sourceTarget = g.getEdgeSource(e).equals(g.getEdgeTarget(currentCircle.get(0)));
						boolean targetSource = g.getEdgeTarget(e).equals(g.getEdgeSource(currentCircle.get(0)));
						if (sourceTarget || targetSource)
						{
							KreisSortieren(l, sourceTarget ? g.getEdgeSource(e) : g.getEdgeTarget(e));
							circleStack.push(l);
							break unvisitedLoop;
						}
					}
				}
				kantenfolge.add(currentCircle.get(0));
				currentCircle.remove(0);
			
			if(currentCircle.isEmpty())
			{
				circleStack.pop();
			}
		}
	}
	
	/* erstellt zufällige Unterkreise und löscht die benutzten Kanten aus dem clonedGraph */
	private void konstruiereUnterkreise(CustomVertex v)
	{
		CustomVertex aktKnoten = v;
		CustomVertex endKnoten = null;
		List<DefaultWeightedEdge> subListEdges = new ArrayList<DefaultWeightedEdge>();
				
		// Wiederhole solange bis zufällig der neue Knoten der Startknoten v ist
		while(!v.equals(endKnoten))
		{
			Set<DefaultWeightedEdge> nachbarKanten = clonedGraph.edgesOf(aktKnoten);
			Iterator<DefaultWeightedEdge> iterE = nachbarKanten.iterator();			
			DefaultWeightedEdge e = iterE.next();
			while(!edgeList.contains(e))
			{
				e = iterE.next();
			}
			CustomVertex source = clonedGraph.getEdgeSource(e);
			CustomVertex target = clonedGraph.getEdgeTarget(e);
			aktKnoten = aktKnoten.equals(source) ? target : source;
			endKnoten = aktKnoten;
			subListEdges.add(e);
			edgeList.remove(e);
			clonedGraph.removeEdge(e);
		}
		
		unterkreise.add(subListEdges);
	}
	
	/* Testet ob der Graph zusammenhängend ist und ob alle Knotengrade gerade sind */
	private Boolean preconditions()
	{
		Boolean erfuellt = true;
		
		// Ist der Graph zusammenhÃ¤ngend?
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
	
	//Ändert den kreis, so das die Kante mit dem gesuchten Knoten an erster Stelle steht
	private void KreisSortieren(List<DefaultWeightedEdge> unterkreis, CustomVertex v)
	{
		while(!g.getEdgeTarget(unterkreis.get(0)).equals(v) && !g.getEdgeSource(unterkreis.get(0)).equals(v))
		{
			DefaultWeightedEdge e = unterkreis.get(0);
			unterkreis.remove(e);
			unterkreis.add(unterkreis.size(), e);
		}
	}
	
	/* Gib die Eulertour als Graph zurück */
	public Graph<CustomVertex, DefaultWeightedEdge> gibEulerGraph() throws Exception
	{
		Graph<CustomVertex, DefaultWeightedEdge> eulerGraph = new Pseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		for(CustomVertex v : g.vertexSet())
		{
			eulerGraph.addVertex(v);
		}
		
		for(DefaultWeightedEdge e : kantenfolge)
		{
			CustomVertex source = g.getEdgeSource(e);
			CustomVertex target = g.getEdgeTarget(e);
			
			eulerGraph.addEdge(source, target);
		}
		
		if(eulerGraph.edgeSet().size() != g.edgeSet().size())
		{
			throw new Exception("Fehler beim Erstellen des Eulergraphs - Fleury");
		}
		new GraphVizExporter().exportGraphToDotFile(eulerGraph, "gespeicherterEulerGraphHierholzer");
		return eulerGraph;
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