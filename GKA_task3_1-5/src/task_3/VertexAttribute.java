package task_3;

import org.jgrapht.graph.DefaultWeightedEdge;

public class VertexAttribute
{
	private DefaultWeightedEdge edge;
	private double weight;
	
	public VertexAttribute() {
		edge = null;
		weight = Double.MAX_VALUE;
	}
	
	public VertexAttribute(DefaultWeightedEdge _edge, double _weight) {
		edge   = _edge;
		weight = _weight;
	}
	
	public Double getWeight()
	{
		return weight;
	}
	
	public DefaultWeightedEdge getEdge()
	{
		return edge;
	}
}