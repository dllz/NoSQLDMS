package db.data.structures.queue;//Overall PriorityQueue class: 5 marks ***********************************************

import db.data.structures.position.PositionException;
import db.data.structures.position.PositionList;
import db.interfaces.position.Position;
import db.models.hash.Entry;

public class PriorityQueue<K extends Comparable <? super K>,V>{
	private PositionList<Entry<K,V>> list = null;
	
	/*
	 * Default constructor
	 */
	public PriorityQueue(){
		this.list = new PositionList<Entry<K,V>>();
	}
	
	/*
	 * Insert method that store the entry in an unordered manner (you can add it at the end)	 
	 */
	public void insert(Entry<K,V> entry){
		list.addLast(entry);		
	}
	
	/*
	 * Remove the entry with the highest priority (typically 0 as an integer)
	 * by performing a Heap sort and returning the root entry
	 * 5 marks ***********************************************
	 */
	public Entry<K,V> removeMin(){
		Entry current = null;
		for (int i = 0; i < list.size(); i++) {
			Entry temp = list.iterator().next();
			if(current == null || (int) temp.getKey() < (int) current.getKey())
			{
				current = temp;
			}
		}
		if(current != null)
			list.remove(list.search(current));
		return current;
	}

	/*
	 * Returns the size of the Priority Queue
	 */
	public Integer size(){
		return this.list.size();
	}
	
	/*
	 * Returns whether the Priority Queue is empty or not.
	 */
	public boolean isEmpty(){
		return this.list.isEmpty();
	}
	
	/*
	 * The overridden toString method
	 */
	public String toString(){
		return list.toString();
	}
	/**
	 * Convert a Position<T> into a Entry<K,V>
	 * @param p a Position<T> to convert
	 * @return the corresponding Entry<K,V>
	 */
	@SuppressWarnings("unchecked")
	private Entry<K,V> checkPosition(Position<Entry<K,V>> p) {
		if (!(p instanceof Entry<?,?>)) {
			throw new PositionException("Invalid Position");
		}
		
		return (Entry<K,V>)p;
	}
}