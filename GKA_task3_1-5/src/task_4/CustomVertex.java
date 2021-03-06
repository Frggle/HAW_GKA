package task_4;

import org.jgrapht.graph.DefaultWeightedEdge;

public class CustomVertex
{
	/**
	 * Instanzvariablen
	 */
	private String _name;
	private int _attribute;
	private boolean _attributeGesetzt = false;
	private DefaultWeightedEdge _kürzesteKantezuT;

	/**
	 * Konstruktor
	 * 
	 * @param name, Name vom Knoten
	 * @param attribute, Heuristik-Wert
	 */
	public CustomVertex(String name, int attribute)
	{
		_name = name;
		_attribute = attribute;
		_attributeGesetzt = true;
		_kürzesteKantezuT = null;
	}

	/**
	 * Konstruktor
	 * 
	 * @param name
	 *            , Name vom Knoten _attributed wird mit 0 initialisiert
	 */
	public CustomVertex(String name)
	{
		_name = name;
		_attribute = 0;
		_kürzesteKantezuT = null;
	}

	/**
	 * generated by Eclipse
	 * 
	 * @return Integer
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + _attribute;
		result = prime * result + ((_name == null) ? 0 : _name.hashCode());
		return result;
	}

	/**
	 * generated by Eclipse
	 * 
	 * @return Boolean
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof CustomVertex))
		{
			return false;
		}
		CustomVertex other = (CustomVertex) obj;
		if (_attribute != other._attribute)
		{
			return false;
		}
		if (_name == null)
		{
			if (other._name != null)
			{
				return false;
			}
		} else if (!_name.equals(other._name))
		{
			return false;
		}
		return true;
	}

	/**
	 * generated by Eclipse
	 */
	// @Override
	// public String toString()
	// {
	// return "CustomVertex [_name=" + _name + ", _attribute=" + _attribute +
	// ", _attributeGesetzt="
	// + _attributeGesetzt + "]";
	// }
	// Der GraphViz Exporter greift auf diese Informationen zu (fÃ¼r die
	// Knotenlabels/Bezeichnungen)
	@Override
	public String toString()
	{
		if (istAttributeGesetzt())
		{
			return "" + _name + ", " + _attribute;
		} else
		{
			return _name;
		}

	}

	/**
	 * Getter Knotenname
	 * 
	 * @return String
	 */
	public String getVertexName()
	{
		return _name;
	}

	public int getVertexAttribute()
	{
		return _attribute;
	}

	public boolean istAttributeGesetzt()
	{
		return _attributeGesetzt;
	}

	public boolean gleicherName(String s)
	{
		return this._name.equals(s);
	}

	public DefaultWeightedEdge getKürzesteKantezuT()
	{
		return _kürzesteKantezuT;
	}

	public void setKuerzesteKantezuT(DefaultWeightedEdge e)
	{
		_kürzesteKantezuT = e;
	}	
	
	public DefaultWeightedEdge getKuerzesteKantezuT()
	{
		return _kürzesteKantezuT;
	}
}
