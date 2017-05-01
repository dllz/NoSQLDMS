package db.models.tree;

import db.interfaces.position.Position;

/**
 * A node in the Position List (this is just a doubly-linked node)
 *
 * @param <T> the object type for this node
 */
public class Node<T> implements Position<T>
{
	T element;
	Node<T> next;
	Node<T> prev;

	public Node(Node<T> next, Node<T> prev, T element)
	{
		this.next = next;
		this.prev = prev;
		this.element = element;
	}

	public Node<T> getNext()
	{
		return next;
	}

	public void setNext(Node<T> next)
	{
		this.next = next;
	}

	public String toString()
	{
		if (element == null)
		{
			return "<>";
		}
		return "<" + element.toString() + ">";
	}

	public T element()
	{
		return element;
	}
}
