package task_4.GraphGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Pseudograph;

import task_4.CustomVertex;

public class EulerGraphGenerator3
{
	private int knotenAnzahl;
	private Graph<CustomVertex, DefaultWeightedEdge> eulerGraph;
	private List<CustomVertex> ungeradeKnoten;
	
	public EulerGraphGenerator3()
	{
		eulerGraph = new Pseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	}
	
	public void baueEulerGraph(int _knotenAnzahl) 
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
	
		ungeradeKnoten = new ArrayList<CustomVertex>();
				
		CustomVertex vorgaengerKnoten = new CustomVertex("0");
		eulerGraph.addVertex(vorgaengerKnoten);
		
		for(int i = 1; i < knotenAnzahl; i++)
		{
			CustomVertex neuerKnoten = new CustomVertex(""+i);
			eulerGraph.addVertex(neuerKnoten);
			
			
			ungeradeKnoten.add(neuerKnoten);
			
			// Kante zwischen neu erstellen Knoten und dem Vorgängerknoten
//			eulerGraph.addEdge(neuerKnoten, vorgaengerKnoten);
			
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
