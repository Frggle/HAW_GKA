package task_4._main;

import java.util.Random;

import task_4.GraphGenerator.GraphGen_mit_Gewichtung_Heuristic;
import task_4.Service.GraphVizExporter;
import task_4.Service.Helper;

public class mainGenerateGraph
{
	public static void main(String[] args)
	{
		int gegebeneKnotenAnzahl = 20;
		int min = 2;
		Random random = new Random();		
		int numEdge = random.nextInt((gegebeneKnotenAnzahl) - min + 1) + (2*gegebeneKnotenAnzahl);
		
		//BIG
//		int gegebeneKnotenAnzahl = 3000;
//		int numEdge = 15000;
		
		// NumVertexes, NumEdges
		GraphGen_mit_Gewichtung_Heuristic gGen = new GraphGen_mit_Gewichtung_Heuristic(gegebeneKnotenAnzahl, numEdge);
		String pathRandomGraph = "./src/bspGraphen/randomGraph.graph";
		Helper helper = new Helper();
		helper.randomGraphSpeichern(gGen.gibGraph(), pathRandomGraph, false);
		
		String randomDateiName = "randomGraph";
		new GraphVizExporter().exportGraphToDotFile(gGen.gibGraph(), randomDateiName);
		System.out.println("Random Graph erstellt");
		System.err.println("Knotenanzahl: " + gGen.gibGraph().vertexSet().size());
		System.err.println("Kantenanzahl: " + gGen.gibGraph().edgeSet().size());
	}

}
