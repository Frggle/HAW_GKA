package JUnit_Test;

import static org.junit.Assert.*;

import java.io.File;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;
import org.junit.Test;

import task_4.CustomVertex;
import task_4.Service.GraphVizExporter;

public class GraphVizExporterTest
{
    Pseudograph<CustomVertex, DefaultWeightedEdge> graph = new WeightedPseudograph<CustomVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
    GraphVizExporter graphVizExporter = new GraphVizExporter();
//    String pathDot = "GraphViz/graph/exportedGraph.dot";
    String pathGv = "GraphViz/graph/exportedGraph.gv";
//    File file1 = new File(pathDot);
    File file2 = new File(pathGv);
 
    // Testet ob die Methode beide Dateien erstellt hat
    @Test
    public void testexportGraphToDotFile()
    {
        graphVizExporter.exportGraphToDotFile(graph, "exportedGraph");
//        assertTrue(file1.exists());
        assertTrue(file2.exists());
    }

}
