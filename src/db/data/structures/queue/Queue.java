package db.data.structures.queue;//Overall PriorityQueue class: 5 marks ***********************************************

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
	 * Adds an entry to the end of the queue
	 */
	public synchronized Position<String> qeueu(String entry)
	{
		return list.addLast(entry);
	}

	/**
	 * Removes the oldest entry from the queue
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
	 * Returns a string
	 */
	public synchronized String toString()
	{
		return list.toString();
	}
}