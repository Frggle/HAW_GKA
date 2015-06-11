package task_4.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.ext.ComponentAttributeProvider;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.IntegerNameProvider;
import org.jgrapht.ext.StringNameProvider;
import org.jgrapht.graph.DefaultWeightedEdge;

import task_4.CustomVertex;

public class GraphVizExporter
{
    public void exportGraphToDotFile(Graph<CustomVertex, DefaultWeightedEdge> graph, String dateiName)
    {
        Boolean graphIsWeighted = graph instanceof WeightedGraph;
        
        // Exportiert den Graphen für GraphViz (Visualisierungstool)
        IntegerNameProvider<CustomVertex> p1 = new IntegerNameProvider<CustomVertex>();
        StringNameProvider<CustomVertex> p2 = new StringNameProvider<CustomVertex>();
        ComponentAttributeProvider<DefaultWeightedEdge> p4 = new ComponentAttributeProvider<DefaultWeightedEdge>()
        {
            public Map<String, String> getComponentAttributes(
                    DefaultWeightedEdge e)
            {
                Map<String, String> map = new LinkedHashMap<String, String>();
                map.put("label",
                        Double.toString(graph.getEdgeWeight(
                                e)));
                return map;
            }
        };
        
        DOTExporter<CustomVertex, DefaultWeightedEdge> exporter = null;
        if(graphIsWeighted)
        {
            exporter = new DOTExporter<CustomVertex, DefaultWeightedEdge>(p1,p2,null,null,p4);
        }
        else
        {
            exporter = new DOTExporter<CustomVertex, DefaultWeightedEdge>(p1,p2,null);

        }
        
        String targetDirectory = "GraphViz/graph/";
        new File(targetDirectory).mkdirs();
        try
        {
            //exporter.export(new FileWriter(targetDirectory + dateiName + ".dot"), graph);
            exporter.export(new FileWriter(targetDirectory + dateiName + ".gv"), graph);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }

}
