package db.models.tree;

/**
 * Created by danie on 2017/05/01.
 */
public class KeyValue<T>
{
	private NodeKey key;
	private T element;

	public NodeKey getKey()
	{
		return key;
	}

	public T getElement()
	{
		return element;
	}

	public KeyValue(NodeKey key, T element)
	{

		this.key = key;
		this.element = element;
	}
}
