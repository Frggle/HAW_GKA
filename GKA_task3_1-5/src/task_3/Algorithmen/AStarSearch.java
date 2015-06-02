package task_3.Algorithmen;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Set;

import org.jgrapht.Graph;

import task_3.CustomVertex;

/*
 * Weg von Husum nach Hamburg
 * kÃ¼rzester Weg: Husum - Kiel - Uelzen - Rotenburg - Soltau - Buxtehude - Hamburg
 * Anzahl der Zugriffe: 11
 * 
 * Weg von Minden nach Hamburg
 * kÃ¼rzester Weg: Minden - Walsrode - Hamburg
 * Anzahl der Zugriffe: 5
 * 
 * Weg von Muenster nach Hamburg
 * kÃ¼rzester Weg: Muenster - Bremen - Hamburg
 * Anzahl der Zugriffe: 4
 */
public class AStarSearch
{
	private LinkedList<CustomVertex> aStarLoesung = null;
	private int counter = 0;
	private int weglaenge = 0;

	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	public AStarSearch(Graph graph, CustomVertex start, CustomVertex ende) throws IOException
	{
		Set<CustomVertex> temp = graph.vertexSet();
		CustomVertex[] knoten = new CustomVertex[temp.size()];
		int[][] felder = new int[temp.size()][5];
		int max = Integer.MAX_VALUE;
		int i = 1;
		int aktKnoten = 0;
		int[][] tempA = new int[1][5];
		int startKnoten = 0;
		int zielindex = temp.size() - 1;

		// startknoten initialisieren
		felder[startKnoten][0] = 0;
		felder[startKnoten][1] = 0;
		felder[startKnoten][2] = 1;
		felder[startKnoten][3] = start.getVertexAttribute();
		felder[startKnoten][4] = 0;
		knoten[startKnoten] = start;

		// endknoten initialisieren
		felder[zielindex][0] = zielindex;
		felder[zielindex][1] = max;
		felder[zielindex][2] = 0;
		felder[zielindex][3] = ende.getVertexAttribute();
		felder[zielindex][4] = max;
		knoten[zielindex] = ende;

		// alle anderen knoten initialisieren
		for (CustomVertex s : temp)
		{
			if (!s.equals(start) && !s.equals(ende))
			{
				knoten[i] = s;
				felder[i][0] = zielindex;
				felder[i][1] = max;
				felder[i][2] = 0;
				felder[i][3] = s.getVertexAttribute();
				felder[i][4] = max;
				i++;
			}
		}

		// kürzesten weg zum Zielknoten suchen
		while (felder[zielindex][2] == 0)
		{
			for (i = 0; i <= knoten.length - 1; i++)
			{
				if (graph.containsEdge(knoten[aktKnoten], knoten[i])
						&& (graph.getEdgeWeight(graph.getEdge(knoten[aktKnoten], knoten[i])) < 0.0))
				{
					throw new IllegalArgumentException("negative Kantengewichtung");
				}

				// es existiert eine Kante und die Kantensumme ist < als die im
				// Nachbarknoten vorhandene
				if (graph.containsEdge(knoten[aktKnoten], knoten[i])
						&& (felder[aktKnoten][1] + graph.getEdgeWeight(graph.getEdge(knoten[aktKnoten], knoten[i]))) < felder[i][1])
				{
					felder[i][1] = (int) (felder[aktKnoten][1] + graph.getEdgeWeight(graph.getEdge(knoten[aktKnoten],
							knoten[i])));
					felder[i][0] = aktKnoten;
					felder[i][4] = felder[i][1] + felder[i][3];
				}
			}
			counter += 1;

			felder[aktKnoten][2] = 1;

			// Standartwerte für den temporären Knoten setzen
			tempA[0][0] = 0;
			tempA[0][1] = max;
			tempA[0][2] = 1;
			tempA[0][3] = max;
			tempA[0][4] = max;

			// nÃ¤chstes zu durchsuchendes element wÃ¤hlen
			for (i = 0; i < felder.length; i++)
			{
				if (felder[i][4] <= tempA[0][4] && felder[i][2] == 0)
				{
					tempA[0] = felder[i].clone();
					aktKnoten = i;
				}
			}
		}

		System.out.println("Counter = " + counter);

		LinkedList<CustomVertex> loesung = new LinkedList();
		FileWriter writer = new FileWriter("./src/_AStarSolulution.txt", false);

		aktKnoten = zielindex;

		i = 0;
		// Weg zum Startknoten zurück gehen
		while (aktKnoten != startKnoten && i < knoten.length)
		{
			loesung.add(0, knoten[aktKnoten]);
			aktKnoten = felder[aktKnoten][0];
			i++;
		}
		loesung.add(0, start);

		// prüfen, ob es einen Weg zum Ziel gibt
		if (i < knoten.length)
		{
			aStarLoesung = loesung;
			writer.write("Länge des kürzesten Wegs: " + felder[zielindex][1] + "\r\n");
			writer.write("Kürzester Weg: ");
			weglaenge = felder[zielindex][1];
			for (CustomVertex s : loesung)
			{
				writer.write(s.getVertexName() + ", ");
			}
		} else
		{
			aStarLoesung = new LinkedList<CustomVertex>();
			aStarLoesung.add(new CustomVertex("Keine Loesung"));
			writer.write("Es gibt keinen Weg vom " + start.getVertexName() + " zum " + ende.getVertexName() + "!");
		}
		writer.close();
	}

	public LinkedList<CustomVertex> gibLoesung()
	{
		return aStarLoesung;
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