package task_4.Algorithmen;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;

import task_4.CustomVertex;


/**
 * BFs benoetigt Knotenname UND Attribut -> so wie der Knoten instanziert wurde (vollstaendige Infos)
 * @author Marc
 *
 */
public class BreadthFirstSearch
{
    private ArrayList<CustomVertex> bfs;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public BreadthFirstSearch(Graph graph, CustomVertex start, CustomVertex ende) throws IOException //Graph, String, String
    {    	
    	//Set<String> alleKnoten = graph.vertexSet();
    	
    	//Helper helper = new Helper();
        //Set<String> alleKnoten = helper.entferneAttribute(graph.vertexSet()); //Hilftsklasse ohne Attribute        
        
    	Set<CustomVertex> alleKnoten = graph.vertexSet();
    	    	
        Integer i = 0;
        HashMap map = new HashMap();
        Set<CustomVertex> quellSet = new HashSet();
        quellSet.add(start);
        Set<CustomVertex> benachbarteKnoten = new HashSet();
        benachbarteKnoten.add(start);
        map.put(i, benachbarteKnoten);
        
        // Sucht solange der gesuchte Knoten nicht gefunden wurde
        // oder das Set mit den Knoten f¸r die ein Nachfolger gesucht werden
        // soll leer ist.
        loop: while (!quellSet.contains(ende) && quellSet.size() != 0 && map.size() <= graph.vertexSet().size())
        {
            quellSet = (Set<CustomVertex>) map.get(i);
            i++;
            benachbarteKnoten = new HashSet();

            // sucht f¸r ein Set mit Knoten alle Nachfolgerknoten.
            for (CustomVertex quellKnoten : quellSet)
            {
                for (CustomVertex s : alleKnoten)
                {
               	if (graph.containsEdge(quellKnoten, s) && !quellKnoten.equals(s))
                    {
                        benachbarteKnoten.add(s);
                        if (s.equals(ende))
                        {
                            break loop;
                        }
                    }
                }
            }
            // markiert alle Nachfolger mit einem Wert i
            map.put(i, benachbarteKnoten);
        }

        ArrayList<CustomVertex> kuerzesterWeg = new ArrayList<CustomVertex>();
        CustomVertex aktElement = ende;

        FileWriter writer = new FileWriter("./src/_BFSsolution.txt", false);
        if (map.size() <= graph.vertexSet().size())
        {
            // es wird ein Weg zur¸ck von dem gesuchten Knoten zum Anfangsknoten
            // in der Liste mit den markierten Elementen gesucht.
            while (i > 0)
            {
                kuerzesterWeg.add(aktElement);
                i--;
                benachbarteKnoten = (HashSet) map.get(i);
                aktElement = gibVorgaenger(graph, aktElement, benachbarteKnoten);
            }
            kuerzesterWeg.add(start);

            // l√∂sche null aus Liste
            kuerzesterWeg.removeAll(Collections.singleton(null));

            // Schreiben des k¸rzesten Wegs und der Anzahl der benˆtigten
            // kannten in eine Datei.
            for (i = kuerzesterWeg.size(); i > 0; i--)
            {
                writer.write((CustomVertex) kuerzesterWeg.get(i - 1) + "\r\n");
            }
            int anzahlInt = kuerzesterWeg.size() - 1;
            String anzahl = "" + anzahlInt;
            writer.write("Anzahl der Kanten: " + anzahl);
            Collections.reverse(kuerzesterWeg);
            bfs = kuerzesterWeg;

        } else
        {
            writer.write("Es existiert keine Kante vom " + start.getVertexName() + " zum " + ende.getVertexName() + "!");
        }
        writer.close();
    }

    /*
     * Methode um von einen Knoten einen Vorg‰nger Knoten aus einer Liste
     * auszuw‰hlen
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public CustomVertex gibVorgaenger(Graph g, CustomVertex aktKnoten, Set<CustomVertex> vorgaengerKnoten)
    {
        CustomVertex vorgaenger = null;
        for (CustomVertex s : vorgaengerKnoten)
        {
            if (g.containsEdge(s, aktKnoten))
            {
                vorgaenger = s;
            }
        }
        return vorgaenger;
    }

    public ArrayList<CustomVertex> gibLoesung()
    {
        return bfs;
    }
}