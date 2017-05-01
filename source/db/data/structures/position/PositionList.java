package db.data.structures.position;

import db.interfaces.hash.IList;
import db.interfaces.position.Position;
import db.models.position.Node;

import java.io.Serializable;
import java.util.Iterator;


/**
 * The db.data.structures.position.position List itself, this is just a doubly-linked list that make use of Positions
 *
 */
public class PositionList<T> implements IList<T>, Serializable
{

	private Node<T> header = null;
	private Node<T> trailer = null;
	private Integer size = 0;

	/**
	 * Default constructor
	 */
	public PositionList()
	{
		trailer = new Node<T>(null, null, null);
		header = new Node<T>(trailer, null, null);
		trailer.setPrev(header);
		size = 0;
	}

	/**
	 * Constructor that adds an entry immediately
	 *
	 * @param entry the entry to be added
	 */
	public PositionList(T entry)
	{
		trailer = new Node<T>(null, null, null);
		header = new Node<T>(trailer, null, null);
		trailer.setPrev(header);
		size = 0;
		addFirst(entry);
	}

	/**
	 * Add an element after the given node in the list
	 * @return Position of T the element added
	 */
	public Position<T> addAfter(Position<T> p, T item)
	{
		Node<T> elem = checkPosition(p);

		Node<T> newNode = new Node<T>(null, null, item);
		newNode.setPrev(elem);
		newNode.setNext(elem.getNext());
		elem.getNext().setPrev(newNode);
		elem.setNext(newNode);
		size++;
		return newNode;
	}

	/**
	 * Add an element before the given node in a list
	 * @return Position of T the element added
	 */
	public Position<T> addBefore(Position<T> p, T item)
	{
		Node<T> elem = checkPosition(p);

		Node<T> newNode = new Node<T>(null, null, item);
		newNode.setPrev(elem.getPrev());
		newNode.setNext(elem);
		elem.getPrev().setNext(newNode);
		elem.setPrev(newNode);
		size++;
		return newNode;
	}

	/**
	 * Add an element to the start of the list
	 * @return Position of T the element added
	 */
	public Position<T> addFirst(T item)
	{
		return addAfter(header, item);
	}

	/**
	 * Add an element to the end of the list
	 * @return Position of T the element added
	 */
	public Position<T> addLast(T item)
	{
		return addBefore(trailer, item);
	}

	/**
	 * Remove a specified node from the list.
	 * @return T The removed element
	 */
	public T remove(Position<T> p)
	{
		Node<T> elem = checkPosition(p);

		T element = elem.element();
		elem.getPrev().setNext(elem.getNext());
		elem.getNext().setPrev(elem.getPrev());
		elem.setNext(null);
		elem.setPrev(null);
		size--;
		return element;
	}

	/**
	 * Remove the first element from the list
	 *
	 * @return The first element
	 */
	public T removeFirst()
	{
		Node<T> first = header.getNext();
		first.getNext().setPrev(header);
		header.setNext(first.getNext());
		return first.element();
	}

	/**
	 * Return the front of the list
	 *
	 * @return the first db.data.structures.position.position of T in the list
	 */
	public Position<T> first()
	{
		if (header.getNext() == trailer)
		{
			throw new PositionListException("List is empty");
		}
		return header.getNext();
	}

	/**
	 * Returns the last element in the list
	 *
	 * @return the last db.data.structures.position.position of T element in the list
	 */
	public Position<T> last()
	{
		if (trailer.getPrev() == header)
		{
			throw new PositionListException("List is empty");
		}
		return trailer.getPrev();
	}

	/**
	 * Get the next element in the list
	 *
	 * @param p an existing db.data.structures.position.position in the list
	 * @return the db.data.structures.position.position of T that corresponds to the next element
	 */
	public Position<T> next(Position<T> p)
	{
		Node<T> node = checkPosition(p);
		if(node == null)
			return null;
		return node.getNext();
	}

	/**
	 * Get the previous element in the list
	 *
	 * @param p an exisiting db.data.structures.position.position in the list
	 * @return the Posiiton of T that corresponds to the prev elements
	 */
	public Position<T> prev(Position<T> p)
	{
		Node<T> node = checkPosition(p);
		return node.getPrev();
	}

	/**
	 * Returns the node that contains the element that is specified
	 * @param elem the element your looking for
	 * @return Position of T the found node
	 */
	public Position<T> search(T elem)
	{
		Node<T> currentNode = header.getNext();
		while (currentNode != trailer)
		{
			if (currentNode.element().equals(elem))
			{
				return currentNode;
			}
			currentNode = currentNode.getNext();
		}
		return null;
	}

	/**
	 * check if the list is empty
	 * @return boolean true if empty
	 */
	public boolean isEmpty()
	{
		return (header.getNext() == trailer);
	}

	/**
	 * the size of the list
	 * @return int the size
	 */
	public int size()
	{
		return size;
	}

	/**
	 * The tostring
	 * @return the to string
	 */
	@Override
	public String toString()
	{
		String result = "";
		Node<T> currentNode = header.getNext();

		while (currentNode != trailer)
		{
			result += currentNode.toString() + " ";
			currentNode = currentNode.getNext();
		}

		return result;
	}

	/**
	 * Convert a db.data.structures.position.position to a Node
	 *
	 * @param position a db.data.structures.position.position of T
	 * @return a Node of T that corresponds to the db.data.structures.position.position of T
	 */
	private Node<T> checkPosition(Position<T> position)
	{
		if(position == null)
		{
			return null;
		}
		if (!(position instanceof Node<?>))
		{
			throw new PositionListException("Invalid db.data.structures.position.position trying to be casted");
		}

		Node<T> newNode = (Node<T>) position;
		return newNode;
	}

	/**
	 * Return a new iterator over the elements in this list
	 */
	public Iterator<T> iterator()
	{
		return new PositionListIterator<T>(this);
	}
}
