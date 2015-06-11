package task_4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;


public class CustomPriorityQueue
{
	private Map<CustomVertex, VertexAttribute> vertexWeightMap;  	
	private Queue<CustomVertex> prioQueue;
	
	public CustomPriorityQueue()
	{
		vertexWeightMap = new HashMap<>();
		prioQueue = new PriorityQueue<CustomVertex>(new WeightComparator(vertexWeightMap));
	}
	
	public void insert(CustomVertex vertex, VertexAttribute vertexAttr)
	{
		vertexWeightMap.put(vertex, vertexAttr);
		prioQueue.add(vertex);
	}
	
	public CustomVertex min()
	{
		return prioQueue.peek();
	}
	
	public CustomVertex removeMin()
	{
		CustomVertex v = prioQueue.poll();
		vertexWeightMap.remove(v);
		return v;
	}
	
	
	private class WeightComparator implements Comparator<CustomVertex>
	{
		private Map<CustomVertex, VertexAttribute> map;

		public WeightComparator(Map<CustomVertex, VertexAttribute> _map)
		{
			map = _map;
		}

		@Override
		public int compare(CustomVertex v1, CustomVertex v2)
		{
			return (int) Double.compare(map.get(v1).getWeight(), map.get(v2).getWeight());
		}
	}
	
	public int size()
	{
		return prioQueue.size();
	}
	
	public VertexAttribute getAttribute(CustomVertex v)
	{
		return vertexWeightMap.get(v);
	}
	
	public boolean contains(CustomVertex v)
	{
		return prioQueue.contains(v);
	}
	
	public void aendereAttribut(CustomVertex vertex, VertexAttribute vertexAttr)
	{
		vertexWeightMap.remove(vertex);
		vertexWeightMap.put(vertex, vertexAttr);
		prioQueue.remove(vertex);
		prioQueue.add(vertex);
		
	}
}
