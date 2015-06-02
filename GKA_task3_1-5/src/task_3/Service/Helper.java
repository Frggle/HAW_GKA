package task_3.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import task_3.CustomVertex;

public class Helper
{
	/**
	 * Liefert die Infos aus der übergebenen Stringzeile node1 : attr1 , node2 :
	 * attr2 :: weight
	 * 
	 * @param eineZeile
	 *            aus dem Array
	 * @return extrahierte Infos aus dem Array
	 */
	@SuppressWarnings(
	{ "unchecked", "rawtypes" })
	public ArrayList<String> gibInfoAusArray(String eineZeile)
	{
		ArrayList infosAusArray = new ArrayList<Object>();

		// Knoten(-label) aus ArrayList extrahieren
		String ersteKnotenHälfte = null;
		String zweiteKnotenHälfte = null;
		String ersterKnotenName = null;
		String zweiterKnotenName = null;
		String ersterKnotenAttribute = null;
		String zweiterKnotenAttribute = null;
		String gewichtungKnoten = null;

		String variante1 = "[a-zA-Z0-9]+,[a-zA-Z0-9]+";
		String variante2 = "[a-zA-Z0-9]+:[0-9]+,[a-zA-Z0-9]+:-?[0-9]+.?[0-9]*";
		String variante3 = "[a-zA-Z0-9]+:[0-9]+,[a-zA-Z0-9]+:[0-9]+::-?[0-9]+.?[0-9]*";
		String variante4 = "[a-zA-Z0-9]+,[a-zA-Z0-9]+::-?[0-9]+.?[0-9]*";
		String variante5 = "[a-zA-Z0-9]+";
		String variante6 = "[a-zA-Z0-9]+:[a-zA-Z0-9]+";

		// node1, node2
		if (eineZeile.matches(variante1))
		{
			String[] temp = eineZeile.split(",");
			ersterKnotenName = temp[0];
			zweiterKnotenName = temp[1];
		}
		// node1 : attribute , node2 : attribute
		else if (eineZeile.matches(variante2))
		{
			String[] temp = eineZeile.split(",");
			ersteKnotenHälfte = temp[0];
			zweiteKnotenHälfte = temp[1];

			String[] temp2 = ersteKnotenHälfte.split(":");
			ersterKnotenName = temp2[0];
			ersterKnotenAttribute = temp2[1];

			String[] temp3 = zweiteKnotenHälfte.split(":");
			zweiterKnotenName = temp3[0];
			zweiterKnotenAttribute = temp3[1];
		}
		// node1 : attribute , node2 : attribute :: weight
		else if (eineZeile.matches(variante3))
		{
			String[] temp = eineZeile.split(",");
			ersteKnotenHälfte = temp[0];
			zweiteKnotenHälfte = temp[1];

			String[] temp2 = ersteKnotenHälfte.split(":");
			ersterKnotenName = temp2[0];
			ersterKnotenAttribute = temp2[1];

			String[] temp3 = zweiteKnotenHälfte.split("::");
			zweiteKnotenHälfte = temp3[0];
			gewichtungKnoten = temp3[1];

			String[] temp4 = zweiteKnotenHälfte.split(":");
			zweiterKnotenName = temp4[0];
			zweiterKnotenAttribute = temp4[1];
		}
		// node1 , node2 :: weight
		else if (eineZeile.matches(variante4))
		{
			String[] temp = eineZeile.split(",");
			ersterKnotenName = temp[0];
			zweiteKnotenHälfte = temp[1];

			String[] temp2 = zweiteKnotenHälfte.split("::");
			zweiterKnotenName = temp2[0];
			gewichtungKnoten = temp2[1];
		} 
		else if (eineZeile.matches(variante5))
		{
			ersterKnotenName = eineZeile;
		}
		else if (eineZeile.matches(variante6))
		{
			String[] temp = eineZeile.split(":");
			ersterKnotenName = temp[0];
			ersterKnotenAttribute = temp[1];
		}
		else
		{
			System.out.println("ungültiger Ausdruck");
			throw new IllegalArgumentException("String " + eineZeile + " does not contain ,");
		}

		infosAusArray.add(ersterKnotenName); // Index: 0 Node1
		infosAusArray.add(ersterKnotenAttribute); // Index: 1 Heuristic
		infosAusArray.add(zweiterKnotenName); // Index: 2 Node2
		infosAusArray.add(zweiterKnotenAttribute); // Index: 3 Heuristic
		infosAusArray.add(gewichtungKnoten); // Index: 4 Weight

		return infosAusArray;
	}

	/**
	 * Wandelt die String Knoteninfos in ein CustomVertex
	 * 
	 * @param KnotenString
	 *            , Knoteninfos als String
	 * @return KnotenInfo, KnotenInfo als CustomVertex-Instanz
	 */
	public ArrayList<CustomVertex> gibKnotenInfo(ArrayList<String> KnotenString)
	{
		ArrayList<CustomVertex> knotenInfo = new ArrayList<CustomVertex>();

		// node1 : attribute1 , node2 : attribute2 :: weight
		// Index 0 = node1 & ggf. Heuristic
		// Index 1 = node2 & ggf. Heuristic

		// Keine Heuristic gegeben (Index 1 und 3)
		if ((KnotenString.get(1) == null) && (KnotenString.get(3) == null))
		{
			// NUR Knoten1 wird erzeugt
			if (KnotenString.get(2) == null)
			{
				CustomVertex v1 = new CustomVertex(KnotenString.get(0));
				knotenInfo.add(v1);
			}
			// NUR Knoten2 wird erzeugt
			else if (KnotenString.get(0) == null)
			{
				CustomVertex v2 = new CustomVertex(KnotenString.get(2));
				knotenInfo.add(v2);
			}
			// BEIDE Knoten werden erzeugt
			else
			{
				CustomVertex v1 = new CustomVertex(KnotenString.get(0));
				knotenInfo.add(v1);
				CustomVertex v2 = new CustomVertex(KnotenString.get(2));
				knotenInfo.add(v2);
			}
		}
		// Heuristic ist gegeben
		else if ((KnotenString.get(1) != null) && (KnotenString.get(3) != null))
		{
			// NUR Knoten1 wird mit Heuristic erzeugt
			if (KnotenString.get(2) == null)
			{
				int heuristic1 = Integer.parseInt(KnotenString.get(1));
				CustomVertex v1 = new CustomVertex(KnotenString.get(0), heuristic1);
				knotenInfo.add(v1);
			}
			// NUR Knoten2 wird mit Heuristic erzeugt
			else if (KnotenString.get(0) == null)
			{
				int heuristic2 = Integer.parseInt(KnotenString.get(3));
				CustomVertex v2 = new CustomVertex(KnotenString.get(2), heuristic2);
				knotenInfo.add(v2);
			}
			// BEIDE Knoten werden mit Heuristic erzeugt
			else
			{
				int heuristic1 = Integer.parseInt(KnotenString.get(1));
				CustomVertex v1 = new CustomVertex(KnotenString.get(0), heuristic1);
				knotenInfo.add(v1);
				int heuristic2 = Integer.parseInt(KnotenString.get(3));
				CustomVertex v2 = new CustomVertex(KnotenString.get(2), heuristic2);
				knotenInfo.add(v2);
			}
		}
		else
		{
			int heuristic = Integer.parseInt(KnotenString.get(1));
			CustomVertex v = new CustomVertex(KnotenString.get(0), heuristic);
			knotenInfo.add(v);
		}

		return knotenInfo;
	}

	/**
	 * Löscht leere Zeilen/Elemente und null-Objekte aus der Liste
	 * 
	 * @return cleanArray
	 */
	public ArrayList<String> dateiBereinigen(ArrayList<String> array)
	{
		ArrayList<String> cleanArray = array;

		// lösche Leerzeichen zwischen den Bezeichnung in der ArrayList
		for (int i = 0; i < (cleanArray.size()); i++)
		{
			String aktuellesElement = cleanArray.get(i);
			aktuellesElement = aktuellesElement.replaceAll("\\s+", "");
			cleanArray.add(i, aktuellesElement);
			cleanArray.remove(i + 1);
		}

		// lösche Leerzeilen bzw. leere Elemente in der ArrayList
		// graphAlsArray.removeAll(Collections.singleton(null));
		cleanArray.removeAll(Arrays.asList(null, ""));
		return cleanArray;
	}

	public void graphSpeichern(Graph<CustomVertex, DefaultWeightedEdge> graph, String path, Boolean ueberschreiben)
	{
		FileWriter writer;
		try
		{
			// Writer erzeugen
			writer = new FileWriter(path, ueberschreiben);

			// Alle Knoten im Graphen
			Set<CustomVertex> alleKnoten = graph.vertexSet();

			// alle in die Datei geschriebenen Zeilen
			ArrayList<String> zeilenInDatei = new ArrayList<String>();

			// Graphenmerkmale/art bestimmen
			ArrayList<String> graphArt = graphArtBestimmen(graph.getClass().getTypeName());
			String merkmale = "";
			Boolean istGewichtet = false;
			Boolean istGerichtet = false;

			for (String s : graphArt)
			{
				merkmale = merkmale + s;
				if (merkmale.matches(".*#weighted.*"))
				{
					// System.out.println("gewichtet");
					istGewichtet = true;
				}
				if (merkmale.matches(".*#directed.*"))
				{
					istGerichtet = true;
				}
			}

			// Schreibe die Merkmale des Graphen in die erste Zeile
			writer.write(merkmale + "\r\n");
			for (CustomVertex v1 : alleKnoten)
			{
				// Knoten hat keine Kanten
				if (graph.edgesOf(v1).isEmpty())
				{
					if (v1.istAttributeGesetzt())
					{
						writer.write(v1.getVertexName() + ":" + v1.getVertexAttribute() + "\r\n");
					} else
					{
						writer.write(v1.getVertexName() + "\r\n");
					}
				} else
				{
					// zweiter Knoten wird gesucht
					for (CustomVertex v2 : alleKnoten)
					{
						// wenn gewichtet
						if (istGewichtet)
						{
							if (graph.containsEdge(v1, v2))
							{
								DefaultWeightedEdge e = graph.getEdge(v1, v2);
								double weight = graph.getEdgeWeight(e);
								String neueZeile = null;
								String neueZeileVertauschteKnoten = null;
								if (v1.istAttributeGesetzt() && v2.istAttributeGesetzt())
								{
									neueZeile = v1.getVertexName() + ":" + v1.getVertexAttribute() + ","
											+ v2.getVertexName() + ":" + v2.getVertexAttribute() + "::" + weight
											+ "\r\n";
									neueZeileVertauschteKnoten = v2.getVertexName() + ":" + v2.getVertexAttribute()
											+ "," + v1.getVertexName() + ":" + v1.getVertexAttribute() + "::" + weight
											+ "\r\n";
								} else
								{
									neueZeile = v1.getVertexName() + "," + v2.getVertexName() + "::" + weight + "\r\n";
									neueZeileVertauschteKnoten = v2.getVertexName() + "," + v1.getVertexName() + "::"
											+ weight + "\r\n";
								}
								// Schreibe nur in die Datei, wenn ungerichtet
								// und noch nicht enthalten
								if (!istGerichtet && !zeilenInDatei.contains(neueZeileVertauschteKnoten))
								{
									writer.write(neueZeile);
									zeilenInDatei.add(neueZeile);
								} else
								{
									//writer.write(neueZeile);
								}
							}
						}
						// wenn nicht gewichtet
						else
						{
							if (graph.containsEdge(v1, v2))
							{
								String neueZeile = v1.getVertexName() + "," + v2.getVertexName() + "\r\n";
								String neueZeileVertauschteKnoten = v2.getVertexName() + "," + v1.getVertexName()
										+ "\r\n";
								// Schreibe nur wenn ungerichtet und noch nicht
								// enthalten
								if (!istGerichtet && !zeilenInDatei.contains(neueZeileVertauschteKnoten))
								{
									zeilenInDatei.add(neueZeile);
									writer.write(neueZeile);
								}
								// Schreibe wenn gerichtet
								else
								{
									writer.write(neueZeile);
								}
							}
						}
					}
				}
			}
			writer.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void randomGraphSpeichern(Graph<CustomVertex, DefaultWeightedEdge> graph, String path, Boolean ueberschreiben)
	{
		FileWriter writer;
		try
		{
			// Writer erzeugen
			writer = new FileWriter(path, ueberschreiben);

			// Alle Knoten im Graphen
			Set<CustomVertex> alleKnoten = graph.vertexSet();

			// alle in die Datei geschriebenen Zeilen
			ArrayList<String> zeilenInDatei = new ArrayList<String>();

			String merkmale = "#attributed#weighted";
			// Schreibe die Merkmale des Graphen in die erste Zeile
			writer.write(merkmale + "\r\n");
			for (CustomVertex v1 : alleKnoten)
			{
				// Knoten hat keine Kanten
				if (graph.edgesOf(v1).isEmpty())
				{
					if (v1.istAttributeGesetzt())
					{
						writer.write(v1.getVertexName() + ":" + v1.getVertexAttribute() + "\r\n");
					} else
					{
						writer.write(v1.getVertexName() + "\r\n");
					}
				} else
				{
					// zweiter Knoten wird gesucht
					for (CustomVertex v2 : alleKnoten)
					{

						if (graph.containsEdge(v1, v2))
						{
							DefaultWeightedEdge e = graph.getEdge(v1, v2);
							Double weight = graph.getEdgeWeight(e);
							String neueZeile = null;
							String neueZeileVertauschteKnoten = null;
							if (v1.istAttributeGesetzt() && v2.istAttributeGesetzt())
							{
								neueZeile = v1.getVertexName() + ":" + v1.getVertexAttribute() + ","
										+ v2.getVertexName() + ":" + v2.getVertexAttribute() + "::" + weight + "\r\n";
								neueZeileVertauschteKnoten = v2.getVertexName() + ":" + v2.getVertexAttribute() + ","
										+ v1.getVertexName() + ":" + v1.getVertexAttribute() + "::" + weight + "\r\n";
							} else
							{
								neueZeile = v1.getVertexName() + "," + v2.getVertexName() + "::" + weight + "\r\n";
								neueZeileVertauschteKnoten = v2.getVertexName() + "," + v1.getVertexName() + "::"
										+ weight + "\r\n";
							}
							// Schreibe nur in die Datei, wenn ungerichtet und
							// noch nicht enthalten
							if (!zeilenInDatei.contains(neueZeileVertauschteKnoten))
							{
								writer.write(neueZeile);
								zeilenInDatei.add(neueZeile);
							} else
							{
								//writer.write(neueZeile);
							}
						}
					}
				}
			}
			writer.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList<String> graphArtBestimmen(String str)
	{
		String gerichtet = ".*Directed.*";
		String ungerichtet = ".*Undirected.*";
		String gewichtet = ".*Weighted.*";
		ArrayList<String> graphArt = new ArrayList<String>();
		if (str.matches(gerichtet))
		{
			graphArt.add("#directed");
		}
		if (str.matches(ungerichtet))
		{
			graphArt.add("#undirected");
		}
		if (str.matches(gewichtet))
		{
			graphArt.add("#weighted");
		}
		return graphArt;
	}
}
