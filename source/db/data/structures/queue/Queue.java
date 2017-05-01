package db.data.structures.queue;

import db.data.structures.position.PositionList;
import db.interfaces.position.Position;

public class Queue
{
	private PositionList<String> list = null;

	/**
	 * Default constructor
	 */
	public Queue()
	{
		this.list = new PositionList<>();
	}

	/**
	 * Add an element to the back of the queue
	 * @param entry the element to be added
	 * @return the position of the element
	 */
	public synchronized Position<String> qeueu(String entry)
	{
		return list.addLast(entry);
	}

	/**
	 * Removes the oldest entry
	 * @return String the oldest entry
	 */
	public synchronized String dequeue()
	{
		try
		{
			String temp = list.removeFirst();
			return temp;
		} catch (NullPointerException e)
		{
			return null;
		}

	}


	/**
	 * The to string
	 * @return String
	 */
	public synchronized String toString()
	{
		return list.toString();
	}
}