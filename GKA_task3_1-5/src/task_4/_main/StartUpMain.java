package task_4._main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

import task_4.CustomVertex;
import task_4.Algorithmen.AStarSearch;
import task_4.Algorithmen.BreadthFirstSearch;
import task_4.Algorithmen.DijkstraSearch;
import task_4.Algorithmen.FleuryEulertour;
import task_4.Algorithmen.HierholzerEulertour;
import task_4.Algorithmen.Kruskal;
import task_4.Algorithmen.Prim_Heap;
import task_4.Algorithmen.Prim_ohne_Heap;
import task_4.Service.GraphVizExporter;
import task_4.Service.Helper;
import task_4.Service.Scanner;

public class StartUpMain
{
	// public static Set<String> alleKnotenNamen = new HashSet<String>();
	@SuppressWarnings("rawtypes")
	private static Graph graph = null;

	@SuppressWarnings( { "unused" })
	public static void main(String[] args)
	{
		// Start und Endknoten der Suche
//		CustomVertex start = new CustomVertex("Endknoten", 0);
//		CustomVertex ende = new CustomVertex("Endknoten", 0);

//		CustomVertex start = new CustomVertex("Muenster", 237);
//		CustomVertex ende = new CustomVertex("Hamburg", 0);
		
		CustomVertex start = new CustomVertex("eins");
		CustomVertex ende = new CustomVertex("eins");
				
		// welcher Algorithmus
		// bfs = BreadthFirstSearch
		// dijkstra = DijkstraSearch
		// astar = A*Search
		String algorithmus = "";

		// Dateipfad
//		String path = "./src/bspGraphen/eulerMini1.graph";
		String path = "./src/bspGraphen/eulerNikolaus.graph";

		List<CustomVertex> loesung = programmStarten(path, start, ende, algorithmus);
//		 List<CustomVertex> loesung = programmStarten(args[0], start, ende, args[1]);
	}

	@SuppressWarnings(
	{ "unchecked", "rawtypes" })
	public static List<CustomVertex> programmStarten(String path, CustomVertex start, CustomVertex ende,
			String algorithmus)
	{
		// Graph speichern?
		Boolean graphSpeichern = true;
		String pathSaver = "./src/bspGraphen/gespeicherterGraph.graph";
		Boolean dateiErweitern = false;

		// Liest die Datei ein
		Scanner scanner = new Scanner();
		ArrayList<String> scannerArray = scanner.dateiLesen(path);

		// Hilfsklasse - bereinigt ein Array von nulls und Leerzeichen +
		// extrahiert Infos aus der Textzeile
		Helper helper = new Helper();

		// Graph
//		Graph graph = null;

		// Bereinigt das Array
		System.out.println("unbereinigt: " + scannerArray.toString());
		ArrayList<String> bereinigtesArray = helper.dateiBereinigen(scannerArray);
		System.out.println("bereinigt:   " + bereinigtesArray.toString());

		// löscht die erste Zeile, wenn [#directed] etc. enthalten
		Boolean ersteZeileGeloescht = false;
		if (bereinigtesArray.get(0).contains("#"))
		{
			bereinigtesArray.remove(0);
			System.out.println("erste Zeile gelöscht");
			ersteZeileGeloescht = true;
		} else
		{
			System.out.println("# nicht enthalten");
		}

		ArrayList<String> infoFuerDenGraph = new ArrayList<String>();

		if (scanner.gibWeighted() && ersteZeileGeloescht)
		{
			if (scanner.gibDirected())
			{
//				graph = new MyDirectedWeightedGraph();
				graph = new DirectedWeightedPseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
			} else if (scanner.gibUndirected() ^ scanner.gibAttributed())
			{
//				graph = new MyUndirectedWeightedGraph();
				graph = new WeightedPseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
			}
			for (String content : bereinigtesArray)
			{
				infoFuerDenGraph = helper.gibInfoAusArray(content);

				CustomVertex v1temp = null;
				CustomVertex v2temp = null;

				ArrayList<CustomVertex> temp = helper.gibKnotenInfo(infoFuerDenGraph);
				if (temp.size() == 1)
				{
					v1temp = temp.get(0); // Knoten mit Heuristic 1
					v2temp = new CustomVertex(null);

				} else if (temp.size() == 2)
				{
					v1temp = temp.get(0); // Knoten mit Heuristic 1
					v2temp = temp.get(1); // Knoten mit Heuristic 2
				}

				// XOR falls nur EIN Knoten OHNE Kante hinzugefügt wird
				if (((v1temp.getVertexName() != null) ^ (v2temp.getVertexName() != null)))
				{
					if (v1temp.getVertexName() == null)
					{
						String name = v2temp.getVertexName();
						CustomVertex customvertex = null;
						if (!v2temp.istAttributeGesetzt())
						{
							customvertex = new CustomVertex(name);
						} else
						{
							int heuristic = v2temp.getVertexAttribute();
							customvertex = new CustomVertex(name, heuristic);
						}
						graph.addVertex(customvertex);
					} else if (v2temp.getVertexName() == null)
					{
						String name = v1temp.getVertexName();
						CustomVertex customvertex = null;
						if (!v1temp.istAttributeGesetzt())
						{
							customvertex = new CustomVertex(name);
						} else
						{
							int heuristic = v2temp.getVertexAttribute();
							customvertex = new CustomVertex(name, heuristic);
						}
						graph.addVertex(customvertex);
					}
				} else
				{
					// fügt den ersten Knoten hinzu
					if (!graph.containsVertex(v1temp.getVertexName()))
					{
						String name = v1temp.getVertexName();
						CustomVertex customvertex = null;
						if (!v1temp.istAttributeGesetzt())
						{
							customvertex = new CustomVertex(name);
						} else
						{
							int heuristic = v1temp.getVertexAttribute();
							customvertex = new CustomVertex(name, heuristic);
						}
						graph.addVertex(customvertex);
					}
					// fügt den zweiten Knoten hinzu
					if (!graph.containsVertex(v2temp.getVertexName()))
					{
						String name = v2temp.getVertexName();
						CustomVertex customvertex = null;
						if (!v2temp.istAttributeGesetzt())
						{
							customvertex = new CustomVertex(name);
						} else
						{
							int heuristic = v2temp.getVertexAttribute();
							customvertex = new CustomVertex(name, heuristic);
						}
						graph.addVertex(customvertex);
					}
					// Kanten und Gewichtung hinzufügen
					if (!graph.containsEdge(v1temp.getVertexName(), v2temp.getVertexName()))
					{
						DefaultWeightedEdge e = (DefaultWeightedEdge) graph.addEdge(v1temp, v2temp);
						Double weight = Double.parseDouble(infoFuerDenGraph.get(4));
						if (scanner.gibDirected())
						{
							((DirectedWeightedPseudograph<CustomVertex, DefaultWeightedEdge>) graph).setEdgeWeight(e,
									weight);
						} else
						{
							((Pseudograph<CustomVertex, DefaultWeightedEdge>) graph).setEdgeWeight(e, weight);
						}
					}
				}

			}
		}

		// jetzt kommt dieser Teil - CustomVertex
		else if ((!scanner.gibWeighted() && ersteZeileGeloescht) ^ !ersteZeileGeloescht)
		{
			if (scanner.gibDirected())
			{
//				graph = new MyDirectedGraph();
				graph = new DirectedWeightedPseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
			} else if (scanner.gibUndirected())
			{
//				graph = new MyUndirectedGraph();
				graph = new WeightedPseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
			} else
			{
//				graph = new MyUndirectedGraph();
				graph = new WeightedPseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
			}
			for (String content : bereinigtesArray)
			{
				infoFuerDenGraph = helper.gibInfoAusArray(content);

				CustomVertex v1temp = null;
				CustomVertex v2temp = null;

				ArrayList<CustomVertex> temp = helper.gibKnotenInfo(infoFuerDenGraph);
				if (temp.size() == 1)
				{
					v1temp = temp.get(0); // Knoten mit Heuristic 1
					v2temp = new CustomVertex(null);
				} else if (temp.size() == 2)
				{
					v1temp = temp.get(0); // Knoten mit Heuristic 1
					v2temp = temp.get(1); // Knoten mit Heuristic 2
				}

				// XOR falls nur EIN Knoten OHNE Kante hinzugefügt wird
				if ((v1temp.getVertexName() != null) ^ (v2temp.getVertexName() != null))
				{
					if (v1temp.getVertexName() == null)
					{
						String name = v2temp.getVertexName();
						CustomVertex customvertex = null;
						if (!v2temp.istAttributeGesetzt())
						{
							customvertex = new CustomVertex(name);
						} else
						{
							int heuristic = v2temp.getVertexAttribute();
							customvertex = new CustomVertex(name, heuristic);
						}
						graph.addVertex(customvertex);
					} else if (v2temp.getVertexName() == null)
					{
						String name = v1temp.getVertexName();
						CustomVertex customvertex = null;
						if (!v1temp.istAttributeGesetzt())
						{
							customvertex = new CustomVertex(name);
						} else
						{
							int heuristic = v2temp.getVertexAttribute();
							customvertex = new CustomVertex(name, heuristic);
						}
						graph.addVertex(customvertex);
					}
				} else
				{
					// fügt den ersten Knoten hinzu
					if (!graph.containsVertex(v1temp.getVertexName()))
					{
						String name = v1temp.getVertexName();
						CustomVertex customvertex = null;
						if (!v1temp.istAttributeGesetzt())
						{
							customvertex = new CustomVertex(name);
						} else
						{
							int heuristic = v1temp.getVertexAttribute();
							customvertex = new CustomVertex(name, heuristic);
						}
						graph.addVertex(customvertex);
					}
					// fügt den zweiten Knoten hinzu
					if (!graph.containsVertex(v2temp.getVertexName()))
					{
						String name = v2temp.getVertexName();
						CustomVertex customvertex = null;
						if (!v2temp.istAttributeGesetzt())
						{
							customvertex = new CustomVertex(name);
						} else
						{
							int heuristic = v2temp.getVertexAttribute();
							customvertex = new CustomVertex(name, heuristic);
						}
						graph.addVertex(customvertex);
					}

					// Kanten hinzufügen
					if (!graph.containsEdge(v1temp.getVertexName(), v2temp.getVertexName()))
					{
						graph.addEdge(v1temp, v2temp);
					}
				}
			}
		}

		// Export als .dot und .gv
		String dateiName = "gespeicherterGraph";
		new GraphVizExporter().exportGraphToDotFile(graph, dateiName);

		if (graphSpeichern)
		{
			helper.graphSpeichern(graph, pathSaver, dateiErweitern);
		}
		
		// Kruskal -----------------------------------------------------
		Kruskal kruskal = new Kruskal();
		kruskal.builtTree(graph);
		Graph<CustomVertex, DefaultWeightedEdge> kruskaltree = kruskal.getGraph();
		System.err.println("");
		System.err.println("Gesamtkantengewichtung vom Kruskalbaum: " + kruskal.gibGesamtgewichtung());
		System.err.println("Dafür brauchten wir " + kruskal.getRunTimeInMiliSec() + "ms");
		System.err.println("");
		new GraphVizExporter().exportGraphToDotFile(kruskaltree, "gespeicherterKruskal");

		// Prim mit Fibonacci Heap
		Prim_Heap prim = new Prim_Heap((WeightedPseudograph) graph);
		System.err.println("Gesamtkantengewichtung vom Prim Heap: " + prim.gibGesamtgewicht());
		System.err.println("Dafür brauchten wir " + prim.gibLaufzeit() + "ms");
		System.err.println("");
		new GraphVizExporter().exportGraphToDotFile(prim.gibGraph(), "gespeicherterPrimHeap");
		
		// Prim ohne Heap
		Prim_ohne_Heap prim2 = new Prim_ohne_Heap((WeightedPseudograph)graph);
		System.err.println("Gesamtkantengewichtung vom Prim ohne Heap: " + prim2.gibGesamtgewicht());
		System.err.println("Dafür brauchten wir " + prim2.gibLaufzeit() + "ms");
		System.err.println("");
		new GraphVizExporter().exportGraphToDotFile(prim2.gibGraph(), "gespeicherterPrimOhneHeap");
		
		
		
		// Fleury
		FleuryEulertour fleury = new FleuryEulertour(graph);
		System.err.println("Kantenfolge Fleury " + fleury.gibKantenfolge());
		
		// Hierholzer
		HierholzerEulertour hierholzer = new HierholzerEulertour(graph);
		System.err.println("Kantenfolge Hierholzer " + hierholzer.gibKantenfolge());
		
		System.err.println("");
		

		// ********************************************************************************************************************
		// ********************************************************************************************************************
		// ********************************************************************************************************************
		List<CustomVertex> mainReturnValue = null;
		if (algorithmus.matches("bfs"))
		{
			System.out.println("BFS");
			try
			{
				BreadthFirstSearch bfSearch = new BreadthFirstSearch(graph, start, ende);
				ArrayList<CustomVertex> loesung = bfSearch.gibLoesung();
				System.out.println("BFS: " + loesung);
				mainReturnValue = loesung;
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} else if (algorithmus.matches("dijkstra"))
		{
			System.out.println("Dijkstra");
			try
			{
				DijkstraSearch dijkstra = new DijkstraSearch(graph, start, ende);
				LinkedList<CustomVertex> loesung = dijkstra.gibLoesung();
				String res = "";
				for (CustomVertex v : loesung)
				{
					res += "-" + v.getVertexName();
				}
				System.out.println("Dijkstra: " + res);
				mainReturnValue = loesung;
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} else if (algorithmus.matches("astar"))
		{
			System.out.println("A*");
			try
			{
				AStarSearch astar = new AStarSearch(graph, start, ende);
				LinkedList<CustomVertex> loesung = astar.gibLoesung();
				String res = "";
				for (CustomVertex v : loesung)
				{
					res += "-" + v.getVertexName();
				}
				System.out.println("A*: " + res);
				mainReturnValue = loesung;
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} else if (algorithmus.matches("beide"))
		{
			System.out.println();
			System.out.println("Beide Algorithmen");
			try
			{
				AStarSearch astar = new AStarSearch(graph, start, ende);
				LinkedList<CustomVertex> loesung = astar.gibLoesung();
				String res = "";
				for (CustomVertex v : loesung)
				{
					res += "-" + v.getVertexName();
				}
				System.out.println("A*: " + res);
				mainReturnValue = loesung;
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			System.out.println();

			try
			{
				DijkstraSearch dijkstra = new DijkstraSearch(graph, start, ende);
				LinkedList<CustomVertex> loesung = dijkstra.gibLoesung();
				String res = "";
				for (CustomVertex v : loesung)
				{
					res += "-" + v.getVertexName();
				}
				System.out.println("Dijkstra: " + res);
				mainReturnValue = loesung;
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		} else
		{
			System.out.println("Kein Algorithmus gewählt");
		}
		
		
				
		// ********************************************************************************************************************
		// ********************************************************************************************************************
		// ********************************************************************************************************************

		
		DijkstraShortestPath<CustomVertex, DefaultWeightedEdge> dijkstraJGraphT = new DijkstraShortestPath<CustomVertex, DefaultWeightedEdge>(
				graph, start, ende);
		System.out.println();
		System.out.println("Dijkstra von JGraphT: " + dijkstraJGraphT.getPath());
		System.out.println("Weglänge: " + dijkstraJGraphT.getPathLength());
	

		return mainReturnValue;
	}
	
	@SuppressWarnings("unchecked")
	public Graph<CustomVertex, DefaultWeightedEdge> gibGraph()
	{
		return graph;
	}
}
