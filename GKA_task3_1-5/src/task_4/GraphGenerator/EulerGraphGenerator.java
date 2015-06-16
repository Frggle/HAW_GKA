package task_4.GraphGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Pseudograph;

import task_4.CustomVertex;

public class EulerGraphGenerator
{
	private int knotenAnzahl;
//	private int kantenAnzahl;
	private Graph<CustomVertex, DefaultWeightedEdge> eulerGraph;
	
	public EulerGraphGenerator()
	{
		eulerGraph = new Pseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	}
	
	public void baueEulerGraph(int _knotenAnzahl) // , int _kantenAnzahl)
	{
		// Knotenanzahl muss mindestens 2 sein
		if(_knotenAnzahl < 2)
		{
			throw new IllegalArgumentException("Die Knotenanzahl ist zu gering!");
		}
		else
		{
			knotenAnzahl = _knotenAnzahl;	
		}
		
		// Kantenanzahl muss ein Vielfaches von Knotenanzahl sein, damit Knotengrad gerade ist
//		if(_kantenAnzahl % knotenAnzahl != 0)
//		{
//			throw new IllegalArgumentException("Die Kantenanzahl ist zu gering!");
//		}
//		else
//		{
//			kantenAnzahl = _kantenAnzahl;
//		}
				
		CustomVertex vorgaengerKnoten = new CustomVertex("0");
		eulerGraph.addVertex(vorgaengerKnoten);
		
		for(int i = 1; i < knotenAnzahl; i++)
		{
			CustomVertex neuerKnoten = new CustomVertex(""+i);
			eulerGraph.addVertex(neuerKnoten);
			
			// Kante zwischen neu erstellen Knoten und dem Vorgängerknoten
			eulerGraph.addEdge(neuerKnoten, vorgaengerKnoten);
			
			CustomVertex knotenMitUngerademGrad = null;
			List<CustomVertex> ungeradeKnotenListe = knotenMitUngerademGrad(neuerKnoten);
			Random random = new Random();
			knotenMitUngerademGrad = ungeradeKnotenListe.get(random.nextInt(ungeradeKnotenListe.size()));

			// Kante zwischen neu erstellen Knoten und einem weiteren Knoten mit ungeradem Grad
			eulerGraph.addEdge(knotenMitUngerademGrad, neuerKnoten);
			
			vorgaengerKnoten = neuerKnoten;
		}
		System.err.println("EulerGraph mit " + knotenAnzahl + " Knoten erzeugt!");
	}
	
	private List<CustomVertex> knotenMitUngerademGrad(CustomVertex neuerKnoten)
	{
		List<CustomVertex> result = new ArrayList<CustomVertex>();
		for(CustomVertex v : eulerGraph.vertexSet())
		{
			if(eulerGraph.edgesOf(v).size() % 2 != 0)
			{
				result.add(v);
			}
		}
		result.remove(neuerKnoten);
		return result;
	}
	
	public Graph<CustomVertex, DefaultWeightedEdge> gibEulerGraph()
	{
		if(eulerGraph.vertexSet().size() == 0)
		{
			throw new NullPointerException("Der Eulergraph wurde noch nicht erstellt!");
		}
		return eulerGraph;
	}
	
}
