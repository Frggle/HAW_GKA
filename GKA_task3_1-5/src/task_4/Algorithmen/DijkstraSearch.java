package task_4.Algorithmen;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Set;

import org.jgrapht.Graph;

import task_4.CustomVertex;

/*
 * Weg von Husum nach Hamburg
 * kÃ¼rzester Weg: Husum - Kiel - Uelzen - Rotenburg - Soltau - Buxtehude - Hamburg
 * Anzahl der Zugriffe: 16
 * 
 * Weg von Minden nach Hamburg
 * kÃ¼rzester Weg: Minden - Walsrode - Hamburg
 * Anzahl der Zugriffe: 10
 * 
 * Weg von Muenster nach Hamburg
 * kÃ¼rzester Weg: Muenster - Bremen - Hamburg
 * Anzahl der Zugriffe: 13
 */
public class DijkstraSearch
{
	private LinkedList<CustomVertex> dijkstraLoesung = null;
	private int counter = 0;
	private int weglaenge = 0;

	@SuppressWarnings(
	{ "unchecked", "rawtypes" })
	public DijkstraSearch(Graph graph, CustomVertex start, CustomVertex ende) throws IOException
	{
		if (graph.containsVertex(start) && graph.containsVertex(ende))
		{
			Set<CustomVertex> temp = graph.vertexSet();
			CustomVertex[] knoten = new CustomVertex[temp.size()];
			int[][] felder = new int[temp.size()][3];
			int max = Integer.MAX_VALUE;
			int i = 1;
			int aktKnoten = 0;
			// aktuelle "Ausgangszeile", die vergleichende Zeile
			int[][] tempA = new int[1][3];
			int startKnoten = 0;
			int zielindex = temp.size()-1;

			felder[startKnoten][0] = 0;
			felder[startKnoten][1] = 0;
			felder[startKnoten][2] = 1;
			knoten[startKnoten] = start;
			
			felder[zielindex][0] = zielindex;
			felder[zielindex][1] = max;
			felder[zielindex][2] = 0;
			knoten[zielindex] = ende;
			
			for(CustomVertex s : temp)
			{
				if(!s.equals(start) && !s.equals(ende))
				{
					knoten[i] = s;
					felder[i][0] = zielindex;
					felder[i][1] = max;
					felder[i][2] = 0;
					i++;
				}
			}
				
			//kürzesten weg zum Zielknoten suchen
			while (felder[zielindex][2] == 0) // OR Zielindex auf TRUE gesetzt
			{
				for (i = 0; i <= knoten.length - 1; i++)
				{
					// es existiert eine Kante und die Kantensumme ist < als die
					// im
					// Nachbarknoten vorhandene
					if (graph.containsEdge(knoten[aktKnoten], knoten[i]) 
							&& (graph.getEdgeWeight(graph.getEdge(knoten[aktKnoten], knoten[i])) <0.0))
					{
						throw new IllegalArgumentException("negative Kantengewichtung");
					}
					
					if (graph.containsEdge(knoten[aktKnoten], knoten[i])
							&& (felder[aktKnoten][1] + (int) graph.getEdgeWeight(graph.getEdge(knoten[aktKnoten],
									knoten[i]))) < felder[i][1])
					{
						// Entfernung vom Nachfolger = Entfernung vom aktuellen
						// Knoten + Kantengewichtung
						felder[i][1] = (int) (felder[aktKnoten][1] + graph.getEdgeWeight(graph.getEdge(
								knoten[aktKnoten], knoten[i])));
						// setze Vorgänger
						felder[i][0] = aktKnoten;
					}					
				}
				counter+=1;

				// aktueller Knoten wird auf TRUE (1) gesetzt
				felder[aktKnoten][2] = 1;

				//Standartwerte für den temporären Knoten setzen
				tempA[0][0] = 0;
				tempA[0][1] = max;
				tempA[0][2] = 1;
				// nÃ¤chstes zu durchsuchendes Element wÃ¤hlen
				// Bedingung: OK darf nicht TRUE (1) sein => (felder[i][2] == 0)
				// Bedingung: der kleinste Entfernungswert => felder[i][1]
				for (i = 0; i < felder.length; i++)
				{
					if (felder[i][1] <= tempA[0][1] && felder[i][2] == 0)
					{
						tempA[0] = felder[i].clone();
						aktKnoten = i;
					}
				}
			}
			
			System.out.println("Counter = " + counter);

			LinkedList<CustomVertex> loesung = new LinkedList();
			FileWriter writer = new FileWriter("./src/_Dijkstrasolution.txt", false);

			aktKnoten = zielindex;

			i = 0;
			while (aktKnoten != startKnoten && i < knoten.length)
			{
				loesung.add(0, knoten[aktKnoten]);
				aktKnoten = felder[aktKnoten][0];
				i++;
			}
			loesung.add(0, start);

			//prüfen, ob es einen Weg zum Ziel gibt
			if (i < knoten.length)
			{
				dijkstraLoesung = loesung;
				writer.write("Länge des kürzesten Wegs: " + felder[zielindex][1] + "\r\n");
				weglaenge = felder[zielindex][1];
				writer.write("Kürzester Weg: ");
				for (CustomVertex s : loesung)
				{
					writer.write(s.getVertexName() + ", ");
				}
			} else
			{
				dijkstraLoesung = new LinkedList<CustomVertex>();
				dijkstraLoesung.add(new CustomVertex("Keine Loesung"));
				writer.write("Es gibt keinen Weg vom start zum Ziel!");
			}
			writer.close();
		}
	}

	public LinkedList<CustomVertex> gibLoesung()
	{
		return dijkstraLoesung;
	}

	public int gibCounter()
	{
		return counter;
	}
	
	public int gibWegLaenge()
	{
		return weglaenge;
	}
}